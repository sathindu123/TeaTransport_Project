package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EmployeeManageDto;
import lk.ijse.entity.EmployeeManage;

import java.sql.SQLException;

public interface EmployeeManageBO extends SuperBO {

    String BtnAddEmployee(EmployeeManageDto employeeManageDto) throws SQLException, ClassNotFoundException;

    String BtnClickEmployeeUpdate(EmployeeManageDto employeeManageDto) throws SQLException, ClassNotFoundException;
}
