package Repository;

import Domain.Entity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonRepository<T extends Entity> implements IRepository<T> {

    /**
     * stocare in Json
     */

    private String filename;
    private Map<Integer, T> storage = new HashMap<>();
    private Type type;

    public JsonRepository(String filename, Type type) {
        this.filename = filename;
        this.type = type;
    }

    private void loadFromFile() {
        storage.clear();
        Gson gson = new Gson();
        try (FileReader in = new FileReader(filename)) {
            try (BufferedReader bufferedReader = new BufferedReader(in)) {
//                String contents = gson.fromJson(bufferedReader.readLine(), Collection<type>);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    T entity = gson.fromJson(line, type);
                    storage.put(entity.getId(), entity);
                }
            }
        } catch (Exception ex) {

        }
}

    private void writeToFile() {
        Gson gson = new Gson();
        try (FileWriter out = new FileWriter(filename)) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(out)) {

                for (T entity : storage.values()) {
                    bufferedWriter.write(gson.toJson(entity));
                    bufferedWriter.newLine();
                }
            }
        } catch (Exception ex) {

        }
    }

    /**
     *
     * @param id
     * @return localizare entry dupa id
     */
    @Override
    public T findById(int id) {
        loadFromFile();
        return storage.get(id);
    }

    /**
     *
     * @param entity adaugare in fct de caracteristicile Map
     */
    @Override
    public void add(T entity) {
        loadFromFile();
        storage.put(Integer.valueOf(entity.getId()), entity);
        writeToFile();
    }

    /**
     *
     * @param id eliminare dupa id
     */
    @Override
    public void remove(int id) {
        loadFromFile();
        if (!storage.containsKey(id)) {
            throw new Repository.RepositoryException("There is no entity with the given id to remove.");
        }

        storage.remove(id);
        writeToFile();
    }

    /**
     *
     * @return lista cu toate entryurile
     */
    @Override
    public List<T> show() {
        loadFromFile();
        return new ArrayList<>(storage.values());
    }

}