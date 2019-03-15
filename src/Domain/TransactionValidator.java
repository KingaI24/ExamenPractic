package Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.zip.DataFormatException;


public class TransactionValidator {

    public void validate(Transaction t) {

        if (t.getQuantity() <= 0) {
            throw new RuntimeException("Invalid number of items");
        }
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            format.parse(t.getDate());
        } catch (ParseException pe) {
            throw new RuntimeException("The date is not in a correct format!");
        }
    }
}
