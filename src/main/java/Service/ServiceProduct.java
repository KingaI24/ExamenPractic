package Service;

import Domain.DrugProduct;
import Repository.IRepository;
import main.java.Service.UndoRedoOp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ServiceProduct {

    private IRepository<DrugProduct> repository;
    private Stack<UndoRedoOp<DrugProduct>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOp<DrugProduct>> redoableeOperations = new Stack<>();

    public ServiceProduct(IRepository<DrugProduct> repository) {
        this.repository = repository;
    }

    public void addOrUpdate (int id, String name, String manufacturer, double price, boolean needPresciption) {
        DrugProduct value = repository.findById(id);
        if (value != null) {
            if (name.isEmpty())
                name = value.getName();
            if (manufacturer.isEmpty())
                manufacturer = value.getManufacturer();
            if (price == 0.00)
                price = value.getPrice();
        }

        DrugProduct dp = new DrugProduct(id, name,  manufacturer, price, needPresciption);
        repository.add(dp);
        undoableOperations.add(new AddOperation<>(repository, dp));
        redoableeOperations.clear();
    }

    public void remove (int id) {
        repository.remove(id);
    }

    public List<DrugProduct> showProducts() {
        return repository.show();
    }

    public List<DrugProduct> fullTextSearch(String text) {
        List<DrugProduct> list = new ArrayList<>();
        for (DrugProduct drugProduct : repository.show()) {
            if (drugProduct.toString().toLowerCase().contains(text.toLowerCase()))
                list.add(drugProduct);
        }
        return list;
    }

    public void priceIncrease(Integer percentage) {
        for (DrugProduct dp : repository.show()) {
            dp.setPrice(dp.getPrice()*percentage/100+dp.getPrice());
            repository.add(dp);
        }
    }

    public List<DrugProduct> priceAboveAverage() {
        List<DrugProduct> list = new ArrayList<>();
        double sum = 0;
        for (DrugProduct drugProduct : repository.show()) {
            sum += drugProduct.getPrice();
        }
        double average = sum / repository.show().size();
        for (DrugProduct drugProduct : repository.show()) {
            if (drugProduct.getPrice() >= average)
            list.add(drugProduct);
        }
        return list;
    }

    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOp<DrugProduct> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableeOperations.add(lastOperation);
        }
    }

    public void redo() {
        if (!redoableeOperations.empty()) {
            UndoRedoOp<DrugProduct> lastOperation = redoableeOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }
}
