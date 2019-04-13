package Tests.Domain;

import Domain.DomainException;
import Domain.DrugProduct;
import Domain.DrugProductValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrugProductValidatorTest {
    private DrugProduct dp = new DrugProduct(1,"Faringosept","Terapia", -12, false);
    private DrugProduct dp1 = new DrugProduct(1,"Faringosept","Terapia", 12, false);
    private DrugProductValidator validator = new DrugProductValidator();

    @Test
    void validateShouldThrowExceptionsCorrectly() throws DomainException {
        try {
            assertDoesNotThrow(() -> validator.validate(dp1));
        } catch (DomainException e) {
            ;
        }
        try {
            dp.setPrice(0.00);
            fail("Drug Product validator threw no exception for faulty input!");
        } catch (DomainException e) {
            assertEquals(e.getMessage(), "price should be > 0");
        }
        try {
            validator.validate(dp);
            fail("Drug Product validator threw no exception for faulty input!");
            } catch (DomainException e) {
            assertEquals(e.getMessage(), "price should be > 0");
            }
    }
}