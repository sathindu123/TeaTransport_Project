package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.CustomerManageDAO;
import lk.ijse.Dao.custom.DailyHomePageDAO;
import lk.ijse.Dao.custom.QueryDAO;
import lk.ijse.bo.custom.DailyHomePageBO;
import lk.ijse.dto.DailyHomePageDto;
import lk.ijse.entity.DailyHomePage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DailyhomepageBoimpl implements DailyHomePageBO {
    DailyHomePageDAO dailyHomePageDAO = (DailyHomePageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DAILY);
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    CustomerManageDAO customerManageDAO = (CustomerManageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return dailyHomePageDAO.getCount();
    }

    @Override
    public int getTeaLeafCoutn(String[] ar) throws SQLException, ClassNotFoundException {
        return dailyHomePageDAO.getTeaLeafCoutn(ar);
    }

    @Override
    public double getPohoraTotal(String[] ar) throws SQLException, ClassNotFoundException {
        return dailyHomePageDAO.getPohoraTotal(ar);
    }

    @Override
    public double getOtherTotal() throws SQLException, ClassNotFoundException {
        return dailyHomePageDAO.getOtherTotal();
    }

    @Override
    public List<DailyHomePageDto> LoadTable() throws SQLException, ClassNotFoundException {
        ArrayList<DailyHomePage> dailyHomePages = (ArrayList<DailyHomePage>) queryDAO.LoadTable();
        ArrayList<DailyHomePageDto> dailyHomePageDtos = new ArrayList<>();
        for (DailyHomePage dailyHomePage:dailyHomePages){
            dailyHomePageDtos.add(new DailyHomePageDto(dailyHomePage.getId(),dailyHomePage.getName(),dailyHomePage.getDate()
            ,dailyHomePage.getGoldLeaf(),dailyHomePage.getGoodLeaf()));
        }
        return dailyHomePageDtos;
    }

    @Override
    public String btnSaveDailyCount(DailyHomePageDto dto) throws SQLException, ClassNotFoundException {
        return dailyHomePageDAO.save(new DailyHomePage(dto.getId(),dto.getDate(),dto.getGoldLeaf(),dto.getGoodLeaf()));
    }


    @Override
    public String getNam(String id) throws SQLException, ClassNotFoundException {
        return customerManageDAO.getNam(id);
    }

    @Override
    public String btnUpdateDailyCount(DailyHomePageDto dto) throws SQLException, ClassNotFoundException {
        return dailyHomePageDAO.update(new DailyHomePage(dto.getId(),dto.getDate()));
    }
}
