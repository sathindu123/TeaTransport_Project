package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.EmpWordDetailDto;
import lk.ijse.entity.EmpWordDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface EmpWorkDetailsDAO extends CrudDAO<EmpWordDetail> {

}
