package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.ViewCustomerFormDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.CustomerManageDto;
import lk.ijse.entity.CustomerManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewCustomerFormDAOimpl implements ViewCustomerFormDAO {
    public List<CustomerManage> getAllCustomer() throws  Exception{
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "Select * from customer";

        PreparedStatement statement = connection.prepareStatement(sql);

        List<CustomerManage> customerDtos = new ArrayList<>();

        ResultSet rst = statement.executeQuery();
        while(rst.next()){
            CustomerManage dto = new CustomerManage(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getInt(4));
            customerDtos.add(dto);
        }
        return customerDtos;
    }

    public CustomerManage searchCustomer(String id) throws Exception{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);

        ResultSet rst = statement.executeQuery();
        if(rst.next()){
            CustomerManage dto = new CustomerManage(rst.getString(1),
                    rst.getString(2), rst.getString(3),
                    rst.getInt(4));

            return  dto;
        }

        return null;
    }

    public List<String> searchCus(String typedText) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT name FROM customer WHERE name LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, typedText + "%"); // Use a wildcard to match names starting with the typed text
        ResultSet resultSet = statement.executeQuery();

        List<String> suggestions = new ArrayList<>();
        while (resultSet.next()) {
            suggestions.add(resultSet.getString("name")); // Add each matching name to the list
        }
        return suggestions; // Return all matching names
    }

    @Override
    public String save(CustomerManage customerManage) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String update(CustomerManage customerManage) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }
}
