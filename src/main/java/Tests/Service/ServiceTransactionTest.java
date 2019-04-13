package Tests.Service;

import Domain.*;
import Repository.GeneralRepository;
import Repository.IRepository;
import Service.ServicePacient;
import Service.ServiceProduct;
import Service.ServiceTransaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTransactionTest {

    IRepository<DrugProduct> productRepository = new GeneralRepository<>(new DrugProductValidator());
    ServiceProduct serviceProduct = new ServiceProduct(productRepository);
    IRepository<Pacient> pacientRepository = new GeneralRepository<>(new PacientValidator());
    IRepository<Transaction> transactionRepository = new GeneralRepository<>(new TransactionValidator());
    ServicePacient servicePacient = new ServicePacient(pacientRepository);
    ServiceTransaction serviceTransaction = new ServiceTransaction(transactionRepository, productRepository, pacientRepository);

    @Test
    void addOrUpdate() {
        serviceProduct.addOrUpdate(1,"Faringosept", "Terapia", 12, false);
        serviceProduct.addOrUpdate(2,"Faringosept", "Terapia", 20, false);

        serviceTransaction.addOrUpdate(1,1,1,1, "12.12.2018 12:12");
        assertEquals(1,serviceTransaction.showAllTransactions().size());
        assertEquals(1,transactionRepository.findById(1).getId());
        assertEquals(1,transactionRepository.findById(1).getIdMed());
        assertEquals(1,transactionRepository.findById(1).getIdCard());
        assertEquals(1,transactionRepository.findById(1).getQuantity());
        assertEquals("12.12.2018 12:12",transactionRepository.findById(1).getDate());
        assertEquals(12,transactionRepository.findById(1).getBasePrice());
        assertEquals(2,transactionRepository.findById(1).getDiscount());
        assertEquals(10,transactionRepository.findById(1).getFinalPrice());
        serviceTransaction.addOrUpdate(1,2,3,1, "12.12.2019 12:12");
        assertEquals(1,serviceTransaction.showAllTransactions().size());
        assertEquals(1,transactionRepository.findById(1).getId());
        assertEquals(2,transactionRepository.findById(1).getIdMed());
        assertEquals(3,transactionRepository.findById(1).getIdCard());
        assertEquals(1,transactionRepository.findById(1).getQuantity());
        assertEquals("12.12.2019 12:12",transactionRepository.findById(1).getDate());
        assertEquals(20,transactionRepository.findById(1).getBasePrice());
        assertEquals(4,transactionRepository.findById(1).getDiscount());
        assertEquals(16,transactionRepository.findById(1).getFinalPrice());

        serviceTransaction.addOrUpdate(2,2,2,1,"12.01.2019 13:15");
        assertEquals(2,serviceTransaction.showAllTransactions().size());
        serviceTransaction.addOrUpdate(1,0,0,0,"12.01.2019 13:15");
        assertEquals(2,serviceTransaction.showAllTransactions().size());
        assertEquals(1,transactionRepository.findById(1).getId());
        assertEquals(2,transactionRepository.findById(1).getIdMed());
        assertEquals(3,transactionRepository.findById(1).getIdCard());
        assertEquals(1,transactionRepository.findById(1).getQuantity());
        assertEquals("12.01.2019 13:15",transactionRepository.findById(1).getDate());
        assertEquals(20,transactionRepository.findById(1).getBasePrice());
        assertEquals(4,transactionRepository.findById(1).getDiscount());
        assertEquals(16,transactionRepository.findById(1).getFinalPrice());

    }

    @Test
    void remove() {
        serviceTransaction.addOrUpdate(1,1,1,1, "12.12.2018 12:12");
        serviceTransaction.addOrUpdate(2,2,2,1,"12.01.2019 13:15");
        serviceTransaction.remove(1);

        assertEquals(1,serviceTransaction.showAllTransactions().size());
    }

    @Test
    void showAllTransactions() {
        serviceProduct.addOrUpdate(1,"Faringosept", "Terapia", 12, false);
        serviceProduct.addOrUpdate(2,"Faringosept", "Terapia", 20, false);

        serviceTransaction.addOrUpdate(1,1,1,1, "12.12.2018 12:12");
        serviceTransaction.addOrUpdate(2,2,2,1,"12.01.2019 13:15");
        Transaction t1 = new Transaction(1,1,1,1, "12.12.2018 12:12", 12, 2, 10);
        Transaction t2 = new Transaction(2,2,2,1,"12.01.2019 13:15",20,4,16);

        ArrayList<Transaction> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);

        assertEquals(list,serviceTransaction.showAllTransactions());
        assertEquals(2,serviceTransaction.showAllTransactions().size());

    }

    @Test
    void restrictTransactions() {
        serviceProduct.addOrUpdate(1,"Faringosept", "Terapia", 12, false);

        serviceTransaction.addOrUpdate(1,1,1,1, "12.12.2018 12:12");
        serviceTransaction.addOrUpdate(2,1,2,1,"12.01.2019 13:15");
        serviceTransaction.addOrUpdate(3,1,3,1,"12.01.2014 13:15");

        Transaction t1 = new Transaction(1,1,1,1, "12.12.2018 12:12",12,2,10);;
        Transaction t2 = new Transaction(2,1,2,1,"12.01.2019 13:15",12,2,10);

        List<Transaction> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);

        assertEquals(list,serviceTransaction.restrictTransactions("12.12.2017 12:12", "12.12.2019 12:12"));

    }

    @Test
    void sortDescSell() {
    }

    @Test
    void sortDescDiscountsReceived() {
    }
}