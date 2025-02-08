package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.EmpWorkDetailsDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmpWordDetailDto;
import lk.ijse.entity.EmpWordDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpWorkDetailDAOimpl implements EmpWorkDetailsDAO {

    public String save(EmpWordDetail empdto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO empworddetails VALUES(?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,empdto.getId());
        statement.setString(2,empdto.getDate());


        int resp = statement.executeUpdate();
        return resp > 0 ? "sucsess" : "fail";

    }

    @Override
    public String update(EmpWordDetail empWordDetail) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }
}
