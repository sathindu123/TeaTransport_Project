package lk.ijse.entity;

public class Advance {
    private String custId;
    private String date;
    private double price;

    private String name;

    private CustomerManage customerDto;

    public Advance(String custId, String date, double price) {
        this.custId = custId;
        this.date = date;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public Advance(String custId, String name, String date, double price) {
        this.custId = custId;
        this.name = name;
        this.date = date;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
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

    public Advance(String custId, String date, double price, CustomerManage customerDto){
        this.custId =custId;
        this.date = date;
        this.price = price;
        this.customerDto = customerDto;
    }

    public CustomerManage getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerManage customerDto) {
        this.customerDto = customerDto;
    }

    public String getCustomerName() {
        return customerDto != null ? customerDto.getName() : "";
    }

    @Override
    public String toString() {
        return "AdvanceDto{" +
                "custId='" + custId + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", customerDto=" + customerDto +
                '}';
    }
}
