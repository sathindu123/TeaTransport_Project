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
import lk.ijse.dto.FactoryManageDto;

import java.util.List;

public class ViewFactoryFormController {
//    private ViewFactoryFormDAOimpl VIEW_FACTORY_FORM_MODEL;
//
//    public TextField txtSearchFactory;
//
//    @FXML
//    private TableView<FactoryManageDto> tblFactoryViewForm;
//
//    @FXML
//    private TableColumn<FactoryManageDto,String> clFactoryId;
//    @FXML
//    private TableColumn<FactoryManageDto,String> clFactoryName;
//    @FXML
//    private TableColumn<FactoryManageDto,String> clFactoryAddress;
//    @FXML
//    private TableColumn<FactoryManageDto,Integer> clFactoryTel;
//
//    public ViewFactoryFormController(){
//        VIEW_FACTORY_FORM_MODEL = new ViewFactoryFormDAOimpl();
//    }
//
//    public void initialize(){
//        clFactoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
//        clFactoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        clFactoryAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
//        clFactoryTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
//
//        LoadTable();
//    }
//
//    public void LoadTable(){
//        ObservableList<FactoryManageDto> FactoryList = FXCollections.observableArrayList();
//        try {
//            List<FactoryManageDto> factoryDtos = VIEW_FACTORY_FORM_MODEL.getAllCustomer();
//            FactoryList.addAll(factoryDtos);
//        } catch (Exception e) {
//            e.printStackTrace();
//            showAlert(Alert.AlertType.ERROR, "Error", "Error loading customer data", e.getMessage());
//        }
//        tblFactoryViewForm.setItems(FactoryList);
//    }
//
//    private void showAlert(Alert.AlertType type, String title, String header, String content) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(header);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//
//    public void txtFactorySearchButton(ActionEvent event) {
//        String id = txtSearchFactory.getText();
//        try {
//            FactoryManageDto dto = VIEW_FACTORY_FORM_MODEL.BtnOKClickSearchFactory(id);
//            if(dto != null){
//                ObservableList<FactoryManageDto> searchResult = FXCollections.observableArrayList();
//                searchResult.add(dto);
//                tblFactoryViewForm.setItems(searchResult);  // Update the table with the search result
//            } else {
//                showAlert(Alert.AlertType.INFORMATION, "Not Found", null, "Customer Not Found");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            showAlert(Alert.AlertType.ERROR, "Error", null, e.getMessage());
//        }
//    }

}
