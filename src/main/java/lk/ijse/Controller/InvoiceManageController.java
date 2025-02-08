package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Dao.custom.impl.CustomerManageDAOimpl;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.*;
import lk.ijse.Dao.*;
import javafx.scene.input.KeyEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.parameters;

public class InvoiceManageController extends ManagementCetegoryController {
    @FXML
    private ComboBox comPID;
    private AnchorPane anchorPane;
    private int kapanagana = 0;

    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};

    private InvoiceCustomerDAOimpl INVOICE_CUSTOMER_MODEL;
    private SalaryPriceEmployeeDAOimpl SALARY_EMP_MODEl;
    private CustomerManageDAOimpl CUST_MNG_MODEL;
    private EmpSalaryDAOimpl EMP_SALARY;
    private InvoiceManageDAOimpl INVOICE_MANAGE_MODEL;
    private ViewEmpWorkDetailsDAOimpl VIEW_EMP_MODEL;
    private MonthlyRateDAOimpl MONTH_RATE;
    private int count = 0;
    private int payCountEmp;
    private int TotPrice = 0;
    public TextField txtEmpSalaryPriceOneDay;

    public TextField txtCID;
    public TextField txtCDate;
    public TextField txtCProductId;
    public TextField txtCCount;
    public TextField txtCPrice;

    public TextField txtCID1;
    public TextField txtCDate1;
    public TextField txtCProductId1;
    public TextField txtCCount1;
    public TextField txtCPrice1;

    @FXML
    private Label LabelCheack;
    public TextField txFID;
    public TextField txFDate;
    @FXML
    private TextField txFProductID;
    public TextField txFCount;
    public TextField txFPrice;
    public TextField txFCount1;

    public TextField txtACustId;
    public TextField txtAAmount;
    public TextField txtADate;
    public TextField txtAMonth;

    public TextField txtEmpId;
    public TextField txtPayCount;
    public TextField txtPayDate;

    public Label lblCount;
    public Label lblAmount;

    @FXML
    private TableView<ViewEmpWorkDetailsDto> tblEmpInvoice;
    @FXML
    private TableColumn<ViewEmpWorkDetailsDto,String >clEmpId;
    @FXML
    private TableColumn<ViewEmpWorkDetailsDto,String >clEmpName;
    @FXML
    private TableColumn<ViewEmpWorkDetailsDto,String >clEmpDate;

    @FXML
    private TableView<EmpSalaryDto> tblPricePay;
    @FXML
    private TableColumn<EmpSalaryDto,String>clPriceEmpId;
    @FXML
    private TableColumn<EmpSalaryDto,String>clPriceDate;
    @FXML
    private TableColumn<EmpSalaryDto,Integer>clPricePayPrice;

    public Label totPriceEmp;

    @FXML
    private TextField txtCustIdCustomer;
    public Label lblCustNameCustomer;
    public Label lbldateCustomer;
    public Label lblNextPohoraHiga;


    public Label i1;
    public Label i2;
    public Label i3;
    public Label i4;
    public Label i5;
    public Label i6;
    public Label i7;
    public Label i8;
    public Label i9;
    public Label i10;
    public Label i11;
    public Label i12;
    public Label i13;
    public Label i14;
    public Label i15;
    public Label i16;
    public Label i17;
    public Label i18;
    public Label i19;
    public Label i20;
    public Label i21;
    public Label i22;
    public Label i23;
    public Label i24;
    public Label i25;
    public Label i26;
    public Label i27;
    public Label i28;
    public Label i29;
    public Label i30;
    public Label i31;

 //   public Label lbltotGod;
    public Label lblTotGold;
    public Label lblTotalAmount;
    public TextField txtOther;
    public TextField txtHiga;
    public TextField txtAd;
    public TextField txtF;
    public TextField txtT;
    public TextField txtO;
    public Label lblGoodLeaf;
    public Label lblGoldLeaf;

    public Label RS;
    public Label Rs;
    public Label KG;
    public Label KG1;
    public Label lbltotGod1;



    private double goodLeafRate;
    private double goldLeafRate;
    private int goldLefKg;
    private int goodLeafKg;


    @FXML
    private TextField lbltotGod;


    public Label sumTot;
    public Label minTot;
    public Label totalCost;


    @FXML
    private Label lblNextDaluHiga;
    @FXML
    private Label lblNextAdvanceHiga;

    @FXML
    private TableView<LoadProductPurchase> tblT;
    @FXML
    private TableColumn<LoadProductPurchase,String >TName;
    @FXML
    private TableColumn<LoadProductPurchase,String>TDate;
    @FXML
    private TableColumn<LoadProductPurchase,String >TProduct;
    @FXML
    private TableColumn<LoadProductPurchase,Integer>TCount;
    @FXML
    private TableColumn<LoadProductPurchase,Double>TPrice;

    @FXML
    private TableView<LoadProductPurchase> tblO;
    @FXML
    private TableColumn<LoadProductPurchase,String >OName;
    @FXML
    private TableColumn<LoadProductPurchase,String>ODate;
    @FXML
    private TableColumn<LoadProductPurchase,String >OProduct;
    @FXML
    private TableColumn<LoadProductPurchase,Integer>OCount;
    @FXML
    private TableColumn<LoadProductPurchase,Double>OPrice;

    @FXML
    private TableView<LoadProductPurchase> tblF;
    @FXML
    private TableColumn<LoadProductPurchase,String >FName;
    @FXML
    private TableColumn<LoadProductPurchase,String>FDate;
    @FXML
    private TableColumn<LoadProductPurchase,String >FProduct;
    @FXML
    private TableColumn<LoadProductPurchase,Integer>FCount;
    @FXML
    private TableColumn<LoadProductPurchase,Double>FPrice;

    @FXML
    private TableView<AdvanceDto> tblA;
    @FXML
    private TableColumn<AdvanceDto,String >AName;
    @FXML
    private TableColumn<AdvanceDto,String>ADate;
    @FXML
    private TableColumn<AdvanceDto,String >AId;
    @FXML
    private TableColumn<AdvanceDto,Double>APrice;

    @FXML
    private Button deleteA;
    @FXML
    private Button UpdateBtnT;
    @FXML
    private Button SaveT;
    @FXML
    private Button DeleteBtnT;
    @FXML
    private Button resetbtnT;
    @FXML
    private Button savebuttonA;
    @FXML
    private Button deleteF;
    @FXML
    private Button saveF;
    @FXML
    private Button resetF;
    @FXML
    private Button saveO;
    @FXML
    private Button updateO;
    @FXML
    private Button deleteO;

    @FXML
    private Label lblPaymentsNb;
    @FXML
    private TextField txFProductID1;

    @FXML
    private Label lblTt;

    @FXML
    private ListView<String > ListViewF;

    private ViewStockDAOimpl VIEW_STOCK_MODEL;


    public InvoiceManageController(){
        INVOICE_MANAGE_MODEL = new InvoiceManageDAOimpl();
        VIEW_EMP_MODEL = new ViewEmpWorkDetailsDAOimpl();
        EMP_SALARY = new EmpSalaryDAOimpl();
        SALARY_EMP_MODEl = new SalaryPriceEmployeeDAOimpl();
        INVOICE_CUSTOMER_MODEL = new InvoiceCustomerDAOimpl();
        MONTH_RATE = new MonthlyRateDAOimpl();
        CUST_MNG_MODEL = new CustomerManageDAOimpl();
        VIEW_STOCK_MODEL = new ViewStockDAOimpl();
    }


    public void initialize() {


      //  txFProductID.setText("mkkmk");
        // Attach listeners for product ID and count changes
        txFProductID.setOnKeyReleased(this::handleKeyReleased);
        txFCount.setOnKeyReleased(this::handleKeyReleased);
        txtCProductId.setOnKeyReleased(this::handleKeyReleasedTeaPacket);
        txtCCount.setOnKeyReleased(this::handleKeyReleasedTeaPacket);
        txtCProductId1.setOnKeyReleased(this::handleKeyReleasedOthers);
        txtCCount1.setOnKeyReleased(this::handleKeyReleasedOthers);

        clEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clEmpDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        clEmpId.setStyle("-fx-font-size: 18px;");
        clEmpName.setStyle("-fx-font-size: 18px;");
        clEmpDate.setStyle("-fx-font-size: 18px;");

        clPriceEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        clPriceDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clPricePayPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        clPriceEmpId.setStyle("-fx-font-size: 18px;");;
        clPricePayPrice.setStyle("-fx-font-size: 18px;"+"-fx-alignment: CENTER-RIGHT;");
        clPriceDate.setStyle("-fx-font-size: 18px;");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDateTime.now().format(dateFormatter);
        txtCDate.setText(""+formattedDate);
        txFDate.setText(""+formattedDate);
        txtCDate1.setText(""+formattedDate);
        txtADate.setText(""+formattedDate);

        oneDayEmpSalaryTxtfield();
        loadTableEmp();
        loadTablePayEmpPrice();

        loadCustomerTabPane();


        TName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        TDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TProduct.setCellValueFactory(new PropertyValueFactory<>("type"));
        TCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        TPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        TName.setStyle("-fx-font-size: 18px;");
        TDate.setStyle("-fx-font-size: 18px;");
        TProduct.setStyle("-fx-font-size: 18px;");
        TPrice.setStyle("-fx-font-size: 18px;");

        OName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        ODate.setCellValueFactory(new PropertyValueFactory<>("date"));
        OProduct.setCellValueFactory(new PropertyValueFactory<>("type"));
        OCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        OPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        OName.setStyle("-fx-font-size: 18px;");
        ODate.setStyle("-fx-font-size: 18px;");
        OPrice.setStyle("-fx-font-size: 18px;");
        OProduct.setStyle("-fx-font-size: 18px;");
        OCount.setStyle("-fx-font-size: 18px;");


        FName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        FDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        FProduct.setCellValueFactory(new PropertyValueFactory<>("type"));
        FCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        FPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        FName.setStyle("-fx-font-size: 18px;");
        FDate.setStyle("-fx-font-size: 18px;");
        FProduct.setStyle("-fx-font-size: 18px;");
        FPrice.setStyle("-fx-font-size: 18px;");
        FCount.setStyle("-fx-font-size: 18px;");

        AId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        AName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ADate.setCellValueFactory(new PropertyValueFactory<>("date"));
        APrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        AId.setStyle("-fx-font-size: 18px;");
        AName.setStyle("-fx-font-size: 18px;");
        ADate.setStyle("-fx-font-size: 18px;");
        APrice.setStyle("-fx-font-size: 18px;");

        loadTableT();
        loadTableO();
        loadTableF();
        loadTableA();

        nextPaymentID();

        ListViewF.setVisible(false);
        txFProductID.setOnKeyReleased(this::handleKeyReleasedStock);

        // Handle clicking on an item in the ListView
        ListViewF.setOnMouseClicked(event -> {
            String selectedSuggestion =ListViewF.getSelectionModel().getSelectedItem();
            if (selectedSuggestion != null) {
                txFProductID.setText(selectedSuggestion); // Update the search bar with the selected suggestion
                ListViewF.getItems().clear(); // Clear the suggestions
                ListViewF.setVisible(false); // Hide the suggestions after selection
            }
        });

        txFProductID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DOWN && ListViewF.isVisible()) {
                // Focus on the ListView when the down arrow key is pressed
                ListViewF.requestFocus();
                ListViewF.getSelectionModel().selectFirst(); // Select the first item in the ListView
            }
        });

        ListViewF.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Select the currently highlighted item in the ListView
                String selectedSuggestion = ListViewF.getSelectionModel().getSelectedItem();
                if (selectedSuggestion != null) {
                    txFProductID.setText(selectedSuggestion); // Update the text field with the selected suggestion
                    ListViewF.getItems().clear(); // Clear the suggestions
                    ListViewF.setVisible(false); // Hide the ListView
                }
            } else if (event.getCode() == KeyCode.ESCAPE) {
                // Hide the ListView when the ESC key is pressed
                ListViewF.getItems().clear();
                ListViewF.setVisible(false);
            }
            txFCount.requestFocus();
        });

    }


    private void handleKeyReleasedStock(KeyEvent keyEvent) {
        String typedText = txFProductID.getText(); // Get the text currently in the search box
        if (typedText.isEmpty()) {
            ListViewF.getItems().clear(); // Clear suggestions if input is empty
            ListViewF.setVisible(false); // Hide suggestions if input is empty
            return;
        }

        List<String> suggestions = new ArrayList<>();
        try {
            suggestions = VIEW_STOCK_MODEL.searchStock(typedText);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found: " + e.getMessage());
        }

        // Update the suggestion list if matches are found
        if (!suggestions.isEmpty()) {
            ListViewF.getItems().setAll(suggestions); // Populate the ListView with suggestions
            ListViewF.setVisible(true);
        } else {
            ListViewF.getItems().clear();
            ListViewF.setVisible(false);
        }
    }

    private void nextPaymentID()  {

        try {
            String dsp = INVOICE_MANAGE_MODEL.getPID();
            lblPaymentsNb.setText(dsp);

        } catch (SQLException e) {
            System.out.println(e);

        } catch (ClassNotFoundException e) {
            System.out.println(e);

        }
    }

    private void loadTableA() {
        deleteA.setDisable(true);
        ObservableList<AdvanceDto> Sli = FXCollections.observableArrayList();
        try {
            List<AdvanceDto> dto = INVOICE_MANAGE_MODEL.loadA();
            Sli.addAll(dto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        tblA.setItems(Sli);
    }

    private void loadTableF() {


        deleteF.setDisable(true);
        ObservableList<LoadProductPurchase> Sli = FXCollections.observableArrayList();
        ObservableList<String > Slis = FXCollections.observableArrayList();
        try {
            List<String> mko = INVOICE_CUSTOMER_MODEL.loadProductId();
            List<LoadProductPurchase> dto = INVOICE_MANAGE_MODEL.loadF();
            Sli.addAll(dto);
            Slis.addAll(mko);
       //     comPID.setItems(Slis);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        tblF.setItems(Sli);
    }

    private void loadTableO() {
        updateO.setDisable(true);
        deleteO.setDisable(true);
        ObservableList<LoadProductPurchase> Sli = FXCollections.observableArrayList();
        try {
            List<LoadProductPurchase> dto = INVOICE_MANAGE_MODEL.loadO();
            Sli.addAll(dto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        tblO.setItems(Sli);
    }

    private void loadTableT() {
        ObservableList<LoadProductPurchase> Sli = FXCollections.observableArrayList();
        try {
            List<LoadProductPurchase> dto = INVOICE_MANAGE_MODEL.loadT();
            Sli.addAll(dto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        UpdateBtnT.setDisable(true);
        DeleteBtnT.setDisable(true);
        tblT.setItems(Sli);
    }

    private void loadCustomerTabPane() {
        LocalDateTime now = LocalDateTime.now();
        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }



        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM");
        String formattedDate = now.format(dateFormatter);

        DateTimeFormatter dateFormatter1 = DateTimeFormatter.ofPattern("yyyy");
        String formattedDate1 = now.format(dateFormatter1);


        String dateMonth = "";
        for (int i = 0; i < dateArray.length; i++) {
            String monthIndex = String.format("%02d", i + 1);
            if (formattedDate.equals(monthIndex)) {
                dateMonth = dateArray[i];
            }
        }


        ObservableList<MonthlyRateDto> nrt = FXCollections.observableArrayList();

        String passDate = formattedDate1 + dateMonth;
        try {
            List<MonthlyRateDto> dto = MONTH_RATE.getLeafPrice(passDate);
            nrt.addAll(dto);

            if (!dto.isEmpty()) {
                MonthlyRateDto data = dto.get(0); // Get the first entry

                lblGoodLeaf.setText(String.valueOf(data.getRate()));
                lblGoldLeaf.setText(String.valueOf(data.getRate1()));

                goldLeafRate = data.getRate1();
                goodLeafRate = data.getRate();

            } else {
                //System.out.println("No data found for customer purchase details.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        lbldateCustomer.setText(""+dateMonth+formattedDate1);
        RS.setText("Rs");
        Rs.setText("Rs");

    }

    private void oneDayEmpSalaryTxtfield() {
        try {
            payCountEmp = SALARY_EMP_MODEl.getSalaryEmp();
            txtEmpSalaryPriceOneDay.setText(""+payCountEmp);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void loadTablePayEmpPrice() {
        ObservableList<EmpSalaryDto> empList = FXCollections.observableArrayList();

        try {
            List<EmpSalaryDto> empDto = EMP_SALARY.loadPayPriceEmp();
            empList.addAll(empDto);
            int py = EMP_SALARY.getSalaryAll();
            totPriceEmp.setText(""+py+" ");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Error loading advance data", e.getMessage());
        }
        tblPricePay.setItems(empList);


    }

    private void loadTableEmp() {
        ObservableList<ViewEmpWorkDetailsDto> empList = FXCollections.observableArrayList();
        try {
            List<ViewEmpWorkDetailsDto> empDtos = VIEW_EMP_MODEL.loadtblEmp();
            empList.addAll(empDtos);
            count = VIEW_EMP_MODEL.getCountEmp();
            lblCount.setText(""+count+"  ");
            lblAmount.setText(""+(count*payCountEmp)+"  ");

            int py = EMP_SALARY.getSalaryAll();
            txtPayCount.setText(""+((count*payCountEmp)-py));

        }catch (Exception e){
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Error loading advance data", e.getMessage());
        }
        tblEmpInvoice.setItems(empList);
    }
    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void handleKeyReleasedTeaPacket(KeyEvent keyEvent) {
        calculateAndSetPriceTeaPacket();
    }


    private void calculateAndSetPriceTeaPacket() {
        String productIdstr = txtCProductId.getText();
        String countstr = txtCCount.getText();

        if (productIdstr == null || productIdstr.isEmpty() || countstr == null || countstr.isEmpty()){
            return;
        }
        int count;
        double pricePerProduct = 0.0;
        double totalPrice = 0.0;

        try {
            // Convert count to an integer
            count = Integer.parseInt(countstr);

            // Retrieve the price based on the product ID
            pricePerProduct = INVOICE_MANAGE_MODEL.getPricPhora(productIdstr);

            // Calculate the total price
            totalPrice = count * pricePerProduct;

            // Set the total price in the price text field
            txtCPrice.setText(String.valueOf(totalPrice));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid count. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving price: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    public void btnSaveCustomer(ActionEvent event) {
        String id = txtCID.getText();
        String date = txtCDate.getText();
        String productId = txtCProductId.getText();
        String countStr = txtCCount.getText();
        String priceStr = txtCPrice.getText();

        // Validation: Ensure all fields are filled
        if (date == null || date.isEmpty() || productId == null || productId.isEmpty() || countStr == null || countStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be completed", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit early if validation fails
        }

        int count;
        double totalPrice;

        try {
            // Convert count and price to the appropriate types
            count = Integer.parseInt(countStr);
            totalPrice = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if there's an input error
        }

        // Create the InvoiceManageDto object
        InvoiceManageDto invoiceManageDto = new InvoiceManageDto(id, date, productId, count, totalPrice);
        StockDto stockDto = new StockDto(productId, "Product Category", count, totalPrice);
        try {
            // Save the data to the database
            String resp = INVOICE_MANAGE_MODEL.insertData(invoiceManageDto,stockDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        loadTableT();
        clearFrom();

    }

    public void clearFrom(){
        txtCID.setText("");
        txtCProductId.setText("");
        txtCCount.setText("");
        txtCPrice.setText("");
    }

    public void btnUpdateTeaPacket(ActionEvent event) {
        String id = txtCID.getText();
        String date = txtCDate.getText();
        String productId = txtCProductId.getText();
        String countStr = txtCCount.getText();
        String priceStr = txtCPrice.getText();

        // Validation: Ensure all fields are filled
        if (date == null || date.isEmpty() || productId == null || productId.isEmpty() || countStr == null || countStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be completed", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit early if validation fails
        }

        int count;
        double totalPrice;

        try {
            // Convert count and price to the appropriate types
            count = Integer.parseInt(countStr);
            totalPrice = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if there's an input error
        }

        // Create the InvoiceManageDto object
        InvoiceManageDto invoiceManageDto = new InvoiceManageDto(id, date, productId, count, totalPrice);
        StockDto stockDto = new StockDto(productId, "Product Category", count, totalPrice);
        try {
            // Save the data to the database
            String resp = INVOICE_MANAGE_MODEL.updateData(invoiceManageDto,stockDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        loadTableT();
        clearFrom();

    }

    private void handleKeyReleased(KeyEvent e) {
        calculateAndSetPrice(); // Call the price calculation when productId or count changes
    }

    public void calculateAndSetPrice() {
        // Get user inputs
        String productId = txFProductID.getText();
        String countStr = txFCount.getText();

        // Ensure productId and count are filled before proceeding
        if (productId == null || productId.isEmpty() || countStr == null || countStr.isEmpty()) {
            return; // Exit if fields are incomplete
        }

        int count;
        double pricePerProduct = 0.0;
        double totalPrice = 0.0;

        try {
            // Convert count to an integer
            count = Integer.parseInt(countStr);

            // Retrieve the price based on the product ID
            pricePerProduct = INVOICE_MANAGE_MODEL.getPricPhora(productId);

            // Calculate the total price
            totalPrice = count * pricePerProduct;

            // Set the total price in the price text field
            txFPrice.setText(String.valueOf(totalPrice));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid count. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving price: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void btnSaveFertilizer(ActionEvent event) {

        String id = txFID.getText();
        String date = txFDate.getText();
        String productId = txFProductID.getText();
        String countStr = txFCount.getText();
        String priceStr = txFPrice.getText();
        String warika = txFCount1.getText();// Already calculated price

        try {
            productId = VIEW_STOCK_MODEL.getNameId(productId);
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {

        }
//
//        if (id == null || id.isEmpty() || date == null || date.isEmpty() || productId == null || productId.isEmpty() || countStr == null || countStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "All fields must be completed", "Validation Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }

        int count;
        double totalPrice = 0;
        int warik = 0;

        try {
            count = Integer.parseInt(countStr);
            totalPrice = Double.parseDouble(priceStr);
            warik = Integer.parseInt(warika);
            if(warik == 0 || warika == null || warika.isEmpty()){
                JOptionPane.showMessageDialog(null, "Count is '0' OR empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        InvoiceManageDto invoiceManageDto = new InvoiceManageDto(id, date, productId, count, totalPrice);
        StockDto stockDto = new StockDto(productId, "Product Category", count, totalPrice);
        try {

            String resp = INVOICE_MANAGE_MODEL.insertDataPohora(invoiceManageDto,stockDto,warik,totalPrice);
            JOptionPane.showMessageDialog(null, resp, "Operation Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        loadTableF();
        clearFromFertilize();

    }

    public void btnUpdateFertilizer(ActionEvent event) {
        String id = txFID.getText();
        String date = txFDate.getText();
        String productId = txFProductID.getText();
        String countStr = txFCount.getText();
        String priceStr = txFPrice.getText(); // Already calculated price
        String warika = txFCount1.getText();

        if (id == null || id.isEmpty() || date == null || date.isEmpty() || productId == null || productId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "'customerId' OR 'Date' OR 'ProductId' not Compited ", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int count = 0;
        double totalPrice = 0;
        int warik = 0;
        try {
            count = Integer.parseInt(countStr);
            totalPrice = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }


        InvoiceManageDto invoiceManageDto = new InvoiceManageDto(id, date, productId, count, totalPrice);
        StockDto stockDto = new StockDto(productId, "Product Category", count, totalPrice);
        try {

            String resp = INVOICE_MANAGE_MODEL.deleteDataPohora(invoiceManageDto,stockDto,warik,totalPrice);
            JOptionPane.showMessageDialog(null, resp, "Operation Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }


            loadTableF();
         clearFromFertilize();
    }



    private void clearFromFertilize() {
        txFID.setText("");
        txFProductID.setText("");
        txFCount.setText("");
        txFPrice.setText("");
        txFCount1.setText("1");

    }

    public void btnSaveAdvance(ActionEvent event) {
        String id = txtACustId.getText();
        String amount = txtAAmount.getText();
        String date = txtADate.getText();
        String month = txtAMonth.getText();

        if(id == null || id.isEmpty() || amount == null || amount.isEmpty() || date == null || date.isEmpty()){
            JOptionPane.showMessageDialog(null,"all filed must be complted","Validation erroe",JOptionPane.ERROR_MESSAGE);
            return;
        }

        double pric;
        int mn = 0;
        double monthPrice = 0;
        try{
            pric = Double.parseDouble(amount);
            mn = Integer.parseInt(month);
            if(mn == 0 || month == null || month.isEmpty()){
                JOptionPane.showMessageDialog(null,"warika ganana is 0 Or Null","Validation erroe",JOptionPane.ERROR_MESSAGE);
                return;
            }
            monthPrice = pric/mn;


        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"","",JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDateTime nows = LocalDateTime.now();
        try {
            if (nows.getDayOfMonth() >= 1 && nows.getDayOfMonth() <= 10) {
                for (int i = 0; i < mn; i++) {
                    LocalDateTime now = LocalDateTime.now();
                    now = now.minusMonths(1);
                    now = now.plusMonths(i);
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy");
                    String formattedDate = now.format(dateFormatter);
                    DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("MM");
                    String formattedDate2 = now.format(dateFormatter2);
                    String moko;

                    int pako = 0;
                    pako = Integer.parseInt(formattedDate2);
                    moko = dateArray[pako - 1];

                    month = moko + formattedDate;

                    InvoiceManageDto invoiceManageDto = new InvoiceManageDto(id, date,month,Double.parseDouble(amount),monthPrice);

                    String resp = "";
                    try {
                         resp = INVOICE_MANAGE_MODEL.btnSaveAdvance(invoiceManageDto);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Error consuded" + e.getMessage());

                    }

                    clearFromAdvance();

                }
                JOptionPane.showMessageDialog(null,"Save Status "  ,"save Status",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                for (int i = 0; i < mn; i++) {
                    LocalDateTime now = LocalDateTime.now();
                    now = now.plusMonths(i);
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy");
                    String formattedDate = now.format(dateFormatter);
                    DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("MM");
                    String formattedDate2 = now.format(dateFormatter2);
                    String moko = "";

                    int pako = 0;
                        pako = Integer.parseInt(formattedDate2);
                        moko = dateArray[pako - 1];
                    month = moko + formattedDate;
                    InvoiceManageDto invoiceManageDto = new InvoiceManageDto(id, date,month,Double.parseDouble(amount),monthPrice);
                    String resp = "";
                    try {
                         resp = INVOICE_MANAGE_MODEL.btnSaveAdvance(invoiceManageDto);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,"Error consuded" + e.getMessage());

                    }
                    loadTableA();
                    clearFromAdvance();
                }
                JOptionPane.showMessageDialog(null,"Save Status " ,"save Status",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }


    }

    public void btnUpdateAdvance(ActionEvent event) {

        String id = txtACustId.getText();
        String amount = txtAAmount.getText();
        String date = txtADate.getText();
        String month = txtAMonth.getText();

        if(id == null || id.isEmpty() || amount == null || amount.isEmpty() || date == null || date.isEmpty()){
            JOptionPane.showMessageDialog(null,"all filed must be complted","Validation erroe",JOptionPane.ERROR_MESSAGE);
        }

        double pric = 0;
        int mn = 0;

        try {
            pric = Double.parseDouble(amount);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"price is not integer","Validation error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String resp = INVOICE_MANAGE_MODEL.btnUpdateAdvance(id,date,pric);
            JOptionPane.showMessageDialog(null,"Save Status " +resp,"save Status",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error consuded" + e.getMessage());
        }

        loadTableA();
        clearFromAdvance();
    }

    private void clearFromAdvance() {
        txtACustId.setText("");
        txtAAmount.setText("");
    }

    private void handleKeyReleasedOthers(KeyEvent keyEvent) {
        calculateAndSetPriceOthers();
    }

    private void calculateAndSetPriceOthers() {
        String productId = txtCProductId1.getText();
        String countStr = txtCCount1.getText();

        // Ensure productId and count are filled before proceeding
        if (productId == null || productId.isEmpty() || countStr == null || countStr.isEmpty()) {
            return; // Exit if fields are incomplete
        }

        int count;
        double pricePerProduct = 0.0;
        double totalPrice = 0.0;

        try {
            // Convert count to an integer
            count = Integer.parseInt(countStr);

            // Retrieve the price based on the product ID
            pricePerProduct = INVOICE_MANAGE_MODEL.getPricPhora(productId);

            // Calculate the total price
            totalPrice = count * pricePerProduct;

            // Set the total price in the price text field
            txtCPrice1.setText(String.valueOf(totalPrice));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid count. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error retrieving price: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void btnSaveCustomerOthers(ActionEvent event) {
        String id = txtCID1.getText();
        String date = txtCDate1.getText();
        String productId = txtCProductId1.getText();
        String countStr = txtCCount1.getText();
        String priceStr = txtCPrice1.getText();

        // Validation: Ensure all fields are filled
        if (id == null || id.isEmpty() || date == null || date.isEmpty() || productId == null || productId.isEmpty() || countStr == null || countStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be completed", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit early if validation fails
        }

        int count;
        double totalPrice;

        try {
            count = Integer.parseInt(countStr);
            totalPrice = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create the InvoiceManageDto object
        InvoiceManageDto invoiceManageDto = new InvoiceManageDto(id, date, productId, count, totalPrice);
        StockDto stockDto = new StockDto(productId, "Product Category", count, totalPrice);
        try {
            // Save the data to the database
            String resp = INVOICE_MANAGE_MODEL.insertData(invoiceManageDto,stockDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        loadTableO();
        clearOthersForm();
    }

    private void clearOthersForm() {
        txtCID1.setText("");
        txtCCount1.setText("");
        txtCProductId1.setText("");
        txtCPrice1.setText("");
    }

    public void ViewStockBtnClick(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ViewStockForm.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(anchorPane));
        stage1.show();
        stage1.setResizable(false);
        stage1.setTitle("Product view form");
    }


    public void btnUpdateOthers(ActionEvent event) {
        String id = txtCID1.getText();
        String date = txtCDate1.getText();
        String productId = txtCProductId1.getText();
        String countStr = txtCCount1.getText();
        String priceStr = txtCPrice1.getText();

        // Validation: Ensure all fields are filled
        if (id == null || id.isEmpty() || date == null || date.isEmpty() || productId == null || productId.isEmpty() || countStr == null || countStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be completed", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit early if validation fails
        }

        int count;
        double totalPrice;

        try {
            count = Integer.parseInt(countStr);
            totalPrice = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if there's an input error
        }


        InvoiceManageDto invoiceManageDto = new InvoiceManageDto(id, date, productId, count, totalPrice);
        StockDto stockDto = new StockDto(productId, "Product Category", count, totalPrice);
        try {
            String resp = INVOICE_MANAGE_MODEL.updateData(invoiceManageDto,stockDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        loadTableO();
        clearOthersForm();
    }


    public void ViewPricePerKgBtnClick(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/NetPriceKGView.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(anchorPane));
        stage1.show();
        stage1.setResizable(false);
        stage1.setTitle("MONTHLY RATE");
    }

    public void btnSaveClickEmp(ActionEvent event) {
        String id = txtEmpId.getText();
        String dateStr = txtPayDate.getText();
        String price = txtPayCount.getText();

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr); // Convert to LocalDate
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid date format.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(id == null || id.isEmpty() || dateStr == null || dateStr.isEmpty() || price == null || price.isEmpty()){
            JOptionPane.showMessageDialog(null, "All fields must be completed", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int pricecont;
        try {
            pricecont = Integer.parseInt(price);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        EmpSalaryDto dto = new EmpSalaryDto(id,date,pricecont);
        try{
            String resp = EMP_SALARY.insertSalarayEmp(dto);

            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void btnOkClickEmp(ActionEvent event) throws SQLException, ClassNotFoundException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDateTime.now().format(dateFormatter);

        txtPayDate.setText(""+formattedDate);

        String id = txtEmpId.getText();
        ObservableList<ViewEmpWorkDetailsDto> empList = FXCollections.observableArrayList();
        ObservableList<EmpSalaryDto> emList = FXCollections.observableArrayList();
        try {
            List<ViewEmpWorkDetailsDto> empDto = VIEW_EMP_MODEL.searchEmpId(id);
            empList.addAll(empDto);

            // employee kenektd gewala thena total price eka
            TotPrice = EMP_SALARY.totalSalaryEmp(id);
            totPriceEmp.setText(""+TotPrice);


            List<EmpSalaryDto> dto = EMP_SALARY.getEmpTotPayPrice(id);
            emList.addAll(dto);

            count = VIEW_EMP_MODEL.searchEmpTblCount(id);



            lblCount.setText(""+count+"   ");
            lblAmount.setText(""+(count*payCountEmp)+"   ");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        tblEmpInvoice.setItems(empList);
        tblPricePay.setItems(emList);

        int tt = (count*payCountEmp);
        txtPayCount.setText(""+(tt-TotPrice));

    }

    public void txtenterOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        btnOkClickEmp(event);
    }

    public void onActionOneDayEmpPrice(ActionEvent event) {
        String price = txtEmpSalaryPriceOneDay.getText();

        if(price == null || price.isEmpty()){
            JOptionPane.showMessageDialog(null, "price must be completed", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int pricecont;
        try {
            pricecont = Integer.parseInt(price);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        salaryPriceEmployeeDto dto = new salaryPriceEmployeeDto(pricecont);
        try{
            String resp = SALARY_EMP_MODEl.insertSlary(dto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }




    }

    public void btnRefersh(ActionEvent event) throws IOException {
        InvoiceManageBTNClick(event);
    }

    public void refershEmpSalry(ActionEvent event) {
        txtEmpId.setText("");
        totPriceEmp.setText("0  ");
        initialize();
    }

    public void clears(){
        txtOther.setText("0.00 ");
        sumTot.setText("0.00 ");
        txtO.setText("0.00 ");
        txtT.setText("0.00 ");
        txtF.setText("0.00 ");
        txtAd.setText("0.00 ");
        txtHiga.setText("0.00 ");
        totalCost.setText("0.00 ");
        minTot.setText("0.00 ");
        lblNextDaluHiga.setText("0.00 ");
        lblTotalAmount.setText("0.00 ");
        lbltotGod.setText("");
        lbltotGod1.setText("");
        lblNextPohoraHiga.setText("0.00 ");
        lblNextAdvanceHiga.setText("0.00 ");
        lblNextDaluHiga.setText("0.00 ");


    }

    public void onActionCustomerCustId(ActionEvent event) {
        LabelCheack.setText("");
        nextPaymentID();
        double teapackert = 0.00;
        double other = 0.00;

        double result = 0.00;
        double purchaseTot = 0.00 ;
        double lastCost = 0.00 ;

        DecimalFormat formatter = new DecimalFormat("#");
        String id = txtCustIdCustomer.getText();
//        try{
//            String sko = INVOICE_CUSTOMER_MODEL.cheackCustomerId(id);
//            if(!(sko == "")){
//                txtCustIdCustomer.setStyle(";-fx-border-color: Blue;");
//                clears();
//                return;
//            }
//            txtCustIdCustomer.setStyle(";-fx-border-color: null;");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        ObservableList<String> skm = FXCollections.observableArrayList();
        try {
            List<String> ssm = INVOICE_CUSTOMER_MODEL.getAllId();
            skm.addAll(ssm);
            if(!skm.contains(id)){
                txtCustIdCustomer.setStyle(";-fx-border-color: red;");
                clears();
            }
            else {
                txtCustIdCustomer.setStyle(";-fx-border-color: null;");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        String month = lbldateCustomer.getText();

        ObservableList<InvoiceCustomerDto> list = FXCollections.observableArrayList();
        try {
            String name = INVOICE_CUSTOMER_MODEL.getCustomerName(id);
            if(name != null) {
                lblCustNameCustomer.setText("" + name);
                List<InvoiceCustomerDto> dtoList = INVOICE_CUSTOMER_MODEL.customeTealeafDateGet(id);
                Label[] labels = {
                        i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15,
                        i16, i17, i18, i19, i20, i21, i22, i23, i24, i25, i26, i27, i28, i29, i30, i31
                };


                for (Label label : labels) {
                    label.setText("");
                }


                for (InvoiceCustomerDto dto : dtoList) {
                    String date = dto.getDate();
                    int day = Integer.parseInt(date.substring(8, 10));


                    if (day >= 1 && day <= 31) {
                        labels[day - 1].setText(String.valueOf(dto.getPrice()));
                    }
                }
            }
            else{
               clears();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);

        }

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY");
            String date = LocalDate.now().format(dateTimeFormatter);

            DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("MM");
            String date1 = LocalDate.now().format(dateTimeFormatter1);

            String dte = date+date1;


            List<InvoiceCustomerDto> cDto = INVOICE_CUSTOMER_MODEL.getDetailsPurchase(id);
            List<InvoiceCustomerDto> cc = INVOICE_CUSTOMER_MODEL.getTeLeaf(id);
            if(cc.size() > 0){
                InvoiceCustomerDto dd = cc.get(0);
                lbltotGod.setText(String.valueOf(dd.getPrice1()));
                lbltotGod1.setText(String.valueOf(dd.getPrice()));


                goldLefKg = dd.getPrice1();
                goodLeafKg = dd.getPrice();

                lblTt.setText(""+(goldLefKg+goodLeafKg));

            }




            if (cDto.size() > 0) {
                InvoiceCustomerDto data = cDto.get(0); // Assume all data is in a single object


                txtT.setText(String.valueOf(data.getTeaPacketTotal()));
                txtF.setText(String.valueOf(data.getFertilizeTotal()));
                txtO.setText(String.valueOf(data.getOtherTotal()));


                other = data.getOtherTotal();
                teapackert = data.getTeaPacketTotal();

                double advnces;
                double nextAd;
                double nextPohora;
                double giyamadaHiga;
                double pohora;
                try {
                    advnces  = INVOICE_CUSTOMER_MODEL.getAdvanceCustomer(id,month);
                    nextAd = INVOICE_CUSTOMER_MODEL.nextAdvanceTotal(id,month);
                    nextPohora = INVOICE_CUSTOMER_MODEL.nextPohoraTotal(id,month);
                    pohora = INVOICE_CUSTOMER_MODEL.getPohoraCountCustomer(id,month);
                    String higaMonth = "";
                    String input = month;

                    String resultsd = input.replaceAll("\\d", ""); // \\d matches all digits

                    for (int i = 0; i < dateArray.length; i++) {
                        if(dateArray[i].equals(resultsd) || dateArray[i] == resultsd){
                            if(i == 0){
                                higaMonth = dateArray[0];
                            }
                            else {
                                higaMonth = dateArray[i-1];
                            }


                        }
                    }
                    String inputs = month;
                    String results = inputs.replaceAll("[^\\d]", ""); // [^\\d] matches everything except digits

                    higaMonth=higaMonth+results;


                    giyamadaHiga = INVOICE_CUSTOMER_MODEL.getGiyamasaHiga(id,higaMonth);
                    System.out.println("ss"+higaMonth);
                    txtHiga.setText(""+giyamadaHiga);
                    txtF.setText(""+pohora);
                    txtAd.setText(""+advnces);
                    lblNextPohoraHiga.setText(""+nextPohora);
                    lblNextAdvanceHiga.setText(""+nextAd);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }



                purchaseTot = (double) (advnces+pohora+other+teapackert+giyamadaHiga+25);


                KG.setText("Kg");
                KG1.setText("Kg");



            } else {
                txtAd.setText("0.00 ");
                txtT.setText("0.00 ");
                txtF.setText("0.00 ");
                txtO.setText("0.00  ");
            }

            //kg  gana price walin wedi karama ena eka...

            /////////////////////////////////////////

            result= (double) ((goodLeafKg*goldLeafRate)+(goodLeafRate*goldLefKg));
            String sms = formatter.format(result);
            lblTotalAmount.setText(""+sms);


            double totalC = result ;

            String ss = formatter.format(totalC);
            sumTot.setText(""+ss);

            String sss = formatter.format(purchaseTot);
            minTot.setText(""+sss);

            if (result >= purchaseTot ){
                lastCost = result-purchaseTot;
                String ssss = formatter.format(lastCost);
                totalCost.setText(""+ssss);
                lblNextDaluHiga.setText("0.00 ");
            }
            else {
                lastCost = purchaseTot-result;
                String sk = formatter.format(lastCost);
                lblNextDaluHiga.setText(""+sk);
                totalCost.setText("0.00 ");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }

    public void onActionOther(ActionEvent event) {
            DecimalFormat formatter = new DecimalFormat("#");

            try {

                double others = Double.parseDouble(txtO.getText());
                double higa = Double.parseDouble(txtHiga.getText());
                double advance = Double.parseDouble(txtAd.getText());
                double pohora = Double.parseDouble(txtF.getText());
                double tea = Double.parseDouble(txtT.getText());


                double totalTeaCost = others + higa + advance + pohora + tea+25;
                minTot.setText(String.valueOf(totalTeaCost));


                double sumTotal = Double.parseDouble(sumTot.getText());
                double remainingTotal = sumTotal - totalTeaCost;


                if (remainingTotal >= 0) {
                    totalCost.setText(String.valueOf(remainingTotal));
                    lblNextDaluHiga.setText("0.0");
                } else {
                    double positiveRemaining = Math.abs(remainingTotal);
                    lblNextDaluHiga.setText(String.valueOf(positiveRemaining));
                    totalCost.setText("0.0");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Invalid input detected. Please ensure all fields contain numeric values.\nError: " + e.getMessage(),
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
    }

    public void onAtcionTeaPacket(ActionEvent event) {
    }

    public void onActionPohora(ActionEvent event) {
    }

    public void onActinAdvance(ActionEvent event) {
    }

//    public void onActionOtherSum(ActionEvent event) {
//        String other = txtOther.getText();
//        double tO = Double.parseDouble(other);
//        double totalC = tO+result ;
//        DecimalFormat formatter = new DecimalFormat("#,###,###");
//        String skl = formatter.format(totalC);
//        sumTot.setText(""+skl);
//
//        if (totalC >= purchaseTot ){
//            lastCost = totalC-purchaseTot;
//            String ss = formatter.format(lastCost);
//            totalCost.setText(""+ss);
//        }
//        else {
//            lastCost = purchaseTot-totalC;
//            String sk = formatter.format(lastCost);
//            txtHiga.setText(""+sk);
//
//        }
//
//    }

    public void PayUpButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        LabelCheack.setText("");
        nextPaymentID();
        String id = txtCustIdCustomer.getText();
        String name = lblCustNameCustomer.getText();
        String randalu = lbltotGod1.getText();
        String hodadalu = lblTt.getText();
        String amount = lblTotalAmount.getText();
        String wenathEkathukirim = txtOther.getText();
        String otherSum = txtOther.getText();
        String higa = txtHiga.getText();
        String advance = txtAd.getText();
        String pohora = txtF.getText();
        String teaPacket = txtT.getText();
        String otherMines = txtO.getText();
        String date = lbldateCustomer.getText();
        String tot = sumTot.getText();
        String rategold = lblGoldLeaf.getText();
        String rategood = lblGoodLeaf.getText();
        String nexthig = lblNextDaluHiga.getText();
        String nextPohora = lblNextPohoraHiga.getText();
        String nextAd = lblNextAdvanceHiga.getText();
        String ssss = totalCost.getText();
        String mkkk = minTot.getText();

        int kpCount =0;
                kpCount= Integer.parseInt(lbltotGod.getText());

        String PayID = lblPaymentsNb.getText();



        String x = "15";

        try{
            String sko = INVOICE_CUSTOMER_MODEL.cheackCustomerId(id,date);
            if(!(sko == "")){
                txtCustIdCustomer.setStyle(";-fx-border-color: Blue;");
                LabelCheack.setText("all ready print this customer riports");
                clears();
                return;
            }
            txtCustIdCustomer.setStyle(";-fx-border-color: null;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ObservableList<DailyHomePageDto> DLst  = FXCollections.observableArrayList();
        double ad = 0;
        double tt = 0;
        double ff = 0;
        double s = 0;

        double Amount ;
        int OtheerSum;
        double Higa ;
        double Advance;
        double Pohora ;
        double TeaPack ;
        double OtherMunies ;
        int randluu ;
        int hoddluu ;
        double totalAmount ;
        double RateGood ;
        double RateGold ;
        double NextHiga ;
        double NextAD ;
        double NextPohor ;
        double counts ;
        try {
            counts = Double.parseDouble(mkkk);
            Amount = Double.parseDouble(amount);
            //OtheerSum = Integer.parseInt(otherSum);
            Higa = Double.parseDouble(higa);
            Advance = Double.parseDouble(advance);
            Pohora = Double.parseDouble(pohora);
            TeaPack = Double.parseDouble(teaPacket);
            OtherMunies = Double.parseDouble(otherMines);
            randluu = Integer.parseInt(randalu);
            hoddluu = Integer.parseInt(hodadalu);
            totalAmount = Double.parseDouble(ssss);
            RateGold = Double.parseDouble(rategold);
            RateGood = Double.parseDouble(rategood);
            NextHiga = Double.parseDouble(nexthig);
            NextAD = Double.parseDouble(nextAd);
            NextPohor = Double.parseDouble(nextPohora);

        } catch (NumberFormatException e){
            throw new RuntimeException(e);
        }
        kpCount = (hoddluu-kpCount);

        PaymentDto pDto = new PaymentDto(PayID,id,name,date,randluu,hoddluu,Amount,Advance,Pohora,TeaPack,OtherMunies,
                totalAmount,Higa,counts,RateGood,RateGold,NextHiga,NextPohor,NextAD,kpCount);

        CustomerHigaPrice customerHigaPrice = new CustomerHigaPrice(id,date,NextHiga);
        try {
            String rsp = INVOICE_MANAGE_MODEL.inserPaymentValues(id,date,pDto,NextHiga);
            JOptionPane.showMessageDialog(null,"ALERT"+rsp+JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"ALERT","Fail Load",JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"ALERT","Fail Load",JOptionPane.INFORMATION_MESSAGE);
        }

        OnActionRePrint(event);
    }

    public void OnActionCustId(ActionEvent event) {
        String id = txFID.getText();
        ObservableList<String> sL = FXCollections.observableArrayList();
        try {
            List<String> dd = CUST_MNG_MODEL.getAllID();
            if(dd.contains(id)){
                txFID.setStyle(";-fx-border-color: null;");
                txFProductID.requestFocus();

            }
            else {
                txFID.setStyle(";-fx-border-color: red;");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void OnActionCount(ActionEvent event) {
        txFCount1.requestFocus();
    }

    public void OnActionProductId(ActionEvent event) {
        txFCount.requestFocus();
//        String prId = txFProductID.getText();
//        ObservableList<String> sL = FXCollections.observableArrayList();
//        try {
//            List<String> dd = INVOICE_CUSTOMER_MODEL.getAllProductId();
//            if (dd.contains(prId)) {
//                txFProductID.setStyle(";-fx-border-color: null;");
//
//
//            } else {
//                txFProductID.setStyle(";-fx-border-color: red;");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }


    }

    public void OnActionDate(ActionEvent event) {
    }

    public void OnActionWarikaganana(ActionEvent event) {
        btnSaveFertilizer(event);
    }

    public void OnActCount(ActionEvent event) {
        btnSaveCustomer(event);
    }

    public void OnActionCustIdT(ActionEvent event) {
        String id = txtCID.getText();
        ObservableList<String> sL = FXCollections.observableArrayList();
        try {
            List<String> dd = CUST_MNG_MODEL.getAllID();
            if(dd.contains(id)){
                txtCID.setStyle(";-fx-border-color: null;");
                txtCProductId.requestFocus();

            }
            else {
                txtCID.setStyle(";-fx-border-color: red;");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void OnActionProductIdT(ActionEvent event) {
        String prId = txtCProductId.getText();
        ObservableList<String> sL = FXCollections.observableArrayList();
        try {
            List<String> dd = INVOICE_CUSTOMER_MODEL.getAllProductId();
            if (dd.contains(prId) && ((prId.charAt(0) == 'T')||(prId.charAt(0) == 't'))) {
                txtCProductId.setStyle(";-fx-border-color: null;");
                txtCCount.requestFocus();

            } else {
                txtCProductId.setStyle(";-fx-border-color: red;");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void OnActionIDO(ActionEvent event) {
        String id = txtCID1.getText();
        ObservableList<String> sL = FXCollections.observableArrayList();
        try {
            List<String> dd = CUST_MNG_MODEL.getAllID();
            if(dd.contains(id)){
                txtCID1.setStyle(";-fx-border-color: null;");
                txtCProductId1.requestFocus();

            }
            else {
                txtCID1.setStyle(";-fx-border-color: red;");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void OnActionDateO(ActionEvent event) {
        String prId = txtCProductId1.getText();
        ObservableList<String> sL = FXCollections.observableArrayList();
        try {
            List<String> dd = INVOICE_CUSTOMER_MODEL.getAllProductId();
            if (dd.contains(prId) && ((prId.charAt(0) == 'm') || prId.charAt(0) == 'M')) {
                txtCProductId1.setStyle(";-fx-border-color: null;");
                txtCCount1.requestFocus();

            } else {
                txtCProductId1.setStyle(";-fx-border-color: red;");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void OnActionCountO(ActionEvent event) {
        btnSaveCustomerOthers(event);
    }

    @FXML
    void mouseEventA(MouseEvent mouseEvent) {
        deleteA.setDisable(false);
        savebuttonA.setDisable(true);
        txtACustId.setText(tblA.getSelectionModel().getSelectedItem().getCustId());
        txtADate.setText(tblA.getSelectionModel().getSelectedItem().getDate());
        txtAAmount.setText(""+(tblA.getSelectionModel().getSelectedItem().getPrice()));
    }

    public void mouseClickT(MouseEvent mouseEvent) {
        UpdateBtnT.setDisable(false);
        DeleteBtnT.setDisable(false);
        SaveT.setDisable(true);
        String mm = (tblT.getSelectionModel().getSelectedItem().getCustName());
        String mk = tblT.getSelectionModel().getSelectedItem().getType();

        try {
            mm = INVOICE_MANAGE_MODEL.getNameAdvance(mm);
            mk = INVOICE_MANAGE_MODEL.getProductIDT(mk);
            txtCID.setText(mm);
            txtCProductId.setText(mk);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        txtCDate.setText(tblT.getSelectionModel().getSelectedItem().getDate());
        txtCCount.setText(""+(tblT.getSelectionModel().getSelectedItem().getCount()));
        txtCPrice.setText(""+(tblT.getSelectionModel().getSelectedItem().getPrice()));

    }

    public void btnDeleteTeaPacket(ActionEvent event) {
        String id = txtCID.getText();
        String date = txtCDate.getText();
        String productId = txtCProductId.getText();
        String countStr = txtCCount.getText();
        String priceStr = txtCPrice.getText();

        // Validation: Ensure all fields are filled
        if (date == null || date.isEmpty() || productId == null || productId.isEmpty() || countStr == null || countStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be completed", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit early if validation fails
        }

        int count;
        double totalPrice;

        try {
            // Convert count and price to the appropriate types
            count = Integer.parseInt(countStr);
            totalPrice = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if there's an input error
        }

        // Create the InvoiceManageDto object
        InvoiceManageDto invoiceManageDto = new InvoiceManageDto(id, date, productId, count, totalPrice);
        StockDto stockDto = new StockDto(productId, "Product Category", count, totalPrice);
        try {
            // Save the data to the database
            String resp = INVOICE_MANAGE_MODEL.DeleteData(invoiceManageDto,stockDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        loadTableT();
        clearFrom();
    }

    public void btnResetTeaPacket(ActionEvent event) {
        SaveT.setDisable(false);
        UpdateBtnT.setDisable(true);
        DeleteBtnT.setDisable(true);

        txtCID.setText("");
        txtCProductId.setText("");
        txtCPrice.setText("");
        txtCCount.setText("");
    }

    public void btnResetAdvance(ActionEvent event) {
        savebuttonA.setDisable(false);
        deleteA.setDisable(true);
        txtACustId.setText("");
        txtAAmount.setText("");

    }

    public void mouseClickF(MouseEvent mouseEvent) {

        deleteF.setDisable(false);
        deleteF.setDisable(false);
        saveF.setDisable(true);
        String mk = (tblF.getSelectionModel().getSelectedItem().getType());
        String mm = (tblF.getSelectionModel().getSelectedItem().getCustName());
        try {
            mm = INVOICE_MANAGE_MODEL.getIdF(mm);
            mk = INVOICE_MANAGE_MODEL.getProductIDF(mk);
            txFID.setText(mm);
            txFProductID.setText(mk);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        txFDate.setText(tblF.getSelectionModel().getSelectedItem().getDate());
        txFCount.setText(""+(tblF.getSelectionModel().getSelectedItem().getCount()));
        txFPrice.setText(""+(tblF.getSelectionModel().getSelectedItem().getPrice()));

    }


    public void rsertF(ActionEvent event) {
        saveF.setDisable(false);
        deleteF.setDisable(true);
        txFID.setText("");
        txFProductID.setText("");
        txFCount.setText("");
        txFPrice.setText("");
        txFCount1.setText("");
    }


    public void mouseClickO(MouseEvent mouseEvent) {
        saveO.setDisable(true);
        deleteO.setDisable(false);
        updateO.setDisable(false);
        String name = (tblO.getSelectionModel().getSelectedItem().getCustName());
        String mk = (tblO.getSelectionModel().getSelectedItem().getType());
        try {
            name = INVOICE_MANAGE_MODEL.getIdF(name);
            mk = INVOICE_MANAGE_MODEL.getProductIDF(mk);
            txtCID1.setText(name);
            txtCProductId1.setText(mk);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        txtCCount1.setText(""+(tblO.getSelectionModel().getSelectedItem().getCount()));
        txtCPrice1.setText(""+(tblO.getSelectionModel().getSelectedItem().getPrice()));

    }

    public void btnRsetOthers(ActionEvent event) {
        saveO.setDisable(false);
        updateO.setDisable(true);
        deleteO.setDisable(true);
        txtCID1.setText("");
        txtCProductId1.setText("");
        txtCCount1.setText("");
        txtCPrice1.setText("");
    }

    public void btnDeleteOthers(ActionEvent event) {
        String id = txtCID1.getText();
        String date = txtCDate1.getText();
        String productId = txtCProductId1.getText();
        String countStr = txtCCount1.getText();
        String priceStr = txtCPrice1.getText();

        if (id == null || id.isEmpty() || date == null || date.isEmpty() || productId == null || productId.isEmpty() || countStr == null || countStr.isEmpty() || priceStr == null || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be completed", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit early if validation fails
        }

        int count;
        double totalPrice;

        try {
            count = Integer.parseInt(countStr);
            totalPrice = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if there's an input error
        }


        InvoiceManageDto invoiceManageDto = new InvoiceManageDto(id, date, productId, count, totalPrice);
        StockDto stockDto = new StockDto(productId, "Product Category", count, totalPrice);
        try {
            String resp = INVOICE_MANAGE_MODEL.deleteOtherData(invoiceManageDto,stockDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        loadTableO();
        clearOthersForm();

    }

   public void OnActionRePrint(ActionEvent event) throws SQLException, ClassNotFoundException {
        String x = txtCustIdCustomer.getText();
        String date = lbldateCustomer.getText();
        double xx = Double.parseDouble(lbltotGod.getText());

        int i =0;

        String xxs = ""+xx;
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/Ripo/sathindu7.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("custId",x);
            parameters.put("date",date);
            parameters.put("Hutta",xxs);
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY_1", " ");
                } else {
                    parameters.put("DAY_1",xs);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY2", " ");
                } else {
                    parameters.put("DAY2",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY3", " ");
                } else {
                    parameters.put("DAY3",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY4", " ");
                } else {
                    parameters.put("DAY4",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY5", " ");
                } else {
                    parameters.put("DAY5",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY6", " ");
                } else {
                    parameters.put("DAY6",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY7", " ");
                } else {
                    parameters.put("DAY7",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY8", " ");
                } else {
                    parameters.put("DAY8",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY9", " ");
                } else {
                    parameters.put("DAY9",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY10", " ");
                } else {
                    parameters.put("DAY10",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY11", " ");
                } else {
                    parameters.put("DAY11",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY12", " ");
                } else {
                    parameters.put("DAY12",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY13", " ");
                } else {
                    parameters.put("DAY13",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY14", " ");
                } else {
                    parameters.put("DAY14",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY15", " ");
                } else {
                    parameters.put("DAY15",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY16", " ");
                } else {
                    parameters.put("DAY16",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY17", " ");
                } else {
                    parameters.put("DAY17",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY18", " ");
                } else {
                    parameters.put("DAY18",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY19", " ");
                } else {
                    parameters.put("DAY19", xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY20", " ");
                } else {
                    parameters.put("DAY20", xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY21", " ");
                } else {
                    parameters.put("DAY21",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY22", " ");
                } else {
                    parameters.put("DAY22",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY23", " ");
                } else {
                    parameters.put("DAY23",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }          try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY24", " ");
                } else {
                    parameters.put("DAY24",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY25", " ");
                } else {
                    parameters.put("DAY25",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }          try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY26", " ");
                } else {
                    parameters.put("DAY26",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY27", " ");
                } else {
                    parameters.put("DAY27",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY28", " ");
                } else {
                    parameters.put("DAY28",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY29", " ");
                } else {
                    parameters.put("DAY29",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
            try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY30", " ");
                } else {
                    parameters.put("DAY30",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }          try {
                i++;
                String startDate = loadDae(i);
                String xs = INVOICE_MANAGE_MODEL.getT(x,startDate);
                if (xs == null || xs.equals("0")) {
                    parameters.put("DAY31", " ");
                } else {
                    parameters.put("DAY31",xs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }




            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,DBConnection.getInstance().getConnection());


            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
   }

    private String loadDae(int i) {
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = (formattedDate + "-"+i) ;
        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return startDate;
    }

    public void OnActionDALU(ActionEvent event) {
        int hod = Integer.parseInt(lbltotGod.getText());
        double hRate = Double.parseDouble(lblGoodLeaf.getText());
        int gold = Integer.parseInt(lbltotGod1.getText());
        double gReta = Double.parseDouble(lblGoldLeaf.getText());

        double x = (hod*hRate)+(gold*gReta);
        lblTotalAmount.setText(""+x);
        sumTot.setText(""+x);

        try {

            double others = Double.parseDouble(txtO.getText());
            double higa = Double.parseDouble(txtHiga.getText());
            double advance = Double.parseDouble(txtAd.getText());
            double pohora = Double.parseDouble(txtF.getText());
            double tea = Double.parseDouble(txtT.getText());


            double totalTeaCost = others + higa + advance + pohora + tea + 25;
            minTot.setText(String.valueOf(totalTeaCost));


            double sumTotal = Double.parseDouble(sumTot.getText());
            double remainingTotal = sumTotal - totalTeaCost;


            if (remainingTotal >= 0) {
                totalCost.setText(String.valueOf(remainingTotal));
                lblNextDaluHiga.setText("0.0");
            } else {
                double positiveRemaining = Math.abs(remainingTotal);
                lblNextDaluHiga.setText(String.valueOf(positiveRemaining));
                totalCost.setText("0.0");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid input detected. Please ensure all fields contain numeric values.\nError: " + e.getMessage(),
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }



    }

    public void ONActionBankBill(ActionEvent event) {
    }

    public void ONACTIONPROFIT(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/PROFIT.fxml"));;
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(anchorPane));
        stage1.show();
        stage1.setResizable(false);
        stage1.setTitle("NET PROFIT");
    }
}
