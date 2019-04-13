package Domain;

public class PacientDiscountVM {

    private int idCard;
    private double discount;

    public PacientDiscountVM(int idCard, double discount) {
        this.idCard = idCard;
        this.discount = discount;
    }

    public int getIdCard() {
        return idCard;
    }

    public double getDiscount() {
        return discount;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "PacientDiscountVM{" +
                "idCard=" + idCard +
                ", discount=" + discount +
                '}';
    }
}