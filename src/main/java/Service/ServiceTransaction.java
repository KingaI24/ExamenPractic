package Service;

import Domain.*;
import Repository.IRepository;

import java.util.*;


public class ServiceTransaction {
    private IRepository<Transaction> transactionRepository;
    private IRepository<DrugProduct> productRepository;
    private IRepository<Pacient> pacientRepository;
    private Stack<main.java.Service.UndoRedoOp<Transaction>> undoableOperations = new Stack<>();
    private Stack<main.java.Service.UndoRedoOp<Transaction>> redoableeOperations = new Stack<>();

    public ServiceTransaction(IRepository<Transaction> transactionRepository, IRepository<DrugProduct> productRepository, IRepository<Pacient> pacientRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
        this.pacientRepository = pacientRepository;
    }

    public Transaction addOrUpdate(int id, int idMed, int idCard, int quantity, String date) {
        Transaction existing = transactionRepository.findById(id);
        if (existing != null) {
            if (idMed == 0) {
                idMed = existing.getIdMed();
            }
            if (idCard == 0) {
                idCard = existing.getIdCard();
            }
            if (quantity == 0) {
                quantity = existing.getQuantity();
            }
            if (date.isEmpty()) {
                date = existing.getDate();
            }
        }

        DrugProduct productSold = productRepository.findById(idMed);
        Pacient buyer = pacientRepository.findById(idCard);
        if (productSold == null) {
            throw new ServiceException("There is no product with the given id!");
        }

        double basePrice = productSold.getPrice();
        double discount = 0;
        double finalPrice = basePrice;
        if (buyer != null) {
            if (productSold.isNeedPresciption()) {
                discount = 0.15; // 15% discount
            } else {
                discount = 0.1; // 10% discount
            }
        }
        finalPrice = finalPrice - (finalPrice*discount);

        Transaction transaction = new Transaction(id, idMed, idCard, quantity, date, basePrice, discount, finalPrice);

        transactionRepository.add(transaction);
        undoableOperations.add(new AddOperation<>(transactionRepository, transaction));
        redoableeOperations.clear();
        return transaction;
    }

    public void remove(int id) {
        transactionRepository.remove(id);
    }

    public List<Transaction> showAllTransactions() {
        return transactionRepository.show();
    }

    public List<Transaction> restrictTransactions(String date1, String date2) {
        List<Transaction> list = new ArrayList<>();
        CompareDate comparator = new CompareDate();
        for (Transaction t : showAllTransactions()) {
            if (!(comparator.compare(t.getDate(),date1) < 0) && !(comparator.compare(date2,t.getDate()) < 0)) {
                list.add(t);
            }
        }
        return list;
    }

    public List<ProductSoldVM> sortDescSell() {
        Map<String, Integer> assess = new HashMap<>();

        for (Transaction t : transactionRepository.show()) {
            if (!assess.containsKey(productRepository.findById(t.getIdCard()).getName())) {
                assess.put(productRepository.findById(t.getIdCard()).getName(), t.getQuantity());
            } else {
                assess.put(productRepository.findById(t.getIdCard()).getName(), assess.get(productRepository.findById(t.getIdCard()).getName()) + t.getQuantity());
            }
        }
        List<ProductSoldVM> listSold = new ArrayList<>();
        for (String item : assess.keySet()) {
            ProductSoldVM p = new ProductSoldVM(item,assess.get(item));
            listSold.add(p);
        }

        listSold.sort((r1, r2) -> r1.getAmount() > r2.getAmount() ? -1 : 1);
        listSold.sort((r1, r2) -> {
            if (r1.getAmount() > r2.getAmount())
                return -1;
            else if (r1.getAmount() == r2.getAmount())
                return 0;
            else
                return 1;
        });
        return listSold;
    }

    public List<PacientDiscountVM> sortDescDiscountsReceived() {
        Map<Integer,Double> buyers = new HashMap<>();

        for (Transaction t : transactionRepository.show()) {
            if (!buyers.containsKey(pacientRepository.findById(t.getIdCard()).getId())){
                buyers.put(pacientRepository.findById(t.getIdCard()).getId(), t.getDiscount());
            } else {
                buyers.put(pacientRepository.findById(t.getIdCard()).getId(), buyers.get(pacientRepository.findById(t.getIdCard()).getId()) + t.getDiscount());
            }
        }

        List<PacientDiscountVM> pacDisc = new ArrayList<>();
        for (Integer id : buyers.keySet()) {
            PacientDiscountVM p = new PacientDiscountVM(id,buyers.get(id));
            pacDisc.add(p);
        }

        pacDisc.sort((r1, r2) -> r1.getDiscount() > r2.getDiscount() ? -1 : 1);
        pacDisc.sort((r1, r2) -> {
            if (r1.getDiscount() > r2.getDiscount())
                return -1;
            else if (r1.getDiscount() == r2.getDiscount())
                return 0;
            else
                return 1;
        });
        return pacDisc;
    }

    public List<Transaction> aboveAveragePrice() {
        List<Transaction> list = new ArrayList<>();
        int sum = 0;
        for (Transaction t : showAllTransactions()) {
            sum += t.getFinalPrice();
        }
        double average = sum / transactionRepository.show().size();
        for (Transaction t : showAllTransactions()) {
            if (t.getFinalPrice()>average)
                list.add(t);
        }
        return list;
    }

    public void undo() {
        if (!undoableOperations.empty()) {
            main.java.Service.UndoRedoOp<Transaction> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableeOperations.add(lastOperation);

        }
    }

    public void redo() {
        if (!redoableeOperations.empty()) {
            main.java.Service.UndoRedoOp<Transaction> lastOperation = redoableeOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }
}