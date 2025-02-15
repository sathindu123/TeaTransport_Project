package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.ViewCustomerFormDAO;
import lk.ijse.bo.custom.ViewCustomerFormBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.CustomerManageDto;
import lk.ijse.entity.CustomerManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewCustomerFormBoimpl implements ViewCustomerFormBO {

    ViewCustomerFormDAO viewCustomerFormDAO = (ViewCustomerFormDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VIEWCUST);

    public List<CustomerManageDto> getAllCustomer() throws  Exception{
        List<CustomerManage> ar = viewCustomerFormDAO.getAllCustomer();

        List<CustomerManageDto> dtoList = new ArrayList<>();

        for (CustomerManage rate : ar) {
            CustomerManageDto dto = new CustomerManageDto(rate.getId(), rate.getName(),
                    rate.getAddress(),rate.getNumber());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public CustomerManageDto searchCustomer(String id) throws Exception{
        CustomerManage customer = viewCustomerFormDAO.searchCustomer(id);

        if (customer != null) {
            return new CustomerManageDto(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getNumber()
            );
        } else {
            return null;
        }
    }

    public List<String> searchCus(String typedText) throws SQLException, ClassNotFoundException {
        return viewCustomerFormDAO.searchCus(typedText);
    }


}
