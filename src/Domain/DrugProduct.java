package Domain;

public class DrugProduct {
    private int id;
    private String name, manufacturer;
    private double price;
    private boolean needPresciption;

    public DrugProduct(int id, String name, String manufacturer, double price, boolean needPresciption) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.needPresciption = needPresciption;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public boolean isNeedPresciption() {
        return needPresciption;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNeedPresciption(boolean needPresciption) {
        this.needPresciption = needPresciption;
    }

    @Override
    public String toString() {
        return "DrugProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", needPresciption=" + needPresciption +
                '}';
    }
}
