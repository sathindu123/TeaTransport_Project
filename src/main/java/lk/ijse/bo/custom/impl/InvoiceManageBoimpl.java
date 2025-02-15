package lk.ijse.bo.custom.impl;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.InvoiceManageDAO;
import lk.ijse.bo.custom.InvoiceManageBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;
import lk.ijse.entity.InvoiceManage;
import lk.ijse.entity.LoadProductPurchase;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Stock;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InvoiceManageBoimpl implements InvoiceManageBO {
    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};

    InvoiceManageDAO invoiceManageDAO = (InvoiceManageDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVOICEMANAGE);

    public String updateData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.insertData(
                new InvoiceManage(
                        invoiceManageDto.getCustId(),
                        invoiceManageDto.getDate(),
                        invoiceManageDto.getProductId(),
                        invoiceManageDto.getCount(),
                        invoiceManageDto.getPrice()
                ),
                new Stock(
                        stockDto.getId(),
                        stockDto.getCategory(),
                        stockDto.getCount(),
                        stockDto.getPrice()
                )
        );
    }

    public String insertData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.insertData(
                new InvoiceManage(
                        invoiceManageDto.getCustId(),
                        invoiceManageDto.getDate(),
                        invoiceManageDto.getProductId(),
                        invoiceManageDto.getCount(),
                        invoiceManageDto.getPrice()
                ),
                new Stock(
                        stockDto.getId(),
                        stockDto.getCategory(),
                        stockDto.getCount(),
                        stockDto.getPrice()
                )
        );
    }

    public double getPric(String productId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT price FROM product WHERE type = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getDouble("price");
        } else {
            throw new SQLException("No product found with ID: " + productId);
        }
    }

    public String btnSaveAdvance(InvoiceManageDto invoiceManageDto) throws SQLException, ClassNotFoundException {
       return invoiceManageDAO.btnSaveAdvance(new InvoiceManage(invoiceManageDto.getCustId(),invoiceManageDto.getDate()
       ,invoiceManageDto.getProductId(),invoiceManageDto.getCount(),invoiceManageDto.getPrice()));
    }




    public String btnUpdateAdvance(String id,String date,double price) throws SQLException, ClassNotFoundException {
       return invoiceManageDAO.btnUpdateAdvance(id,date,price);
    }

    public String insertDataPohora(InvoiceManageDto invoiceManageDto, StockDto stockDto, int warik, double totalPrice) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.insertDataPohora(new InvoiceManage(
                        invoiceManageDto.getCustId(),
                        invoiceManageDto.getDate(),
                        invoiceManageDto.getProductId(),
                        invoiceManageDto.getCount(),
                        invoiceManageDto.getPrice()

                ),new Stock(stockDto.getId(),
                        stockDto.getCategory(),
                        stockDto.getCount(),
                        stockDto.getPrice()
                ),warik,totalPrice
        );


    }


    public String deleteDataPohora(InvoiceManageDto invoiceManageDto, StockDto stockDto, int warik, double totalPrice) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.deleteDataPohora(new InvoiceManage(
                        invoiceManageDto.getCustId(),
                        invoiceManageDto.getDate(),
                        invoiceManageDto.getProductId(),
                        invoiceManageDto.getCount(),
                        invoiceManageDto.getPrice()

                ),new Stock(stockDto.getId(),
                        stockDto.getCategory(),
                        stockDto.getCount(),
                        stockDto.getPrice()
                ),warik,totalPrice
        );

    }


    public List<LoadProductPurchaseDto> loadT() throws SQLException, ClassNotFoundException {
        List<LoadProductPurchase> ar = invoiceManageDAO.loadT();
        List<LoadProductPurchaseDto> dtoList = new ArrayList<>();

        for (LoadProductPurchase rate : ar) {
            LoadProductPurchaseDto dto = new LoadProductPurchaseDto(rate.getCustName(), rate.getDate(),rate.getType(),rate.getCount(),rate.getPrice());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<LoadProductPurchaseDto> loadO() throws SQLException, ClassNotFoundException {
        List<LoadProductPurchase> ar = invoiceManageDAO.loadO();
        List<LoadProductPurchaseDto> dtoList = new ArrayList<>();

        for (LoadProductPurchase rate : ar) {
            LoadProductPurchaseDto dto = new LoadProductPurchaseDto(rate.getCustName(), rate.getDate(),rate.getType(),rate.getCount(),rate.getPrice());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<LoadProductPurchaseDto> loadF() throws SQLException, ClassNotFoundException {
        List<LoadProductPurchase> ar = invoiceManageDAO.loadF();

        List<LoadProductPurchaseDto> dtoList = new ArrayList<>();

        for (LoadProductPurchase rate : ar) {
            LoadProductPurchaseDto dto = new LoadProductPurchaseDto(rate.getCustName(), rate.getDate(),rate.getType(),rate.getCount(),rate.getPrice());
            dtoList.add(dto);
        }

        return dtoList;
    }

//    public List<AdvanceDto> loadA() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql ="SELECT a.custId,c.name, a.date,SUM(a.monthPrice) AS total_price_sum\n" +
//                "FROM advance a JOIN customer c ON a.custId = c.id\n" +
//                "GROUP BY custId, date;";
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        ResultSet resultSet = statement.executeQuery();
//
//        ArrayList<AdvanceDto> rst = new ArrayList<>();
//
//        while (resultSet.next()){
//            AdvanceDto dd = new AdvanceDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getDouble(4)
//            );
//            rst.add(dd);
//        }
//        return rst;
//    }

    public String getNameAdvance(String mm) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.getNameAdvance(mm);
    }

    public String getProductIDT(String mk) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.getProductIDT(mk);
    }

    public String DeleteData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.DeleteData(new InvoiceManage(
                invoiceManageDto.getCustId(),
                invoiceManageDto.getDate(),
                invoiceManageDto.getProductId(),
                invoiceManageDto.getCount(),
                invoiceManageDto.getPrice()

        ),new Stock(stockDto.getId(),
                stockDto.getCategory(),
                stockDto.getCount(),
                stockDto.getPrice())
        );

    }

    public String getIdF(String mm) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.getIdF(mm);
    }

    public String getProductIDF(String mk) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.getProductIDF(mk);
    }

    public String deleteOtherData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.deleteOtherData(new InvoiceManage(
                        invoiceManageDto.getCustId(),
                        invoiceManageDto.getDate(),
                        invoiceManageDto.getProductId(),
                        invoiceManageDto.getCount(),
                        invoiceManageDto.getPrice()

                ),new Stock(stockDto.getId(),
                        stockDto.getCategory(),
                        stockDto.getCount(),
                        stockDto.getPrice())
        );


    }

    public String inserPaymentValues(String id, String date, PaymentDto pDto, double Higa) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.inserPaymentValues(id,date,new Payment(pDto.getId(),pDto.getCustId(),pDto.getCustName()
        ,pDto.getDate(),pDto.getGoodLeaf(),pDto.getGoldLeaf(),pDto.getTealeafAmount(),pDto.getTotalAdvance(),pDto.getTotalFertilize()
        ,pDto.getTotalTeaPacket(),pDto.getTotalOthers(),pDto.getTotalAmounr(),pDto.getPreviasMonthlyHiga(),pDto.getCounts()
        ,pDto.getRategood(),pDto.getRategold(),pDto.getNextHigaPrice(),pDto.getNextHigaPohora(),pDto.getNextAdvanceHiga(),pDto.getKapanagana()),Higa);
    }


    public String getPID() throws SQLException, ClassNotFoundException {
      return invoiceManageDAO.getPID();
    }

    public String getT(String x, String startDate) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.getT(x,startDate);
    }

    public String getT2(String x, String startDate) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Select SUM(goldLeafAmount+goodLeafAmount) from teabaginventory WHERE custId = ? AND date = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,x);
        statement.setString(2,startDate);

        String xc = "";
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            xc = String.valueOf(resultSet.getInt(1));
        }
        if(xc == "0"){
            return "";
        }

        return xc;
    }

    public double getPricPhora(String productId) throws SQLException, ClassNotFoundException {
        return invoiceManageDAO.getPricPhora(productId);
    }
}
