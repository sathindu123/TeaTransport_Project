package lk.ijse.Dao;


import lk.ijse.Dao.custom.DailyHomePageDAO;
import lk.ijse.Dao.custom.impl.*;

public class DAOFactory{
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getInstance(){
        return (daoFactory==null)?daoFactory
                =new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,DAILY,EMPLOYEEmANAGE,OEMPLOYEESALARY,EMPWORK,GETCUSTOMERDETAILS,INVOICECUSTOMER,INVOICEMANAGE,LOGINPAGE,
        MONTHLYRATE,SALARYPRICEEMP,STOCK,VIEWCUST,VIEWEMP,VIEWEMPWORKDETAILS,VIEWMANAGE,VIEWSTOCK
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerManageDAOimpl();
            case DAILY:
                return new DailyHomePageDAOimpl();
            case EMPLOYEEmANAGE:
                return new EmployeeManageDAOimpl();
            case OEMPLOYEESALARY:
                return new EmpSalaryDAOimpl();
            case EMPWORK:
                return new EmpWorkDetailDAOimpl();
            case GETCUSTOMERDETAILS:
                return new GetCustomerAllDetailsDAOimpl();
            case INVOICECUSTOMER:
                return new InvoiceCustomerDAOimpl();
            case INVOICEMANAGE:
                return new QueryDAOImpl();
            case LOGINPAGE:
                return new QueryDAOImpl();
            case MONTHLYRATE:
                return new QueryDAOImpl();
            case SALARYPRICEEMP:
                return new SalaryPriceEmployeeDAOimpl();
            case STOCK:
                return new QueryDAOImpl();
            case VIEWCUST:
                return new QueryDAOImpl();
            case VIEWEMP:
                return new QueryDAOImpl();
            case VIEWEMPWORKDETAILS:
                return new QueryDAOImpl();
            case VIEWMANAGE:
                return new QueryDAOImpl();
            case VIEWSTOCK:
                return new QueryDAOImpl();

            default:
                return null;
        }
    }

}
