package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.custom.MonthlyRateDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.MonthlyRateDto;
import lk.ijse.entity.MonthlyRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MonthlyRateBoimpl implements MonthlyRateDAO {
    public String save(MonthlyRate dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO monthlyRate VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,dto.getMonth());
        statement.setDouble(2,dto.getRate());
        statement.setDouble(3,dto.getRate1());

        int  resp = statement.executeUpdate();
        return resp > 0 ? "Sucssess" : "Faild";

    }

    public String update(MonthlyRate dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE monthlyRate SET goldrate = ?,goodrate=? WHERE month = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setDouble(1,dto.getRate());
        statement.setDouble(2,dto.getRate1());

        statement.setString(3,dto.getMonth());

        int  resp = statement.executeUpdate();
        return resp > 0 ? "Sucssess" : "Faild";
    }

    @Override
    public String delete(String t) throws SQLException, ClassNotFoundException {
        return "";
    }

    public List<MonthlyRateDto> loadDetails() throws SQLException, ClassNotFoundException {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY");
        String date = LocalDate.now().format(dateTimeFormatter);

        String strDate = date+("-01-01");
        String endDate = date+("-12-31");

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM monthlyRate ";
        PreparedStatement statement = connection.prepareStatement(sql);

//        statement.setString(1,strDate);
//        statement.setString(2,endDate);

        List<MonthlyRateDto> list = new ArrayList<>();
        ResultSet rst = statement.executeQuery();
        while (rst.next()){
            MonthlyRateDto dto = new MonthlyRateDto(rst.getString(1),rst.getDouble(2)
            ,rst.getDouble(3));

            list.add(dto);
        }
        return list;
    }


    public List<MonthlyRateDto> getLeafPrice(String date) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT goldrate,goodrate FROM monthlyRate WHERE month = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1,date);


        List<MonthlyRateDto> list = new ArrayList<>();

        ResultSet rst = statement.executeQuery();

        while (rst.next()){
            MonthlyRateDto dto = new MonthlyRateDto(rst.getDouble("goldrate"),rst.getDouble("goodrate"));
            list.add(dto);
        }
        return list;
    }

    public double[] getAllCustomerHiga(String dateMonth, double lblGoodLea, double lblGoldLeaf) throws SQLException, ClassNotFoundException {

        double[] temp = new double[2];
        double returnPrice = 0;
        double returnPrice1 = 0;

        LocalDateTime now = LocalDateTime.now();
        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String stDate = formattedDate+"-01";
        String endDate = formattedDate+"-31";


        now = now.plusMonths(1);
        DateTimeFormatter dateFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate1 = now.format(dateFormatter1);
        String endoDate = formattedDate1+"-10";



        Connection connection = DBConnection.getInstance().getConnection();

        String sqlid = "select id from customer";
        PreparedStatement statementid = connection.prepareStatement(sqlid);
        ResultSet resultSetid = statementid.executeQuery();

        while (resultSetid.next()){
            double good = 0;
            double gold = 0;
            double totalSUM = 0;
            String id = "";
            id = resultSetid.getString(1);
            double totLeaf = 0;

            String sql =
                    "select SUM(goldLeafAmount) from teabaginventory WHERE custId = ? AND date BETWEEN ? AND ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,id);
            statement.setString(2,stDate);
            statement.setString(3,endDate);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                gold += resultSet.getDouble(1);
            }


            String sql4 = "select SUM(goodLeafAmount) from teabaginventory WHERE custId = ? AND date BETWEEN ? AND ?";
            PreparedStatement statement4 = connection.prepareStatement(sql4);

            statement4.setString(1,id);
            statement4.setString(2,stDate);
            statement4.setString(3,endDate);
            ResultSet resultSet4 = statement4.executeQuery();

            while (resultSet4.next()){
                good += resultSet4.getDouble(1);
            }

            totLeaf = (good*lblGoodLea)+(gold*lblGoldLeaf);

            String sql1 = "select monthPrice from advance WHERE month = ? AND custId = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1,dateMonth);
            statement1.setString(2,id);
            ResultSet rst = statement1.executeQuery();
            double adprice = 0;
            double fprice = 0;
            if (rst.next()){
                adprice = rst.getDouble(1);
            }

            String sql12 = "select monthPrice from pohorapurchasecustomer WHERE month = ? AND custId = ?";
            PreparedStatement statement12 = connection.prepareStatement(sql12);
            statement12.setString(1,dateMonth);
            statement12.setString(2,id);
            ResultSet rst2 = statement12.executeQuery();

            if (rst2.next()){
                fprice = rst2.getDouble(1);
            }


            double othrPrice = 0;
            String sql2 = "SELECT SUM(totalPrice) AS totalPriceSum FROM productpurchasecustomer WHERE custId = ? AND date BETWEEN ? AND ?";
            try (PreparedStatement statement2 = connection.prepareStatement(sql2)) {
                statement2.setString(1, id);
                statement2.setString(2, stDate);
                statement2.setString(3, endoDate);
                try (ResultSet rsqt = statement2.executeQuery()) {
                    if (rsqt.next()) {
                        othrPrice = rsqt.getDouble("totalPriceSum");
                    }
                }
            }


            String sql5= "select price from customerhigaprice  where custId = ? AND month = ?";
            PreparedStatement statement5 = connection.prepareStatement(sql5);
            statement5.setString(1,id);
            statement5.setString(2,dateMonth);
            ResultSet rst5 = statement5.executeQuery();
            double kalinhiga = 0;
            if (rst5.next()){
                kalinhiga = rst5.getDouble(1);
            }

            totalSUM = kalinhiga+othrPrice+25+adprice+fprice;
            totLeaf = totLeaf-totalSUM;



            if (totLeaf >= 0){
                returnPrice += totLeaf;
                temp[0] = returnPrice;
            }else {
                returnPrice1 += totLeaf;
               temp[1] = returnPrice1;
            }

        }

            return temp;
    }

    public List<Integer> getallMonthlyLeafCount(String date) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT SUM(goldLeafAmount) AS goldLeafAmount,SUM(goodLeafAmount) AS goodLeafAmount FROM teabaginventory WHERE date BETWEEN ? AND ? ";
        PreparedStatement statement = connection.prepareStatement(sql);

        String st = date+"-01";
        String en = date+"-31";


        statement.setString(1,st);
        statement.setString(2,en);

        List<Integer> list = new ArrayList<>();

        ResultSet rst = statement.executeQuery();
        while (rst.next()){
            list.add(rst.getInt(1));
            list.add(rst.getInt(2));
        }

        return list;
    }

    public Double getPurchase(String date) throws SQLException, ClassNotFoundException {

        String startDate = date + "-01";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        LocalDate startDateParsed = LocalDate.parse(startDate, dateFormatter);
        LocalDate endDateParsed = startDateParsed.plusMonths(1).withDayOfMonth(10);

        String endDate = endDateParsed.toString();



        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "select SUM(totalPrice) from productpurchasecustomer where date BETWEEN ? AND ?";


        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, startDate);
        statement.setString(2, endDate);


        ResultSet rst = statement.executeQuery();
        Double ssss = 0.0;

        if (rst.next()){
            ssss = rst.getDouble(1);
        }


        return ssss;
    }

    public Double getAdvance(String month) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql1 = "select SUM(monthPrice) from advance WHERE month = ?";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        statement1.setString(1,month);
        ResultSet rst = statement1.executeQuery();
        double adprice = 0;
        if (rst.next()){
            adprice = rst.getDouble(1);
        }
        return adprice;
    }

    public Double getPohora(String month) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql1 = "select SUM(monthPrice) from pohorapurchasecustomer WHERE month = ?";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        statement1.setString(1,month);
        ResultSet rst = statement1.executeQuery();
        double adprice = 0;
        if (rst.next()){
            adprice = rst.getDouble(1);
        }
        return adprice;
    }

    public Double get100tganan(String month) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql1 = "select SUM(kapanagana) from payments WHERE date = ?";
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        statement1.setString(1,month);
        ResultSet rst = statement1.executeQuery();
        double adprice = 0;
        if (rst.next()){
            adprice = rst.getDouble(1);
        }
        return adprice;
    }

    ////////////////invoiceCustomerDAO///////////

    public List<MonthlyRateDto> getRates(String date) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT goldrate,goodrate FROM monthlyRate WHERE month = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,date);

        List<MonthlyRateDto> list = new ArrayList<>();
        ResultSet rst = statement.executeQuery();
        while (rst.next()){
            MonthlyRateDto dto = new MonthlyRateDto(rst.getDouble("goldrate")
                    ,rst.getDouble("goodrate"));

            list.add(dto);
        }
        return list;

    }



}
