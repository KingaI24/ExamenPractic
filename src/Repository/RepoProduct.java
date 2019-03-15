package Repository;

import Domain.DrugProduct;
import Domain.DrugProductValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepoProduct {
    private DrugProductValidator validator;
    private Map<Integer, DrugProduct> products= new HashMap<>();

    public RepoProduct(DrugProductValidator validator) {
        this.validator = validator;
    }

    public void add(DrugProduct product) {
        validator.validate(product);
        products.put(product.getId(), product);
    }

    public void remove(int id) {
        if (!products.containsKey(id)) {
            throw new RuntimeException("the ID does not exist");
        }
        products.remove(id);
    }

    public DrugProduct findById(int id) {
        return products.get(id);
    }

    public List<DrugProduct> showProducts() {
        return new ArrayList<>(products.values());
    }
}
