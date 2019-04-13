package Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.zip.DataFormatException;


public class TransactionValidator implements IValidator<Transaction> {

    public void validate(Transaction t) {

        if (t.getQuantity() <= 0) {
            throw new DomainException("Invalid number of items");
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        try {
            format.parse(t.getDate());
        } catch (ParseException pe) {
            throw new DomainException("The date is not in a correct format!");
        }
    }
}
