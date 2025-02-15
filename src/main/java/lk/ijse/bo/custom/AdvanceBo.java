package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface AdvanceBo extends SuperBO {
    double getTotalAdvance() throws SQLException, ClassNotFoundException;

}