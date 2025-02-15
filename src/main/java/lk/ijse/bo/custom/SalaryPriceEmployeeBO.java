package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.salaryPriceEmployeeDto;
import lk.ijse.entity.SalaryPriceEmployee;

import java.sql.SQLException;

public interface SalaryPriceEmployeeBO extends SuperBO {
    int getSalaryEmp() throws SQLException, ClassNotFoundException;

    String insertSlary(salaryPriceEmployeeDto dto) throws SQLException, ClassNotFoundException;
}
