package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.entity.Advance;

import java.sql.SQLException;

public interface LoginPAgeDAO extends CrudDAO<Advance> {
    Boolean cheach(String cu, String cp , String cpc) throws SQLException, ClassNotFoundException;
    String updateCreadtitaonal(String cu, String np, String nu) throws SQLException, ClassNotFoundException;
    Boolean cheack(String un, String up) throws SQLException, ClassNotFoundException;
    String getUderName() throws SQLException, ClassNotFoundException;
    String getPAssword() throws SQLException, ClassNotFoundException;
}
