package lk.ijse.Dao;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.CustomerManageDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface CrudDAO<T> extends SuperDAO{

    String save(T t) throws SQLException, ClassNotFoundException;
    String update(T t) throws SQLException, ClassNotFoundException;
    String delete(String t) throws SQLException, ClassNotFoundException;



}
