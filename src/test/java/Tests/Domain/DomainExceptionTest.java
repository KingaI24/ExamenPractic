package Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DomainExceptionTest {

    @Test
    public void DomainExceptionTest() {
        DrugProduct dp = new DrugProduct(2, "test", "test", -12.24, false);
        Transaction t = new Transaction(1, 1, 1, -1, "22.12.2018 12:12", 12, 0, 0);
        Transaction t1 = new Transaction(1, 1, 1, 1, "22.12.2018", 12, 0, 0);
        Pacient p = new Pacient(1, "1", "Dorin", "Dorin", "12.12.2008", "12.12.2019");
        Pacient p1 = new Pacient(1, "1234567890123", "Dorin", "Vlad", "12.2008", "12.12.2019");
        Pacient p2 = new Pacient(1, "1234567890124", "Dorin", "Vlad", "01.12.2001", "2019");

        DrugProductValidator validator = new DrugProductValidator();
        DomainException ex = assertThrows(DomainException.class, () -> validator.validate(dp));
        assertEquals("price should be > 0", ex.getMessage());

        PacientValidator validatorp = new PacientValidator();
        DomainException ex1 = assertThrows(DomainException.class, () -> validatorp.validate(p));
        assertEquals("The CNP format is incorrect", ex1.getMessage());
        DomainException ex2 = assertThrows(DomainException.class, () -> validatorp.validate(p1));
        assertEquals("The date of birth is not in a correct format!", ex2.getMessage());
        DomainException ex3 = assertThrows(DomainException.class, () -> validatorp.validate(p2));
        assertEquals("The date of registration is not in a correct format!", ex3.getMessage());

        TransactionValidator validatort = new TransactionValidator();
        DomainException ex4 = assertThrows(DomainException.class, () -> validatort.validate(t));
        assertEquals("Invalid number of items", ex4.getMessage());
        DomainException ex5 = assertThrows(DomainException.class, () -> validatort.validate(t1));
        assertEquals("The date is not in a correct format!", ex5.getMessage());
    }

}