package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerManageDto;
import lk.ijse.entity.CustomerManage;

import java.sql.SQLException;
import java.util.List;

public interface ViewCustomerFormBO extends SuperBO {
    List<CustomerManageDto> getAllCustomer() throws  Exception;
    CustomerManageDto searchCustomer(String id) throws Exception;
    List<String> searchCus(String typedText) throws SQLException, ClassNotFoundException;
}
