package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.Dao.custom.ViewCustomerFormDAO;
import lk.ijse.bo.custom.ViewCustomerFormBO;
import lk.ijse.bo.custom.impl.ViewCustomerFormBoimpl;
import lk.ijse.dto.CustomerManageDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewCustomerFormController {

    public TextField txtSearch;

    @FXML
    private TableView<CustomerManageDto> tblCustomerViewForm;

    ViewCustomerFormBO viewCustomerFormBO = new ViewCustomerFormBoimpl();

    @FXML
    private TableColumn<CustomerManageDto, String> clCustomerId;
    @FXML
    private TableColumn<CustomerManageDto, String> clCustomerName;
    @FXML
    private TableColumn<CustomerManageDto, String> clCustomerAddress;
    @FXML
    private TableColumn<CustomerManageDto, Integer> clCustomerTel;

    @FXML
    private ListView<String> lstSuggestions;

    public ViewCustomerFormController(){


    }

    public void initialize() {
        txtSearch.setOnKeyReleased(this::handleKeyReleased);
        clCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clCustomerTel.setCellValueFactory(new PropertyValueFactory<>("number"));

        loadTable();

        lstSuggestions.setVisible(false);

        // Handle clicking on an item in the ListView
        lstSuggestions.setOnMouseClicked(event -> {
            String selectedSuggestion = lstSuggestions.getSelectionModel().getSelectedItem();
            if (selectedSuggestion != null) {
                txtSearch.setText(selectedSuggestion); // Update the search bar with the selected suggestion
                lstSuggestions.getItems().clear(); // Clear the suggestions
                lstSuggestions.setVisible(false); // Hide the suggestions after selection
            }
        });
    }

    private void handleKeyReleased(KeyEvent keyEvent) {
        String typedText = txtSearch.getText(); // Get the text currently in the search box
        if (typedText.isEmpty()) {
            lstSuggestions.getItems().clear(); // Clear suggestions if input is empty
            lstSuggestions.setVisible(false); // Hide suggestions if input is empty
            return;
        }

        List<String> suggestions = new ArrayList<>();
        try {
            suggestions = viewCustomerFormBO.searchCus(typedText);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found: " + e.getMessage());
        }

        // Update the suggestion list if matches are found
        if (!suggestions.isEmpty()) {
            lstSuggestions.getItems().setAll(suggestions); // Populate the ListView with suggestions
            lstSuggestions.setVisible(true);
        } else {
            lstSuggestions.getItems().clear();
            lstSuggestions.setVisible(false);
        }
    }


    public void loadTable(){
        ObservableList<CustomerManageDto> customerList = FXCollections.observableArrayList();
        try {
            List<CustomerManageDto> customerDtos = viewCustomerFormBO.getAllCustomer();
            customerList.addAll(customerDtos);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Error loading customer data", e.getMessage());
        }
        tblCustomerViewForm.setItems(customerList);
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void BtnOKClickCearch(){
        String id = txtSearch.getText();

        try {
            CustomerManageDto dto = viewCustomerFormBO.searchCustomer(id);
            if (dto != null) {
                ObservableList<CustomerManageDto> searchResult = FXCollections.observableArrayList();
                searchResult.add(dto);
                tblCustomerViewForm.setItems(searchResult);  // Update the table with the search result
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Not Found", null, "Customer Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());
        }
        clearCustomerFrom();
    }

    private void clearCustomerFrom() {
        txtSearch.setText("");
    }


    public void btnRefeshViewCustomer(ActionEvent event) {
        loadTable();
    }
}
