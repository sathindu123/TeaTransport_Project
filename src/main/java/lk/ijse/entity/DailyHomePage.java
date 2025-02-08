package lk.ijse.entity;

public class DailyHomePage {
    private int qty;
    private String id;
    private String name;
    private String date;
    private int goldLeaf;
    private int goodLeaf;

    public DailyHomePage(){

    }

    public DailyHomePage(String custId, int goldLeaf, int goodLeaf){
        this.id = custId;
        this.goldLeaf = goldLeaf;
        this.goodLeaf = goodLeaf;
    }
    public DailyHomePage(String custId, String date){
        this.id = custId;
        this.date = date;
    }
    public DailyHomePage(String custId){
        this.id = custId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public int getGoodLeaf() {
        return goodLeaf;
    }

    public void setGoodLeaf(int goodLeaf) {
        this.goodLeaf = goodLeaf;
    }

    public int getGoldLeaf() {
        return goldLeaf;
    }

    public void setGoldLeaf(int goldLeaf) {
        this.goldLeaf = goldLeaf;
    }

    @Override
    public String toString() {
        return "DailyHomePageDto{" +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", goldLeaf=" + goldLeaf +
                ", goodLeaf=" + goodLeaf +
                '}';
    }
    public DailyHomePage(String custId, String date, int goldLeaf, int goodLeaf){
        this.id = custId;
        this.date = date;
        this.goldLeaf = goldLeaf;
        this.goodLeaf = goodLeaf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DailyHomePage(String id, String name, String date, int goldLeaf, int goodLeaf) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.goldLeaf = goldLeaf;
        this.goodLeaf = goodLeaf;
    }

}
