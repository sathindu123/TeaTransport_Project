package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.CustomerManageDAO;
import lk.ijse.bo.custom.CustomerManageBO;
import lk.ijse.dto.CustomerManageDto;
import lk.ijse.entity.CustomerManage;

import java.sql.SQLException;
import java.util.List;

public class CustomerManageBoimpl implements CustomerManageBO {
    CustomerManageDAO customerManageDAO = (CustomerManageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public List<String> getAllID() throws SQLException, ClassNotFoundException {
        return customerManageDAO.getAllID();
    }

    @Override
    public boolean checkCustomerExists(String custId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNam(String id) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String savebtnclick(CustomerManageDto customerManageDto) throws SQLException, ClassNotFoundException {
        return customerManageDAO.save(new CustomerManage(customerManageDto.getId(),
                customerManageDto.getName(),customerManageDto.getAddress(),
                customerManageDto.getNumber()));
    }

    @Override
    public String BtnUpdateClick(CustomerManageDto customerManageDto) throws SQLException, ClassNotFoundException {
        return customerManageDAO.update(new CustomerManage(customerManageDto.getId(),
                customerManageDto.getName(),customerManageDto.getAddress(),
                customerManageDto.getNumber()));
    }

    @Override
    public String BtnClickDelete(String id) throws SQLException, ClassNotFoundException {
        return customerManageDAO.delete(id);
    }
}
