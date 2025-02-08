package lk.ijse.Dao.custom.impl;

import lk.ijse.Dao.custom.InvoiceManageDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InvoiceManageDAOimpl implements InvoiceManageDAO {
    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};

    public String updateData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
//dffjh
        try {
            connection.setAutoCommit(false);


            String sql1 = "SELECT quntity FROM productpurchasecustomer WHERE custId = ? AND date = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, invoiceManageDto.getCustId());
            statement1.setString(2, invoiceManageDto.getDate());

            ResultSet resultSet = statement1.executeQuery();

            if (!resultSet.next()) {
                return "Order not found"; // Return if no matching order is found
            }

            int previousCount = resultSet.getInt("quntity");


            String sql = "UPDATE productpurchasecustomer SET productId = ?, quntity = ?, totalPrice = ? WHERE custId = ? AND date = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, invoiceManageDto.getProductId());
            statement.setInt(2, invoiceManageDto.getCount());
            statement.setDouble(3, invoiceManageDto.getPrice());
            statement.setString(4, invoiceManageDto.getCustId());
            statement.setString(5, invoiceManageDto.getDate());

            boolean isOrderUpdated = statement.executeUpdate() > 0;

            if (isOrderUpdated) {
                int newCount = invoiceManageDto.getCount();
                int stockDifference = newCount - previousCount;

                if (stockDifference != 0) {
                    String stockUpdateSql;
                    PreparedStatement stockStatement;

                    if (stockDifference > 0) {

                        stockUpdateSql = "UPDATE product SET count = count - ? WHERE id = ?";
                        stockStatement = connection.prepareStatement(stockUpdateSql);
                        stockStatement.setInt(1, stockDifference);
                        stockStatement.setString(2, stockDto.getId());

                        // Check if enough stock is available before updating
                        if (stockDto.getCount() < stockDifference) {
                            connection.rollback();
                            JOptionPane.showMessageDialog(null, "Insufficient stock available", "Stock Error", JOptionPane.ERROR_MESSAGE);
                            return "Stock update failed: insufficient stock";
                        }
                    } else {
                        // If the new count is less, increase the stock by the absolute difference
                        stockUpdateSql = "UPDATE product SET count = count + ? WHERE id = ?";
                        stockStatement = connection.prepareStatement(stockUpdateSql);
                        stockStatement.setInt(1, -stockDifference); // Use absolute value of the difference
                        stockStatement.setString(2, stockDto.getId());
                    }

                    boolean isStockUpdated = stockStatement.executeUpdate() > 0;

                    if (!isStockUpdated) {
                        connection.rollback();
                        return "Stock update failed";
                    }
                }

                // Commit the transaction if everything is successful
                connection.commit();
                return "Update successful";
            } else {
                connection.rollback();
                return "Order update failed";
            }

        } catch (Exception e) {
            connection.rollback(); // Rollback in case of an exception
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restore auto-commit mode
        }
    }

    public String insertData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();


        try{
            connection.setAutoCommit(false);

            String sqlCount = "SELECT count FROM product WHERE id = ?";
            PreparedStatement statementCount = connection.prepareStatement(sqlCount);
            statementCount.setString(1,stockDto.getId());

            ResultSet resultSet = statementCount.executeQuery();

            if (!resultSet.next()) {
                connection.rollback();
                return "Product not found";
            }

            int productCount = resultSet.getInt("count");


            String sql = "INSERT INTO productpurchasecustomer VALUES(?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,invoiceManageDto.getCustId());
            statement.setString(2,invoiceManageDto.getDate());
            statement.setString(3,invoiceManageDto.getProductId());
            statement.setInt(4,invoiceManageDto.getCount());
            statement.setDouble(5,invoiceManageDto.getPrice());

            boolean isOrderSaved = statement.executeUpdate() > 0;

            if (isOrderSaved) {
                boolean isItemUpdated = true;


                if (productCount >= invoiceManageDto.getCount()) {
                    String sqlUpdate = "UPDATE product SET count = count - ? WHERE id = ?";
                    PreparedStatement statement1 = connection.prepareStatement(sqlUpdate);
                    statement1.setInt(1, invoiceManageDto.getCount());
                    statement1.setString(2, stockDto.getId());

                    if (!(statement1.executeUpdate() > 0)) {
                        isItemUpdated = false;
                    }
                } else {
                    connection.rollback();
                    return "Insufficient stock";
                }

                if (isItemUpdated) {
                    connection.commit();
                    return "Saved";
                } else {
                    connection.rollback();
                    return "Item update Error";
                }
            } else {
                connection.rollback();
                return "Order save Error";
            }

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    public double getPric(String productId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT price FROM product WHERE type = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getDouble("price");
        } else {
            throw new SQLException("No product found with ID: " + productId);
        }
    }

    public String btnSaveAdvance(InvoiceManageDto invoiceManageDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO advance VALUES(?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,invoiceManageDto.getCustId());
        statement.setString(2,invoiceManageDto.getDate());
        statement.setString(3,invoiceManageDto.getMonth());
        statement.setDouble(4,invoiceManageDto.getPrice());
        statement.setDouble(5,invoiceManageDto.getMonthPrice());


        int resp = statement.executeUpdate();
        return resp > 0 ? "Succsess" : "Fail";
    }




    public String btnUpdateAdvance(String id,String date,double price) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM advance  WHERE custId = ? and date = ? and price = ?"  ;

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,id);
        statement.setString(2,date);
        statement.setDouble(3,price);
       // statement.setDouble(3,price);


        int resp = statement.executeUpdate();
        return resp > 0 ? "Succsess" : "Fail";
    }

    public String insertDataPohora(InvoiceManageDto invoiceManageDto, StockDto stockDto, int warik, double totalPrice) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();


        try{
            connection.setAutoCommit(false);

            String sqlCount = "SELECT count FROM product WHERE id = ?";
            PreparedStatement statementCount = connection.prepareStatement(sqlCount);
            statementCount.setString(1,stockDto.getId());

            ResultSet resultSet = statementCount.executeQuery();

            if (!resultSet.next()) {
                connection.rollback();
                return "Product not found";
            }

            int productCount = resultSet.getInt("count");

            boolean isOrderSaved = false ;
            String month ="";
            LocalDateTime nows = LocalDateTime.now();

                if (nows.getDayOfMonth() >= 1 && nows.getDayOfMonth() <= 10) {
                    for (int i = 0; i < warik; i++) {
                        LocalDateTime now = LocalDateTime.now();
                        now = now.minusMonths(1);
                        now = now.plusMonths(i);
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy");
                        String formattedDate = now.format(dateFormatter);
                        DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("MM");
                        String formattedDate2 = now.format(dateFormatter2);
                        String moko;

                        int pako = 0;
                        pako = Integer.parseInt(formattedDate2);
                        moko = dateArray[pako - 1];

                        month = moko + formattedDate;

                        String sql = "INSERT INTO pohorapurchasecustomer VALUES(?,?,?,?,?,?,?)";

                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1,invoiceManageDto.getCustId());
                        statement.setString(2,invoiceManageDto.getDate());
                        statement.setString(3,invoiceManageDto.getProductId());
                        statement.setInt(4,invoiceManageDto.getCount());
                        statement.setString(5,month);
                        statement.setDouble(6,invoiceManageDto.getPrice());
                        statement.setDouble(7,totalPrice/warik);

                        isOrderSaved = statement.executeUpdate() > 0;


                    }
                }
                else {
                    for (int i = 0; i < warik; i++) {
                        LocalDateTime now = LocalDateTime.now();
                        now = now.plusMonths(i);
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy");
                        String formattedDate = now.format(dateFormatter);
                        DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("MM");
                        String formattedDate2 = now.format(dateFormatter2);
                        String moko = "";

                        int pako = 0;
                        pako = Integer.parseInt(formattedDate2);
                        moko = dateArray[pako - 1];
                        month = moko + formattedDate;

                        String sql = "INSERT INTO pohorapurchasecustomer VALUES(?,?,?,?,?,?,?)";

                        PreparedStatement statement = connection.prepareStatement(sql);

                        statement.setString(1,invoiceManageDto.getCustId());
                        statement.setString(2,invoiceManageDto.getDate());
                        statement.setString(3,invoiceManageDto.getProductId());
                        statement.setInt(4,invoiceManageDto.getCount());
                        statement.setString(5,month);
                        statement.setDouble(6,invoiceManageDto.getPrice());
                        statement.setDouble(7,totalPrice/warik);

                        isOrderSaved = statement.executeUpdate() > 0;

                    }

                }



            if (isOrderSaved) {
                boolean isItemUpdated = true;

                // Check if stock is available before updating
                if (productCount >= invoiceManageDto.getCount()) {
                    String sqlUpdate = "UPDATE product SET count = count - ? WHERE id = ?";
                    PreparedStatement statement1 = connection.prepareStatement(sqlUpdate);
                    statement1.setInt(1, invoiceManageDto.getCount());
                    statement1.setString(2, stockDto.getId());

                    if (!(statement1.executeUpdate() > 0)) {
                        isItemUpdated = false;
                    }
                } else {
                    connection.rollback();
                    return "Insufficient stock";
                }


                if (isItemUpdated) {
                    connection.commit();
                    return "Saved";
                } else {
                    connection.rollback();
                    return "Item update Error";
                }
            } else {
                connection.rollback();
                return "Order save Error";
            }

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }

    }


    public String deleteDataPohora(InvoiceManageDto invoiceManageDto, StockDto stockDto, int warik, double totalPrice) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            String sql1 = "SELECT quntity FROM pohorapurchasecustomer WHERE custId = ? AND date = ? AND productId = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, invoiceManageDto.getCustId());
            statement1.setString(2, invoiceManageDto.getDate());
            statement1.setString(3, invoiceManageDto.getProductId());

            ResultSet resultSet = statement1.executeQuery();

            if (!resultSet.next()) {
                return "Order not found";
            }

            int previousCount = resultSet.getInt("quntity");

            boolean isOrderUpdated = false ;

            String sql = "DELETE FROM pohorapurchasecustomer WHERE custId = ? AND productId = ? AND date = ? AND  quntity = ? AND totalPrice = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, invoiceManageDto.getCustId());
            statement.setString(2, invoiceManageDto.getProductId());
            statement.setString(3, invoiceManageDto.getDate());
            statement.setInt(4,invoiceManageDto.getCount());
            statement.setDouble(5,invoiceManageDto.getPrice());

            isOrderUpdated = statement.executeUpdate() > 0;

            if (isOrderUpdated) {
                        String stockUpdateSql = "UPDATE product SET count = count + ? WHERE id = ?";
                        PreparedStatement stockStatement = connection.prepareStatement(stockUpdateSql);
                        stockStatement.setInt(1,previousCount); // Use absolute value of the difference
                        stockStatement.setString(2, stockDto.getId());


                    boolean isStockUpdated = stockStatement.executeUpdate() > 0;

                    if (!isStockUpdated) {
                        connection.rollback();
                        return "Stock update failed";
                    }

                connection.commit();
                return "Update successful";
            } else {
                connection.rollback();
                return "Order update failed";
            }

        } catch (Exception e) {
            connection.rollback(); // Rollback in case of an exception
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restore auto-commit mode
        }
    }


    public List<LoadProductPurchase> loadT() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="SELECT c.name, t.date, m.type, t.quntity, t.totalPrice\n" +
                "FROM productpurchasecustomer t\n" +
                "JOIN customer c ON t.custId = c.id\n" +
                "JOIN product m ON t.productId = m.id\n" +
                "WHERE t.productId LIKE 'T%'";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<LoadProductPurchase> rst = new ArrayList<>();

        while (resultSet.next()){
            LoadProductPurchase dd = new LoadProductPurchase(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5)
            );
              rst.add(dd);
        }
        return rst;
    }

    public List<LoadProductPurchase> loadO() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="SELECT c.name, t.date, m.type, t.quntity, t.totalPrice\n" +
                "FROM productpurchasecustomer t\n" +
                "JOIN customer c ON t.custId = c.id\n" +
                "JOIN product m ON t.productId = m.id\n" +
                "WHERE t.productId LIKE 'M%';";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<LoadProductPurchase> rst = new ArrayList<>();

        while (resultSet.next()){
            LoadProductPurchase dd = new LoadProductPurchase(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5)
            );
            rst.add(dd);
        }
        return rst;
    }

    public List<LoadProductPurchase> loadF() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="SELECT c.name, t.date, m.type, t.quntity, SUM(t.monthPrice) AS Price\n" +
                "FROM pohorapurchasecustomer t\n" +
                "JOIN customer c ON t.custId = c.id\n" +
                "JOIN product m ON t.productId = m.id\n" +
                "GROUP BY custId, date ";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<LoadProductPurchase> rst = new ArrayList<>();

        while (resultSet.next()){
            LoadProductPurchase dd = new LoadProductPurchase(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5)
            );
            rst.add(dd);
        }
        return rst;
    }

    public List<AdvanceDto> loadA() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql ="SELECT a.custId,c.name, a.date,SUM(a.monthPrice) AS total_price_sum\n" +
                "FROM advance a JOIN customer c ON a.custId = c.id\n" +
                "GROUP BY custId, date;";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        ArrayList<AdvanceDto> rst = new ArrayList<>();

        while (resultSet.next()){
            AdvanceDto dd = new AdvanceDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
            rst.add(dd);
        }
        return rst;
    }

    public String getNameAdvance(String mm) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql  = "select id from customer WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,mm);
        String mk = "";
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            mk = resultSet.getString("id");
        }
        return mk;
    }

    public String getProductIDT(String mk) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql  = "select id from product WHERE type = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,mk);
        String mm = "";
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            mm = resultSet.getString("id");
        }
        return mm;
    }

    public String DeleteData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);


            String sql1 = "SELECT quntity FROM productpurchasecustomer WHERE custId = ? AND date = ? AND productId = ? " +
                    "AND totalPrice = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, invoiceManageDto.getCustId());
            statement1.setString(2, invoiceManageDto.getDate());
            statement1.setString(3, invoiceManageDto.getProductId());
            statement1.setDouble(4, invoiceManageDto.getPrice());

            ResultSet resultSet = statement1.executeQuery();

            if (!resultSet.next()) {
                return "Order not found"; // Return if no matching order is found
            }

            int previousCount = resultSet.getInt("quntity");


            String sql = "DELETE FROM productpurchasecustomer WHERE productId = ? AND quntity = ? AND totalPrice = ? AND custId = ? AND date = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, invoiceManageDto.getProductId());
            statement.setInt(2, invoiceManageDto.getCount());
            statement.setDouble(3, invoiceManageDto.getPrice());
            statement.setString(4, invoiceManageDto.getCustId());
            statement.setString(5, invoiceManageDto.getDate());

            boolean isOrderUpdated = statement.executeUpdate() > 0;

            if (isOrderUpdated) {
                String stockUpdateSql;
                PreparedStatement stockStatement;
                int newCount = invoiceManageDto.getCount();

                stockUpdateSql = "UPDATE product SET count = count + ? WHERE id = ?";
                stockStatement = connection.prepareStatement(stockUpdateSql);
                stockStatement.setInt(1, previousCount); // Use absolute value of the difference
                stockStatement.setString(2, stockDto.getId());


            boolean isStockUpdated = stockStatement.executeUpdate() > 0;

            if (!isStockUpdated) {
                connection.rollback();
                return "Stock update failed";
            }

                connection.commit();
                return "Update successful";
            } else {
                connection.rollback();
                return "Order update failed";
            }

        } catch (Exception e) {
            connection.rollback(); // Rollback in case of an exception
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restore auto-commit mode
        }
    }

    public String getIdF(String mm) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT id FROM customer WHERE name = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,mm);

        ResultSet rst = statement.executeQuery();
        String mk = "";
        if(rst.next()){
            mk = rst.getString("id");
        }
        return mk;
    }

    public String getProductIDF(String mk) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT id FROM product WHERE type = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,mk);

        ResultSet rst = statement.executeQuery();
        String mm = "";
        if(rst.next()){
            mm = rst.getString("id");
        }
        return mm;
    }

    public String deleteOtherData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);


            String sql1 = "SELECT quntity FROM productpurchasecustomer WHERE custId = ? AND date = ? AND productId = ? " +
                    "AND totalPrice = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, invoiceManageDto.getCustId());
            statement1.setString(2, invoiceManageDto.getDate());
            statement1.setString(3, invoiceManageDto.getProductId());
            statement1.setDouble(4, invoiceManageDto.getPrice());

            ResultSet resultSet = statement1.executeQuery();

            if (!resultSet.next()) {
                return "Order not found"; // Return if no matching order is found
            }

            int previousCount = resultSet.getInt("quntity");


            String sql = "DELETE FROM productpurchasecustomer WHERE productId = ? AND quntity = ? AND totalPrice = ? AND custId = ? AND date = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, invoiceManageDto.getProductId());
            statement.setInt(2, invoiceManageDto.getCount());
            statement.setDouble(3, invoiceManageDto.getPrice());
            statement.setString(4, invoiceManageDto.getCustId());
            statement.setString(5, invoiceManageDto.getDate());

            boolean isOrderUpdated = statement.executeUpdate() > 0;

            if (isOrderUpdated) {
                String stockUpdateSql;
                PreparedStatement stockStatement;
                int newCount = invoiceManageDto.getCount();

                stockUpdateSql = "UPDATE product SET count = count + ? WHERE id = ?";
                stockStatement = connection.prepareStatement(stockUpdateSql);
                stockStatement.setInt(1, previousCount); // Use absolute value of the difference
                stockStatement.setString(2, stockDto.getId());


                boolean isStockUpdated = stockStatement.executeUpdate() > 0;

                if (!isStockUpdated) {
                    connection.rollback();
                    return "Stock update failed";
                }

                connection.commit();
                return "Update successful";
            } else {
                connection.rollback();
                return "Order update failed";
            }

        } catch (Exception e) {
            connection.rollback(); // Rollback in case of an exception
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restore auto-commit mode
        }
    }

    public String inserPaymentValues(String id, String date, PaymentDto pDto, double Higa)
            throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            // Insert into payments
            String sql = "INSERT INTO payments VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pDto.getId());
                statement.setString(2, pDto.getCustId());
                statement.setString(3, pDto.getCustName());
                statement.setString(4, pDto.getDate());
                statement.setInt(5, pDto.getGoodLeaf());
                statement.setInt(6, pDto.getGoldLeaf());
                statement.setDouble(7, pDto.getTealeafAmount());
                statement.setDouble(8, pDto.getTotalAdvance());
                statement.setDouble(9, pDto.getTotalFertilize());
                statement.setDouble(10, pDto.getTotalTeaPacket());
                statement.setDouble(11, pDto.getTotalOthers());
                statement.setDouble(12, pDto.getTotalAmounr());
                statement.setDouble(13, pDto.getPreviasMonthlyHiga());
                statement.setDouble(14, pDto.getCounts());
                statement.setDouble(15, pDto.getRategood());
                statement.setDouble(16, pDto.getRategold());
                statement.setDouble(17, pDto.getNextHigaPrice());
                statement.setDouble(18, pDto.getNextHigaPohora());
                statement.setDouble(19, pDto.getNextAdvanceHiga());
                statement.setInt(20, pDto.getKapanagana());

                int isSave = statement.executeUpdate();
                if (isSave == 0) {
                    connection.rollback();
                    return "Payment data not saved";
                }
            }

            // Insert into customerhigaprice
            String customerHigaSql = "INSERT INTO customerhigaprice VALUES (?, ?, ?)";
            try (PreparedStatement st1 = connection.prepareStatement(customerHigaSql)) {
                st1.setString(1, id);
                st1.setString(2, date);
                st1.setDouble(3, Higa);

                int cussave = st1.executeUpdate();
                if (cussave == 0) {
                    connection.rollback();
                    return "Customer Higa Price data not saved";
                }
            }

            connection.commit();
            return "Success";
        } catch (SQLException e) {
            connection.rollback();
            System.err.println("Error occurred: " + e.getMessage());
            return "Fail - Try again";
        } finally {
            connection.setAutoCommit(true);
        }
    }


    public String getPID() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT id FROM payments ORDER BY id DESC LIMIT 1";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String customerId = resultSet.getString("id");

                if (customerId != null && customerId.length() > 1) {
                    String supString = customerId.substring(1); // Exclude the prefix (e.g., "C001" -> "001")

                    try {
                        int numericPart = Integer.parseInt(supString); // Parse numeric part
                        int newIDInt = numericPart + 1; // Increment ID
                        return String.format("P%03d", newIDInt); // Format as "Cxxx" (e.g., "C002")
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing numeric part of ID: " + e.getMessage());
                    }
                }
            }
        }
        return "P001";
    }

    public String getT(String x, String startDate) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Select SUM(goldLeafAmount+goodLeafAmount) from teabaginventory WHERE custId = ? AND date = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,x);
        statement.setString(2,startDate);

        String xc = "";
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            xc = String.valueOf(resultSet.getInt(1));
        }
        if(xc == "0"){
            return "0";
        }

        return xc;
    }

    public String getT2(String x, String startDate) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Select SUM(goldLeafAmount+goodLeafAmount) from teabaginventory WHERE custId = ? AND date = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,x);
        statement.setString(2,startDate);

        String xc = "";
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            xc = String.valueOf(resultSet.getInt(1));
        }
        if(xc == "0"){
            return "";
        }

        return xc;
    }

    public double getPricPhora(String productId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT price FROM product WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getDouble("price");
        } else {
            String sql1 = "SELECT price FROM product WHERE type = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1, productId);

            ResultSet resultSet1 = statement1.executeQuery();

            if (resultSet1.next()) {
                return resultSet1.getDouble("price");
            }
        }
        return 0;
    }
}
