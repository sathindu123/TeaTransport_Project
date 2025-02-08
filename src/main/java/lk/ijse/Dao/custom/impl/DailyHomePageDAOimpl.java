package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.DailyHomePageDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.DailyHomePageDto;
import lk.ijse.dto.InvoiceCustomerDto;
import lk.ijse.entity.DailyHomePage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DailyHomePageDAOimpl implements DailyHomePageDAO {
    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};

    public String save(DailyHomePage dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "INSERT INTO teaBagInventory  VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, dto.getId());
        statement.setString(2, dto.getDate());
        statement.setInt(3, dto.getGoldLeaf());
        statement.setInt(4, dto.getGoodLeaf());

        int resp = statement.executeUpdate();
        return resp > 0 ? "success" : "fail";
    }

//    public boolean checkCustomerExists(String custId) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT COUNT(*) FROM customer WHERE id = ?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, custId);
//        ResultSet rst = statement.executeQuery();
//
//        if (rst.next()) {
//            return rst.getInt(1) > 0; // Returns true if customer exists
//        }
//        return false; // Returns false if customer does not exist
//    }

//    public String getNam(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT name FROM customer WHERE id = ?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1, id);
//        ResultSet rst = statement.executeQuery();
//
//        String name = "";
//        if (rst.next()) {
//            name = rst.getString("name");
//        }
//       return name;
//
//    }

//    public List<DailyHomePageDto> LoadTable() throws SQLException, ClassNotFoundException {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String date = LocalDateTime.now().format(dateTimeFormatter);
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "SELECT c.custId,d.name,c.date,c.goldLeafAmount,c.goodLeafAmount FROM teaBagInventory c JOIN customer d ON c.custId = d.id WHERE date = ?";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1,date );
//        List<DailyHomePageDto> Dtos = new ArrayList<>();
//        ResultSet rst = statement.executeQuery();
//
//
//        String name = "";
//        while (rst.next()) {
//            DailyHomePageDto dto = new DailyHomePageDto(rst.getString("custId"),rst.getString("name"),rst.getString("date"),
//                    rst.getInt("goldLeafAmount"),rst.getInt("goodLeafAmount"));
//            Dtos.add(dto);
//        }
//        return Dtos;
//    }

    public int getCount() throws SQLException, ClassNotFoundException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDateTime.now().format(dateTimeFormatter);
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT (SUM(goldLeafAmount) + SUM(goodLeafAmount)) AS totalLeafAmount FROM teaBagInventory WHERE date = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, date);
        ResultSet rst = statement.executeQuery();

       int count = 0;
        if (rst.next()) {
            count = rst.getInt(1);
        }
        return count;
    }

    public String update(DailyHomePage dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM teaBagInventory  WHERE custId = ? AND date = ?";
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, dto.getId());
        statement.setString(2, dto.getDate());

        int resp = statement.executeUpdate();
        return resp > 0 ? "success" : "fail";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }

//    public double getTotalAdvance() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "select SUM(monthPrice)as monthPrice from advance";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//
//        ResultSet rst = statement.executeQuery();
//        double price = 0;
//        if(rst.next()){
//            price = rst.getDouble("monthPrice");
//        }
//        return price;
//    }

    public int getTeaLeafCoutn(String[] ar) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select SUM(goldLeafAmount+goodLeafAmount)as amount FROM teabaginventory WHERE date BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,ar[0]);
        statement.setString(2,ar[1]);

        ResultSet rst = statement.executeQuery();

        int pric = 0;
        if(rst.next()){
            pric = rst.getInt("amount");
        }

        return pric;
    }

    public double getPohoraTotal(String[] ar) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select SUM(monthPrice)as amount FROM pohorapurchasecustomer";
        PreparedStatement statement = connection.prepareStatement(sql);


        ResultSet rst = statement.executeQuery();

        int pric = 0;
        if(rst.next()){
            pric = rst.getInt("amount");
        }

        return pric;
    }

    public double getOtherTotal() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select SUM(totalPrice)as amount FROM productpurchasecustomer";
        PreparedStatement statement = connection.prepareStatement(sql);


        ResultSet rst = statement.executeQuery();

        int pric = 0;
        if(rst.next()){
            pric = rst.getInt("amount");
        }

        return pric;
    }

//
//    public List<InvoiceCustomerDto> getAllTeaLeafCount() throws SQLException, ClassNotFoundException {
//        LocalDateTime now = LocalDateTime.now();
//
//        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
//            now = now.minusMonths(1);
//            if (now.getDayOfMonth() == 1) {
//                now = now.minusYears(1);
//            }
//        }
//
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        String formattedDate = now.format(dateFormatter);
//
//        String startDate = formattedDate + "-01";
//        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT SUM(goldLeafAmount) , SUM(goodLeafAmount) " +
//                "FROM teabaginventory WHERE " +
//                "date BETWEEN ? AND ?";
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setString(1,startDate);
//        statement.setString(2,endDate);
//
//        ResultSet resultSet = statement.executeQuery();
//
//        List<InvoiceCustomerDto> dtoList = new ArrayList<>();
//        while (resultSet.next()) {
//            InvoiceCustomerDto dto = new InvoiceCustomerDto(resultSet.getInt(1),resultSet.getInt(2));
//            dtoList.add(dto);
//        }
//
//        return dtoList;
//
//    }



}
