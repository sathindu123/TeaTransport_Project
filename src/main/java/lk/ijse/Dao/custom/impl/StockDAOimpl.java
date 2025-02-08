package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.StockDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDAOimpl implements StockDAO {
    public String save(Stock stockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Insert into product Values(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,stockDto.getId());
        statement.setString(2,stockDto.getCategory());
        statement.setInt(3,stockDto.getCount());
        statement.setDouble(4,stockDto.getPrice());

        int resp = statement.executeUpdate();
        return resp > 0 ? "Succsess" : "Fail";
    }


    public String update(Stock stockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Update product SET type = ? ,count =? , price = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,stockDto.getCategory());
        statement.setInt(2,stockDto.getCount());
        statement.setDouble(3,stockDto.getPrice());
        statement.setString(4,stockDto.getId());

        int resp = statement.executeUpdate();
        return resp > 0 ? "Succsess" : "Fail";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }

    public String btnUpdateClick(StockDto stockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Update product SET type = ? ,count =? , price = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,stockDto.getCategory());
        statement.setInt(2,stockDto.getCount());
        statement.setDouble(3,stockDto.getPrice());
        statement.setString(4,stockDto.getId());

        int resp = statement.executeUpdate();
        return resp > 0 ? "Succsess" : "Fail";
    }

    public String getNames(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT type FROM product WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);

        ResultSet rst = statement.executeQuery();

        String name = "";
        if(rst.next()){
            name = rst.getString("type");
        }
        return name;
    }

    public int getCounts(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT count FROM product WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);

        ResultSet rst = statement.executeQuery();

        int name = 0;
        if(rst.next()){
            name = rst.getInt("count");
        }
        return name;
    }
}
