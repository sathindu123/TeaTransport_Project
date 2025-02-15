package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.EmpWorkDetailsDAO;
import lk.ijse.bo.custom.EmpWorkDetailsBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmpWordDetailDto;
import lk.ijse.entity.EmpWordDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpWorkDetailBoimpl implements EmpWorkDetailsBO {

    EmpWorkDetailsDAO empWorkDetailsDAO = (EmpWorkDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPWORK);

    public String save(EmpWordDetail empdto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO empworddetails VALUES(?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1,empdto.getId());
        statement.setString(2,empdto.getDate());


        int resp = statement.executeUpdate();
        return resp > 0 ? "sucsess" : "fail";

    }


    @Override
    public String addEmpDetails(EmpWordDetailDto empdto) throws SQLException, ClassNotFoundException {
        return empWorkDetailsDAO.save(new EmpWordDetail(empdto.getId(),empdto.getDate()));
    }
}
