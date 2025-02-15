package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;
import lk.ijse.entity.CustomerManage;
import lk.ijse.entity.InvoiceManage;
import lk.ijse.entity.ViewEmpWorkDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface QuearyBO extends SuperBO {
    List<AdvanceDto> loadA() throws SQLException, ClassNotFoundException;

    List<ViewEmpWorkDetailsDto> loadtblEmp() throws SQLException, ClassNotFoundException;

    int getCountEmp() throws SQLException, ClassNotFoundException;

    List<ViewEmpWorkDetailsDto> searchEmpId(String id) throws SQLException, ClassNotFoundException;


    int searchEmpTblCount(String id) throws SQLException, ClassNotFoundException;

    List<InvoiceCustomerDto> getDetailsPurchase(String id) throws SQLException, ClassNotFoundException;
    List<ViewManageDto> getStockPurchases() throws SQLException, ClassNotFoundException;

    List<ViewManageDto> getPohoraStockPurchase() throws SQLException, ClassNotFoundException;

    int getTot() throws SQLException, ClassNotFoundException;

    List<ViewEmpWorkDetailsDto> loadEmp() throws SQLException, ClassNotFoundException;


    List<InvoiceManageDto> getAllAdvance() throws SQLException, ClassNotFoundException;

    List<InvoiceManageDto> ViewManageSearchAdvance(String id) throws SQLException, ClassNotFoundException;

    List<InvoiceManageDto> searchDateDetailsAdvance(String stDate, String enDate) throws SQLException, ClassNotFoundException;


    List<getCustomerAllDetailsDto> getAllCustomerDetails() throws SQLException, ClassNotFoundException;

    List<getCustomerAllDetailsDto> ViewManageSearchCustomer(String id) throws SQLException, ClassNotFoundException;

    List<getCustomerAllDetailsDto> selectDateCust(String strDate, String endDate) throws SQLException, ClassNotFoundException;
    List<ViewEmpWorkDetailsDto> searchDateDetailsEMP(String strDate, String endDate) throws SQLException, ClassNotFoundException;

    List<ViewEmpWorkDetailsDto>  searchEmployes(String id) throws SQLException, ClassNotFoundException;

    int searchEmpCount(String id) throws SQLException, ClassNotFoundException;

}
