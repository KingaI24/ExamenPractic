package Domain;

import Domain.DomainException;
import Domain.Transaction;
import Domain.TransactionValidator;
import Repository.GeneralRepository;
import Repository.IRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionValidatorTest {
    private Transaction tranTest1 = new Transaction(1, 1, 1, -1, "22.12.2018", 12, 0, 0);
    private Transaction tranTest2 = new Transaction(2, 2, 2, 1, "2018", 12, 0, 0);
    private TransactionValidator validator = new TransactionValidator();

    @Test
    void validateShouldThrowExceptionsForWrongTransaction() throws DomainException {
        try {
            validator.validate(tranTest1);
            fail("Transaction validator threw no exception for faulty transaction!");
        } catch (DomainException e) {
            assertEquals(e.getMessage(), "Invalid number of items");
        }
    }

    @Test
    void validateShouldThrowExceptionsForWrongDateFormat() throws DomainException {
        try {
            validator.validate(tranTest2);
            fail("Transaction validator threw no exception for faulty transaction!");
        } catch (DomainException e) {
            assertEquals(e.getMessage(), "The date is not in a correct format!");
        }
    }
}