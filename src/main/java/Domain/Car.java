package Domain;

public class Car extends Entity{
    private String model;
    private double kmInitial,pricePerDay;

    /**
     *
     * @param id
     * @param model - model masina
     * @param kmInitial -km initiali
     * @param pricePerDay - pret pe zi
     */

    public Car(int id, String model, double kmInitial, double pricePerDay) {
        super(id);
        this.model = model;
        this.kmInitial = kmInitial;
        this.pricePerDay = pricePerDay;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getKmInitial() {
        return kmInitial;
    }

    public void setKmInitial(double kmInitial) {
        this.kmInitial = kmInitial;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
