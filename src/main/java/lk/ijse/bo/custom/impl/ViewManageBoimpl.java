package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.ViewManageDAO;
import lk.ijse.bo.custom.ViewManageBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmpSalaryDto;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.EmpSalary;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewManageBoimpl implements ViewManageBO {

    ViewManageDAO viewManageDAO = (ViewManageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VIEWMANAGE);

    public List<StockDto> getAllStocks() throws SQLException, ClassNotFoundException {

        List<Stock> ar = viewManageDAO.getAllStocks();

        List<StockDto> dtoList = new ArrayList<>();

        for (Stock rate : ar) {
            StockDto dto = new StockDto(rate.getId(),rate.getCategory(),rate.getCount(),rate.getPrice());
            dtoList.add(dto);
        }

        return dtoList;

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
        return viewManageDAO.getTotalAdvance();
    }

    public double getTotalAdvanceCustomer(String id) throws SQLException, ClassNotFoundException {
        return viewManageDAO.getTotalAdvanceCustomer(id);
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
        Stock st = viewManageDAO.ViewManageSearchStock(id);

        if (st != null) {
            return new StockDto(
                    st.getId(),
                    st.getCategory(),
                    st.getCount(),
                    st.getPrice()
            );
        } else {
            return null;
        }
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
       return viewManageDAO.getTotalAdvanceSelectDate(strDate,endDate);
    }


}
