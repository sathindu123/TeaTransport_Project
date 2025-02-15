package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.bo.custom.ViewStockBO;
import lk.ijse.bo.custom.impl.ViewStockBoimpl;
import lk.ijse.dto.StockDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewStockController {
    ViewStockBO viewStockBO = new ViewStockBoimpl();

    public TextField txtSearchProduct;

    @FXML
    private TableView<StockDto> tblProductViewForm;

    @FXML
    private TableColumn<StockDto,String> clProductId;
    @FXML
    private TableColumn<StockDto,String>clCategory;

    @FXML
    private TableColumn<StockDto, Integer>clCount;
    @FXML
    private TableColumn<StockDto, Double>clPrice;
    @FXML
    ListView ListView;



    public ViewStockController(){

    }

    public void initialize(){
        txtSearchProduct.setOnKeyReleased(this::handleKeyReleased);
        clProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        clCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        clPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        loadTable();

        ListView.setVisible(false);

        // Handle clicking on an item in the ListView
        ListView.setOnMouseClicked(event -> {
            String selectedSuggestion = (String) ListView.getSelectionModel().getSelectedItem();
            if (selectedSuggestion != null) {
                txtSearchProduct.setText(selectedSuggestion); // Update the search bar with the selected suggestion
                ListView.getItems().clear(); // Clear the suggestions
                ListView.setVisible(false); // Hide the suggestions after selection
            }
        });

    }

    private void handleKeyReleased(KeyEvent keyEvent) {
        String typedText = txtSearchProduct.getText(); // Get the text currently in the search box
        if (typedText.isEmpty()) {
            ListView.getItems().clear(); // Clear suggestions if input is empty
            ListView.setVisible(false); // Hide suggestions if input is empty
            return;
        }

        List<String> suggestions = new ArrayList<>();
        try {
            suggestions = viewStockBO.searchStock(typedText);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found: " + e.getMessage());
        }


        if (!suggestions.isEmpty()) {
            ListView.getItems().setAll(suggestions);
            ListView.setVisible(true);
        } else {
            ListView.getItems().clear();
            ListView.setVisible(false);
        }
    }

    public void loadTable() {
        ObservableList<StockDto> productList = FXCollections.observableArrayList();
        try {
            // Fetch product data from the model
            List<StockDto> productDtos = viewStockBO.getAllProducts();  // Updated method name
            // Add fetched data to the ObservableList
            productList.addAll(productDtos);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Error loading product data", e.getMessage());
        }

        tblProductViewForm.setItems(productList);
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void BtnOKClickSearchProduct(ActionEvent event) {
        String id = txtSearchProduct.getText();

        try {
            StockDto dto = viewStockBO.searchProduct(id);  // Updated method name
            if (dto != null) {
                ObservableList<StockDto> searchResult = FXCollections.observableArrayList();
                searchResult.add(dto);
                tblProductViewForm.setItems(searchResult);  // Update the table with the search result
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Not Found", null, "Product Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());
        }
        clearStock();
    }

    private void clearStock() {
        txtSearchProduct.setText("");
    }

    public void btnRefeshViewStocks(ActionEvent event) {
        loadTable();
    }
}
