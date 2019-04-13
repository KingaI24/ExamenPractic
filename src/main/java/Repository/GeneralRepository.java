package Repository;

import Domain.Entity;
import Domain.IValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneralRepository <T extends Entity> implements IRepository<T> {

    private IValidator<T> validator;
    private HashMap<Integer, T> storage = new HashMap<>();

    public GeneralRepository(IValidator<T> validator) {
        this.validator = validator;
    }

    public T findById(int id) {
        return storage.get(id);
    }

    public void add(T Entity) {
        validator.validate(Entity);
        storage.put(Entity.getId(),Entity);
    }

    public void remove(int id) {
        if (!storage.containsKey(id)) {
            throw new RepositoryException("There is no entity with the given id to remove.");
        }
        storage.remove(id);
    }

    public List<T> show() {
        return new ArrayList<>(storage.values());
    }
}
