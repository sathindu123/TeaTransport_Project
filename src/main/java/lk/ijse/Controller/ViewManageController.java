package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.*;
import lk.ijse.Dao.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewManageController extends DashboardFormController {
    private GetCustomerAllDetailsDAOimpl GET_CUS_ALL_DETAIL;
    private ViewEmpWorkDetailsDAOimpl EMP_WORK_DETAILS_VIEW;
    private AnchorPane anchorPane;
    private SalaryPriceEmployeeDAOimpl SALARY_EMP_MODEl;
    @FXML
    private javafx.scene.control.TextField txtSearchCust;
    @FXML
    private TableView<getCustomerAllDetailsDto> tblCustomer;
    @FXML
    private TableColumn<getCustomerAllDetailsDto, String> colCustomerId;
    @FXML
    private TableColumn<getCustomerAllDetailsDto, String> colCustomerName;
    @FXML
    private TableColumn<getCustomerAllDetailsDto, Integer>colTeaLeafAmount;
    @FXML
    private TableColumn<getCustomerAllDetailsDto, Double>colAdvance;
    @FXML
    private TableColumn<getCustomerAllDetailsDto,Double>colTeaPacket;
    @FXML
    private TableColumn<getCustomerAllDetailsDto, Double>colFertilize;
    @FXML
    private TableColumn<getCustomerAllDetailsDto, Double>colOther;

    public Label lbltt1;
    public Label lbltt2;
    public Label lbltt3;
    public Label lbltt4;


    @FXML
    private Label L5;

    @FXML
    private Label L4;

    @FXML
    private Label L3;

    @FXML
    private Label L2;

    @FXML
    private Label L1;

    @FXML
    private TableView<StockDto> tblStock;
    @FXML
    private TextField txtSearchStock;
    @FXML
    private TableColumn<StockDto, String>clProductId;
    @FXML
    private TableColumn<StockDto, String>clCategory;
    @FXML
    private TableColumn<StockDto, Integer>clCount;
    @FXML
    private TableColumn<StockDto, Double>clPrice;

    @FXML
    private TableView<InvoiceManageDto>tblAdvance;
    @FXML
    private TableColumn<InvoiceManageDto,String>clCustomerIdAdvance;
    @FXML
    private TableColumn<InvoiceManageDto,String>clCustomerNameAdvance;
    @FXML
    private TableColumn<InvoiceManageDto,String>clDateAdvance;
    @FXML
    private TableColumn<InvoiceManageDto, Double>clPriceAdvance;
    @FXML
    private Label txtTotalPriceAdvance;
    @FXML
    private TextField txtSearchCust1;

    @FXML
    private TableView<ViewEmpWorkDetailsDto>tblEmp;
    @FXML
    private TableColumn<ViewEmpWorkDetailsDto,String>clEmpId;
    @FXML
    private TableColumn<ViewEmpWorkDetailsDto,String>clEmpName;
    @FXML
    private TableColumn<ViewEmpWorkDetailsDto,String>clEmpAddress;
    @FXML
    private TableColumn<ViewEmpWorkDetailsDto,String>clEmpTel;
    @FXML
    private TableColumn<ViewEmpWorkDetailsDto,String>clEmpWorkDetails;
    @FXML
    private TextField txtSearchEmp;
    @FXML
    private TextField strDateEmp;
    @FXML
    private TextField endDateEmp;
    @FXML
    private Label lblCount;

    @FXML
    private Label lblPrice;

    @FXML
    private TextField txtEndDateAdvance;
    @FXML
    private TextField txtStartDateAdvance;

    @FXML
    private TableView<ViewManageDto>tbleCustomeSummery;
    @FXML
    private TableColumn<ViewManageDto,String>clCId;
    @FXML
    private TableColumn<ViewManageDto,String>clCName;
    @FXML
    private TableColumn<ViewManageDto,String>clCDate;
    @FXML
    private TableColumn<ViewManageDto,Integer>clCCount;
    @FXML
    private TableColumn<ViewManageDto,Double>clCPrice;
    @FXML
    private TableColumn<ViewManageDto,String>clCProductId;
    public Label lbltotStockPurchase;


    @FXML
    private TextField txCustSearch;

    public TextField txtStrDateCust;
    public TextField txtEndDateCust;


    private ViewManageDAOimpl VIEW_MANAGE_MODEL;
    private EmployeeManageDAOimpl EMP_MODEL;
    private CustomerManageDAOimpl CUST_MANAGE_MODEL;

    public ViewManageController() {
        VIEW_MANAGE_MODEL = new ViewManageDAOimpl();
        EMP_MODEL = new EmployeeManageDAOimpl();
        CUST_MANAGE_MODEL = new CustomerManageDAOimpl();
        EMP_WORK_DETAILS_VIEW = new ViewEmpWorkDetailsDAOimpl();
        GET_CUS_ALL_DETAIL = new GetCustomerAllDetailsDAOimpl();
        SALARY_EMP_MODEl = new SalaryPriceEmployeeDAOimpl();
    }

    @FXML
    public void initialize() {
        // Initialize columns
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTeaLeafAmount.setCellValueFactory(new PropertyValueFactory<>("TeaLeafAmount"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("AdvancePurchase"));
        colTeaPacket.setCellValueFactory(new PropertyValueFactory<>("TeaPacketPurchase"));
        colFertilize.setCellValueFactory(new PropertyValueFactory<>("FertilizePurchase"));
        colOther.setCellValueFactory(new PropertyValueFactory<>("OtherPurchase"));
        //stock view
        clProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        clCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        clPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Advance View
        clCustomerIdAdvance.setCellValueFactory(new PropertyValueFactory<>("custId"));
        clCustomerNameAdvance.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        clDateAdvance.setCellValueFactory(new PropertyValueFactory<>("date"));
        clPriceAdvance.setCellValueFactory(new PropertyValueFactory<>("price"));
        //emp view
        clEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clEmpTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        clEmpWorkDetails.setCellValueFactory(new PropertyValueFactory<>("date"));
        //CustMonthSummary
        clCId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clCName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clCDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        clCProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        clCCount.setCellValueFactory(new PropertyValueFactory<>("quntity"));
        clCPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));





        loadTable();
        loadTableStock();
        loadTableAdvance();
        displayTotalAdvance();
        loadTabelEmp();
        loadTableStockPurchase();
    }

    private void loadTableStockPurchase() {
        clCProductId.setStyle("-fx-alignment: CENTER-RIGHT;"+"-fx-font-size: 17px;");
        clCCount.setStyle("-fx-alignment: CENTER-RIGHT;"+"-fx-font-size: 17px;");
        clCPrice.setStyle("-fx-alignment: CENTER-RIGHT;"+"-fx-font-size: 17px;");
        clCDate.setStyle("-fx-alignment: CENTER-RIGHT;"+"-fx-font-size: 17px;");
        clCId.setStyle("-fx-font-size: 17px;");
        clCName.setStyle("-fx-font-size: 17px;");
        ObservableList<ViewManageDto> cList = FXCollections.observableArrayList();

        try{
            List<ViewManageDto> cDto = CUST_MANAGE_MODEL.getStockPurchases();
            List<ViewManageDto> ss = CUST_MANAGE_MODEL.getPohoraStockPurchase();
            cList.addAll(cDto);
            cList.addAll(ss);
            int price = CUST_MANAGE_MODEL.getTot();
            lbltotStockPurchase.setText(""+price+".00 ");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error loading advance data", e.getMessage());
        }
        tbleCustomeSummery.setItems(cList);
    }

    private void loadTabelEmp() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-");
        String formattedDate = LocalDateTime.now().format(dateFormatter);
        strDateEmp.setText(""+formattedDate);
        endDateEmp.setText(""+formattedDate);

        clEmpId.setStyle("-fx-font-size: 17px;");
        clEmpName.setStyle("-fx-font-size: 17px;");
        clEmpAddress.setStyle("-fx-font-size: 17px;");
        clEmpTel.setStyle("-fx-font-size: 17px;");
        clEmpWorkDetails.setStyle("-fx-font-size: 17px;");
        ObservableList<ViewEmpWorkDetailsDto> empList = FXCollections.observableArrayList();
        try{
            List<ViewEmpWorkDetailsDto> empDtos=EMP_WORK_DETAILS_VIEW.loadEmp();
            empList.addAll(empDtos);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error loading advance data", e.getMessage());
        }
        tblEmp.setItems(empList);
