package lk.ijse.dto;

public class EmpWordDetailDto {
    private String id;
    private String date;

    public EmpWordDetailDto(String id,String date){
        this.id = id;
        this.date = date;
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

    @Override
    public String toString() {
        return "EmpWordDetailDto{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
