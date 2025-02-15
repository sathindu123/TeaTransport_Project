package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.entity.Advance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdvanceDAO extends CrudDAO<Advance> {
    double getTotalAdvance() throws SQLException, ClassNotFoundException;

}