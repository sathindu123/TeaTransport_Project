package lk.ijse.entity;

public class SalaryPriceEmployee {
    private int price;

    public SalaryPriceEmployee(int price){
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
