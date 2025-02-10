package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.StockDAO;
import lk.ijse.bo.custom.StockBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockBoimpl implements StockBO {

    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);

    public String save(StockDto stockDto) throws SQLException, ClassNotFoundException{
        return stockDAO.save(new Stock(stockDto.getId(),stockDto.getCategory(),stockDto.getCount(),stockDto.getPrice()));
    }

    public String update(StockDto stockDto) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(stockDto.getId(),stockDto.getCategory(),stockDto.getCount(),stockDto.getPrice()));
    }

    public String btnUpdateClick(StockDto stockDto) throws SQLException, ClassNotFoundException {
        return stockDAO.btnUpdateClick(new Stock(stockDto.getId(),stockDto.getCategory(),stockDto.getCount(),stockDto.getPrice()));
    }

    public String getNames(String id) throws SQLException, ClassNotFoundException {
        return stockDAO.getNames(id);
    }

    public int getCounts(String id) throws SQLException, ClassNotFoundException {
        return stockDAO.getCounts(id);
    }

}
