package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.EmployeeManageDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmployeeManageDto;
import lk.ijse.entity.EmployeeManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeManageDAOimpl implements EmployeeManageDAO {

    public String save(EmployeeManage employeeManage) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Insert into employee Values(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,employeeManage.getId());
        statement.setString(2,employeeManage.getName());
        statement.setString(3,employeeManage.getAddress());
        statement.setInt(4,employeeManage.getNumber());

        int resp = statement.executeUpdate();
        return resp > 0 ? "Sucsses" : "Fail";
    }

    public String update(EmployeeManage employeeManage) throws SQLException, ClassNotFoundException {
        Connection connection  = DBConnection.getInstance().getConnection();
        String sql = "Update employee SET name = ? , address = ? , telNb = ? WHERE empId = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,employeeManage.getName());
        statement.setString(2,employeeManage.getAddress());
        statement.setInt(3,employeeManage.getNumber());
        statement.setString(4,employeeManage.getId());

        int resp = statement.executeUpdate();
        return resp > 0 ? "Succsess Update" : "Fail Update";
    }


    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }
}
