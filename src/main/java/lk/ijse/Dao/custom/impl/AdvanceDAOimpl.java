package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.AdvanceDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.Advance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvanceDAOimpl implements AdvanceDAO {
    public double getTotalAdvance() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select SUM(monthPrice)as monthPrice from advance";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rst = statement.executeQuery();
        double price = 0;
        if(rst.next()){
            price = rst.getDouble("monthPrice");
        }
        return price;
    }

    @Override
    public String save(Advance advance) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String update(Advance advance) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }
}
