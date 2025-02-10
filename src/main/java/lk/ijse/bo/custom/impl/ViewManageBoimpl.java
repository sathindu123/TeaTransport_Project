package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.custom.ViewManageDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewManageBoimpl implements ViewManageDAO {


    public List<StockDto> getAllStocks() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM product";

        PreparedStatement statement = connection.prepareStatement(sql);

        List<StockDto> stockDtos = new ArrayList<>();

        ResultSet rst = statement.executeQuery();
        while(rst.next()){
            StockDto dto = new StockDto(rst.getString(1),rst.getString(2),
                    rst.getInt(3),rst.getDouble(4));
            stockDtos.add(dto);
        }
        return stockDtos;
    }

//    public List<InvoiceManageDto> getAllAdvance() throws SQLException, ClassNotFoundException {
//
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT a.custId, c.name, a.date, a.price " +
//                    "FROM customer c " +
//                    "JOIN advance a ON c.id = a.custId " +
//                    "WHERE a.date GROUP BY a.date";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        List<InvoiceManageDto> advanceDtos = new ArrayList<>();
//
//        ResultSet rst = statement.executeQuery();
//        while (rst.next()) {
//            CustomerManageDto customerDto = new CustomerManageDto(
//                    rst.getString(1),
//                    rst.getString(2)
//            );
//
//            InvoiceManageDto dto = new InvoiceManageDto(
//                    rst.getString(1),
//                    rst.getString(3),
//                    rst.getDouble(4),
//                    customerDto
//            );
//
//            advanceDtos.add(dto);
//        }
//        return advanceDtos;
//    }

    public double getTotalAdvance() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT SUM(price) FROM advance";

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rst = statement.executeQuery();
        double totalPrice = 0.0;

        if (rst.next()) {
            totalPrice = rst.getDouble(1); // Get the total price from the result set
        }

        return totalPrice; // Return the total price
    }

    public double getTotalAdvanceCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT SUM(price) FROM advance WHERE custId = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);  // Bind the customer ID to the query

        ResultSet rst = statement.executeQuery();
        double totalPrice = 0.0;

        if (rst.next()) {
            totalPrice = rst.getDouble(1); // Get the total price from the result set
        }

        return totalPrice;
    }

//    public List<InvoiceManageDto> ViewManageSearchAdvance(String id) throws SQLException, ClassNotFoundException {
//
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT a.custId, c.name, a.date, a.price " +
//                "FROM customer c " +
//                "JOIN advance a ON c.id = a.custId " +
//                "WHERE a.custId = ? ";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1,id);
//
//
//        ResultSet rst = statement.executeQuery();
//        List<InvoiceManageDto> searchAdvanceDtos = new ArrayList<>();
//
//
//        while (rst.next()) {
//
//            CustomerManageDto customerDto = new CustomerManageDto(
//                    rst.getString(1),
//                    rst.getString(2)
//            );
//
//            InvoiceManageDto dto = new InvoiceManageDto(
//                    rst.getString(1),
//                    rst.getString(3),
//                    rst.getDouble(4),
//                    customerDto
//            );
//            searchAdvanceDtos.add(dto);
//        }
//        return searchAdvanceDtos;
//    }

    public StockDto ViewManageSearchStock(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM product WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);

        ResultSet rst = statement.executeQuery();

        if(rst.next()){
            StockDto dto = new StockDto(rst.getString(1),
                    rst.getString(2),rst.getInt(3),rst.getDouble(4));


            return  dto;
        }

        return null;
    }

//    public List<InvoiceManageDto> searchDateDetailsAdvance(String stDate, String enDate) throws SQLException, ClassNotFoundException {
//        Connection connectionc = DBConnection.getInstance().getConnection();
//        String sql = "SELECT a.custId, c.name, a.date, a.price " +
//                "FROM customer c " +
//                "JOIN advance a ON c.id = a.custId " +
//                "WHERE a.date BETWEEN ? AND ? " +
//                "ORDER BY a.date";
//        PreparedStatement statement = connectionc.prepareStatement(sql);
//
//        statement.setString(1, stDate);
//        statement.setString(2, enDate);
//
//        ResultSet rst = statement.executeQuery();
//        List<InvoiceManageDto> advanceDtos = new ArrayList<>();
//
//        while (rst.next()) {
//            CustomerManageDto customerDto = new CustomerManageDto(
//                    rst.getString(1),
//                    rst.getString(2)
//            );
//
//            InvoiceManageDto dto = new InvoiceManageDto(
//                    rst.getString(1),
//                    rst.getString(3),
//                    rst.getDouble(4),
//                    customerDto
//            );
//
//            advanceDtos.add(dto);
//        }
//
//        return advanceDtos;
//    }

    public double getTotalAdvanceSelectDate(String strDate, String endDate) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();;

        String sql = "SELECT SUM(price) FROM advance WHERE date BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, strDate);
        statement.setString(2, endDate);

        ResultSet rst = statement.executeQuery();
        double totalPrice = 0.0;

        if(rst.next()){
            totalPrice = rst.getDouble(1);
        }
        return totalPrice;
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
