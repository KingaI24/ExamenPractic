package Tests.Domain;

import Domain.DomainException;
import Domain.DrugProduct;
import Domain.Pacient;
import Domain.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainExceptionTest {

    @Test
    public void DomainExceptionTest() throws DomainException {
        DrugProduct dp = new DrugProduct(2, "test", "test", -12.24, false);
        Transaction tr = new Transaction(1, 1, 1, -1, "22.12.2018", 12, 0, 0);
        Pacient p1 = new Pacient(1,"1","Dorin","Dorin", "12.12.2008", "12.12.2019");
    }
}