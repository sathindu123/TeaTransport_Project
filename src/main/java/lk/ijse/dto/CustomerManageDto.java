package lk.ijse.dto;

public class CustomerManageDto {
    private String id;
    private String name;
    private String address;
    private int number;


    private AdvanceDto advanceDto;

    public CustomerManageDto(String id, String name, String address, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.address = address;
    }
    public CustomerManageDto(String id,String name){
        this.id = id;
        this.name = name;
    }

    public CustomerManageDto(String name){
        this.name = name;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CustomerManageDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", number=" + number +
                '}';
    }

    public CustomerManageDto(String id,String name,AdvanceDto advanceDto){
        this.id = id;
        this.name = name;
        this.advanceDto = advanceDto;
    }
    public AdvanceDto getAdvanceDto() {
        return advanceDto;
    }

    public void setAdvanceDto(AdvanceDto advanceDto) {
        this.advanceDto = advanceDto;
    }


    public double getAdvancePrice() {
        return advanceDto != null ? advanceDto.getPrice() : 0.0;
    }
    public String getDate() {
        return advanceDto != null ? advanceDto.getDate() : "";
    }


}