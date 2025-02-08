package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.InvoiceCustomerDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.InvoiceCustomerDto;
import lk.ijse.dto.MonthlyRateDto;
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

public class InvoiceCustomerDAOimpl implements InvoiceCustomerDAO {
    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};

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


        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT SUM(goldLeafAmount) , SUM(goodLeafAmount) " +
                "FROM teabaginventory WHERE custId = ?" +
                "AND date BETWEEN ? AND ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,id);
        statement.setString(2,startDate);
        statement.setString(3,endDate);

        ResultSet resultSet = statement.executeQuery();

        List<InvoiceCustomerDto> dtoList = new ArrayList<>();
        while (resultSet.next()) {
            InvoiceCustomerDto dto = new InvoiceCustomerDto(resultSet.getInt(1),resultSet.getInt(2));
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


        Connection connection = DBConnection.getInstance().getConnection();


        String sql = "SELECT date, SUM(goldLeafAmount + goodLeafAmount) AS totalPrice " +
                "FROM teabaginventory WHERE custId = ? " +
                "AND date BETWEEN ? AND ? GROUP BY date";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        statement.setString(2,startDate);
        statement.setString(3,endDate);

        ResultSet resultSet = statement.executeQuery();

        List<InvoiceCustomerDto> dtoList = new ArrayList<>();
        while (resultSet.next()) {
            String date = resultSet.getString("date");
            int totalPrice = resultSet.getInt("totalPrice");

            // Create a DTO object to hold the result
            InvoiceCustomerDto dto = new InvoiceCustomerDto(date, totalPrice);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public String getCustomerName(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT name FROM customer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);

        ResultSet rst = statement.executeQuery();
        String name = "";
        if (rst.next()){
            name = rst.getString(1);
        }
        return name;
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
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT SUM(monthPrice) AS monthPrice FROM advance WHERE custId = ? AND month = ? group by custId";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,month);

        ResultSet rst = statement.executeQuery();
        double ad = 0;

        if(rst.next()){
            ad = rst.getDouble("monthPrice");
        }
       return ad;
    }

    public double nextAdvanceTotal(String id,String month) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT SUM(monthPrice) AS monthPrice FROM advance WHERE custId = ? AND month != ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,month);

        ResultSet rst = statement.executeQuery();
        double ad = 0;

        if(rst.next()){
            ad = rst.getDouble("monthPrice");
        }
        return ad;
    }


    public double getPohoraCountCustomer(String id, String month) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT SUM(monthPrice) AS monthPrice FROM pohorapurchasecustomer WHERE custId = ? AND month = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,month);

        ResultSet rst = statement.executeQuery();
        double ad = 0;

        if(rst.next()){
            ad = rst.getDouble("monthPrice");
        }
        return ad;
    }

    public double nextPohoraTotal(String id, String month) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT SUM(monthPrice) AS monthPrice FROM pohorapurchasecustomer WHERE custId = ? AND month != ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,month);

        ResultSet rst = statement.executeQuery();
        double ad = 0;

        if(rst.next()){
            ad = rst.getDouble("monthPrice");
        }
        return ad;
    }

    public double getGiyamasaHiga(String id, String month) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT price FROM customerhigaprice WHERE custId = ? and month = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,month);

        ResultSet rst = statement.executeQuery();
        double ad = 0;

        if(rst.next()){
            ad = rst.getDouble("price");
        }
        return ad;
    }


    public List<String> getAllProductId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT id FROM product";

        PreparedStatement statement = connection.prepareStatement(sql);
        List<String> custDtos = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            String id = rst.getString("id");
            custDtos.add(id);
        }
        return custDtos;
    }

    public List<String> getAllId() throws SQLException, ClassNotFoundException {
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


    public String cheackCustomerId(String id,String date) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT custId from payments WHERE custId = ? AND date = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,id);
        statement.setString(2,date);
        ResultSet resultSet = statement.executeQuery();
        String ssl = "";
        while (resultSet.next()){
            ssl = resultSet.getString("custId");
        }
        return ssl;
    }

    public List<String> loadProductId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT id from product";
        PreparedStatement statement = connection.prepareStatement(sql);
        List<String> mk = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            mk = Collections.singletonList(resultSet.getString(1));
        }
        return mk;
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


        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT SUM(goldLeafAmount) , SUM(goodLeafAmount) " +
                "FROM teabaginventory WHERE " +
                "date BETWEEN ? AND ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,startDate);
        statement.setString(2,endDate);

        ResultSet resultSet = statement.executeQuery();

        List<InvoiceCustomerDto> dtoList = new ArrayList<>();
        while (resultSet.next()) {
            InvoiceCustomerDto dto = new InvoiceCustomerDto(resultSet.getInt(1),resultSet.getInt(2));
            dtoList.add(dto);
        }

        return dtoList;

    }


    @Override
    public String save(InvoiceCustomer invoiceCustomer) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String update(InvoiceCustomer invoiceCustomer) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }
}
