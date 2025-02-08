package lk.ijse.dto;

public class productDto {
    private String id;
    private String date;
    private double price;

    public productDto(String id, String date,double price){
        this.price = price;
        this.id = id;
        this.date = date;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "productDto{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                '}';
    }
}
