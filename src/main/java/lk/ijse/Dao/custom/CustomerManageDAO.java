package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.entity.CustomerManage;

import java.sql.SQLException;
import java.util.List;

public interface CustomerManageDAO extends CrudDAO<CustomerManage> {


   // CustomerManageDto custSerachsummery(String id) throws SQLException, ClassNotFoundException;
   // List<ViewManageDto> getStockPurchases() throws SQLException, ClassNotFoundException;
   // int getTot() throws SQLException, ClassNotFoundException;
    List<String> getAllID() throws SQLException, ClassNotFoundException;
   // List<ViewManageDto> getPohoraStockPurchase() throws SQLException, ClassNotFoundException;
   boolean checkCustomerExists(String custId) throws SQLException, ClassNotFoundException;
   String getNam(String id) throws SQLException, ClassNotFoundException;

}
