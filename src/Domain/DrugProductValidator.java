package Domain;

public class DrugProductValidator {

    public void validate(DrugProduct dp) {

        if (dp.getPrice() <= 0) {
            throw new RuntimeException("price should be > 0");
        }

    }
}
