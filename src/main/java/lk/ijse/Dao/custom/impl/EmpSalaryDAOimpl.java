package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.EmpSalaryDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.EmpSalary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpSalaryDAOimpl implements EmpSalaryDAO

{

    public String save(EmpSalary dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO employesalary VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, dto.getEmpId());
        statement.setString(2, dto.getDate().toString()); // Use formatted date here
        statement.setInt(3, dto.getPrice());

        int resp = statement.executeUpdate();
        return resp > 0 ? "sucsess" : "Fail";
    }

    @Override
    public String update(EmpSalary empSalary) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }

    public List<EmpSalary> loadPayPriceEmp(String startDate, String endDate) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employesalary WHERE date BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,startDate);
        statement.setString(2,endDate);

        List<EmpSalary> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            EmpSalary dto = new EmpSalary(rst.getString(1), rst.getDate(2).toLocalDate(),
                    rst.getInt(3));
            empList.add(dto);
        }

        return empList;

    }


    public int totalSalaryEmp(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql =  "SELECT SUM(price) FROM employesalary WHERE empId = ? AND date BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,startDate);
        statement.setString(3,endDate);


        ResultSet rst = statement.executeQuery();
        int salary;
        if(rst.next()){
            salary = rst.getInt(1);
            return salary;
        }
        return 0;
    }

    public List<EmpSalary> getEmpTotPayPrice(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employesalary WHERE empId = ? AND date BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,startDate);
        statement.setString(3,endDate);

        List<EmpSalary> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            EmpSalary dto = new EmpSalary(rst.getString(1), rst.getDate(2).toLocalDate(),
                    rst.getInt(3));
            empList.add(dto);
        }


        return empList;
    }

    public int getSalaryAll(String startDate, String endDate) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT SUM(price) FROM employesalary WHERE date BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,startDate);
        statement.setString(2,endDate);


        ResultSet rst = statement.executeQuery();

        int price = 0;

        if (rst.next()){
            price = rst.getInt(1);
        }
        return price;
    }
}
