package Domain;

public class Borrow extends Entity{

    private int IdCar, noDays;
    private double km;

    /**
     *
     * @param id
     * @param idCar - id masina
     * @param noDays - nr zile imprumutate
     * @param km - km folositi
     */
    public Borrow(int id, int idCar, int noDays, double km) {
        super(id);
        IdCar = idCar;
        this.noDays = noDays;
        this.km = km;
    }

    public void setIdCar(int idCar) {
        IdCar = idCar;
    }

    public int getIdCar() {
        return IdCar;
    }

    public int getNoDays() {
        return noDays;
    }

    public void setNoDays(int noDays) {
        this.noDays = noDays;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }
}
