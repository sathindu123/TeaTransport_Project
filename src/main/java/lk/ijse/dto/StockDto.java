package lk.ijse.dto;

public class StockDto {
    private String id;
    private String category;  // Fixed spelling from 'cetegory' to 'category'
    private int count;
    private double price;

    public StockDto(String id, String category, int count, double price) {
        this.id = id;
        this.category = category;
        this.count = count;
        this.price = price;

    }



    @Override
    public String toString() {
        return "StockDto{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
