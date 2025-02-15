package lk.ijse.Dao.custom;

import lk.ijse.Dao.SuperDAO;
import lk.ijse.dto.*;
import lk.ijse.entity.*;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    CustomerManageDto custSerachsummery(String id) throws SQLException, ClassNotFoundException;
    List<ViewManage> getStockPurchases() throws SQLException, ClassNotFoundException;
    int getTot() throws SQLException, ClassNotFoundException;
    List<ViewManage> getPohoraStockPurchase() throws SQLException, ClassNotFoundException;
    List<DailyHomePage> LoadTable(String date) throws SQLException, ClassNotFoundException;

    List<getCustomerAllDetails> getAllCustomerDetails(String startDate, String endoDate, String endDate, String ss) throws SQLException, ClassNotFoundException;
    List<getCustomerAllDetails> ViewManageSearchCustomer(String id) throws SQLException, ClassNotFoundException;
    List<getCustomerAllDetails> selectDateCust(String strDate, String endDate) throws SQLException, ClassNotFoundException;


    List<InvoiceCustomer> getDetailsPurchase(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException;


    List<ViewEmpWorkDetails> loadEmp(String startDate, String endDate) throws SQLException, ClassNotFoundException;
    List<ViewEmpWorkDetails>  searchEmployes(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException;
    int searchEmpCount(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException;
    List<ViewEmpWorkDetails> searchDateDetailsEMP(String strDate, String endDate) throws SQLException, ClassNotFoundException;
    //Invoice eke emp table eka load karan code eka
    List<ViewEmpWorkDetails> loadtblEmp(String startDate, String endDate) throws SQLException, ClassNotFoundException;
    int getCountEmp(String startDate, String endDate) throws SQLException, ClassNotFoundException;
    List<ViewEmpWorkDetails> searchEmpId(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException;
    int searchEmpTblCount(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException;

    List<InvoiceManage> getAllAdvance() throws SQLException, ClassNotFoundException;
    List<InvoiceManage> ViewManageSearchAdvance(String id) throws SQLException, ClassNotFoundException;
    List<InvoiceManage> searchDateDetailsAdvance(String stDate, String enDate) throws SQLException, ClassNotFoundException;

    List<Advance> loadA() throws SQLException, ClassNotFoundException;


}
