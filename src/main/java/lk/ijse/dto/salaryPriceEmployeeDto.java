package lk.ijse.dto;

public class salaryPriceEmployeeDto {
    private int price;

    public salaryPriceEmployeeDto(int price){
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "salaryPriceEmployeeDto{" +
                "price=" + price +
                '}';
    }
}
