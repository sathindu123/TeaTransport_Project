package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmployeeManageDto;
import lk.ijse.entity.EmployeeManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ViewEmployeeFormDAO extends CrudDAO<EmployeeManage> {
    List<EmployeeManageDto> getAllCustomer() throws Exception;
    EmployeeManageDto BtnOKClickSearchEmployee(String id) throws SQLException, ClassNotFoundException;

}
