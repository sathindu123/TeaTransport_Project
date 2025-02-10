package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.entity.SalaryPriceEmployee;

import java.sql.SQLException;

public interface SalaryPriceEmployeeDAO extends CrudDAO<SalaryPriceEmployee> {
    int getSalaryEmp() throws SQLException, ClassNotFoundException;
}
