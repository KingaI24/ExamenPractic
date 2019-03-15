package Service;

import Domain.DrugProduct;
import Repository.RepoProduct;

import java.util.List;

public class ServiceProduct {

    private RepoProduct repository;

    public ServiceProduct(RepoProduct repository) {
        this.repository = repository;
    }

    public void addOrUpdate (int id, String name, String manufacturer, double price, boolean needPresciption) {
        DrugProduct value = repository.findById(id);
        if (value != null) {
            if (name.isEmpty())
                name = value.getName();
            if (manufacturer.isEmpty())
                manufacturer = value.getManufacturer();
            if (price == 0)
                price = value.getPrice();
        }
        DrugProduct dp = new DrugProduct(id, name,  manufacturer, price, needPresciption);
        repository.add(dp);
    }

    public void remove (int id) {
        repository.remove(id);
    }

    public List<DrugProduct> showProducts() {
        return repository.showProducts();
    }
}
