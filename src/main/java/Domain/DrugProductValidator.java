package Domain;

public class DrugProductValidator implements IValidator<DrugProduct> {

    public void validate(DrugProduct dp) {

        if (dp.getPrice() <= 0) {
            throw new DomainException("price should be > 0");
        }

    }
}
