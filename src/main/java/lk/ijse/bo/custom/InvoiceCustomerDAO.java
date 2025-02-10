package lk.ijse.bo.custom;

import lk.ijse.Dao.CrudDAO;
import lk.ijse.dto.InvoiceCustomerDto;
import lk.ijse.entity.InvoiceCustomer;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceCustomerDAO extends CrudDAO<InvoiceCustomer> {
    List<InvoiceCustomerDto> getTeLeaf(String id) throws SQLException, ClassNotFoundException;
    List<InvoiceCustomerDto> customeTealeafDateGet(String id) throws SQLException, ClassNotFoundException;
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
    List<InvoiceCustomerDto> getAllTeaLeafCount() throws SQLException, ClassNotFoundException;

}
