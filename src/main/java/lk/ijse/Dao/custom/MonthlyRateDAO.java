package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.MonthlyRateDto;
import lk.ijse.entity.MonthlyRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface MonthlyRateDAO extends CrudDAO<MonthlyRate> {
    List<MonthlyRateDto> loadDetails() throws SQLException, ClassNotFoundException;
    List<MonthlyRateDto> getLeafPrice(String date) throws SQLException, ClassNotFoundException;
    double[] getAllCustomerHiga(String dateMonth, double lblGoodLea, double lblGoldLeaf) throws SQLException, ClassNotFoundException;
    List<Integer> getallMonthlyLeafCount(String date) throws SQLException, ClassNotFoundException;
    Double getPurchase(String date) throws SQLException, ClassNotFoundException;
    Double getAdvance(String month) throws SQLException, ClassNotFoundException;
    Double getPohora(String month) throws SQLException, ClassNotFoundException;
    Double get100tganan(String month) throws SQLException, ClassNotFoundException;

    List<MonthlyRateDto> getRates(String date) throws SQLException, ClassNotFoundException;
}
