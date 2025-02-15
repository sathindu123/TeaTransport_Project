package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.dto.*;
import lk.ijse.entity.InvoiceManage;
import lk.ijse.entity.LoadProductPurchase;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Stock;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceManageDAO extends CrudDAO<InvoiceManage> {
    String updateData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException;
    String insertData(InvoiceManage invoiceManage, Stock stock) throws SQLException, ClassNotFoundException;
    double getPric(String productId) throws SQLException, ClassNotFoundException;
    String btnSaveAdvance(InvoiceManage invoiceManageDto) throws SQLException, ClassNotFoundException;
    String btnUpdateAdvance(String id,String date,double price) throws SQLException, ClassNotFoundException;
    String insertDataPohora(InvoiceManage invoiceManageDto, Stock stockDto, int warik, double totalPrice) throws SQLException, ClassNotFoundException;
    String deleteDataPohora(InvoiceManage invoiceManageDto, Stock stockDto, int warik, double totalPrice) throws SQLException, ClassNotFoundException;
    List<LoadProductPurchase> loadT() throws SQLException, ClassNotFoundException;
    List<LoadProductPurchase> loadO() throws SQLException, ClassNotFoundException;
    List<LoadProductPurchase> loadF() throws SQLException, ClassNotFoundException;
    List<AdvanceDto> loadA() throws SQLException, ClassNotFoundException;
    String getNameAdvance(String mm) throws SQLException, ClassNotFoundException;
    String getProductIDT(String mk) throws SQLException, ClassNotFoundException;
    String DeleteData(InvoiceManage invoiceManageDto, Stock stockDto) throws SQLException, ClassNotFoundException;
    String getIdF(String mm) throws SQLException, ClassNotFoundException;
    String getProductIDF(String mk) throws SQLException, ClassNotFoundException;
    String deleteOtherData(InvoiceManage invoiceManageDto, Stock stockDto) throws SQLException, ClassNotFoundException;
    String inserPaymentValues(String id, String date, Payment pDto, double Higa) throws SQLException, ClassNotFoundException;
    String getPID() throws SQLException, ClassNotFoundException;
    String getT(String x, String startDate) throws SQLException, ClassNotFoundException;
    String getT2(String x, String startDate) throws SQLException, ClassNotFoundException;
    double getPricPhora(String productId) throws SQLException, ClassNotFoundException;
}
