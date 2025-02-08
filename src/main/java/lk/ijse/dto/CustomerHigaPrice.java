package lk.ijse.dto;

public class CustomerHigaPrice {
    private String custID;
    private String month;
    private double price;

    public CustomerHigaPrice(String custID, String month, double price) {
        this.custID = custID;
        this.month = month;
        this.price = price;
    }

    @Override
    public String toString() {
        return "CustomerHigaPrice{" +
                "custID='" + custID + '\'' +
                ", month='" + month + '\'' +
                ", price=" + price +
                '}';
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
