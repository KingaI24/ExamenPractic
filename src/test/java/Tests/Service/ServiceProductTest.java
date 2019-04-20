package Service;

import Domain.DrugProduct;
import Domain.DrugProductValidator;
import Repository.GeneralRepository;
import Repository.IRepository;
import Service.ServiceProduct;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceProductTest {

    IRepository<DrugProduct> productRepository = new GeneralRepository<>(new DrugProductValidator());
    ServiceProduct serviceProduct = new ServiceProduct(productRepository);

    @Test
    void addAndUpdateCorrectly() {
        serviceProduct.addOrUpdate(1,"Faringosept", "Terapia", 12.00, false);
        assertEquals(1,serviceProduct.showProducts().size());
        assertEquals(1,productRepository.findById(1).getId());
        assertEquals("Faringosept",productRepository.findById(1).getName());
        assertEquals("Terapia",productRepository.findById(1).getManufacturer());
        assertEquals(12.00,productRepository.findById(1).getPrice());
        assertEquals(false,productRepository.findById(1).isNeedPresciption());
        serviceProduct.addOrUpdate(1,"Echinaceae", "Parafarm", 6.00, false);
        assertEquals(1,serviceProduct.showProducts().size());
        assertEquals(1,productRepository.findById(1).getId());
        assertEquals("Echinaceae",productRepository.findById(1).getName());
        assertEquals("Parafarm",productRepository.findById(1).getManufacturer());
        assertEquals(6.00,productRepository.findById(1).getPrice());
        assertEquals(false,productRepository.findById(1).isNeedPresciption());
        serviceProduct.addOrUpdate(1,"", "", 6.50, false);
        assertEquals(1,serviceProduct.showProducts().size());
        assertEquals(1,productRepository.findById(1).getId());
        assertEquals("Echinaceae",productRepository.findById(1).getName());
        assertEquals("Parafarm",productRepository.findById(1).getManufacturer());
        assertEquals(6.50,productRepository.findById(1).getPrice());
        assertEquals(false,productRepository.findById(1).isNeedPresciption());
    }

    @Test
    void shouldRemoveCorrectly() {
        serviceProduct.addOrUpdate(1,"Faringosept", "Terapia", 12.00, false);
        serviceProduct.addOrUpdate(2,"Echinaceae", "Parafarm", 6.00, false);
        serviceProduct.remove(1);

        assertEquals(1,serviceProduct.showProducts().size());
    }

    @Test
    void shouldShowAllProducts() {
        DrugProduct dp1 = new DrugProduct(1,"Faringosept", "Terapia", 12, false);
        DrugProduct dp2 = new DrugProduct(2,"Echinaceae", "Parafarm", 6, false);
        serviceProduct.addOrUpdate(1,"Faringosept", "Terapia", 12, false);
        serviceProduct.addOrUpdate(2,"Echinaceae", "Parafarm", 6, false);
        ArrayList<DrugProduct> list = new ArrayList<>();
        list.add(dp1);
        list.add(dp2);

        assertEquals(list,serviceProduct.showProducts());
        assertEquals(2,serviceProduct.showProducts().size());
    }

    @Test
    void correctFullTextSearch() {
        DrugProduct dp1 = new DrugProduct(1,"Faringosept", "Terapia", 12, false);
        DrugProduct dp2 = new DrugProduct(2,"Echinaceae", "Parafarm", 6, false);
        serviceProduct.addOrUpdate(1,"Faringosept", "Terapia", 12, false);
        serviceProduct.addOrUpdate(2,"Echinaceae", "Parafarm", 6, false);

        List<DrugProduct> list = new ArrayList<>();
        list.add(dp1);
        list.add(dp2);

        assertEquals(list.get(0),serviceProduct.fullTextSearch("Terapia").get(0));
        assertEquals(0,serviceProduct.fullTextSearch("test").size());
    }

    @Test
    void shouldPriceCorrectly() {

    }
}