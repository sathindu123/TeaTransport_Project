package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.custom.QueryDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QueryBoimpl implements QueryDAO {
    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};

    public CustomerManageDto custSerachsummery(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        // Added WHERE clause to filter by customer id
        String sql = "SELECT a.custId, c.name, a.date, a.price " +
                "FROM customer c " +
                "JOIN advance a ON c.id = a.custId " +
                "WHERE c.id = ? " +
                "ORDER BY a.date";


        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);

        List<CustomerManageDto> custDtos = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()) {

            CustomerManageDto customerDto = new CustomerManageDto(rst.getString(1), rst.getString(2));
            AdvanceDto ado = new AdvanceDto(rst.getString(1), rst.getString(3), rst.getDouble(4), customerDto);
            customerDto.setAdvanceDto(ado);
            custDtos.add(customerDto);
        }

        return custDtos.isEmpty() ? null : custDtos.get(0);
    }

    public List<ViewManageDto> getStockPurchases() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT c.id,c.name,p.date,p.productId,p.quntity,p.totalPrice FROM customer c JOIN \n" +
                "                productpurchasecustomer p ON c.id = p.custId " +
                "                ORDER BY p.date";

        PreparedStatement statement = connection.prepareStatement(sql);
        List<ViewManageDto> custDtos = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            ViewManageDto dto = new ViewManageDto(rst.getString(1),
                    rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getInt(5),rst.getDouble(6));

            custDtos.add(dto);
        }
        return custDtos;
    }

    public int getTot() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT SUM(p.totalPrice) FROM customer c JOIN \n" +
                "                productpurchasecustomer p ON c.id = p.custId ";


        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rst = statement.executeQuery();

        int price = 0;
        if(rst.next()){
            price = rst.getInt(1);
        }

        return price;
    }

    public List<ViewManageDto> getPohoraStockPurchase() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT c.id,c.name,p.date,p.productId,p.quntity,SUM(p.monthPrice) as totalPrice FROM customer c JOIN \n" +
                "                               pohorapurchasecustomer p ON c.id = p.custId GROUP BY c.id\n" +
                "                              ORDER BY p.date\n";

        PreparedStatement statement = connection.prepareStatement(sql);
        List<ViewManageDto> custDtos = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            ViewManageDto dto = new ViewManageDto(rst.getString(1),
                    rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getInt(5),rst.getDouble(6));

            custDtos.add(dto);
        }
        return custDtos;
    }

    //////DailyHomePage////

    public List<DailyHomePageDto> LoadTable() throws SQLException, ClassNotFoundException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDateTime.now().format(dateTimeFormatter);

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT c.custId,d.name,c.date,c.goldLeafAmount,c.goodLeafAmount FROM teaBagInventory c JOIN customer d ON c.custId = d.id WHERE date = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,date );
        List<DailyHomePageDto> Dtos = new ArrayList<>();
        ResultSet rst = statement.executeQuery();


        String name = "";
        while (rst.next()) {
            DailyHomePageDto dto = new DailyHomePageDto(rst.getString("custId"),rst.getString("name"),rst.getString("date"),
                    rst.getInt("goldLeafAmount"),rst.getInt("goodLeafAmount"));
            Dtos.add(dto);
        }
        return Dtos;
    }

    //////////////////////////getcustomerAllDetails/////////////////

    public List<getCustomerAllDetailsDto> getAllCustomerDetails() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        LocalDateTime now = LocalDateTime.now();

        // Adjust the date range to include the previous month's details if within the first 10 days of the current month
        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }
        String ss = "";
        DateTimeFormatter dateFormatter1 = DateTimeFormatter.ofPattern("MM");
        String formattedDate1 = now.format(dateFormatter1);
        int monthNumber = Integer.parseInt(formattedDate1);
        for (int i = 0; i < dateArray.length; i++) {
            if (monthNumber == i + 1) {
                System.out.println("pako");
                if (i + 1 == 12) {
                    ss = dateArray[11];
                } else {
                    ss = dateArray[i + 1];
                }
                break;
            }
        }
        DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("yyyy");
        String formattedDate2 = now.format(dateFormatter2);
        ss = ss+formattedDate2;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01"; // Start of the month
        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // End of the month

        now = now.plusMonths(1);
        DateTimeFormatter dateFormatter4 = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate4 = now.format(dateFormatter4);
        String endoDate = formattedDate4+"-10";


        String sql = "SELECT " +
                "    c.id, " +
                "    c.name, " +
                "    COALESCE(t.totalLeafAmount, 0) AS totalLeafAmount, " +
                "    COALESCE(a.totalAdvance, 0) AS totalAdvance, " +
                "    COALESCE(p.teaPacketTotal, 0) AS teaPacketTotal, " +
                "    COALESCE(f.fertilizeTotal, 0) AS fertilizeTotal, " +
                "    COALESCE(p.otherTotal, 0) AS otherTotal " +
                "FROM " +
                "    customer c " +
                "LEFT JOIN " +
                "    (SELECT " +
                "        custId, " +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'T' THEN totalPrice ELSE 0 END) AS teaPacketTotal, " +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'M' THEN totalPrice ELSE 0 END) AS otherTotal " +
                "     FROM productpurchasecustomer " +
                "          WHERE date BETWEEN ? AND ?" +
                "     GROUP BY custId) p ON c.id = p.custId " +
                "LEFT JOIN " +
                "    (SELECT " +
                "        custId, " +
                "        (SUM(goldLeafAmount) + SUM(goodLeafAmount)) AS totalLeafAmount " +
                "     FROM teabaginventory " +
                "     WHERE date BETWEEN ? AND ? " +
                "     GROUP BY custId) t ON c.id = t.custId " +
                "LEFT JOIN " +
                "    (SELECT custId, SUM(monthPrice) AS totalAdvance " +
                "     FROM advance " +
                "      where month = ? " +
                "     GROUP BY custId) a ON c.id = a.custId " +
                "LEFT JOIN " +
                "    (SELECT custId, SUM(monthPrice) AS fertilizeTotal " +
                "     FROM pohorapurchasecustomer " +
                "       where month = ?" +
                "     GROUP BY custId) f ON c.id = f.custId ";

        PreparedStatement statement = connection.prepareStatement(sql);

        // Set parameters for date range in the teabaginventory subquery
        statement.setString(1, startDate);
        statement.setString(2, endoDate);
        statement.setString(3, startDate);
        statement.setString(4, endDate);
        statement.setString(5, ss);
        statement.setString(6, ss);

        ResultSet rst = statement.executeQuery();

        List<getCustomerAllDetailsDto> custDtos = new ArrayList<>();

        while (rst.next()) {
            getCustomerAllDetailsDto dto = new getCustomerAllDetailsDto(
                    rst.getString("id"),                // Customer ID
                    rst.getString("name"),              // Customer Name
                    rst.getInt("totalLeafAmount"),      // Total Leaf Amount
                    rst.getInt("totalAdvance"),         // Total Advance
                    rst.getDouble("teaPacketTotal"),    // Tea Packet Total
                    rst.getDouble("fertilizeTotal"),    // Fertilize Total
                    rst.getDouble("otherTotal")         // Other Total
            );
            custDtos.add(dto);
        }

        return custDtos;
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

    public List<getCustomerAllDetailsDto> gettot() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//
