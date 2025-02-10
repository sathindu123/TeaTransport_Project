package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DailyHomePageDto;
import lk.ijse.entity.DailyHomePage;

import java.sql.SQLException;
import java.util.List;

public interface DailyHomePageBO extends SuperBO {
    int getCount() throws SQLException, ClassNotFoundException;
    int getTeaLeafCoutn(String[] ar) throws SQLException, ClassNotFoundException;
    double getPohoraTotal(String[] ar) throws SQLException, ClassNotFoundException;
    double getOtherTotal() throws SQLException, ClassNotFoundException;
    List<DailyHomePageDto> LoadTable()throws SQLException, ClassNotFoundException;

    String btnSaveDailyCount(DailyHomePageDto dto);

    String getNam(String id);

    String btnUpdateDailyCount(DailyHomePageDto dto);
}
