package Tests.Repository;

import Domain.DrugProduct;
import Domain.Pacient;
import Domain.Transaction;
import Repository.RepositoryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryExceptionTest{

    @Test
    public void RepositoryExceptionTest() throws RepositoryException {
        DrugProduct dp = new DrugProduct(2, "test", "test", -12.24, false);
        Transaction tr = new Transaction(1, 1, 1, -1, "22.12.2018", 12, 0, 0);
        Pacient p1 = new Pacient(1,"1","Dorin","Dorin", "12.12.2008", "12.12.2019");
    }
}