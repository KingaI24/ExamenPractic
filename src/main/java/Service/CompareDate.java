package Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class CompareDate implements Comparator<String> {

    public int compare(String a, String b) {
        Date d1 = converse(a);
        Date d2 = converse(b);
        return d1.compareTo(d2);
    }

    public Date converse(String a) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date d;
        try {
            d = format.parse(a);
        } catch (ParseException pe) {
            throw new ServiceException("The date is not in a correct format!");
        }
        return d;
    }
}
