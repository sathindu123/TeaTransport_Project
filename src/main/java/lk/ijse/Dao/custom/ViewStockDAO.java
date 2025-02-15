package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ViewStockDAO extends CrudDAO<Stock> {
    List<Stock> getAllProducts() throws SQLException, ClassNotFoundException;
    Stock searchProduct(String id) throws SQLException, ClassNotFoundException;
    List<String> searchStock(String typedText) throws SQLException, ClassNotFoundException;
    String getNameId(String productId) throws SQLException, ClassNotFoundException;
}
