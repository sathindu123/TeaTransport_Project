package lk.ijse.bo;

import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.SuperDAO;
import lk.ijse.Dao.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)?boFactory=
                new BOFactory():boFactory;

    }
    public enum BOTypes{
        CUSTOMER,DAILY,EMPLOYEEmANAGE,EMPLOYEESALARY,EMPWORK,GETCUSTOMERDETAILS,INVOICECUSTOMER,INVOICEMANAGE,LOGINPAGE,
        MONTHLYRATE,SALARYPRICEEMP,STOCK,VIEWCUST,VIEWEMP,VIEWEMPWORKDETAILS,VIEWMANAGE,VIEWSTOCK,QUERY,ADVANCE
    }
    public SuperDAO getBO(BOFactory.BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerManageDAOimpl();
            case DAILY:
                return new DailyHomePageDAOimpl();
            case EMPLOYEEmANAGE:
                return new EmployeeManageDAOimpl();
            case EMPLOYEESALARY:
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
            case QUERY:
                return new QueryDAOimpl();
            case ADVANCE:
                return new AdvanceDAOimpl();
            default:
                return null;
        }
    }
}
