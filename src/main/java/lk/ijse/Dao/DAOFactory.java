package lk.ijse.Dao;


import lk.ijse.Dao.custom.DailyHomePageDAO;
import lk.ijse.Dao.custom.impl.*;

public class DAOFactory{
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
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
                return new InvoiceManageDAOimpl();
            case LOGINPAGE:
                return new LoginPAgeDAOimpl();
            case MONTHLYRATE:
                return new MonthlyRateDAOimpl();
            case SALARYPRICEEMP:
                return new SalaryPriceEmployeeDAOimpl();
            case STOCK:
                return new StockDAOimpl();
            case VIEWCUST:
                return new ViewCustomerFormDAOimpl();
            case VIEWEMP:
                return new ViewEmployeeFormDAOimpl();
            case VIEWEMPWORKDETAILS:
                return new ViewEmpWorkDetailsDAOimpl();
            case VIEWMANAGE:
                return new ViewManageDAOimpl();
            case VIEWSTOCK:
                return new ViewStockDAOimpl();

            default:
                return null;
        }
    }

}
