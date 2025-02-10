package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.entity.CustomerManage;

import java.sql.SQLException;
import java.util.List;

public interface CustomerManageDAO extends CrudDAO<CustomerManage> {


    List<String> getAllID() throws SQLException, ClassNotFoundException;
   boolean checkCustomerExists(String custId) throws SQLException, ClassNotFoundException;
   String getNam(String id) throws SQLException, ClassNotFoundException;

}
