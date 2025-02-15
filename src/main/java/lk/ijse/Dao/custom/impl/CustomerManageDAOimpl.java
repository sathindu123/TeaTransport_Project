package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.CustomerManageDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.AdvanceDto;
import lk.ijse.dto.CustomerManageDto;
import lk.ijse.dto.ViewManageDto;
import lk.ijse.entity.CustomerManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerManageDAOimpl implements CustomerManageDAO {
    public String save(CustomerManage customerManageDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Insert into customer Values(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,customerManageDto.getId());
        statement.setString(2,customerManageDto.getName());
        statement.setString(3,customerManageDto.getAddress());
        statement.setInt(4,customerManageDto.getNumber());

        int resp = statement.executeUpdate();
        return resp > 0 ? "sucsess" : "fail";

    }

    public String update(CustomerManage customerManageDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Update customer SET name = ? , address = ? , Telephone_Nb = ? WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,customerManageDto.getName());
        statement.setString(2,customerManageDto.getAddress());
        statement.setInt(3,customerManageDto.getNumber());
        statement.setString(4,customerManageDto.getId());

        int result = statement.executeUpdate();
        return result > 0 ? "Successfully Updated" : "Fail";

    }


    public String delete(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "delete from customer where id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, id);

        int result = statement.executeUpdate();
        return result > 0 ? "Successfully Delete" : "Fail";
    }

//    public CustomerManageDto custSerachsummery(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        // Added WHERE clause to filter by customer id
//        String sql = "SELECT a.custId, c.name, a.date, a.price " +
//        "FROM customer c " +
//        "JOIN advance a ON c.id = a.custId " +
//        "WHERE c.id = ? " +
//        "ORDER BY a.date";
//
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, id);
//
//        List<CustomerManageDto> custDtos = new ArrayList<>();
//        ResultSet rst = statement.executeQuery();
//
//        while (rst.next()) {
//
//            CustomerManageDto customerDto = new CustomerManageDto(rst.getString(1), rst.getString(2));
//            AdvanceDto ado = new AdvanceDto(rst.getString(1), rst.getString(3), rst.getDouble(4), customerDto);
//            customerDto.setAdvanceDto(ado);
//            custDtos.add(customerDto);
//        }
//
//        return custDtos.isEmpty() ? null : custDtos.get(0);
//    }



//    public List<ViewManageDto> getStockPurchases() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT c.id,c.name,p.date,p.productId,p.quntity,p.totalPrice FROM customer c JOIN \n" +
//                "                productpurchasecustomer p ON c.id = p.custId " +
//                "                ORDER BY p.date";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        List<ViewManageDto> custDtos = new ArrayList<>();
//        ResultSet rst = statement.executeQuery();
//
//        while (rst.next()){
//            ViewManageDto dto = new ViewManageDto(rst.getString(1),
//                    rst.getString(2),rst.getString(3),
//                    rst.getString(4),rst.getInt(5),rst.getDouble(6));
//
//            custDtos.add(dto);
//        }
//            return custDtos;
//    }

//    public int getTot() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT SUM(p.totalPrice) FROM customer c JOIN \n" +
//                "                productpurchasecustomer p ON c.id = p.custId ";
//
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        ResultSet rst = statement.executeQuery();
//
//        int price = 0;
//        if(rst.next()){
//            price = rst.getInt(1);
//        }
//
//        return price;
//    }


    public List<String> getAllID() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT id FROM customer";

        PreparedStatement statement = connection.prepareStatement(sql);
        List<String> custDtos = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            String id = rst.getString("id");
            custDtos.add(id);
        }
        return custDtos;
    }

//    public List<ViewManageDto> getPohoraStockPurchase() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT c.id,c.name,p.date,p.productId,p.quntity,SUM(p.monthPrice) as totalPrice FROM customer c JOIN \n" +
//                "                               pohorapurchasecustomer p ON c.id = p.custId GROUP BY c.id\n" +
//                "                              ORDER BY p.date\n";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        List<ViewManageDto> custDtos = new ArrayList<>();
//        ResultSet rst = statement.executeQuery();
//
//        while (rst.next()){
//            ViewManageDto dto = new ViewManageDto(rst.getString(1),
//                    rst.getString(2),rst.getString(3),
//                    rst.getString(4),rst.getInt(5),rst.getDouble(6));
//
//            custDtos.add(dto);
//        }
//        return custDtos;
//    }

    ////////////////////////////////////////////////

    public boolean checkCustomerExists(String custId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) FROM customer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, custId);
        ResultSet rst = statement.executeQuery();

        if (rst.next()) {
            return rst.getInt(1) > 0;
        }
        return false;
    }

    public String getNam(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT name FROM customer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet rst = statement.executeQuery();

        String name = "";
        if (rst.next()) {
            name = rst.getString("name");
        }
        return name;

    }










}

