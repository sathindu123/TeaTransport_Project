package lk.ijse.entity;

import java.time.LocalDate;

public class EmpSalary {
    private String empId;
    private LocalDate date;
    private int price;

    public EmpSalary(String id, LocalDate date, int price){
        this.empId = id;
        this.date = date;
        this.price = price;
    }
    public EmpSalary(int price){
        this.price = price;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "EmpSalaryDto{" +
                "empId='" + empId + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                '}';
    }
}
