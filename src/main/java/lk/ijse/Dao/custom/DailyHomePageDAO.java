package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.DailyHomePageDto;
import lk.ijse.dto.InvoiceCustomerDto;
import lk.ijse.entity.DailyHomePage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface DailyHomePageDAO extends CrudDAO<DailyHomePage> {
    int getCount() throws SQLException, ClassNotFoundException;
    int getTeaLeafCoutn(String[] ar) throws SQLException, ClassNotFoundException;
    double getPohoraTotal(String[] ar) throws SQLException, ClassNotFoundException;
    double getOtherTotal() throws SQLException, ClassNotFoundException;
    //List<InvoiceCustomerDto> getAllTeaLeafCount() throws SQLException, ClassNotFoundException;


}
