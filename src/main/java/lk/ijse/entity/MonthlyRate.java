package lk.ijse.entity;

public class MonthlyRate {
    private String month;
    private double rate;
    private double rate1;

    public MonthlyRate(String month, double rate, double rate1){
        this.month = month;
        this.rate = rate;
        this.rate1 = rate1;
    }
    public MonthlyRate(double rate, double rate1){
        this.rate = rate;
        this.rate1 = rate1;
    }

    @Override
    public String toString() {
        return "MonthlyRateDto{" +
                "month='" + month + '\'' +
                ", rate=" + rate +
                ", rate1=" + rate1 +
                '}';
    }

    public double getRate1() {
        return rate1;
    }

    public void setRate1(double rate1) {
        this.rate1 = rate1;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

}
