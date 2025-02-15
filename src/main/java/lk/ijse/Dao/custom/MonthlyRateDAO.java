package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.dto.MonthlyRateDto;
import lk.ijse.entity.MonthlyRate;

import java.sql.SQLException;
import java.util.List;

public interface MonthlyRateDAO extends CrudDAO<MonthlyRate> {
    List<MonthlyRate> loadDetails() throws SQLException, ClassNotFoundException;
    List<MonthlyRate> getLeafPrice(String date) throws SQLException, ClassNotFoundException;
    double[] getAllCustomerHiga(String dateMonth, double lblGoodLea, double lblGoldLeaf) throws SQLException, ClassNotFoundException;
    List<Integer> getallMonthlyLeafCount(String date, String st, String en) throws SQLException, ClassNotFoundException;
    Double getPurchase(String date, String startDate, String endDate) throws SQLException, ClassNotFoundException;
    Double getAdvance(String month) throws SQLException, ClassNotFoundException;
    Double getPohora(String month) throws SQLException, ClassNotFoundException;
    Double get100tganan(String month) throws SQLException, ClassNotFoundException;

    List<MonthlyRateDto> getRates(String date) throws SQLException, ClassNotFoundException;
}
