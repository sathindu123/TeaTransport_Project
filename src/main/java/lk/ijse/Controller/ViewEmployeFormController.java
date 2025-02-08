package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.EmployeeManageDto;

import java.util.List;

public class ViewEmployeFormController extends ManagementCetegoryController {
    @FXML
    private TextField txtSearchEmp;

    private ViewEmployeeFormDAOimpl VIEW_EMPLOYEE_MODEL;
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


    public ViewEmployeFormController(){
        VIEW_EMPLOYEE_MODEL = new ViewEmployeeFormDAOimpl();
    }

    public void initialize(){
        clEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clEmpTel.setCellValueFactory(new PropertyValueFactory<>("number"));

        loadTable();
    }
    public void loadTable(){
        ObservableList<EmployeeManageDto> employeeList = FXCollections.observableArrayList();
        try {
            List<EmployeeManageDto> empDtos = VIEW_EMPLOYEE_MODEL.getAllCustomer();
            employeeList.addAll(empDtos);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Error loading customer data", e.getMessage());
        }
        tblEmpViewForm.setItems(employeeList);
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void BtnOKClickSearchEmployee(){
        String id = txtSearchEmp.getText();
        try {
            EmployeeManageDto dto = VIEW_EMPLOYEE_MODEL.BtnOKClickSearchEmployee(id);
            if (dto != null) {
                ObservableList<EmployeeManageDto> searchResult = FXCollections.observableArrayList();
                searchResult.add(dto);
                tblEmpViewForm.setItems(searchResult);  // Update the table with the search result
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Not Found", null, "Customer Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());
        }
        clearFromEmp();
    }

    private void clearFromEmp() {
        txtSearchEmp.setText("");
    }

    public void btnRefeshViewEmployee(ActionEvent event) {
        loadTable();
    }
}
