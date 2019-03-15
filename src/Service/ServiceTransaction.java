package Service;

import Domain.DrugProduct;
import Domain.Transaction;
import Repository.RepoProduct;
import Repository.RepoTransaction;

import java.util.List;

public class ServiceTransaction {
    private RepoTransaction transactionRepository;
    private RepoProduct productRepository;

    public ServiceTransaction(RepoTransaction transactionRepository, RepoProduct productRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
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
        if (productSold == null) {
            throw new RuntimeException("There is no product with the given id!");
        }
        double basePrice = productSold.getPrice();
        double discount = 0;
        double finalPrice = basePrice;
        if (idCard != 0) {
            if (productSold.isNeedPresciption()) {
                discount = 0.15; // 15% discount
            } else {
                discount = 0.1; // 10% discount
            }
            finalPrice = finalPrice - (finalPrice*discount);
        }

        Transaction transaction = new Transaction(id, idMed, idCard, quantity, date, basePrice, discount, finalPrice);
        transactionRepository.add(transaction);
        return transaction;
    }

    public void remove(int id) {
        transactionRepository.remove(id);
    }

    public List<Transaction> showAllTransactions() {
        return transactionRepository.showTransactions();
    }
}