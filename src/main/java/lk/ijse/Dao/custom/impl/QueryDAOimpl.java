package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.QueryDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;
import lk.ijse.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOimpl implements QueryDAO {
    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};

//invoice manage eken awe
    public List<Advance> loadA() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="SELECT a.custId,c.name, a.date,SUM(a.monthPrice) AS total_price_sum\n" +
                "FROM advance a JOIN customer c ON a.custId = c.id\n" +
                "GROUP BY custId, date;";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<Advance> rst = new ArrayList<>();

        while (resultSet.next()){
            Advance dd = new Advance(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
            rst.add(dd);
        }
        return rst;
    }


    public CustomerManageDto custSerachsummery(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

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

    public List<ViewManage> getStockPurchases() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT c.id,c.name,p.date,p.productId,p.quntity,p.totalPrice FROM customer c JOIN \n" +
                "                productpurchasecustomer p ON c.id = p.custId " +
                "                ORDER BY p.date";

        PreparedStatement statement = connection.prepareStatement(sql);
        List<ViewManage> custDtos = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            ViewManage dto = new ViewManage(rst.getString(1),
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

    public List<ViewManage> getPohoraStockPurchase() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT c.id,c.name,p.date,p.productId,p.quntity,SUM(p.monthPrice) as totalPrice FROM customer c JOIN \n" +
                "                               pohorapurchasecustomer p ON c.id = p.custId GROUP BY c.id\n" +
                "                              ORDER BY p.date\n";

        PreparedStatement statement = connection.prepareStatement(sql);
        List<ViewManage> custDtos = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            ViewManage dto = new ViewManage(rst.getString(1),
                    rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getInt(5),rst.getDouble(6));

            custDtos.add(dto);
        }
        return custDtos;
    }

    //////DailyHomePage////

    public List<DailyHomePage> LoadTable(String date) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT c.custId,d.name,c.date,c.goldLeafAmount,c.goodLeafAmount FROM teaBagInventory c JOIN customer d ON c.custId = d.id WHERE date = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,date );
        List<DailyHomePage> Dtos = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        String name = "";
        while (rst.next()) {
            DailyHomePage dto = new DailyHomePage(rst.getString("custId"),rst.getString("name"),rst.getString("date"),
                    rst.getInt("goldLeafAmount"),rst.getInt("goodLeafAmount"));
            Dtos.add(dto);
        }
        return Dtos;
    }

    //////////////////////////getcustomerAllDetails/////////////////

    public List<getCustomerAllDetails> getAllCustomerDetails(String startDate, String endoDate, String endDate, String ss) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

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

        List<getCustomerAllDetails> custDtos = new ArrayList<>();

        while (rst.next()) {
            getCustomerAllDetails dto = new getCustomerAllDetails(
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



    public List<getCustomerAllDetails> ViewManageSearchCustomer(String id) throws SQLException, ClassNotFoundException {
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

        List<getCustomerAllDetails> custDtos = new ArrayList<>();

        while (rst.next()) {

            getCustomerAllDetails dto = new getCustomerAllDetails(
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

    public List<getCustomerAllDetails> selectDateCust(String strDate, String endDate) throws SQLException, ClassNotFoundException {
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

        List<getCustomerAllDetails> custDtos = new ArrayList<>();

        while (rst.next()) {

            getCustomerAllDetails dto = new getCustomerAllDetails(
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



    //////////////invoiceCustomer/////////////////////////

    public List<InvoiceCustomer> getDetailsPurchase(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException {

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
        List<InvoiceCustomer> custDtos = new ArrayList<>();

        while (rst.next()) {
            InvoiceCustomer dto = new InvoiceCustomer(
                    rst.getDouble("teaPacketTotal"), // Tea Packet Total
                    rst.getDouble("fertilizeTotal"), // Fertilize Total
                    rst.getDouble("otherTotal")     // Other Total
            );
            custDtos.add(dto);
        }



        return custDtos;
    }

    ///////////////////////////view emp work detail.////////////

    public List<ViewEmpWorkDetails> loadEmp(String startDate, String endDate) throws SQLException, ClassNotFoundException {


        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT e.empId, e.name, e.address, e.telnb, em.date " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId " +
                "WHERE em.date BETWEEN ? AND ? " +
                "ORDER BY em.date";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, startDate);
        statement.setString(2, endDate);

        List<ViewEmpWorkDetails> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            ViewEmpWorkDetails dto = new ViewEmpWorkDetails(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getInt(4),rst.getString(5));
            empList.add(dto);
        }
        return empList;
    }

    public List<ViewEmpWorkDetails>  searchEmployes(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException {

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

        List<ViewEmpWorkDetails> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            ViewEmpWorkDetails dto = new ViewEmpWorkDetails(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getInt(4),rst.getString(5));
            empList.add(dto);

        }
        if(empList != null ){
            return empList;
        }

        return null;
    }

    public int searchEmpCount(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException {

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

    public List<ViewEmpWorkDetails> searchDateDetailsEMP(String strDate, String endDate) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="SELECT e.empId, e.name, e.address, e.telnb, em.date " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId " +
                "WHERE em.date BETWEEN ? AND ? " +
                "ORDER BY em.date";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, strDate);
        statement.setString(2, endDate);

        List<ViewEmpWorkDetails> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            ViewEmpWorkDetails dto = new ViewEmpWorkDetails(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getInt(4),rst.getString(5));
            empList.add(dto);

        }
        if(empList != null ){
            return empList;
        }

        return null;
    }
    //Invoice eke emp table eka load karan code eka
    public List<ViewEmpWorkDetails> loadtblEmp(String startDate, String endDate) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT e.empId,e.name,em.date " +
                "FROM employee e " +
                "JOIN empworddetails em ON e.empId = em.empId "+
                "WHERE em.date BETWEEN ? AND ? " +
                "ORDER BY em.date ";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,startDate);
        statement.setString(2,endDate);

        List<ViewEmpWorkDetails> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();
        while (rst.next()){
            ViewEmpWorkDetails empDtos = new ViewEmpWorkDetails(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));
            empList.add(empDtos);
        }
        if (empList != null){
            return empList;
        }
        return null;
    }

    public int getCountEmp(String startDate, String endDate) throws SQLException, ClassNotFoundException {

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

    public List<ViewEmpWorkDetails> searchEmpId(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException {

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

        List<ViewEmpWorkDetails> empList = new ArrayList<>();
        ResultSet rst = statement.executeQuery();
        while (rst.next()){
            ViewEmpWorkDetails empDtos = new ViewEmpWorkDetails(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));
            empList.add(empDtos);
        }
        if (empList != null){
            return empList;
        }
        return null;
    }

    public int searchEmpTblCount(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException {

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

    public List<InvoiceManage> getAllAdvance() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT a.custId, c.name, a.date, a.price " +
                "FROM customer c " +
                "JOIN advance a ON c.id = a.custId " +
                "WHERE a.date GROUP BY a.date";

        PreparedStatement statement = connection.prepareStatement(sql);

        List<InvoiceManage> advanceDtos = new ArrayList<>();

        ResultSet rst = statement.executeQuery();
        while (rst.next()) {
            CustomerManage customerDto = new CustomerManage(
                    rst.getString(1),
                    rst.getString(2)
            );

            InvoiceManage dto = new InvoiceManage(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getDouble(4),
                    customerDto
            );

            advanceDtos.add(dto);
        }
        return advanceDtos;
    }

    public List<InvoiceManage> ViewManageSearchAdvance(String id) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT a.custId, c.name, a.date, a.price " +
                "FROM customer c " +
                "JOIN advance a ON c.id = a.custId " +
                "WHERE a.custId = ? ";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,id);


        ResultSet rst = statement.executeQuery();
        List<InvoiceManage> searchAdvanceDtos = new ArrayList<>();


        while (rst.next()) {

            CustomerManage customerDto = new CustomerManage(
                    rst.getString(1),
                    rst.getString(2)
            );

            InvoiceManage dto = new InvoiceManage(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getDouble(4),
                    customerDto
            );
            searchAdvanceDtos.add(dto);
        }
        return searchAdvanceDtos;
    }

    public List<InvoiceManage> searchDateDetailsAdvance(String stDate, String enDate) throws SQLException, ClassNotFoundException {
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
        List<InvoiceManage> advanceDtos = new ArrayList<>();

        while (rst.next()) {
            CustomerManage customerDto = new CustomerManage(
                    rst.getString(1),
                    rst.getString(2)
            );

            InvoiceManage dto = new InvoiceManage(
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
