package Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.zip.DataFormatException;

public class PacientValidator implements IValidator<Pacient> {

    public void validate(Pacient p) {

        if (p.getCNP().length() != 13)  {
            throw new DomainException("The CNP format is incorrect");
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            format.parse(p.getBirthDate());
        } catch (ParseException pe) {
            throw new DomainException("The date of birth is not in a correct format!");
        }

        try {
            format.parse(p.getRegDate());
        } catch (ParseException pe) {
            throw new DomainException("The date of registration is not in a correct format!");
        }
    }
}
