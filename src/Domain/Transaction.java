package Domain;

public class Transaction {
    private int id, idMed, idCard, quantity;
    private String date;
    private double basePrice;
    private double discount;
    private double finalPrice;

    public Transaction(int id, int idMed, int idCard, int quantity, String date, double basePrice, double discount, double finalPrice) {
        this.id = id;
        this.idMed = idMed;
        this.idCard = idCard;
        this.quantity = quantity;
        this.date = date;
        this.basePrice = basePrice;
        this.discount = discount;
        this.finalPrice = finalPrice;
    }

    public int getId() {
        return id;
    }

    public int getIdMed() {
        return idMed;
    }

    public int getIdCard() {
        return idCard;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdMed(int idMed) {
        this.idMed = idMed;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", idMed=" + idMed +
                ", idCard=" + idCard +
                ", quantity=" + quantity +
                ", date='" + date + '\'' +
                ", basePrice=" + basePrice +
                ", discount=" + discount +
                ", finalPrice=" + finalPrice +
                '}';
    }
}
