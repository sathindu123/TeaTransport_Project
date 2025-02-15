package lk.ijse.dto;

public class LoadProductPurchaseDto {
    private String custName;
    private String date;
    private String Type;
    private int count;
    private double price;

    private String productId;

    public LoadProductPurchaseDto(String custName, String date, String productId, int count, double price) {
        this.custName = custName;
        this.date = date;
        this.Type = productId;
        this.count = count;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LoadProductPurchase{" +
                "custName='" + custName + '\'' +
                ", date='" + date + '\'' +
                ", productId='" + Type + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
