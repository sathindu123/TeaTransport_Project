package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.custom.StockBO;
import lk.ijse.bo.custom.impl.StockBoimpl;
import lk.ijse.dto.StockDto;

import javax.swing.*;
import java.io.IOException;

public class BtnTeaStockController extends StockManagamentController{
    AnchorPane anchorPane;

    StockBO stockBO = new StockBoimpl();

    public TextField txId;
    public TextField txCategory;
    public TextField txCount;
    public TextField txPrice;

    public BtnTeaStockController(){

    }

    public void PohoraButtonClick(javafx.event.ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/StockManagement.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Tea Stock Management ");
    }

    public void btnSaveTeaStock(){
        String id = txId.getText();
        String category = txCategory.getText();
        String count = txCount.getText();
        String price = txPrice.getText();

        if(id == null || id.isEmpty() || category == null || category.isEmpty() || count == null || count.isEmpty() || price == null || price.isEmpty()){
            JOptionPane.showMessageDialog(null,"ALL Field must be erroe","Validation Erroe",JOptionPane.ERROR_MESSAGE);
        }

        double pric;
        int cont;
        try {
            pric = Double.parseDouble(price);
            cont = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"price must be a valid integer!","Validation Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        StockDto stockDto = new StockDto(id,category,cont,pric);

        try {
            String resp =stockBO.save(stockDto);
            JOptionPane.showMessageDialog(null,"Save status " + resp , "Save status",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erroe occurride" + e.getMessage());
        }
        clearFromTea();
    }

    private void clearFromTea() {
        txPrice.setText("");
        txId.setText("");
        txCount.setText("");
        txCategory.setText("");
    }


    public void btnUpdateClick(ActionEvent event) {
        String id = txId.getText();
        String category = txCategory.getText();
        String count = txCount.getText();
        String price = txPrice.getText();

        if(id == null || id.isEmpty() || category == null || category.isEmpty() || count == null || count.isEmpty() || price == null || price.isEmpty()){
            JOptionPane.showMessageDialog(null,"","",JOptionPane.ERROR_MESSAGE);
        }
        double pric;
        int cont;
        try {
            pric = Double.parseDouble(price);
            cont = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"price must be a valid integer!","Validation Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        StockDto stockDto = new StockDto(id,category,cont,pric);

        try {
            String resp =stockBO.btnUpdateClick(stockDto);
            JOptionPane.showMessageDialog(null,"Save status " + resp , "Save status",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erroe occurride" + e.getMessage());
        }
        clearFromTea();

    }

    public void productIdTeaStockOnAction(ActionEvent event) {
        String id = txId.getText();
        try {
            String nam = stockBO.getNames(id);
            txCategory.setText(""+nam);
            int con = stockBO.getCounts(id);
            txCount.setText(""+con);
        } catch (Exception e) {
            System.out.println("NUll");
        }
    }
}
