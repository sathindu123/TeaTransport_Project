package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface StockDAO extends CrudDAO<Stock> {
    String btnUpdateClick(StockDto stockDto) throws SQLException, ClassNotFoundException;
    String getNames(String id) throws SQLException, ClassNotFoundException;
    int getCounts(String id) throws SQLException, ClassNotFoundException;
}
