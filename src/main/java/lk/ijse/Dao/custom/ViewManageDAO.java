package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.CustomerManageDto;
import lk.ijse.dto.InvoiceManageDto;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ViewManageDAO extends CrudDAO<Stock> {
    List<StockDto> getAllStocks() throws SQLException, ClassNotFoundException;
    double getTotalAdvance() throws SQLException, ClassNotFoundException;
    double getTotalAdvanceCustomer(String id) throws SQLException, ClassNotFoundException;
    StockDto ViewManageSearchStock(String id) throws SQLException, ClassNotFoundException;
    double getTotalAdvanceSelectDate(String strDate, String endDate) throws SQLException, ClassNotFoundException;
}
