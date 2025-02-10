package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.dto.EmployeeManageDto;
import lk.ijse.entity.EmployeeManage;

import java.sql.SQLException;
import java.util.List;

public interface ViewEmployeeFormDAO extends CrudDAO<EmployeeManage> {
    List<EmployeeManageDto> getAllCustomer() throws Exception;
    EmployeeManageDto BtnOKClickSearchEmployee(String id) throws SQLException, ClassNotFoundException;

}
