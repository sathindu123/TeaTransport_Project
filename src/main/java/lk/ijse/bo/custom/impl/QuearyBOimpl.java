package lk.ijse.bo.custom.impl;

import javafx.scene.control.skin.SliderSkin;
import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.InvoiceManageDAO;
import lk.ijse.Dao.custom.QueryDAO;
import lk.ijse.bo.custom.QuearyBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;
import lk.ijse.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QuearyBOimpl implements QuearyBO {
    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};



    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    //invoiceManage
    public List<AdvanceDto> loadA() throws SQLException, ClassNotFoundException {
        List<Advance> ar = queryDAO.loadA();

        List<AdvanceDto> dtoList = new ArrayList<>();

        for (Advance rate : ar) {
            AdvanceDto dto = new AdvanceDto(rate.getCustId(), rate.getName(),rate.getDate(),rate.getPrice());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<ViewEmpWorkDetailsDto> loadtblEmp() throws SQLException, ClassNotFoundException {

        List<ViewEmpWorkDetails> ar = queryDAO.loadtblEmp();

        List<ViewEmpWorkDetailsDto> dtoList = new ArrayList<>();

        for (ViewEmpWorkDetails rate : ar) {
            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(rate.getId(), rate.getName(),rate.getDate());
            dtoList.add(dto);
        }

        return dtoList;

    }

    public int getCountEmp() throws SQLException, ClassNotFoundException {

        return queryDAO.getCountEmp();

    }

    public List<ViewEmpWorkDetailsDto> searchEmpId(String id) throws SQLException, ClassNotFoundException {

        List<ViewEmpWorkDetails> ar = queryDAO.searchEmpId(id);

        List<ViewEmpWorkDetailsDto> dtoList = new ArrayList<>();
        for (ViewEmpWorkDetails rate : ar) {
            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(rate.getId(), rate.getName(),rate.getDate());
            dtoList.add(dto);
        }

        return dtoList;
    }



    public int searchEmpTblCount(String id) throws SQLException, ClassNotFoundException {
        return queryDAO.searchEmpTblCount(id);
    }

    public List<InvoiceCustomerDto> getDetailsPurchase(String id) throws SQLException, ClassNotFoundException {

        List<InvoiceCustomer> ar = queryDAO.getDetailsPurchase(id);

        List<InvoiceCustomerDto> dtoList = new ArrayList<>();

        for (InvoiceCustomer rate : ar) {
            InvoiceCustomerDto dto = new InvoiceCustomerDto(rate.getTeaPacketTotal(), rate.getFertilizeTotal(), rate.getOtherTotal());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<ViewManageDto> getStockPurchases() throws SQLException, ClassNotFoundException {

        List<ViewManage> ar = queryDAO.getStockPurchases();

        List<ViewManageDto> dtoList = new ArrayList<>();

        for (ViewManage rate : ar) {
            ViewManageDto dto = new ViewManageDto(rate.getId(), rate.getName(), rate.getDate()
            ,rate.getProductId(),rate.getQuntity(),rate.getTotalPrice());
            dtoList.add(dto);
        }
        return dtoList;

    }

    public List<ViewManageDto> getPohoraStockPurchase() throws SQLException, ClassNotFoundException {
        List<ViewManage> ar = queryDAO.getPohoraStockPurchase();

        List<ViewManageDto> dtoList = new ArrayList<>();

        for (ViewManage rate : ar) {
            ViewManageDto dto = new ViewManageDto(rate.getId(), rate.getName(), rate.getDate()
                    ,rate.getProductId(),rate.getQuntity(),rate.getTotalPrice());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public int getTot() throws SQLException, ClassNotFoundException {
        return queryDAO.getTot();
    }

    public List<ViewEmpWorkDetailsDto> loadEmp() throws SQLException, ClassNotFoundException {

        List<ViewEmpWorkDetails> ar = queryDAO.loadEmp();

        List<ViewEmpWorkDetailsDto> dtoList = new ArrayList<>();

        for (ViewEmpWorkDetails rate : ar) {
            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(
                    rate.getId(),
                    rate.getName(),
                    rate.getAddress(),
                    rate.getTel(),
                    rate.getDate()
            );
            dtoList.add(dto);
        }
        return dtoList;

    }

    public List<InvoiceManageDto> getAllAdvance() throws SQLException, ClassNotFoundException {

        List<InvoiceManage> ar = queryDAO.getAllAdvance();

        List<InvoiceManageDto> dtoList = new ArrayList<>();

        for (InvoiceManage rate : ar) {
            InvoiceManageDto dto = new InvoiceManageDto(
                    rate.getCustId(),
                    rate.getCustName(),
                    rate.getDate(),
                    rate.getPrice(),
                    rate.getMonthPrice()
            );
            dtoList.add(dto);
        }
        return dtoList;

    }

    public List<InvoiceManageDto> ViewManageSearchAdvance(String id) throws SQLException, ClassNotFoundException {
        List<InvoiceManage> ar = queryDAO.ViewManageSearchAdvance(id);

        List<InvoiceManageDto> dtoList = new ArrayList<>();

        for (InvoiceManage rate : ar) {
            InvoiceManageDto dto = new InvoiceManageDto(
                    rate.getCustId(),
                    rate.getCustName(),
                    rate.getDate(),
                    rate.getPrice(),
                    rate.getMonthPrice()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<InvoiceManageDto> searchDateDetailsAdvance(String stDate, String enDate) throws SQLException, ClassNotFoundException {
        List<InvoiceManage> ar = queryDAO.searchDateDetailsAdvance(stDate,enDate);

        List<InvoiceManageDto> dtoList = new ArrayList<>();

        for (InvoiceManage rate : ar) {
            InvoiceManageDto dto = new InvoiceManageDto(
                    rate.getCustId(),
                    rate.getCustName(),
                    rate.getDate(),
                    rate.getPrice(),
                    rate.getMonthPrice()
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<getCustomerAllDetailsDto> getAllCustomerDetails() throws SQLException, ClassNotFoundException {

        List<getCustomerAllDetails> ar = queryDAO.getAllCustomerDetails();

        List<getCustomerAllDetailsDto> dtoList = new ArrayList<>();

        for (getCustomerAllDetails rate : ar) {
            getCustomerAllDetailsDto dto = new getCustomerAllDetailsDto(
                    rate.getId(),
                    rate.getName(),
                    rate.getTeaLeafAmount(),
                    rate.getAdvancePurchase(),
                    rate.getTeaPacketPurchase(),
                    rate.getFertilizePurchase(),
                    rate.getOtherPurchase()
            );
            dtoList.add(dto);
        }
        return dtoList;

    }



    public List<getCustomerAllDetailsDto> ViewManageSearchCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT \n" +
                "    c.id, \n" +
                "    c.name, \n" +
                "    COALESCE(t.totalLeafAmount, 0) AS totalLeafAmount,  \n" +
                "    COALESCE(a.totalAdvance, 0) AS totalAdvance, \n" +
                "    COALESCE(p.teaPacketTotal, 0) AS teaPacketTotal,  \n" +
                "    COALESCE(p.fertilizeTotal, 0) AS fertilizeTotal,  \n" +
                "    COALESCE(p.otherTotal, 0) AS otherTotal  \n" +
                "FROM \n" +
                "    customer c\n" +
                "LEFT JOIN \n" +
                "    (SELECT \n" +
                "        custId, \n" +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'P' THEN totalPrice ELSE 0 END) AS fertilizeTotal,\n" +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'T' THEN totalPrice ELSE 0 END) AS teaPacketTotal,\n" +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'M' THEN totalPrice ELSE 0 END) AS otherTotal\n" +
                "     FROM productpurchasecustomer\n" +
                "     GROUP BY custId) p ON c.id = p.custId\n" +
                "LEFT JOIN \n" +
                "    (SELECT custId, (SUM(goldLeafAmount) + SUM(goodLeafAmount)) AS totalLeafAmount\n" +
                "     FROM teabaginventory\n" +
                "     GROUP BY custId) t ON c.id = t.custId\n" +
                "LEFT JOIN \n" +
                "    (SELECT custId, SUM(price) AS totalAdvance \n" +
                "     FROM advance \n" +
                "     GROUP BY custId) a ON c.id = a.custId\n" +
                "WHERE c.id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, id);
        ResultSet rst = statement.executeQuery();

        List<getCustomerAllDetailsDto> custDtos = new ArrayList<>();

        while (rst.next()) {

            getCustomerAllDetailsDto dto = new getCustomerAllDetailsDto(
                    rst.getString("id"),         // Customer ID
                    rst.getString("name"),       // Customer Name
                    rst.getInt("totalLeafAmount"),  // Total Leaf Amount
                    rst.getInt("totalAdvance"),     // Total Advance
                    rst.getDouble("teaPacketTotal"), // Tea Packet Total
                    rst.getDouble("fertilizeTotal"), // Fertilize Total
                    rst.getDouble("otherTotal")      // Other Total
            );
            custDtos.add(dto);
        }

        return custDtos;
    }

    public List<getCustomerAllDetailsDto> selectDateCust(String strDate, String endDate) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();


        String sql = "SELECT \n" +
                "    c.id, \n" +
                "    c.name, \n" +
                "    COALESCE(t.totalLeafAmount, 0) AS totalLeafAmount,  \n" +
                "    COALESCE(a.totalAdvance, 0) AS totalAdvance, \n" +
                "    COALESCE(p.teaPacketTotal, 0) AS teaPacketTotal,  \n" +
                "    COALESCE(p.fertilizeTotal, 0) AS fertilizeTotal,  \n" +
                "    COALESCE(p.otherTotal, 0) AS otherTotal  \n" +
                "FROM \n" +
                "    customer c\n" +
                "LEFT JOIN \n" +
                "    (SELECT \n" +
                "        custId, \n" +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'P' THEN totalPrice ELSE 0 END) AS fertilizeTotal,\n" +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'T' THEN totalPrice ELSE 0 END) AS teaPacketTotal,\n" +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'M' THEN totalPrice ELSE 0 END) AS otherTotal\n" +
                "     FROM productpurchasecustomer\n" +
                "     WHERE date BETWEEN ? AND ? \n" +
                "     GROUP BY custId) p ON c.id = p.custId\n" +
                "LEFT JOIN \n" +
                "    (SELECT custId, (SUM(goldLeafAmount) + SUM(goodLeafAmount)) AS totalLeafAmount\n" +
                "     FROM teabaginventory\n" +
                "     WHERE date BETWEEN ? AND ? \n" +
                "     GROUP BY custId) t ON c.id = t.custId\n" +
                "LEFT JOIN \n" +
                "    (SELECT custId, SUM(price) AS totalAdvance \n" +
                "     FROM advance \n" +
                "     WHERE date BETWEEN ? AND ? \n" +
                "     GROUP BY custId) a ON c.id = a.custId";

        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, strDate);
        statement.setString(2, endDate);
        statement.setString(3, strDate);
        statement.setString(4, endDate);
        statement.setString(5, strDate);
        statement.setString(6, endDate);
        ResultSet rst = statement.executeQuery();

        List<getCustomerAllDetailsDto> custDtos = new ArrayList<>();

        while (rst.next()) {

            getCustomerAllDetailsDto dto = new getCustomerAllDetailsDto(
                    rst.getString("id"),         // Customer ID
                    rst.getString("name"),       // Customer Name
                    rst.getInt("totalLeafAmount"),  // Total Leaf Amount
                    rst.getInt("totalAdvance"),     // Total Advance
                    rst.getDouble("teaPacketTotal"), // Tea Packet Total
                    rst.getDouble("fertilizeTotal"), // Fertilize Total
                    rst.getDouble("otherTotal")      // Other Total
            );
            custDtos.add(dto);
        }

        return custDtos;
    }

    public List<ViewEmpWorkDetailsDto> searchDateDetailsEMP(String strDate, String endDate) throws SQLException, ClassNotFoundException {

        List<ViewEmpWorkDetails> ar = queryDAO.searchDateDetailsEMP(strDate,endDate);

        List<ViewEmpWorkDetailsDto> dtoList = new ArrayList<>();
        for (ViewEmpWorkDetails rate : ar) {
            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(rate.getId(), rate.getName(),rate.getAddress(),rate.getTel(),rate.getDate());
            dtoList.add(dto);
        }

        return dtoList;

    }


    public List<ViewEmpWorkDetailsDto> searchEmployes(String id) throws SQLException, ClassNotFoundException {

        List<ViewEmpWorkDetails> ar = queryDAO.searchEmployes(id);

        List<ViewEmpWorkDetailsDto> dtoList = new ArrayList<>();
        for (ViewEmpWorkDetails rate : ar) {
            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(rate.getId(), rate.getName(),rate.getAddress(),rate.getTel(),rate.getDate());
            dtoList.add(dto);
        }

        return dtoList;

    }

    public int searchEmpCount(String id) throws SQLException, ClassNotFoundException {
        return queryDAO.searchEmpCount(id);
    }

}
