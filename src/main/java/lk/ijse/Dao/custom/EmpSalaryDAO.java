package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.entity.EmpSalary;

import java.sql.SQLException;
import java.util.List;

public interface EmpSalaryDAO extends CrudDAO<EmpSalary> {

    List<EmpSalary> loadPayPriceEmp(String startDate, String endDate) throws SQLException, ClassNotFoundException;
    int totalSalaryEmp(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException;
    List<EmpSalary> getEmpTotPayPrice(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException;
    int getSalaryAll(String startDate, String endDate) throws SQLException, ClassNotFoundException;
}
