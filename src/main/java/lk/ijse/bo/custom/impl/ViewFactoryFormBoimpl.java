package lk.ijse.bo.custom.impl;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.FactoryManageDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewFactoryFormBoimpl {


    public List<FactoryManageDto> getAllCustomer() throws  Exception{
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "Select * from factory";

        PreparedStatement statement = connection.prepareStatement(sql);

        List<FactoryManageDto> factoryDtos = new ArrayList<>();

        ResultSet rst = statement.executeQuery();
        while(rst.next()){
            FactoryManageDto dto = new FactoryManageDto(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getInt(4));
            factoryDtos.add(dto);
        }
        return factoryDtos;
    }

    public FactoryManageDto BtnOKClickSearchFactory(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM factory WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);

        ResultSet rst = statement.executeQuery();

        if(rst.next()){
            FactoryManageDto dto = new FactoryManageDto(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getInt(4));

            return dto;
        }
        return null;
    }



}
