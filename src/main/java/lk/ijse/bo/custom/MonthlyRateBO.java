package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.MonthlyRateDto;
import lk.ijse.entity.MonthlyRate;

import java.sql.SQLException;
import java.util.List;

public interface MonthlyRateBO extends SuperBO {
    List<MonthlyRateDto> loadDetails() throws SQLException, ClassNotFoundException;
    List<MonthlyRateDto> getLeafPrice(String date) throws SQLException, ClassNotFoundException;
    double[] getAllCustomerHiga(String dateMonth, double lblGoodLea, double lblGoldLeaf) throws SQLException, ClassNotFoundException;
    List<Integer> getallMonthlyLeafCount(String date) throws SQLException, ClassNotFoundException;
    Double getPurchase(String date) throws SQLException, ClassNotFoundException;
    Double getAdvance(String month) throws SQLException, ClassNotFoundException;
    Double getPohora(String month) throws SQLException, ClassNotFoundException;
    Double get100tganan(String month) throws SQLException, ClassNotFoundException;

    List<MonthlyRateDto> getRates(String date) throws SQLException, ClassNotFoundException;

    String btnSaveRate(MonthlyRateDto dto) throws SQLException, ClassNotFoundException;

    String btnUpdateRate(MonthlyRateDto dto) throws SQLException, ClassNotFoundException;
}
