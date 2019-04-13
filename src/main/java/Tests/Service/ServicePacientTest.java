package Tests.Service;

import Domain.*;
import Repository.GeneralRepository;
import Repository.IRepository;
import Service.ServicePacient;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServicePacientTest {

    IRepository<Pacient> pacientRepository = new GeneralRepository<>(new PacientValidator());
    IRepository<Transaction> transactionRepository = new GeneralRepository<>(new TransactionValidator());
    ServicePacient servicePacient = new ServicePacient(pacientRepository);

    @Test
    void addOrUpdate() {
        servicePacient.addOrUpdate(1,"2900912123123", "Marin", "Petra", "12.09.1990","12.12.2018");
        assertEquals(1,pacientRepository.show().size());
        assertEquals(1,pacientRepository.findById(1).getId());
        assertEquals("2900912123123",pacientRepository.findById(1).getCNP());
        assertEquals("Marin",pacientRepository.findById(1).getFirstName());
        assertEquals("Petra",pacientRepository.findById(1).getLastName());
        assertEquals("12.09.1990",pacientRepository.findById(1).getBirthDate());
        assertEquals("12.12.2018",pacientRepository.findById(1).getRegDate());
        servicePacient.addOrUpdate(1,"1111111111111", "Marina", "Dana", "13.09.1990","12.12.2018");
        assertEquals(1,pacientRepository.show().size());
        assertEquals(1,pacientRepository.findById(1).getId());
        assertEquals("1111111111111",pacientRepository.findById(1).getCNP());
        assertEquals("Marina",pacientRepository.findById(1).getFirstName());
        assertEquals("Dana",pacientRepository.findById(1).getLastName());
        assertEquals("13.09.1990",pacientRepository.findById(1).getBirthDate());
        assertEquals("12.12.2018",pacientRepository.findById(1).getRegDate());
        servicePacient.addOrUpdate(1,"1111111111113","","", "","");
        assertEquals(1,pacientRepository.show().size());
        assertEquals(1,pacientRepository.findById(1).getId());
        assertEquals("1111111111113",pacientRepository.findById(1).getCNP());
        assertEquals("Marina",pacientRepository.findById(1).getFirstName());
        assertEquals("Dana",pacientRepository.findById(1).getLastName());
        assertEquals("13.09.1990",pacientRepository.findById(1).getBirthDate());
        assertEquals("12.12.2018",pacientRepository.findById(1).getRegDate());
    }

    @Test
    void remove() {
        servicePacient.addOrUpdate(1,"2900912123123", "Marin", "Petra", "12.09.1990","12.12.2018");
        servicePacient.addOrUpdate(2,"2900912123124", "Marin", "Ana", "12.09.1990","12.12.2018");
        servicePacient.remove(1);

        assertEquals(1,servicePacient.showPacients().size());
    }

    @Test
    void showPacients() {
        Pacient p1 = new Pacient(1,"2900912123123", "Marin", "Petra", "12.09.1990","12.12.2018");
        Pacient p2 = new Pacient(2,"2900912123124", "Marin", "Ana", "12.09.1990","12.12.2018");
        servicePacient.addOrUpdate(1,"2900912123123", "Marin", "Petra", "12.09.1990","12.12.2018");
        servicePacient.addOrUpdate(2,"2900912123124", "Marin", "Ana", "12.09.1990","12.12.2018");

        ArrayList<Pacient> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

        assertEquals(list,servicePacient.showPacients());
        assertEquals(2,servicePacient.showPacients().size());
    }

    @Test
    void fullTextSearch() {
        servicePacient.addOrUpdate(1,"2900912123123", "Marin", "Petra", "12.09.1990","12.12.2018");
        servicePacient.addOrUpdate(2,"2900912123124", "Marin", "Ana", "12.09.1990","12.12.2018");
        Pacient p1 = new Pacient(1,"2900912123123", "Marin", "Petra", "12.09.1990","12.12.2018");
        Pacient p2 = new Pacient(2,"2900912123124", "Marin", "Ana", "12.09.1990","12.12.2018");

        List<Pacient> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        assertEquals(list.get(0),servicePacient.fullTextSearch("Petra").get(0));
        assertEquals(list,servicePacient.fullTextSearch("Marin"));
    }
}