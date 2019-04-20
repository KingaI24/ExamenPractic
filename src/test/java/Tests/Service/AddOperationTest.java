package Service;

import Domain.Pacient;
import Domain.PacientValidator;
import Repository.GeneralRepository;
import Repository.IRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddOperationTest {

    private IRepository<Pacient> repository = new GeneralRepository<>(new PacientValidator());
    ServicePacient service = new ServicePacient(repository);

    @Test
    void doUndo() {
        service.addOrUpdate(1,"1234567890123","test", "test", "12.12.2000", "12.12.2018");
        assertEquals(1,repository.show().size());
        service.undo();
        assertEquals(0,repository.show().size());
    }

    @Test
    void doRedo() {
        service.addOrUpdate(1,"1234567890123","test", "test", "12.12.2000", "12.12.2018");
        assertEquals(1,repository.show().size());
        service.undo();
        assertEquals(0,repository.show().size());
        service.redo();
        assertEquals(1,repository.show().size());
    }
}