package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.SalaryPriceEmployeeDAO;
import lk.ijse.bo.custom.SalaryPriceEmployeeBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.salaryPriceEmployeeDto;
import lk.ijse.entity.SalaryPriceEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryPriceEmployeeBoimpl implements SalaryPriceEmployeeBO {

    SalaryPriceEmployeeDAO salaryPriceEmployeeDAO = (SalaryPriceEmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SALARYPRICEEMP);

    public String save(SalaryPriceEmployee dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO salaryPriceEmployee (price) VALUES(?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1,dto.getPrice());

        int  resp = statement.executeUpdate();
        return resp > 0 ? "Sucssess" : "Faild";
    }



    public int getSalaryEmp() throws SQLException, ClassNotFoundException {
        return salaryPriceEmployeeDAO.getSalaryEmp();
    }

    @Override
    public String insertSlary(salaryPriceEmployeeDto dto) throws SQLException, ClassNotFoundException {
        return salaryPriceEmployeeDAO.save(new SalaryPriceEmployee(dto.getPrice()));
    }
}
