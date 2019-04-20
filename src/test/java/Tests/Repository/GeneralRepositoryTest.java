package Repository;

import Domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeneralRepositoryTest {

    private DrugProduct dp = new DrugProduct(1,"Faringosept","Terapia", 12, false);
    private DrugProduct dp1 = new DrugProduct(1,"Faringosept1","Terapia1", 13, false);
    private DrugProductValidator productValidator = new DrugProductValidator();
    private Pacient p = new Pacient(1,"1234567890123","test", "test", "12.12.2000", "12.12.2018");
    private PacientValidator pacientValidator = new PacientValidator();
    private Transaction t = new Transaction(1,1,1,1, "12.12.2018",12.00, 2.00, 10.00);
    private TransactionValidator transactionValidator = new TransactionValidator();
    private IRepository<DrugProduct> productIRepository = new GeneralRepository<>(productValidator);
    private IRepository<Pacient> pacientIRepository = new GeneralRepository<>(pacientValidator);
    private IRepository<Transaction> transactionIRepository = new GeneralRepository<>(transactionValidator);

    @Test
    void getByIdShouldReturnCorrectEntity() {
        productIRepository.add(dp);
        assertEquals(dp, productIRepository.findById(1));
        assertEquals(1, productIRepository.show().size());
        pacientIRepository.add(p);
        assertEquals(p,pacientIRepository.findById(1));
        assertEquals(1,pacientIRepository.show().size());
        transactionIRepository.add(t);
        assertEquals(t,transactionIRepository.findById(1));
        assertEquals(1,transactionIRepository.show().size());
    }

    @Test
    void addCorrectlyNewEntity() {
        productIRepository.add(dp);
        List<DrugProduct> products = productIRepository.show();
        assertEquals(1,products.size());
        pacientIRepository.add(p);
        List<Pacient> pacients = pacientIRepository.show();
        assertEquals(1,pacients.size());
        transactionIRepository.add(t);
        List<Transaction> transactions = transactionIRepository.show();
        assertEquals(1,transactions.size());
    }

    @Test
    void addShouldUpdateEntity() {
        productIRepository.add(dp);
        productIRepository.add(dp1);
        List<DrugProduct> products = productIRepository.show();
        assertEquals(1,products.size());
        assertEquals(1,products.get(0).getId());
        assertEquals("Faringosept1",products.get(0).getName());
        assertEquals("Terapia1",products.get(0).getManufacturer());
        assertEquals(13.00,products.get(0).getPrice());
        assertFalse(products.get(0).isNeedPresciption());
    }

    @Test
    void removeShouldDeleteEntity() {
        productIRepository.add(dp1);
        productIRepository.remove(1);
        List<DrugProduct> products = productIRepository.show();
        assertEquals(0, products.size());
    }

    @Test
    void removeShouldSendErrorIfNotFound() {
        try {
            productIRepository.remove(1);
            List<DrugProduct> products = productIRepository.show();
            assertEquals(0, products.size());
            fail("Date comparator threw no exception for non existent entry!");
        } catch (RepositoryException e) {
            assertEquals(e.getMessage(), "There is no entity with the given id to remove.");
        }
    }

    @Test
    void showShouldShowAllEntities() {
        productIRepository.add(dp1);
        DrugProduct dp2 = new DrugProduct(2,"Echinaceae","Parafarm", 6.50, false);
        productIRepository.add(dp2);
        List<DrugProduct> products = productIRepository.show();

        assertEquals(2, products.size());
        assertEquals(1, products.get(0).getId());
        assertEquals(2, products.get(1).getId());
    }
}