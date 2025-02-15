package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.LoginPAgeDAO;
import lk.ijse.bo.custom.LoginPAgeBO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.Advance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPAgeBoimpl implements LoginPAgeBO {
    LoginPAgeDAO loginPAgeDAO = (LoginPAgeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGINPAGE);
    public Boolean cheach(String cu, String cp , String cpc) throws SQLException, ClassNotFoundException {
        return loginPAgeDAO.cheach(cu,cp,cpc);
    }

    public String updateCreadtitaonal(String cu, String np, String nu) throws SQLException, ClassNotFoundException {
        return loginPAgeDAO.updateCreadtitaonal(cu,np,nu);

    }

    public Boolean cheack(String un, String up) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Select userName from admin WHERE userName = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,un);
        ResultSet resultSet = statement.executeQuery();
        String sss = "s";
        System.out.println(sss);
        while (resultSet.next()){
            System.out.println(sss);
            sss = resultSet.getString("userName");

        }
        if(sss.equals(un)){
            String sql1 = "Select password from admin WHERE password = ?";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            statement1.setString(1,up);
            ResultSet resultSet1 = statement1.executeQuery();
            String sss1 = "s";
            while (resultSet1.next()){
                System.out.println(sss1);
                sss1 = resultSet1.getString("password");
            }
            if (sss1.equals(up)){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public String getUderName() throws SQLException, ClassNotFoundException {
        return loginPAgeDAO.getUderName();
    }

    public String getPAssword() throws SQLException, ClassNotFoundException {
        return loginPAgeDAO.getPAssword();
    }


}
