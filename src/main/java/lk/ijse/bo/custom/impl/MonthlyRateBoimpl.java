package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.MonthlyRateDAO;
import lk.ijse.bo.custom.MonthlyRateBO;
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

public class MonthlyRateBoimpl implements MonthlyRateBO {
    MonthlyRateDAO monthlyRateDAO = (MonthlyRateDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MONTHLYRATE);

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


    public List<MonthlyRateDto> loadDetails() throws SQLException, ClassNotFoundException {

       List<MonthlyRate> ar = monthlyRateDAO.loadDetails();

        List<MonthlyRateDto> dtoList = new ArrayList<>();

        for (MonthlyRate rate : ar) {
            MonthlyRateDto dto = new MonthlyRateDto(rate.getRate(), rate.getRate1());
            dtoList.add(dto);
        }

        return dtoList;
    }


    public List<MonthlyRateDto> getLeafPrice(String date) throws SQLException, ClassNotFoundException {
        List<MonthlyRate> ar = monthlyRateDAO.getLeafPrice(date);
        List<MonthlyRateDto> dtoList = new ArrayList<>();

        for (MonthlyRate rate : ar) {
            MonthlyRateDto dto = new MonthlyRateDto(rate.getRate(), rate.getRate1());
            dtoList.add(dto);
        }

        return dtoList;

    }

    public double[] getAllCustomerHiga(String dateMonth, double lblGoodLea, double lblGoldLeaf) throws SQLException, ClassNotFoundException {
            return monthlyRateDAO.getAllCustomerHiga(dateMonth,lblGoodLea,lblGoldLeaf);

    }

    public List<Integer> getallMonthlyLeafCount(String date) throws SQLException, ClassNotFoundException {
       return monthlyRateDAO.getallMonthlyLeafCount(date);
    }

    public Double getPurchase(String date) throws SQLException, ClassNotFoundException {
        return monthlyRateDAO.getPurchase(date);
    }

    public Double getAdvance(String month) throws SQLException, ClassNotFoundException {
       return monthlyRateDAO.getAdvance(month);
    }

    public Double getPohora(String month) throws SQLException, ClassNotFoundException {
        return monthlyRateDAO.getPohora(month);
    }

    public Double get100tganan(String month) throws SQLException, ClassNotFoundException {
        return monthlyRateDAO.get100tganan(month);
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

    @Override
    public String btnSaveRate(MonthlyRateDto dto) throws SQLException, ClassNotFoundException {
        return monthlyRateDAO.save(new MonthlyRate(dto.getMonth(),dto.getRate(),dto.getRate1()));
    }

    @Override
    public String btnUpdateRate(MonthlyRateDto dto) throws SQLException, ClassNotFoundException {
        return monthlyRateDAO.update(new MonthlyRate(dto.getMonth(),dto.getRate(),dto.getRate1()));
    }


}
