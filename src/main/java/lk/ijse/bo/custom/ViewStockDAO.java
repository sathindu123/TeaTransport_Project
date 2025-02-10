package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.SQLException;
import java.util.List;

public interface ViewStockDAO extends CrudDAO<Stock> {
    List<StockDto> getAllProducts() throws SQLException, ClassNotFoundException;
    StockDto searchProduct(String id) throws SQLException, ClassNotFoundException;
    List<String> searchStock(String typedText) throws SQLException, ClassNotFoundException;
    String getNameId(String productId) throws SQLException, ClassNotFoundException;
}
