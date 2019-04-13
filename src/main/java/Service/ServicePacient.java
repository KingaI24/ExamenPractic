package Service;

import Domain.Pacient;
import Repository.IRepository;
import main.java.Service.UndoRedoOp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ServicePacient {

    private IRepository<Pacient> repository;
    private Stack<UndoRedoOp<Pacient>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOp<Pacient>> redoableeOperations = new Stack<>();

    public ServicePacient(IRepository<Pacient> repository) {
        this.repository = repository;
    }

    public void addOrUpdate(int id, String CNP, String firstName, String lastName, String date, String regDate) {
        List <Pacient> CNPcheck= repository.show();

        for (Pacient c: CNPcheck) {
            if (c.getCNP().equals(CNP)) {
                throw new ServiceException("error: existent CNP");
            }
        }

        IsPrim is = new IsPrim();
        if (is.isPrime(id))
            throw new ServiceException("error: ID should be prime");

        Pacient pp = new Pacient(id, CNP, firstName, lastName, date, regDate);
        repository.add(pp);
        undoableOperations.add(new AddOperation<>(repository, pp));
        redoableeOperations.clear();
    }

    public void remove (int id) {
        repository.remove(id);
    }

    public List<Pacient> showPacients() {
        return repository.show();
    }

    public List<Pacient> fullTextSearch(String text) {
        List<Pacient> list = new ArrayList<>();
        for (Pacient pacient : repository.show()) {
            if (pacient.toString().toLowerCase().contains(text.toLowerCase()))
                list.add(pacient);
        }
        return list;
    }

    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOp<Pacient> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableeOperations.add(lastOperation);
        }
    }

    public void redo() {
        if (!redoableeOperations.empty()) {
            UndoRedoOp<Pacient> lastOperation = redoableeOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }
}
