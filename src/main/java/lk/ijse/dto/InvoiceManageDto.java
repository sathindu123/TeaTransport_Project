package lk.ijse.dto;

public class InvoiceManageDto {
    private String custId;
    private String date;
    private String productId;
    private int count;
    private double price;

    private String custName;
    private CustomerManageDto customerDto;

    private String month;
    private double monthPrice;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(double monthPrice) {
        this.monthPrice = monthPrice;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
        return "InvoiceManageDto{" +
                "custId='" + custId + '\'' +
                ", date='" + date + '\'' +
                ", productId='" + productId + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    public InvoiceManageDto(String custId, String date, String productId, int count, String month, double price, double monthPrice) {
        this.custId = custId;
        this.date = date;
        this.productId = productId;
        this.count = count;
        this.month = month;
        this.price = price;
        this.monthPrice = monthPrice;
    }

    public InvoiceManageDto(String custId, String date, String month, double price, double monthPrice) {
        this.custId = custId;
        this.date = date;
        this.month = month;
        this.price = price;
        this.monthPrice = monthPrice;
    }

    public InvoiceManageDto(String custId, String date, String productId, int count, double price){
        this.custId = custId;
        this.date = date;
        this.productId = productId;
        this.count = count;
        this.price = price;

    }

    public InvoiceManageDto(String id,String date,double price){
        this.custId = id;
        this.date = date;
        this.price = price;
    }

    public InvoiceManageDto(String custId, String date, double price, CustomerManageDto customerDto){
        this.custId = custId;
        this.date = date;
        this.price = price;
        this.customerDto = customerDto;
    }

    public CustomerManageDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerManageDto customerDto) {
        this.customerDto = customerDto;
    }

    public String getCustomerName() {
        return customerDto != null ? customerDto.getName() : "";
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

}
