package Service;

import Domain.Borrow;
import Domain.Car;
import Domain.IdDays;
import Domain.PopularityReportVM;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceBorrow {

    private IRepository<Borrow> borrowRepository;
    private IRepository<Car> carRepository;

    public ServiceBorrow(IRepository<Borrow> borrowRepository, IRepository<Car> carRepository) {
        this.borrowRepository = borrowRepository;
        this.carRepository = carRepository;
    }

    /**
     *
     * @param id
     * @param idCar
     * @param noDays
     * @param km
     * adaugare respectand sa fie id unic si id masina existent
     */
    public void addOrUpdate(int id, int idCar, int noDays, double km) {
        Borrow existentBorrow = borrowRepository.findById(id);
        if (existentBorrow != null) {
            throw new ServiceException("ID taken!");
        }

        Car existentCar = carRepository.findById(idCar);
        if (existentCar == null) {
            throw new ServiceException("Car ID not existent!");
        }

        Borrow borrow = new Borrow(id,idCar,noDays,km);
        borrowRepository.add(borrow);
    }

    /**
     *
     * @return arata toata imprumuturile
     */
    public List<Borrow> showBorrows() {
        return borrowRepository.show();
    }

    /**
     *
     * @param id
     * @return raport veituri pe masina
     */
    public Double incomePerCar(int id) {

        Car existentCar = carRepository.findById(id);
        if (existentCar == null) {
            throw new ServiceException("Inexistent car ID!");
        }

        double income = 0;
        for (Borrow b : borrowRepository.show()) {
            if (b.getId() == id) {
                income += b.getNoDays() * carRepository.findById(b.getId()).getPricePerDay();
            }
        }
        return income;
    }

    /**
     *
     * @param id
     * @return raport km pe masina
     */
    public Double kmPerCar(int id) {

        Car existentCar = carRepository.findById(id);
        if (existentCar == null) {
            throw new ServiceException("Inexistent car ID!");
        }

        double km = carRepository.findById(id).getKmInitial();
        for (Borrow b : borrowRepository.show()) {
            if (b.getId() == id) {
                km += b.getKm();
            }
        }
        return km;
    }

    /**
     *
     * @return raport cerere
     */
    public List<PopularityReportVM> reportPopularity() {
        Map<Integer, Integer> assess = new HashMap<>();

        for (Borrow b : borrowRepository.show()) {
            if (!assess.containsKey(carRepository.findById(b.getIdCar()).getId())) {
                assess.put(carRepository.findById(b.getId()).getId(), b.getNoDays());
            } else {
                assess.put(carRepository.findById(b.getIdCar()).getId(), borrowRepository.findById(b.getIdCar()).getNoDays() + b.getNoDays());
            }
        }
        List<IdDays> listIdDays = new ArrayList<>();
        for (int id : assess.keySet()) {
            IdDays p = new IdDays(id, assess.get(id));
            listIdDays.add(p);
        }

        listIdDays.sort((r1, r2) -> r1.getNoDays() > r2.getNoDays() ? -1 : 1);
        listIdDays.sort((r1, r2) -> {
            if (r1.getNoDays() > r2.getNoDays())
                return -1;
            else if (r1.getNoDays() == r2.getNoDays())
                return 0;
            else
                return 1;
            });

        List<PopularityReportVM> listPopularity = new ArrayList<>();

        for (int i = 0; i < listIdDays.size(); i++) {
            PopularityReportVM item = new PopularityReportVM(listIdDays.get(i).getId(), carRepository.findById(listIdDays.get(i).getId()).getModel(), listIdDays.get(i).getNoDays());
            listPopularity.add(item);
        }
        return listPopularity;


    }

}
