package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerManageDto;
import lk.ijse.entity.CustomerManage;

import java.sql.SQLException;
import java.util.List;

public interface CustomerManageBO extends SuperBO {
    List<String> getAllID() throws SQLException, ClassNotFoundException;
   boolean checkCustomerExists(String custId) throws SQLException, ClassNotFoundException;
   String getNam(String id) throws SQLException, ClassNotFoundException;

    String savebtnclick(CustomerManageDto customerManageDto) throws SQLException, ClassNotFoundException;

    String BtnUpdateClick(CustomerManageDto customerManageDto) throws SQLException, ClassNotFoundException;

    String BtnClickDelete(String id) throws SQLException, ClassNotFoundException;
}
