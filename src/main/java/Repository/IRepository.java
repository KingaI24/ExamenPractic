package Repository;

import Domain.Entity;

import java.util.List;

public interface IRepository <T extends Entity> {
    void add(T Entity);

    void remove(int id);

    T findById(int id);

    List<T> show();
}
