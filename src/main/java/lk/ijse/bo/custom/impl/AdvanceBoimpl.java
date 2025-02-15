package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.AdvanceDAO;
import lk.ijse.bo.custom.AdvanceBo;

import java.sql.SQLException;

public class AdvanceBoimpl implements AdvanceBo {
    AdvanceDAO advanceDAO = (AdvanceDAO)  DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADVANCE);
    @Override
    public double getTotalAdvance() throws SQLException, ClassNotFoundException {
        return advanceDAO.getTotalAdvance();
    }
}
