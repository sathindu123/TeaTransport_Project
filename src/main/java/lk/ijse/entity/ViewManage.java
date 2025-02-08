package lk.ijse.entity;

public class ViewManage {
    private String id;
    private String name;
    private String date;
    private String productId;
    private int quntity;
    private double totalPrice;

    public ViewManage(String id, String name, String date, String productId, int quntity, double totalPrice){
        this.id = id;
        this.name = name;
        this.date = date;
        this.productId = productId;
        this.quntity = quntity;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuntity() {
        return quntity;
    }

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ViewManageDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", productId='" + productId + '\'' +
                ", quntity=" + quntity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
