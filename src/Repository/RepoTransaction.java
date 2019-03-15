package Repository;

import Domain.Transaction;
import Domain.TransactionValidator;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class RepoTransaction {
    private TransactionValidator validator;
    private Map<Integer, Transaction> storage = new HashMap<>();

    public RepoTransaction(TransactionValidator validator) {
        this.validator = validator;
    }

    public void add(Transaction transaction) {
        validator.validate(transaction);
        storage.put(transaction.getId(), transaction);
    }

    public void remove(int id) {
        if (!storage.containsKey(id)) {
            throw new RuntimeException("There is no transaction with the given id to remove.");
        }
        storage.remove(id);
    }

    public Transaction findById(int id) {
        return storage.get(id);
    }

    public List<Transaction> showTransactions() {
        return new ArrayList<>(storage.values());
    }
}
