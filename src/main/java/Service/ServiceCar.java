package Service;

import Domain.Car;
import Repository.IRepository;

import java.util.List;

public class ServiceCar {

    private IRepository<Car> repository;

    public ServiceCar(IRepository<Car> repository) {
        this.repository = repository;
    }

    public void addOrUpdate(int id, String model, double kmInitial, double pricePerDay) {
        Car existent = repository.findById(id);
        if (existent != null) {
            throw new ServiceException("ID taken!");
        }

        Car car = new Car(id, model, kmInitial, pricePerDay);
        repository.add(car);
    }

    public List<Car> showCars() {
        return repository.show();
    }

}
