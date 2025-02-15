package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.ViewEmployeeFormDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmployeeManageDto;
import lk.ijse.entity.EmployeeManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewEmployeeFormDAOimpl implements ViewEmployeeFormDAO {

    public List<EmployeeManage> getAllCustomer() throws  Exception{
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "Select * from employee";

        PreparedStatement statement = connection.prepareStatement(sql);

        List<EmployeeManage> empDtos = new ArrayList<>();

        ResultSet rst = statement.executeQuery();
        while(rst.next()){
            EmployeeManage dto = new EmployeeManage(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getInt(4));
            empDtos.add(dto);
        }
        return empDtos;
    }



    public EmployeeManage BtnOKClickSearchEmployee(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee WHERE empId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);

        ResultSet rst = statement.executeQuery();
        if(rst.next()){
            EmployeeManage dto = new EmployeeManage(rst.getString(1),
                    rst.getString(2), rst.getString(3),
                    rst.getInt(4));

            return  dto;
        }

        return null;
    }

    @Override
    public String save(EmployeeManage employeeManage) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String update(EmployeeManage employeeManage) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }
}
