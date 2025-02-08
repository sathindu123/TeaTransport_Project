package lk.ijse.Dao.custom;

import lk.ijse.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdvanceDAO {
    double getTotalAdvance() throws SQLException, ClassNotFoundException;

}