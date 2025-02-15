package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.EmployeeManageDAO;
import lk.ijse.bo.custom.EmployeeManageBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmployeeManageDto;
import lk.ijse.entity.EmployeeManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeManageBoimpl implements EmployeeManageBO {

    EmployeeManageDAO employeeManageDAO = (EmployeeManageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEEmANAGE);



    @Override
    public String BtnAddEmployee(EmployeeManageDto employeeManagedto) throws SQLException, ClassNotFoundException {
        return employeeManageDAO.save(new EmployeeManage(employeeManagedto.getId(),
                employeeManagedto.getName(),
                employeeManagedto.getAddress(),
                employeeManagedto.getNumber())
        );
    }

    @Override
    public String BtnClickEmployeeUpdate(EmployeeManageDto employeeManagedto) throws SQLException, ClassNotFoundException {
        return employeeManageDAO.update(new EmployeeManage(employeeManagedto.getId(),
                employeeManagedto.getName(),
                employeeManagedto.getAddress(),
                employeeManagedto.getNumber())
        );
    }
}
