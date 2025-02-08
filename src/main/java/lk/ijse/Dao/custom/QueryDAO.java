package lk.ijse.Dao.custom;

import lk.ijse.Dao.SuperDAO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;

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
    List<ViewManageDto> getStockPurchases() throws SQLException, ClassNotFoundException;
    int getTot() throws SQLException, ClassNotFoundException;
    List<ViewManageDto> getPohoraStockPurchase() throws SQLException, ClassNotFoundException;
    List<DailyHomePageDto> LoadTable() throws SQLException, ClassNotFoundException;

    List<getCustomerAllDetailsDto> getAllCustomerDetails() throws SQLException, ClassNotFoundException;
    List<getCustomerAllDetailsDto> ViewManageSearchCustomer(String id) throws SQLException, ClassNotFoundException;
    List<getCustomerAllDetailsDto> selectDateCust(String strDate, String endDate) throws SQLException, ClassNotFoundException;
    List<getCustomerAllDetailsDto> gettot() throws SQLException, ClassNotFoundException;

    List<InvoiceCustomerDto> getDetailsPurchase(String id) throws SQLException, ClassNotFoundException;


    List<ViewEmpWorkDetailsDto> loadEmp() throws SQLException, ClassNotFoundException;
    List<ViewEmpWorkDetailsDto>  searchEmployes(String id) throws SQLException, ClassNotFoundException;
    int searchEmpCount(String id) throws SQLException, ClassNotFoundException;
    List<ViewEmpWorkDetailsDto> searchDateDetailsEMP(String strDate, String endDate) throws SQLException, ClassNotFoundException;
    //Invoice eke emp table eka load karan code eka
    List<ViewEmpWorkDetailsDto> loadtblEmp() throws SQLException, ClassNotFoundException;
    int getCountEmp() throws SQLException, ClassNotFoundException;
    List<ViewEmpWorkDetailsDto> searchEmpId(String id) throws SQLException, ClassNotFoundException;
    int searchEmpTblCount(String id) throws SQLException, ClassNotFoundException;

    List<InvoiceManageDto> getAllAdvance() throws SQLException, ClassNotFoundException;
    List<InvoiceManageDto> ViewManageSearchAdvance(String id) throws SQLException, ClassNotFoundException;
    List<InvoiceManageDto> searchDateDetailsAdvance(String stDate, String enDate) throws SQLException, ClassNotFoundException;



}
