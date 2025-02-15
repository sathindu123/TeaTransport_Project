package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.SQLException;
import java.util.List;

public interface ViewManageBO extends SuperBO {
    List<StockDto> getAllStocks() throws SQLException, ClassNotFoundException;
    double getTotalAdvance() throws SQLException, ClassNotFoundException;
    double getTotalAdvanceCustomer(String id) throws SQLException, ClassNotFoundException;
    StockDto ViewManageSearchStock(String id) throws SQLException, ClassNotFoundException;
    double getTotalAdvanceSelectDate(String strDate, String endDate) throws SQLException, ClassNotFoundException;
}
