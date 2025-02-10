package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.DailyHomePageBO;
import lk.ijse.dto.DailyHomePageDto;

import java.sql.SQLException;
import java.util.List;

public class DailyhomepageBoimpl implements DailyHomePageBO {
    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int getTeaLeafCoutn(String[] ar) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public double getPohoraTotal(String[] ar) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public double getOtherTotal() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public List<DailyHomePageDto> LoadTable() {
        return List.of();
    }

    @Override
    public String btnSaveDailyCount(DailyHomePageDto dto) {
        return "";
    }

    @Override
    public String getNam(String id) {
        return "";
    }

    @Override
    public String btnUpdateDailyCount(DailyHomePageDto dto) {
        return "";
    }
}
