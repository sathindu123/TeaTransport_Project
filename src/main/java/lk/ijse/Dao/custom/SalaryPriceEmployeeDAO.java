package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.salaryPriceEmployeeDto;
import lk.ijse.entity.EmpSalary;
import lk.ijse.entity.SalaryPriceEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SalaryPriceEmployeeDAO extends CrudDAO<SalaryPriceEmployee> {
    int getSalaryEmp() throws SQLException, ClassNotFoundException;
}
