package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.ViewEmployeeFormDAO;
import lk.ijse.bo.custom.ViewEmployeeFormBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmployeeManageDto;
import lk.ijse.entity.EmployeeManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewEmployeeFormBoimpl implements ViewEmployeeFormBO {

    ViewEmployeeFormDAO viewEmployeeFormDAO = (ViewEmployeeFormDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VIEWEMP);

    public List<EmployeeManageDto> getAllCustomer() throws  Exception{
       List<EmployeeManage> ar = viewEmployeeFormDAO.getAllCustomer();

        List<EmployeeManageDto> dtoList = new ArrayList<>();

        for (EmployeeManage rate : ar) {
            EmployeeManageDto dto = new EmployeeManageDto(rate.getId(), rate.getName(),
                    rate.getAddress(),rate.getNumber());
            dtoList.add(dto);
        }

        return dtoList;
    }



    public EmployeeManageDto BtnOKClickSearchEmployee(String id) throws SQLException, ClassNotFoundException {
        EmployeeManage emp = viewEmployeeFormDAO.BtnOKClickSearchEmployee(id);
        if (emp != null) {
            return new EmployeeManageDto(
                    emp.getId(),
                    emp.getName(),
                    emp.getAddress(),
                    emp.getNumber()
            );
        } else {
            return null;
        }
    }


}
