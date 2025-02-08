package lk.ijse.entity;

public class InvoiceCustomer {
    private String id;
    private String name;

    private String date;
    private int price;
    private int price1;

    private int totalLeafAmount;
    private int goodLeafAmount;
    private int totalAdvance;
    private double teaPacketTotal;
    private double fertilizeTotal;
    private double otherTotal;

    public InvoiceCustomer(double teaPacketTotal, double fertilizeTotal, double otherTotal){
        this.teaPacketTotal = teaPacketTotal;
        this.fertilizeTotal = fertilizeTotal;
        this.otherTotal = otherTotal;
    }

    public int getGoodLeafAmount() {
        return goodLeafAmount;
    }

    public void setGoodLeafAmount(int goodLeafAmount) {
        this.goodLeafAmount = goodLeafAmount;
    }

    public int getTotalLeafAmount() {
        return totalLeafAmount;
    }

    public void setTotalLeafAmount(int totalLeafAmount) {
        this.totalLeafAmount = totalLeafAmount;
    }

    public int getTotalAdvance() {
        return totalAdvance;
    }

    public void setTotalAdvance(int totalAdvance) {
        this.totalAdvance = totalAdvance;
    }

    public double getTeaPacketTotal() {
        return teaPacketTotal;
    }

    public void setTeaPacketTotal(double teaPacketTotal) {
        this.teaPacketTotal = teaPacketTotal;
    }

    public double getFertilizeTotal() {
        return fertilizeTotal;
    }

    public void setFertilizeTotal(double fertilizeTotal) {
        this.fertilizeTotal = fertilizeTotal;
    }

    public double getOtherTotal() {
        return otherTotal;
    }

    public void setOtherTotal(double otherTotal) {
        this.otherTotal = otherTotal;
    }

    public InvoiceCustomer(String date, int price){
        this.date = date;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public InvoiceCustomer(int price, int price1){
        this.price1 = price1;
        this.price = price;
    }

    @Override
    public String toString() {
        return "InvoiceCustomerDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", price1=" + price1 +
                ", totalLeafAmount=" + totalLeafAmount +
                ", goodLeafAmount=" + goodLeafAmount +
                ", totalAdvance=" + totalAdvance +
                ", teaPacketTotal=" + teaPacketTotal +
                ", fertilizeTotal=" + fertilizeTotal +
                ", otherTotal=" + otherTotal +
                '}';
    }

    public int getPrice1() {
        return price1;
    }

    public void setPrice1(int price1) {
        this.price1 = price1;
    }
}
