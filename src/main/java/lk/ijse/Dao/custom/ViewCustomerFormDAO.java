package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.CustomerManageDto;
import lk.ijse.entity.CustomerManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ViewCustomerFormDAO extends CrudDAO<CustomerManage> {
    List<CustomerManage> getAllCustomer() throws  Exception;
    CustomerManage searchCustomer(String id) throws Exception;
    List<String> searchCus(String typedText) throws SQLException, ClassNotFoundException;
}
