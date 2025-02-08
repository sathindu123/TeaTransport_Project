package lk.ijse.Dao.custom;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface InvoiceManageDAO {
    String updateData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException;
    String insertData(InvoiceManageDto invoiceManageDto, StockDto stockDto) throws SQLException, ClassNotFoundException;
    double getPric(String productId) throws SQLException, ClassNotFoundException;
    String btnSaveAdvance(InvoiceManageDto invoiceManageDto) throws SQLException, ClassNotFoundException;
    String btnUpdateAdvance(String id,String date,double price) throws SQLException, ClassNotFoundException;
    String insertDataPohora(InvoiceManageDto invoiceManageDto, StockDto stockDto, int warik, double totalPrice) throws SQLException, ClassNotFoundException;
    String deleteDataPohora(InvoiceManageDto invoiceManageDto, StockDto stockDto, int warik, double totalPrice) throws SQLException, ClassNotFoundException;
    List<LoadProductPurchase> loadT() throws SQLException, ClassNotFoundException;
    List<LoadProductPurchase> loadO() throws SQLException, ClassNotFoundException;
    List<LoadProductPurchase> loadF() throws SQLException, ClassNotFoundException;
    List<AdvanceDto> loadA() throws SQLException, ClassNotFoundException;
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