//        lblPrice.setText(""+0+"  ");
//        lblCount.setText(""+0+"  ");
    }

    private void displayTotalAdvance() {
        try{
            double totAdvance =  VIEW_MANAGE_MODEL.getTotalAdvance();
            txtTotalPriceAdvance.setText(""+totAdvance);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error loading advance data", e.getMessage());
        }
    }

    private void loadTableAdvance() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-");
        String formattedDate = LocalDateTime.now().format(dateFormatter);
        txtStartDateAdvance.setText(""+formattedDate);
        txtEndDateAdvance.setText(""+formattedDate);

        clPriceAdvance.setStyle("-fx-alignment: CENTER-RIGHT;"+"-fx-font-size: 18px;");
        clDateAdvance.setStyle("-fx-font-size: 17px;");
        clCustomerNameAdvance.setStyle("-fx-font-size: 17px;");
        clCustomerIdAdvance.setStyle("-fx-font-size: 17px;");

        ObservableList<InvoiceManageDto> advanceList = FXCollections.observableArrayList();
        try{
            List<InvoiceManageDto> advanceDtos = VIEW_MANAGE_MODEL.getAllAdvance();
            advanceList.addAll(advanceDtos);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error loading advance data", e.getMessage());
        }
        tblAdvance.setItems(advanceList);
        displayTotalAdvance();
    }

    private void loadTableStock() {
        clPrice.setStyle("-fx-font-size: 18px;"+"-fx-alignment: CENTER-RIGHT;");
        clProductId.setStyle("-fx-font-size: 18px;");
        clCount.setStyle("-fx-font-size: 18px;"+"-fx-alignment: CENTER-RIGHT;");
        clCategory.setStyle("-fx-font-size: 18px;");
        ObservableList<StockDto> stockList = FXCollections.observableArrayList();
        try{
            List<StockDto> stockDtos = VIEW_MANAGE_MODEL.getAllStocks();
            stockList.addAll(stockDtos);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error loading stock data", e.getMessage());
        }
        tblStock.setItems(stockList);
    }

    private void loadTable() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-");
        String formattedDate = LocalDateTime.now().format(dateFormatter);
        txtStrDateCust.setText(""+formattedDate);
        txtEndDateCust.setText(""+formattedDate);

        colCustomerId.setStyle("-fx-font-size: 18px;");
        colCustomerName.setStyle("-fx-font-size: 18px;");
        colAdvance.setStyle("-fx-alignment: CENTER-RIGHT;"+"-fx-font-size: 18px;");
        colTeaPacket.setStyle("-fx-alignment: CENTER-RIGHT;"+"-fx-font-size: 18px;");
        colOther.setStyle("-fx-alignment: CENTER-RIGHT;"+"-fx-font-size: 18px;");
        colFertilize.setStyle("-fx-alignment: CENTER-RIGHT;"+"-fx-font-size: 18px;");
        colTeaLeafAmount.setStyle("-fx-alignment: CENTER-RIGHT;"+"-fx-font-size: 18px;");
        ObservableList<getCustomerAllDetailsDto> customerList = FXCollections.observableArrayList();
        try {
            List<getCustomerAllDetailsDto> customerDtos = GET_CUS_ALL_DETAIL.getAllCustomerDetails();
            customerList.addAll(customerDtos);
            List<getCustomerAllDetailsDto> cc = GET_CUS_ALL_DETAIL.gettot();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error loading customer data", e.getMessage());
        }
        tblCustomer.setItems(customerList);
    }

    private void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }




    public void ViewManageSearch(javafx.event.ActionEvent event) {
        String id = txtSearchCust.getText();

        ObservableList<getCustomerAllDetailsDto> searchResult = FXCollections.observableArrayList();
        try {
           List<getCustomerAllDetailsDto> emdto = GET_CUS_ALL_DETAIL.ViewManageSearchCustomer(id);
           searchResult.addAll(emdto);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error loading customer data", e.getMessage());
        }
        tblCustomer.setItems(searchResult);
    }

    public void ViewManageSearchAdvance(ActionEvent event) {
        String id = txtSearchCust1.getText();
        ObservableList<InvoiceManageDto> advanceList = FXCollections.observableArrayList();
        try {
            List<InvoiceManageDto> advanceDtos = VIEW_MANAGE_MODEL.ViewManageSearchAdvance(id);
            advanceList.addAll(advanceDtos);
            if (advanceDtos != null) {
                tblAdvance.setItems(advanceList);
                double customerAdvanceTot =VIEW_MANAGE_MODEL.getTotalAdvanceCustomer(id);
                txtTotalPriceAdvance.setText(""+customerAdvanceTot);

            } else {
                showAlert(Alert.AlertType.INFORMATION, "Not Found", null, "Customer Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());
        }
        clearAdvanceSerach();
    }

    private void clearAdvanceSerach() {
        txtSearchCust1.setText("");
    }

    public void btnRefeshAdvance(ActionEvent event) {
        loadTableAdvance();

    }

    public void btnRefeshStock(ActionEvent event) {
        loadTableStock();
    }

    public void btnRefeshEmployee(ActionEvent event) {
        loadTabelEmp();
    }

    public void btnRefeshSummery(ActionEvent event) {
    }

    public void btnRefeshCustomer(ActionEvent event) {
        loadTable();
    }

    public void ViewManageSearchStock(ActionEvent event) {
        String id = txtSearchStock.getText();

        try {
            StockDto dto = VIEW_MANAGE_MODEL.ViewManageSearchStock(id);
            if (dto != null) {
                ObservableList<StockDto> searchResultStock = FXCollections.observableArrayList();
                searchResultStock.add(dto);
                tblStock.setItems(searchResultStock);  // Update the table with the search result
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Not Found", null, "Customer Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());

        }
        clearStock();
    }

    private void clearStock() {
        txtSearchStock.setText("");
    }


    public void selectDateAdvance(ActionEvent event) {
        String strDate = txtStartDateAdvance.getText();
        String endDate = txtEndDateAdvance.getText();

        ObservableList<InvoiceManageDto> advanceList = FXCollections.observableArrayList();

        try {
            List<InvoiceManageDto> advanceDtos = VIEW_MANAGE_MODEL.searchDateDetailsAdvance(strDate,endDate);
            advanceList.addAll(advanceDtos);
            if(advanceList != null){
                tblAdvance.setItems(advanceList);
                double customerAdvanceTot =VIEW_MANAGE_MODEL.getTotalAdvanceSelectDate(strDate,endDate);
                txtTotalPriceAdvance.setText(""+customerAdvanceTot);
            }
            else {
                showAlert(Alert.AlertType.INFORMATION, "Not Found", null, "Customer Not Found");
            }

        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());
        }
    }

    public void refershViewManagePage(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/ViewManagement.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("View Management");
    }

    public void selectDateEmp(ActionEvent event) {
        String strDate = strDateEmp.getText();
        String endDate = endDateEmp.getText();

        ObservableList<ViewEmpWorkDetailsDto> empList = FXCollections.observableArrayList();

        try {
            List<ViewEmpWorkDetailsDto> empDtos = EMP_WORK_DETAILS_VIEW.searchDateDetailsEMP(strDate,endDate);
            empList.addAll(empDtos);
            if(empList != null){
                tblEmp.setItems(empList);

            }
            else {
                showAlert(Alert.AlertType.INFORMATION, "Not Found", null, "Customer Not Found");
            }

        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());
        }


    }

    public void searchEmp(ActionEvent event) {
        String id = txtSearchEmp.getText();

        ObservableList<ViewEmpWorkDetailsDto> empList = FXCollections.observableArrayList();
        try {
            List<ViewEmpWorkDetailsDto> empDtos = EMP_WORK_DETAILS_VIEW.searchEmployes(id);
            empList.addAll(empDtos);
            if (empList != null){
                int count = EMP_WORK_DETAILS_VIEW.searchEmpCount(id);
                lblCount.setText("  "+count+"   ");
                int prc = SALARY_EMP_MODEl.getSalaryEmp();
                lblPrice.setText(""+(count*prc)+"   ");
                tblEmp.setItems(empList);
            }
            else {
                showAlert(Alert.AlertType.INFORMATION, "Not Found", null, "Customer Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());
        }
        clerEmpSearch();
    }

    private void clerEmpSearch() {
        txtSearchEmp.setText("");
    }

//    public void CustSerch(ActionEvent event) {
//        String id = txCustSearch.getText();
//
//        try {
//            CustomerManageDto dto = CUST_MANAGE_MODEL.custSerachsummery(id);
//            if(dto != null){
//                ObservableList<CustomerManageDto> custList = FXCollections.observableArrayList();
//                custList.addAll(dto);
//                tbleCustomeSummery.setItems(custList);
//            }
//            else{
//                showAlert(Alert.AlertType.INFORMATION, "Not Found", null, "Customer Not Found");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());
//        }
//    }

    public void onActionDateCustomer(ActionEvent event) {
        String strDate = txtStrDateCust.getText();
        String endDate = txtEndDateCust.getText();

        ObservableList<getCustomerAllDetailsDto> custList = FXCollections.observableArrayList();

        try {
            List<getCustomerAllDetailsDto> custDto = GET_CUS_ALL_DETAIL.selectDateCust(strDate,endDate);
            custList.addAll(custDto);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());
        }
        tblCustomer.setItems(custList);
    }


}
