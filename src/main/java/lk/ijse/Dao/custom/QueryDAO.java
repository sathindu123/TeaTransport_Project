package lk.ijse.Dao.custom;

import lk.ijse.Dao.SuperDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;
import lk.ijse.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    CustomerManageDto custSerachsummery(String id) throws SQLException, ClassNotFoundException;
    List<ViewManage> getStockPurchases() throws SQLException, ClassNotFoundException;
    int getTot() throws SQLException, ClassNotFoundException;
    List<ViewManage> getPohoraStockPurchase() throws SQLException, ClassNotFoundException;
    List<DailyHomePage> LoadTable() throws SQLException, ClassNotFoundException;

    List<getCustomerAllDetails> getAllCustomerDetails() throws SQLException, ClassNotFoundException;
    List<getCustomerAllDetails> ViewManageSearchCustomer(String id) throws SQLException, ClassNotFoundException;
    List<getCustomerAllDetails> selectDateCust(String strDate, String endDate) throws SQLException, ClassNotFoundException;


    List<InvoiceCustomer> getDetailsPurchase(String id) throws SQLException, ClassNotFoundException;


    List<ViewEmpWorkDetails> loadEmp() throws SQLException, ClassNotFoundException;
    List<ViewEmpWorkDetails>  searchEmployes(String id) throws SQLException, ClassNotFoundException;
    int searchEmpCount(String id) throws SQLException, ClassNotFoundException;
    List<ViewEmpWorkDetails> searchDateDetailsEMP(String strDate, String endDate) throws SQLException, ClassNotFoundException;
    //Invoice eke emp table eka load karan code eka
    List<ViewEmpWorkDetails> loadtblEmp() throws SQLException, ClassNotFoundException;
    int getCountEmp() throws SQLException, ClassNotFoundException;
    List<ViewEmpWorkDetails> searchEmpId(String id) throws SQLException, ClassNotFoundException;
    int searchEmpTblCount(String id) throws SQLException, ClassNotFoundException;

    List<InvoiceManage> getAllAdvance() throws SQLException, ClassNotFoundException;
    List<InvoiceManage> ViewManageSearchAdvance(String id) throws SQLException, ClassNotFoundException;
    List<InvoiceManage> searchDateDetailsAdvance(String stDate, String enDate) throws SQLException, ClassNotFoundException;

    List<Advance> loadA() throws SQLException, ClassNotFoundException;


}
