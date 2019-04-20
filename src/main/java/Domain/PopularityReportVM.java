package Domain;

public class PopularityReportVM {

    private int id, noDays;
    private String model;

    public PopularityReportVM(int id, String model, int noDays) {
        this.id = id;
        this.model = model;
        this.noDays = noDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoDays() {
        return noDays;
    }

    public void setNoDays(int noDays) {
        this.noDays = noDays;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
