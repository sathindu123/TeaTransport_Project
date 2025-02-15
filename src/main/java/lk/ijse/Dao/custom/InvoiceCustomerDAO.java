package lk.ijse.Dao.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.entity.InvoiceCustomer;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceCustomerDAO extends CrudDAO<InvoiceCustomer> {
    List<InvoiceCustomer> getTeLeaf(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException;
    List<InvoiceCustomer> customeTealeafDateGet(String id, String startDate, String endDate) throws SQLException, ClassNotFoundException;
    String getCustomerName(String id) throws SQLException, ClassNotFoundException;
    double getAdvanceCustomer(String id, String month) throws SQLException, ClassNotFoundException;
    double nextAdvanceTotal(String id,String month) throws SQLException, ClassNotFoundException;
    double getPohoraCountCustomer(String id, String month) throws SQLException, ClassNotFoundException;
    double nextPohoraTotal(String id, String month) throws SQLException, ClassNotFoundException;
    double getGiyamasaHiga(String id, String month) throws SQLException, ClassNotFoundException;
    List<String> getAllProductId() throws SQLException, ClassNotFoundException;
    List<String> getAllId() throws SQLException, ClassNotFoundException;
    String cheackCustomerId(String id,String date) throws SQLException, ClassNotFoundException;
    List<String> loadProductId() throws SQLException, ClassNotFoundException;

    ///dailyHomaPage
    List<InvoiceCustomer> getAllTeaLeafCount(String startDate, String endDate) throws SQLException, ClassNotFoundException;

}
