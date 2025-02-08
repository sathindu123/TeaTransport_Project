package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.MonthlyRateDto;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class NetPriceKgController {
    public TextField txtRate;
    public TextField txtRate1;
    public ComboBox comBoxId;
    public TextField txtYear;
    private MonthlyRateDAOimpl MONTH_RATE_MODEL;
    @FXML
    private TableView<MonthlyRateDto> tblNetPrice;
    @FXML
    private TableColumn<MonthlyRateDto,String > clMonth;
    @FXML
    private TableColumn<MonthlyRateDto,Integer> clRate;
    @FXML
    private TableColumn<MonthlyRateDto,Integer> clRate1;

    public void initialize() {
        comboxLoad();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy");
        String formattedDate = LocalDateTime.now().format(dateFormatter);
        txtYear.setText(""+formattedDate);

        clMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        clRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        clRate1.setCellValueFactory(new PropertyValueFactory<>("rate1"));

        loadTableprice();
    }

    private void loadTableprice() {
        ObservableList<MonthlyRateDto> rateList =FXCollections.observableArrayList();
        try {
            List<MonthlyRateDto> rateDto = MONTH_RATE_MODEL.loadDetails();
            rateList.addAll(rateDto);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error occurred" + e.getMessage());
        }
        tblNetPrice.setItems(rateList);
    }

    private void comboxLoad() {
        ObservableList<String> com =FXCollections.observableArrayList(
                "JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"
        );


        comBoxId.setItems(com);

    }

    public NetPriceKgController(){
        MONTH_RATE_MODEL = new MonthlyRateDAOimpl();
    }


    public void btnSave(ActionEvent event) {
        String month = txtYear.getText();
        String rate = txtRate.getText();
        String rate1 = txtRate1.getText();
        String combox =(String) comBoxId.getValue();

        String date = month+combox;

        if(month == null || month.isEmpty() || rate == null || rate.isEmpty()|| rate1 == null || rate1.isEmpty()){
            JOptionPane.showMessageDialog(null,"ALL Field must be not complited","Validation Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        double pric;
        double pric1;

        try {
            pric = Double.parseDouble(rate);
            pric1 = Double.parseDouble(rate1);

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "price must be a valid integer!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        MonthlyRateDto dto = new MonthlyRateDto(date,pric,pric1);
        try {
            String resp = MONTH_RATE_MODEL.btnSaveRate(dto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error occurred" + e.getMessage());
        }
        loadTableprice();
        clearFromrate();
    }

    private void clearFromrate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY");
        String date = LocalDateTime.now().format(dateTimeFormatter);
        txtRate.setText("");
        txtRate1.setText("");
        txtYear.setText(""+date);

    }

    public void btnUpdate(ActionEvent event) {
        String month = txtYear.getText();
        String rate = txtRate.getText();
        String rate1 = txtRate1.getText();
        String combox =(String) comBoxId.getValue();

        String date = month+combox;

        if(month == null || month.isEmpty() || rate == null || rate.isEmpty()|| rate1 == null || rate1.isEmpty()){
            JOptionPane.showMessageDialog(null,"ALL Field must be not complited","Validation Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        double pric;
        double pric1;

        try {
            pric = Double.parseDouble(rate);
            pric1 = Double.parseDouble(rate1);

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "price must be a valid integer!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        MonthlyRateDto dto = new MonthlyRateDto(date,pric,pric1);
        try {
            String resp = MONTH_RATE_MODEL.btnUpdateRate(dto);
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error occurred" + e.getMessage());
        }

        clearFromrate();
        loadTableprice();

    }
}
