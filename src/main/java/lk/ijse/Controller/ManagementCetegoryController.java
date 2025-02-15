package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.custom.*;
import lk.ijse.bo.custom.impl.*;
import lk.ijse.dto.CustomerManageDto;
import lk.ijse.dto.EmployeeManageDto;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ManagementCetegoryController extends DashboardFormController  {

    CustomerManageBO customerManageBO = new CustomerManageBoimpl();
    EmployeeManageBO employeeManageBO = new EmployeeManageBoimpl();
    ViewCustomerFormBO viewCustomerFormBO = new ViewCustomerFormBoimpl();
    ViewEmployeeFormBO viewEmployeeFormBO = new ViewEmployeeFormBoimpl();
    QuearyBO quearyBO = new QuearyBOimpl();

    public TextField CustId;
    public TextField CustName;
    public TextField CustAddress;
    public TextField CustTel;


    public TextField FactoryId;
    public TextField FactoryName;
    public TextField FactoryAddress;
    public TextField FactoryTel;

    public TextField UpdateFactoryId;
    public TextField UpdateFactoryName;
    public TextField UpdateFactoryAddress;
    public TextField UpdateFactoryTel;

    public TextField DeleteFactory;

    public TextField EmpId;
    public TextField EmpName;
    public TextField EmpAddress;
    public TextField EmpTel;

    @FXML
    private TableView<CustomerManageDto> tblCustomerViewForm;



    @FXML
    private TableColumn<CustomerManageDto, String> clCustomerId;
    @FXML
    private TableColumn<CustomerManageDto, String> clCustomerName;
    @FXML
    private TableColumn<CustomerManageDto, String> clCustomerAddress;
    @FXML
    private TableColumn<CustomerManageDto, Integer> clCustomerTel;

    @FXML
    private TableView<EmployeeManageDto> tblEmpViewForm;

    @FXML
    private TableColumn<EmployeeManageDto, String> clEmpId;
    @FXML
    private TableColumn<EmployeeManageDto, String> clEmpName;
    @FXML
    private TableColumn<EmployeeManageDto, String> clEmpAddress;
    @FXML
    private TableColumn<EmployeeManageDto, Integer> clEmpTel;

    @FXML
    private Button updatebtnCcs;
    @FXML
    private Button deleteC;

    public  ManagementCetegoryController(){


    }


    public void  initialize(){
        clCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clCustomerTel.setCellValueFactory(new PropertyValueFactory<>("number"));

        clEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clEmpTel.setCellValueFactory(new PropertyValueFactory<>("number"));

        loadTableCust();
        loadTableEMP();

    }

    public void loadTableEMP(){
        ObservableList<EmployeeManageDto> employeeList = FXCollections.observableArrayList();
        try {
            List<EmployeeManageDto> empDtos = viewEmployeeFormBO.getAllCustomer();
            employeeList.addAll(empDtos);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Error loading customer data", e.getMessage());
        }
        tblEmpViewForm.setItems(employeeList);
    }



    public void loadTableCust(){
        
        ObservableList<CustomerManageDto> customerList = FXCollections.observableArrayList();
        try {
            List<CustomerManageDto> customerDtos = viewCustomerFormBO.getAllCustomer();
            customerList.addAll(customerDtos);
            tblCustomerViewForm.setItems(customerList);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Error loading customer data", e.getMessage());
        }

    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void ViewCustomerBtnClick(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ViewCustomerForm.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(anchorPane));
        stage1.show();
        stage1.setResizable(false);
        stage1.setTitle("Employee view form");
    }

    public void ViewEmpoyeBtnClick(ActionEvent event) throws IOException{
        AnchorPane anchorPane =FXMLLoader.load(getClass().getResource("/view/ViewEmployeForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane));
        stage.show();
        stage.setResizable(false);
        stage.setTitle("View Employee");
    }

    public void ViewSupplierBtnClick(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ViewEmployeForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane));
        stage.show();
        stage.setResizable(false);
        stage.setTitle("View Suppliers");
    }

    public void ViewFactoryFormBtnClick() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ViewFactoryForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(anchorPane));
        stage.show();
        stage.setResizable(false);
        stage.setTitle("View Factoryes");
    }

    public void BtnSaveClickCustomer(){
        String id = CustId.getText();
        String name = CustName.getText();
        String address = CustAddress.getText();
        String tel = CustTel.getText();

//        if (id == null || id.isEmpty() || name == null || name.isEmpty() || address == null || address.isEmpty() || tel == null || tel.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "All fields must be filled!", "Validation Error", JOptionPane.ERROR_MESSAGE);
//            return; // Exit the method if validation fails
//        }
        int telNB;
        try {
            telNB = Integer.parseInt(tel);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Telephone number must be a valid integer!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if tel is not a valid integer
        }

        CustName.setStyle(CustName.getStyle());




        if(true) {
            CustomerManageDto customerManageDto = new CustomerManageDto(id, name, address, Integer.parseInt(tel));

            try {
                String resp = customerManageBO.savebtnclick(customerManageDto);
                JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!! Customer Not Save: " , "Error", JOptionPane.ERROR_MESSAGE);
            }
            loadTableCust();
            clearForm();
        }

    }

    private void clearForm(){
        CustId.setText("");
        CustName.setText("");
        CustAddress.setText("");
        CustTel.setText("");
    }


    public void BtnUpdateClick(ActionEvent event){
        String id = CustId.getText();
        String name = CustName.getText();
        String address = CustAddress.getText();
        String tel = CustTel.getText();
        if(id == null || id.isEmpty() || name == null || name.isEmpty() || address == null || address.isEmpty() || tel == null || tel.isEmpty()){
            JOptionPane.showMessageDialog(null,"ALl feild must be filled","validation error",JOptionPane.ERROR_MESSAGE);
        }

        int telNB;
        try {
            telNB = Integer.parseInt(tel);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Telephone number must be a valid integer!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        CustomerManageDto customerManageDto = new CustomerManageDto(id,name,address,Integer.parseInt(tel));

        try {
            String resp = customerManageBO.BtnUpdateClick(customerManageDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error occurred" + e.getMessage());
        }
        loadTableCust();
        clearForm();
    }





    public void BtnClickDelete(ActionEvent event){
        String id = CustId.getText();
        if(id == null || id.isEmpty()){
            JOptionPane.showMessageDialog(null,"ALl feild must be filled","validation error",JOptionPane.ERROR_MESSAGE);
        }

        try{
            String resp = customerManageBO.BtnClickDelete(id);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error occurred" + e.getMessage());
        }

        loadTableCust();
        clearForm();

    }

    public void BtnAddEmployee(){
        String id = EmpId.getText();
        String name = EmpName.getText();
        String address = EmpAddress.getText();
        String tel = EmpTel.getText();

        if(id == null || id.isEmpty() || name == null || name.isEmpty() || address == null || address.isEmpty() || tel == null || tel.isEmpty()){
            JOptionPane.showMessageDialog(null,"ALl feild must be filled","validation error",JOptionPane.ERROR_MESSAGE);
        }

        int telNB;
        try {
            telNB = Integer.parseInt(tel);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Telephone number must be a valid integer!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EmployeeManageDto employeeManageDto = new EmployeeManageDto(id,name,address,Integer.parseInt(tel));

        try {
            String resp = employeeManageBO.BtnAddEmployee(employeeManageDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error occurred" + e.getMessage());
        }
        loadTableEMP();
        BtnCancelEmployee();

    }

    public void BtnCancelEmployee(){
        EmpId.setText("");
        EmpName.setText("");
        EmpAddress.setText("");
        EmpTel.setText("");
    }


    public void BtnClickEmployeeUpdate(ActionEvent event){
        String id = EmpId.getText();
        String name = EmpName.getText();
        String address = EmpAddress.getText();
        String tel = EmpTel.getText();

        if(id == null || id.isEmpty() || name == null || name.isEmpty() || address == null || address.isEmpty() || tel == null || tel.isEmpty()){
            JOptionPane.showMessageDialog(null,"ALl feild must be filled","validation error",JOptionPane.ERROR_MESSAGE);
        }

        int telNB;
        try {
            telNB = Integer.parseInt(tel);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Telephone number must be a valid integer!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        EmployeeManageDto employeeManageDto = new EmployeeManageDto(id,name,address,Integer.parseInt(tel));

        try {
            String resp = employeeManageBO.BtnClickEmployeeUpdate(employeeManageDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error occurred" + e.getMessage());
        }
        loadTableEMP();
        BtnCancelEmployee();

    }






    public void OnActionId(ActionEvent event) {
        String id = CustId.getText();
        ObservableList<String> sL = FXCollections.observableArrayList();
        try {
            List<String> dd = customerManageBO.getAllID();
            if(dd.contains(id)){
                CustId.setStyle(";-fx-border-color: red;");
            }
            else {
                CustId.setStyle(";-fx-border-color: null;");
                CustName.requestFocus();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void OnActionTell(ActionEvent event) {
        BtnSaveClickCustomer();
    }

    public void OnActionAddress(ActionEvent event) {
        CustTel.requestFocus();
    }

    public void OnActionName(ActionEvent event) {
        String name = CustName.getText();
        CustAddress.requestFocus();
    }

    public void mouseCLickCustomer(MouseEvent mouseEvent) {
        CustId.setText(tblCustomerViewForm.getSelectionModel().getSelectedItem().getId());
        CustName.setText(tblCustomerViewForm.getSelectionModel().getSelectedItem().getName());
        CustAddress.setText(tblCustomerViewForm.getSelectionModel().getSelectedItem().getAddress());
        CustTel.setText(""+tblCustomerViewForm.getSelectionModel().getSelectedItem().getNumber());
    }

    public void mouseEmp(MouseEvent mouseEvent) {
    }
}
