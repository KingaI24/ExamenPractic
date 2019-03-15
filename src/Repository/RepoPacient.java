package Repository;

import Domain.PacientValidator;
import Domain.Pacient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepoPacient {
    private PacientValidator validator;
    private Map<Integer, Pacient> storage = new HashMap<>();

    public RepoPacient(PacientValidator validator) {
        this.validator = validator;
    }

    public void add(Pacient pacient) {
        validator.validate(pacient);
        storage.put(pacient.getId(), pacient);
    }

    public void remove(int id) {
        if (!storage.containsKey(id)) {
            throw new RuntimeException("There is no transaction with the given id to remove.");
        }
        storage.remove(id);
    }

    public Pacient findById(int id) {
        return storage.get(id);
    }

    public List<Pacient> showPacients() {
        return new ArrayList<>(storage.values());
    }
}
