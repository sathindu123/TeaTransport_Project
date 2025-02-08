package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.ViewStockDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewStockDAOimpl implements ViewStockDAO {

    public List<StockDto> getAllProducts() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM product";

        PreparedStatement statement = connection.prepareStatement(sql);
        List<StockDto> productDtos = new ArrayList<>();

        ResultSet rst = statement.executeQuery();
        while (rst.next()) {
            StockDto dto = new StockDto(rst.getString("id"),
                    rst.getString("type"),
                    rst.getInt("count"),
                    rst.getDouble("price"));
            productDtos.add(dto);
        }
        return productDtos;
    }


    public StockDto searchProduct(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM product WHERE type = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);

        ResultSet rst = statement.executeQuery();
        if (rst.next()) {
            return new StockDto(rst.getString("id"),
                    rst.getString("type"),
                    rst.getInt("count"),
                    rst.getDouble("price"));
        }
        return null;
    }

    public List<String> searchStock(String typedText) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT type FROM product WHERE type LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, typedText + "%"); // Use a wildcard to match names starting with the typed text
        ResultSet resultSet = statement.executeQuery();

        List<String> suggestions = new ArrayList<>();
        while (resultSet.next()) {
            suggestions.add(resultSet.getString("type")); // Add each matching name to the list
        }
        return suggestions; // Return all matching names
    }

    public String getNameId(String productId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT id FROM product WHERE type = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {

        }
        return productId;
    }

    @Override
    public String save(Stock stock) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String update(Stock stock) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }
}
