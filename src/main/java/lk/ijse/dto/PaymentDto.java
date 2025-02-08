package lk.ijse.dto;

public class PaymentDto {
    private String id;
    private String custId;
    private String custName;
    private String date;
    private int goodLeaf;
    private int goldLeaf;
    private double tealeafAmount;
    private double totalAdvance;
    private double totalFertilize;
    private double totalTeaPacket;
    private double totalOthers;
    private double totalAmounr;
    private double previasMonthlyHiga;
    private double counts;
    private double rategood;
    private double rategold;
    private double nextHigaPrice;
    private double nextHigaPohora;
    private double nextAdvanceHiga;
    private int kapanagana;


    public PaymentDto(String id, String custId, String custName, String date, int goodLeaf, int goldLeaf, double tealeafAmount, double totalAdvance, double totalFertilize, double totalTeaPacket, double totalOthers, double totalAmounr, double previasMonthlyHiga, double counts, double rategood, double rategold, double nextHigaPrice, double nextHigaPohora, double nextAdvanceHiga,int kapanagana) {
        this.id = id;
        this.custId = custId;
        this.custName = custName;
        this.date = date;
        this.goodLeaf = goodLeaf;
        this.goldLeaf = goldLeaf;
        this.tealeafAmount = tealeafAmount;
        this.totalAdvance = totalAdvance;
        this.totalFertilize = totalFertilize;
        this.totalTeaPacket = totalTeaPacket;
        this.totalOthers = totalOthers;
        this.totalAmounr = totalAmounr;
        this.previasMonthlyHiga = previasMonthlyHiga;
        this.counts = counts;
        this.rategood = rategood;
        this.rategold = rategold;
        this.nextHigaPrice = nextHigaPrice;
        this.nextHigaPohora = nextHigaPohora;
        this.nextAdvanceHiga = nextAdvanceHiga;
        this.kapanagana = kapanagana;
    }

    public int getKapanagana() {
        return kapanagana;
    }

    public void setKapanagana(int kapanagana) {
        this.kapanagana = kapanagana;
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
                "id='" + id + '\'' +
                ", custId='" + custId + '\'' +
                ", custName='" + custName + '\'' +
                ", date='" + date + '\'' +
                ", goodLeaf=" + goodLeaf +
                ", goldLeaf=" + goldLeaf +
                ", tealeafAmount=" + tealeafAmount +
                ", totalAdvance=" + totalAdvance +
                ", totalFertilize=" + totalFertilize +
                ", totalTeaPacket=" + totalTeaPacket +
                ", totalOthers=" + totalOthers +
                ", totalAmounr=" + totalAmounr +
                ", previasMonthlyHiga=" + previasMonthlyHiga +
                ", counts=" + counts +
                ", rategood=" + rategood +
                ", rategold=" + rategold +
                ", nextHigaPrice=" + nextHigaPrice +
                ", nextHigaPohora=" + nextHigaPohora +
                ", nextAdvanceHiga=" + nextAdvanceHiga +
                ", kapanagana=" + kapanagana +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public double getTealeafAmount() {
        return tealeafAmount;
    }

    public void setTealeafAmount(double tealeafAmount) {
        this.tealeafAmount = tealeafAmount;
    }

    public double getTotalAdvance() {
        return totalAdvance;
    }

    public void setTotalAdvance(double totalAdvance) {
        this.totalAdvance = totalAdvance;
    }

    public double getTotalFertilize() {
        return totalFertilize;
    }

    public void setTotalFertilize(double totalFertilize) {
        this.totalFertilize = totalFertilize;
    }

    public double getTotalTeaPacket() {
        return totalTeaPacket;
    }

    public void setTotalTeaPacket(double totalTeaPacket) {
        this.totalTeaPacket = totalTeaPacket;
    }

    public double getTotalOthers() {
        return totalOthers;
    }

    public void setTotalOthers(double totalOthers) {
        this.totalOthers = totalOthers;
    }

    public double getTotalAmounr() {
        return totalAmounr;
    }

    public void setTotalAmounr(double totalAmounr) {
        this.totalAmounr = totalAmounr;
    }

    public double getPreviasMonthlyHiga() {
        return previasMonthlyHiga;
    }

    public void setPreviasMonthlyHiga(double previasMonthlyHiga) {
        this.previasMonthlyHiga = previasMonthlyHiga;
    }

    public double getCounts() {
        return counts;
    }

    public void setCounts(double counts) {
        this.counts = counts;
    }

    public double getRategood() {
        return rategood;
    }

    public void setRategood(double rategood) {
        this.rategood = rategood;
    }

    public double getRategold() {
        return rategold;
    }

    public void setRategold(double rategold) {
        this.rategold = rategold;
    }

    public double getNextHigaPrice() {
        return nextHigaPrice;
    }

    public void setNextHigaPrice(double nextHigaPrice) {
        this.nextHigaPrice = nextHigaPrice;
    }

    public double getNextHigaPohora() {
        return nextHigaPohora;
    }

    public void setNextHigaPohora(double nextHigaPohora) {
        this.nextHigaPohora = nextHigaPohora;
    }

    public double getNextAdvanceHiga() {
        return nextAdvanceHiga;
    }

    public void setNextAdvanceHiga(double nextAdvanceHiga) {
        this.nextAdvanceHiga = nextAdvanceHiga;
    }
}
