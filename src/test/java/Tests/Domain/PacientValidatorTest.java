package Domain;

import Domain.DomainException;
import Domain.Pacient;
import Domain.PacientValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacientValidatorTest {
    private Pacient p1 = new Pacient(1,"1","Dorin","Dorin", "12.12.2008", "12.12.2019");
    private Pacient p2 = new Pacient(1,"1111111111111","Dorin","Dorin", "2008", "12.12.2019");
    private Pacient p3 = new Pacient(1,"1111111111111","Dorin","Dorin", "12.12.2008", "2019");
    private PacientValidator validator = new PacientValidator();

    @Test
    void validateShouldThrowExceptionsForWrongCNP() throws DomainException {
        try {
            validator.validate(p1);
            fail("Transaction validator threw no exception for faulty input!");
        } catch (DomainException e) {
            assertEquals(e.getMessage(), "The CNP format is incorrect");
        }
    }

    @Test
    void validateShouldThrowExceptionsForBirthDate() throws DomainException {
        try {
            validator.validate(p2);
            fail("Transaction validator threw no exception for faulty input!");
        } catch (DomainException e) {
            assertEquals(e.getMessage(), "The date of birth is not in a correct format!");
        }
    }

    @Test
    void validateShouldThrowExceptionsForRegDate() throws DomainException {
        try {
            validator.validate(p3);
            fail("Transaction validator threw no exception for faulty input!");
        } catch (DomainException e) {
            assertEquals(e.getMessage(), "The date of registration is not in a correct format!");
        }
    }
}