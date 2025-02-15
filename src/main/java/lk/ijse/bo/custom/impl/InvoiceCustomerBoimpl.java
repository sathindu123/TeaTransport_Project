package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.InvoiceCustomerDAO;
import lk.ijse.bo.custom.InvoiceCustomerBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.InvoiceCustomerDto;
import lk.ijse.entity.InvoiceCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InvoiceCustomerBoimpl implements InvoiceCustomerBO {
    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};

    InvoiceCustomerDAO invoiceCustomerDAO = (InvoiceCustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVOICECUSTOMER);

    public List<InvoiceCustomerDto> getTeLeaf(String id) throws SQLException, ClassNotFoundException {

        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";
        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        List<InvoiceCustomer> ar = invoiceCustomerDAO.getTeLeaf(id,startDate,endDate);

        List<InvoiceCustomerDto> dtoList = new ArrayList<>();

        for (InvoiceCustomer rate : ar) {
            InvoiceCustomerDto dto = new InvoiceCustomerDto(rate.getPrice(), rate.getPrice1());
            dtoList.add(dto);
        }
        return dtoList;



    }

    public List<InvoiceCustomerDto> customeTealeafDateGet(String id) throws SQLException, ClassNotFoundException {

        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";
        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        List<InvoiceCustomer> ar = invoiceCustomerDAO.customeTealeafDateGet(id,startDate,endDate);

        List<InvoiceCustomerDto> dtoList = new ArrayList<>();

        for (InvoiceCustomer rate : ar) {
            InvoiceCustomerDto dto = new InvoiceCustomerDto(rate.getDate(), rate.getPrice());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public String getCustomerName(String id) throws SQLException, ClassNotFoundException {
        return invoiceCustomerDAO.getCustomerName(id);
    }
//    public List<InvoiceCustomerDto> getDetailsPurchase(String id) throws SQLException, ClassNotFoundException {
//
//        LocalDateTime now = LocalDateTime.now();
//
//
//        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
//            now = now.minusMonths(1);
//            if (now.getDayOfMonth() == 1) {
//                now = now.minusYears(1);
//            }
//        }
//
//
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        String startDate = now.format(dateFormatter) + "-01";
//
//
//        now = now.plusMonths(1);
//        String endDate = now.format(dateFormatter) + "-10";
//
//
//        Connection connection = DBConnection.getInstance().getConnection();
//
//
//        String sql = "SELECT " +
//                "    COALESCE(p.teaPacketTotal, 0) AS teaPacketTotal, " +
//                "    COALESCE(p.fertilizeTotal, 0) AS fertilizeTotal, " +
//                "    COALESCE(p.otherTotal, 0) AS otherTotal " +
//                "FROM customer c " +
//                "LEFT JOIN (" +
//                "    SELECT " +
//                "        custId, " +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'P' THEN totalPrice ELSE 0 END) AS fertilizeTotal, " +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'T' THEN totalPrice ELSE 0 END) AS teaPacketTotal, " +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'M' THEN totalPrice ELSE 0 END) AS otherTotal " +
//                "    FROM productpurchasecustomer " +
//                "    WHERE date BETWEEN ? AND ? " + // Filter dates within the subquery
//                "    GROUP BY custId" +
//                ") p ON c.id = p.custId " +
//                "WHERE c.id = ?;";
//
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//
//        statement.setString(1, startDate); // Start date for the subquery
//        statement.setString(2, endDate);   // End date for the subquery
//        statement.setString(3, id);        // Customer ID
//
//
//        ResultSet rst = statement.executeQuery();
//        List<InvoiceCustomerDto> custDtos = new ArrayList<>();
//
//        while (rst.next()) {
//            InvoiceCustomerDto dto = new InvoiceCustomerDto(
//                    rst.getDouble("teaPacketTotal"), // Tea Packet Total
//                    rst.getDouble("fertilizeTotal"), // Fertilize Total
//                    rst.getDouble("otherTotal")     // Other Total
//            );
//            custDtos.add(dto);
//        }
//
//
//
//        return custDtos;
//    }

//    public List<MonthlyRateDto> getRates(String date) throws SQLException, ClassNotFoundException {
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT goldrate,goodrate FROM monthlyRate WHERE month = ? ";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        statement.setString(1,date);
//
//        List<MonthlyRateDto> list = new ArrayList<>();
//        ResultSet rst = statement.executeQuery();
//        while (rst.next()){
//            MonthlyRateDto dto = new MonthlyRateDto(rst.getDouble("goldrate")
//                    ,rst.getDouble("goodrate"));
//
//            list.add(dto);
//        }
//        return list;
//
//    }


    public double getAdvanceCustomer(String id, String month) throws SQLException, ClassNotFoundException {
       return invoiceCustomerDAO.getAdvanceCustomer(id,month);
    }

    public double nextAdvanceTotal(String id,String month) throws SQLException, ClassNotFoundException {
        return invoiceCustomerDAO.nextAdvanceTotal(id,month);
    }


    public double getPohoraCountCustomer(String id, String month) throws SQLException, ClassNotFoundException {
       return invoiceCustomerDAO.getPohoraCountCustomer(id,month);
    }

    public double nextPohoraTotal(String id, String month) throws SQLException, ClassNotFoundException {
        return invoiceCustomerDAO.nextPohoraTotal(id,month);
    }

    public double getGiyamasaHiga(String id, String month) throws SQLException, ClassNotFoundException {
        return invoiceCustomerDAO.getGiyamasaHiga(id,month);
    }


    public List<String> getAllProductId() throws SQLException, ClassNotFoundException {
        return invoiceCustomerDAO.getAllProductId();
    }

    public List<String> getAllId() throws SQLException, ClassNotFoundException {
        return invoiceCustomerDAO.getAllId();
    }


    public String cheackCustomerId(String id,String date) throws SQLException, ClassNotFoundException {
        return invoiceCustomerDAO.cheackCustomerId(id,date);
    }

    public List<String> loadProductId() throws SQLException, ClassNotFoundException {
        return invoiceCustomerDAO.loadProductId();
    }

    ////////////////////////////dailyHomagePage///////////////////////


    public List<InvoiceCustomerDto> getAllTeaLeafCount() throws SQLException, ClassNotFoundException {

        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";
        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        List<InvoiceCustomer> ar = invoiceCustomerDAO.getAllTeaLeafCount(startDate,endDate);
        List<InvoiceCustomerDto> dtoList = new ArrayList<>();

        for (InvoiceCustomer rate : ar) {
            InvoiceCustomerDto dto = new InvoiceCustomerDto(rate.getPrice(), rate.getPrice1());
            dtoList.add(dto);
        }

        return dtoList;

    }


}
