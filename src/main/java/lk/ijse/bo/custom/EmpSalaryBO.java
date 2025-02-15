package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EmpSalaryDto;
import lk.ijse.entity.EmpSalary;

import java.sql.SQLException;
import java.util.List;

public interface EmpSalaryBO extends SuperBO {
    List<EmpSalaryDto> loadPayPriceEmp() throws SQLException, ClassNotFoundException;
    int totalSalaryEmp(String id) throws SQLException, ClassNotFoundException;
    List<EmpSalaryDto> getEmpTotPayPrice(String id) throws SQLException, ClassNotFoundException;
    int getSalaryAll() throws SQLException, ClassNotFoundException;

    String insertSalarayEmp(EmpSalaryDto dto) throws SQLException, ClassNotFoundException;
}
