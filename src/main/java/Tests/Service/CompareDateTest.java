package Tests.Service;

import Domain.Transaction;
import Service.CompareDate;
import Service.ServiceException;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CompareDateTest {

    private Transaction t1 = new Transaction(1,1,1,1,"12.12.2018 12:12",12,2,10);
    private Transaction t2 = new Transaction(1,1,1,1,"12.12.2018 12:12",12,2,10);
    private Transaction t3 = new Transaction(3,1,2,1,"12.04.2019",12,2,10);
    private Transaction t4 = new Transaction(3,1,2,1,"12.04.2017 12:12",12,2,10);
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    @Test
    void compareCorrectlyDates() {

        try {
            Date d1 = format.parse(t1.getDate());
            Date d2 = format.parse(t2.getDate());
            Date d4 = format.parse(t4.getDate());
            assertEquals(d1.compareTo(d2),0);
            assertEquals(d2.compareTo(d1),0);
            assertEquals(d4.compareTo(d1),-1);
            assertEquals(d4.compareTo(d1),-1);
            assertEquals(d4.compareTo(d2),-1);
        } catch (ParseException e) {
            ;
        }
    }

    @Test
    void converseShouldBeDoneCorrectly() {
        try {
            CompareDate d = new CompareDate();
            d.converse(t3.getDate());
            fail("Date comparator threw no exception for faulty date format!");
        } catch (ServiceException e) {
            assertEquals(e.getMessage(),"The date is not in a correct format!");
        }

        try {
            Date d1 = format.parse(t1.getDate());
            Date d2 = format.parse(t2.getDate());
            assertEquals(d1,d2);
        } catch (ParseException e) {
           ;
        }
    }
}