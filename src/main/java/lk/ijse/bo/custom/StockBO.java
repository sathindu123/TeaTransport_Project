package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StockBO extends SuperBO {
    String save(StockDto stockDto) throws SQLException, ClassNotFoundException;
    String btnUpdateClick(StockDto stockDto) throws SQLException, ClassNotFoundException;
    String getNames(String id) throws SQLException, ClassNotFoundException;
    int getCounts(String id) throws SQLException, ClassNotFoundException;
    String update(StockDto stockDto) throws SQLException, ClassNotFoundException;
}
