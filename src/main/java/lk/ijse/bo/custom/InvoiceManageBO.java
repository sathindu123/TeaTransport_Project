package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.*;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceManageBO extends SuperBO {
    String updateData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException;
    String insertData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException;
    double getPric(String productId) throws SQLException, ClassNotFoundException;
    String btnSaveAdvance(InvoiceManageDto invoiceManageDto) throws SQLException, ClassNotFoundException;
    String btnUpdateAdvance(String id,String date,double price) throws SQLException, ClassNotFoundException;
    String insertDataPohora(InvoiceManageDto invoiceManageDto, StockDto stockDto, int warik, double totalPrice) throws SQLException, ClassNotFoundException;
    String deleteDataPohora(InvoiceManageDto invoiceManageDto, StockDto stockDto, int warik, double totalPrice) throws SQLException, ClassNotFoundException;
    List<LoadProductPurchaseDto> loadT() throws SQLException, ClassNotFoundException;
     List<LoadProductPurchaseDto> loadO() throws SQLException, ClassNotFoundException;
    List<LoadProductPurchaseDto> loadF() throws SQLException, ClassNotFoundException;
    String getNameAdvance(String mm) throws SQLException, ClassNotFoundException;
    String getProductIDT(String mk) throws SQLException, ClassNotFoundException;
    String DeleteData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException;
    String getIdF(String mm) throws SQLException, ClassNotFoundException;
    String getProductIDF(String mk) throws SQLException, ClassNotFoundException;
    String deleteOtherData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException;
    String inserPaymentValues(String id, String date, PaymentDto pDto, double Higa) throws SQLException, ClassNotFoundException;
    String getPID() throws SQLException, ClassNotFoundException;
    String getT(String x, String startDate) throws SQLException, ClassNotFoundException;
    String getT2(String x, String startDate) throws SQLException, ClassNotFoundException;
    double getPricPhora(String productId) throws SQLException, ClassNotFoundException;
}
