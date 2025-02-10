package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.custom.SalaryPriceEmployeeDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.SalaryPriceEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryPriceEmployeeBoimpl implements SalaryPriceEmployeeDAO {

    public String save(SalaryPriceEmployee dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO salaryPriceEmployee (price) VALUES(?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1,dto.getPrice());

        int  resp = statement.executeUpdate();
        return resp > 0 ? "Sucssess" : "Faild";
    }

    @Override
    public String update(SalaryPriceEmployee salaryPriceEmployee) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }

    public int getSalaryEmp() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT price FROM salaryPriceEmployee ORDER BY price DESC LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rst = statement.executeQuery();

        int price = 0;
        if (rst.next()){
            price = rst.getInt(1);

        }
        return price;
    }
}