//        String sql = "SELECT \n" +
//                "    COALESCE(t.totalLeafAmount, 0) AS totalLeafAmount,  \n" +
//                "    COALESCE(a.totalAdvance, 0) AS totalAdvance, \n" +
//                "    COALESCE(p.teaPacketTotal, 0) AS teaPacketTotal,  \n" +
//                "    COALESCE(p.fertilizeTotal, 0) AS fertilizeTotal,  \n" +
//                "    COALESCE(p.otherTotal, 0) AS otherTotal  \n" +
//                "FROM \n" +
//                "    customer c\n" +
//                "LEFT JOIN \n" +
//                "    (SELECT \n" +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'P' THEN totalPrice ELSE 0 END) AS fertilizeTotal,\n" +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'T' THEN totalPrice ELSE 0 END) AS teaPacketTotal,\n" +
//                "        SUM(CASE WHEN LEFT(productId, 1) = 'M' THEN totalPrice ELSE 0 END) AS otherTotal\n" +
//                "     FROM productpurchasecustomer\n" +
//                "     GROUP BY custId) p ON c.id = p.custId\n" +
//                "LEFT JOIN \n" +
//                "    (SELECT (SUM(goldLeafAmount) + SUM(goodLeafAmount)) AS totalLeafAmount\n" +
//                "     FROM teabaginventory\n" +
//                "     GROUP BY custId) t ON c.id = t.custId\n" +
//                "LEFT JOIN \n" +
//                "    (SELECT SUM(price) AS totalAdvance \n" +
//                "     FROM advance \n" +
//                "     GROUP BY custId) a ON c.id = a.custId";
//
//
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//
//        ResultSet rst = statement.executeQuery();
//
//
//        List<getCustomerAllDetailsDto> custDtos = new ArrayList<>();
        return null;

    }


    //////////////invoiceCustomer/////////////////////////

    public List<InvoiceCustomerDto> getDetailsPurchase(String id) throws SQLException, ClassNotFoundException {

        LocalDateTime now = LocalDateTime.now();


        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }



        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String startDate = now.format(dateFormatter) + "-01";


        now = now.plusMonths(1);
        String endDate = now.format(dateFormatter) + "-10";


        Connection connection = DBConnection.getInstance().getConnection();


        String sql = "SELECT " +
                "    COALESCE(p.teaPacketTotal, 0) AS teaPacketTotal, " +
                "    COALESCE(p.fertilizeTotal, 0) AS fertilizeTotal, " +
                "    COALESCE(p.otherTotal, 0) AS otherTotal " +
                "FROM customer c " +
                "LEFT JOIN (" +
                "    SELECT " +
                "        custId, " +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'P' THEN totalPrice ELSE 0 END) AS fertilizeTotal, " +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'T' THEN totalPrice ELSE 0 END) AS teaPacketTotal, " +
                "        SUM(CASE WHEN LEFT(productId, 1) = 'M' THEN totalPrice ELSE 0 END) AS otherTotal " +
                "    FROM productpurchasecustomer " +
                "    WHERE date BETWEEN ? AND ? " + // Filter dates within the subquery
                "    GROUP BY custId" +
                ") p ON c.id = p.custId " +
                "WHERE c.id = ?;";


        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, startDate); // Start date for the subquery
        statement.setString(2, endDate);   // End date for the subquery
        statement.setString(3, id);        // Customer ID


        ResultSet rst = statement.executeQuery();
        List<InvoiceCustomerDto> custDtos = new ArrayList<>();

        while (rst.next()) {
            InvoiceCustomerDto dto = new InvoiceCustomerDto(
                    rst.getDouble("teaPacketTotal"), // Tea Packet Total
                    rst.getDouble("fertilizeTotal"), // Fertilize Total
                    rst.getDouble("otherTotal")     // Other Total
            );
            custDtos.add(dto);
        }



        return custDtos;
    }

    ///////////////////////////view emp work detail.////////////

    public List<ViewEmpWorkDetailsDto> loadEmp() throws SQLException, ClassNotFoundException {
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {

            now = now.minusMonths(1);
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";


        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT e.empId, e.name, e.address, e.telnb, em.date " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId " +
                "WHERE em.date BETWEEN ? AND ? " +
                "ORDER BY em.date";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, startDate);
        statement.setString(2, endDate);

        List<ViewEmpWorkDetailsDto> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getInt(4),rst.getString(5));
            empList.add(dto);
        }
        return empList;
    }

    public List<ViewEmpWorkDetailsDto>  searchEmployes(String id) throws SQLException, ClassNotFoundException {
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {

            now = now.minusMonths(1);
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";


        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="SELECT e.empId, e.name, e.address, e.telnb, em.date " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId " +
                "WHERE e.empId = ? AND em.date BETWEEN ? AND ? " +
                "ORDER BY em.date";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,id);
        statement.setString(2, startDate);
        statement.setString(3, endDate);

        List<ViewEmpWorkDetailsDto> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getInt(4),rst.getString(5));
            empList.add(dto);

        }
        if(empList != null ){
            return empList;
        }

        return null;
    }

    public int searchEmpCount(String id) throws SQLException, ClassNotFoundException {
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {

            now = now.minusMonths(1);
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";


        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(em.date) " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId " +
                "WHERE e.empId = ? " +
                "AND em.date BETWEEN ? AND ? " +
                "ORDER BY em.date";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,startDate);
        statement.setString(3,endDate);

        ResultSet rst = statement.executeQuery();
        int count = 0;
        if (rst.next()){
            count = rst.getInt(1);

        }
        return count;
    }

    public List<ViewEmpWorkDetailsDto> searchDateDetailsEMP(String strDate, String endDate) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="SELECT e.empId, e.name, e.address, e.telnb, em.date " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId " +
                "WHERE em.date BETWEEN ? AND ? " +
                "ORDER BY em.date";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, strDate);
        statement.setString(2, endDate);

        List<ViewEmpWorkDetailsDto> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            ViewEmpWorkDetailsDto dto = new ViewEmpWorkDetailsDto(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getInt(4),rst.getString(5));
            empList.add(dto);

        }
        if(empList != null ){
            return empList;
        }

        return null;
    }
    //Invoice eke emp table eka load karan code eka
    public List<ViewEmpWorkDetailsDto> loadtblEmp() throws SQLException, ClassNotFoundException {

        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {

            now = now.minusMonths(1);
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";


        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT e.empId,e.name,em.date " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId "+
                "WHERE em.date BETWEEN ? AND ? " +
                "ORDER BY em.date ";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,startDate);
        statement.setString(2,endDate);

        List<ViewEmpWorkDetailsDto> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();
        while (rst.next()){
            ViewEmpWorkDetailsDto empDtos = new ViewEmpWorkDetailsDto(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));
            empList.add(empDtos);
        }
        if (empList != null){
            return empList;
        }
        return null;
    }

    public int getCountEmp() throws SQLException, ClassNotFoundException {

        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {

            now = now.minusMonths(1);
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);


        String startDate = formattedDate + "-01";

        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(em.date) " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId " +
                "WHERE " +
                "em.date BETWEEN ? AND ? " +
                "ORDER BY em.date";
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1,startDate);
        statement.setString(2,endDate);

        ResultSet rst = statement.executeQuery();
        int count = 0;
        if (rst.next()){
            count = rst.getInt(1);

        }
        return count;
    }

    public List<ViewEmpWorkDetailsDto> searchEmpId(String id) throws SQLException, ClassNotFoundException {

        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {

            now = now.minusMonths(1);
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);


        String startDate = formattedDate + "-01";

        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT e.empId,e.name,em.date " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId "+
                "WHERE em.empId = ? AND em.date BETWEEN ? AND ? " +
                "ORDER BY em.date ";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,startDate);
        statement.setString(3,endDate);

        List<ViewEmpWorkDetailsDto> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();
        while (rst.next()){
            ViewEmpWorkDetailsDto empDtos = new ViewEmpWorkDetailsDto(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));
            empList.add(empDtos);
        }
        if (empList != null){
            return empList;
        }
        return null;
    }

    public int searchEmpTblCount(String id) throws SQLException, ClassNotFoundException {

        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 7) {

            now = now.minusMonths(1);
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);


        String startDate = formattedDate + "-01";

        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(em.date) " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId " +
                "WHERE e.empId = ? AND " +
                "em.date BETWEEN ? AND ? " +
                "ORDER BY em.date";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,startDate);
        statement.setString(3,endDate);

        ResultSet rst = statement.executeQuery();
        int count = 0;
        if (rst.next()){
            count = rst.getInt(1);

        }
        return count;
    }



    /////////////////////////vieew manage dao//////////

    public List<InvoiceManageDto> getAllAdvance() throws SQLException, ClassNotFoundException {


        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT a.custId, c.name, a.date, a.price " +
                "FROM customer c " +
                "JOIN advance a ON c.id = a.custId " +
                "WHERE a.date GROUP BY a.date";

        PreparedStatement statement = connection.prepareStatement(sql);

        List<InvoiceManageDto> advanceDtos = new ArrayList<>();

        ResultSet rst = statement.executeQuery();
        while (rst.next()) {
            CustomerManageDto customerDto = new CustomerManageDto(
                    rst.getString(1),
                    rst.getString(2)
            );

            InvoiceManageDto dto = new InvoiceManageDto(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getDouble(4),
                    customerDto
            );

            advanceDtos.add(dto);
        }
        return advanceDtos;
    }

    public List<InvoiceManageDto> ViewManageSearchAdvance(String id) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT a.custId, c.name, a.date, a.price " +
                "FROM customer c " +
                "JOIN advance a ON c.id = a.custId " +
                "WHERE a.custId = ? ";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,id);


        ResultSet rst = statement.executeQuery();
        List<InvoiceManageDto> searchAdvanceDtos = new ArrayList<>();


        while (rst.next()) {

            CustomerManageDto customerDto = new CustomerManageDto(
                    rst.getString(1),
                    rst.getString(2)
            );

            InvoiceManageDto dto = new InvoiceManageDto(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getDouble(4),
                    customerDto
            );
            searchAdvanceDtos.add(dto);
        }
        return searchAdvanceDtos;
    }

    public List<InvoiceManageDto> searchDateDetailsAdvance(String stDate, String enDate) throws SQLException, ClassNotFoundException {
        Connection connectionc = DBConnection.getInstance().getConnection();
        String sql = "SELECT a.custId, c.name, a.date, a.price " +
                "FROM customer c " +
                "JOIN advance a ON c.id = a.custId " +
                "WHERE a.date BETWEEN ? AND ? " +
                "ORDER BY a.date";
        PreparedStatement statement = connectionc.prepareStatement(sql);

        statement.setString(1, stDate);
        statement.setString(2, enDate);

        ResultSet rst = statement.executeQuery();
        List<InvoiceManageDto> advanceDtos = new ArrayList<>();

        while (rst.next()) {
            CustomerManageDto customerDto = new CustomerManageDto(
                    rst.getString(1),
                    rst.getString(2)
            );

            InvoiceManageDto dto = new InvoiceManageDto(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getDouble(4),
                    customerDto
            );

            advanceDtos.add(dto);
        }

        return advanceDtos;
    }








    }
