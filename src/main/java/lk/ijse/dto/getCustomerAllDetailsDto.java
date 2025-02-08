package lk.ijse.dto;

import java.beans.FeatureDescriptor;

public class getCustomerAllDetailsDto {
    private String id;
    private String name;
    private int teaLeafAmount;
    private int advancePurchase;
    private double teaPacketPurchase;
    private double fertilizePurchase;
    private double otherPurchase;

    // Constructor
    public getCustomerAllDetailsDto(String id, String name, int teaLeafAmount, int advancePurchase,
                                    double fertilizePurchase, double teaPacketPurchase, double otherPurchase) {
        this.id = id;
        this.name = name;
        this.teaLeafAmount = teaLeafAmount;
        this.advancePurchase = advancePurchase;
        this.teaPacketPurchase = teaPacketPurchase;
        this.fertilizePurchase = fertilizePurchase;
        this.otherPurchase = otherPurchase;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeaLeafAmount() {
        return teaLeafAmount;
    }

    public void setTeaLeafAmount(int teaLeafAmount) {
        this.teaLeafAmount = teaLeafAmount;
    }

    public int getAdvancePurchase() {
        return advancePurchase;
    }

    public void setAdvancePurchase(int advancePurchase) {
        this.advancePurchase = advancePurchase;
    }

    public double getTeaPacketPurchase() {
        return teaPacketPurchase;
    }

    public void setTeaPacketPurchase(double teaPacketPurchase) {
        this.teaPacketPurchase = teaPacketPurchase;
    }

    public double getFertilizePurchase() {
        return fertilizePurchase;
    }

    public void setFertilizePurchase(double fertilizePurchase) {
        this.fertilizePurchase = fertilizePurchase;
    }

    public double getOtherPurchase() {
        return otherPurchase;
    }

    public void setOtherPurchase(double otherPurchase) {
        this.otherPurchase = otherPurchase;
    }

    @Override
    public String toString() {
        return "getCustomerAllDetailsDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", teaLeafAmount=" + teaLeafAmount +
                ", advancePurchase=" + advancePurchase +
                ", teaPacketPurchase=" + teaPacketPurchase +
                ", fertilizePurchase=" + fertilizePurchase +
                ", otherPurchase=" + otherPurchase +
                '}';
    }
}
