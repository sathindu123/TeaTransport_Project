package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.StockDto;


import javax.swing.*;
import java.io.IOException;

public class StockManagamentController extends DashboardFormController {
    AnchorPane anchorPane;
    private StockDAOimpl STOCK_MODEL;

   public TextField txtId;
   public TextField txtCategory;
   public TextField txtCount;
   public TextField txtPrice;

    public StockManagamentController(){
        STOCK_MODEL=new StockDAOimpl();
    }

    public void TeaStockBTNClick(javafx.event.ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/BtnTeaStockClick.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Stock Management Page");
    }


    public void btnOthersClick(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/btnOthersClick.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Stock Management Page");
    }


    public void btnSaveStock(ActionEvent event) {
        String id = txtId.getText();
        String cetegory = txtCategory.getText();
        String count = txtCount.getText();
        String price = txtPrice.getText();

        if(id == null || id.isEmpty() || cetegory == null || cetegory.isEmpty() || count == null || count.isEmpty() || price == null || price.isEmpty()){
            JOptionPane.showMessageDialog(null, "All fields must be filled!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int cont;
        double pric;
        try {
            pric = Double.parseDouble(price);
            cont = Integer.parseInt(count);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "price must be a valid integer!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StockDto stockDto = new StockDto(id,cetegory,Integer.parseInt(count),Double.parseDouble(price));


        try {
            String resp = STOCK_MODEL.btnSaveStock(stockDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error occurred" + e.getMessage());
        }

        clearFrom();
    }

    public void clearFrom(){
        txtId.setText("");
        txtCategory.setText("");
        txtCount.setText("");
        txtPrice.setText("");
    }


    public void btnUpdateStock(ActionEvent event) {
        String id = txtId.getText();
        String category = txtCategory.getText();
        String count = txtCount.getText();
        String price = txtPrice.getText();


        if (id == null || id.isEmpty() || category == null || category.isEmpty() || count == null || count.isEmpty() || price == null || price.isEmpty()){
            JOptionPane.showMessageDialog(null,"All filed must be completed","Validation Error",JOptionPane.ERROR_MESSAGE);
        }
        int cont;
        double pric;
        try{
            pric = Double.parseDouble(price);
            cont = Integer.parseInt(count);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "price must be a valid integer!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StockDto stockDto = new StockDto(id,category,cont,Double.parseDouble(price));

        try {
            String resp = STOCK_MODEL.btnUpdateStock(stockDto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error occurred" + e.getMessage());
        }

        clearFrom();
    }


    public void ProductIdOnAction(ActionEvent event) {
        String id = txtId.getText();
        try {
            String nam = STOCK_MODEL.getNames(id);
            txtCategory.setText(""+nam);
            int con = STOCK_MODEL.getCounts(id);
            txtCount.setText(""+con);
        } catch (Exception e) {
            System.out.println("NUllll");
        }
    }
}
