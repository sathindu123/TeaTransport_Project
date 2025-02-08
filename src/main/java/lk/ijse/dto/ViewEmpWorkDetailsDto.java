package lk.ijse.dto;

public class ViewEmpWorkDetailsDto {
    private String id;
    private String name;
    private String address;
    private int tel;
    private String date;

    public ViewEmpWorkDetailsDto(String id, String name,String date){
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public ViewEmpWorkDetailsDto(String id, String name, String address, int tel, String date){
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.date = date;
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

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ViewEmpWorkDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel=" + tel +
                ", date='" + date + '\'' +
                '}';
    }
}
