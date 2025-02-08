package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmpSalaryDto;
import lk.ijse.entity.EmpSalary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface EmpSalaryDAO extends CrudDAO<EmpSalary> {
    List<EmpSalaryDto> loadPayPriceEmp() throws SQLException, ClassNotFoundException;
    int totalSalaryEmp(String id) throws SQLException, ClassNotFoundException;
    List<EmpSalaryDto> getEmpTotPayPrice(String id) throws SQLException, ClassNotFoundException;
    int getSalaryAll() throws SQLException, ClassNotFoundException;
}
