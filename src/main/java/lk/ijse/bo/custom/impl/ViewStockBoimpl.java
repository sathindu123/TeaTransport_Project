package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.ViewStockDAO;
import lk.ijse.bo.custom.ViewStockBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.MonthlyRateDto;
import lk.ijse.dto.StockDto;
import lk.ijse.entity.MonthlyRate;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewStockBoimpl implements ViewStockBO {
    ViewStockDAO viewStockDAO = (ViewStockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VIEWSTOCK);

    public List<StockDto> getAllProducts() throws SQLException, ClassNotFoundException {
       List<Stock> ar = viewStockDAO.getAllProducts();

        List<StockDto> dtoList = new ArrayList<>();

        for (Stock rate : ar) {
            StockDto dto = new StockDto(rate.getId(), rate.getCategory(),rate.getCount(),rate.getPrice());
            dtoList.add(dto);
        }

        return dtoList;
    }


    public StockDto searchProduct(String id) throws SQLException, ClassNotFoundException {
        Stock ar = viewStockDAO.searchProduct(id);
        if (ar != null) {
            return new StockDto(
                    ar.getId(),
                    ar.getCategory(),
                    ar.getCount(),
                    ar.getPrice()
            );
        } else {
            return null;
        }
    }

    public List<String> searchStock(String typedText) throws SQLException, ClassNotFoundException {
        return viewStockDAO.searchStock(typedText);
    }

    public String getNameId(String productId) throws SQLException, ClassNotFoundException {
       return viewStockDAO.getNameId(productId);
    }

}
