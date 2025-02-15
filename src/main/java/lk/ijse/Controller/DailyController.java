package lk.ijse.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Dao.custom.impl.DailyHomePageDAOimpl;
import lk.ijse.bo.custom.DailyHomePageBO;
import lk.ijse.bo.custom.EmpWorkDetailsBO;
import lk.ijse.bo.custom.impl.DailyhomepageBoimpl;
import lk.ijse.bo.custom.impl.EmpWorkDetailBoimpl;
import lk.ijse.dto.DailyHomePageDto;
import lk.ijse.dto.EmpWordDetailDto;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DailyController extends DashboardFormController{

    EmpWorkDetailsBO empWorkDetailsBO = new EmpWorkDetailBoimpl();
    DailyHomePageBO dailyHomePageBO = new DailyhomepageBoimpl();

    private int count =0;
    @FXML
    private AnchorPane anchorPane;

    public Label lblCount ;

    @FXML
    private Label TxtDateTime;
    @FXML
    private TextField txtEmpId;
    @FXML
    private TextField txtDate;

    @FXML
    private TextField CustomerId;
    @FXML
    private Label CustomerName;
    @FXML
    private TextField Date;
    @FXML
    private TextField GoldLeaf;
    @FXML
    private TextField GoodLeafAmount;

    private int cont = 1;

    @FXML
    private TableView<DailyHomePageDto> tblDalily;
    @FXML
    private TableColumn<DailyHomePageDto,String > Cid;
    @FXML
    private TableColumn<DailyHomePageDto,Integer>GLAmount;
    @FXML
    private TableColumn<DailyHomePageDto,Integer>GGLeafAmount;
    @FXML
    private TableColumn<DailyHomePageDto,String>CName;
    @FXML
    private TableColumn<DailyHomePageDto,String>CDate;

    public DailyController(){

    }


    @FXML
    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd      HH:mm:ss");
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (TxtDateTime != null) {
                        TxtDateTime.setText(LocalDateTime.now().format(formatter));
                    }
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        if (Cid != null && GLAmount != null && GGLeafAmount != null) {
            Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
            CName.setCellValueFactory(new PropertyValueFactory<>("name"));
            CDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            GLAmount.setCellValueFactory(new PropertyValueFactory<>("goldLeaf"));
            GGLeafAmount.setCellValueFactory(new PropertyValueFactory<>("goodLeaf"));
        } else {
            System.out.println("TableColumns not initialized properly.");
        }


        loadd();

        loadTableDaily();
    }

    private void loadd() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDateTime.now().format(dateFormatter);
        try{
            txtDate.setText(formattedDate);
            Date.setText(formattedDate);
        } catch (Exception e) {
            System.out.println("NUll");
        }

    }

    private void loadTableDaily() {
        // ObservableList<DailyHomePageDto> DsList = FXCollections.observableArrayList();

        try{
            ObservableList<DailyHomePageDto> DsList = FXCollections.observableArrayList();
            List<DailyHomePageDto> DsDto = dailyHomePageBO.LoadTable();
            count = dailyHomePageBO.getCount();
            lblCount.setText(" "+count+".00");
            DsList.addAll(DsDto);
            tblDalily.setItems(DsList);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }



    }


    public void btnEmpAdd(ActionEvent event) {
        String id = txtEmpId.getText();
        String date = txtDate.getText();

        if(id == null || id.isEmpty() || date == null || date.isEmpty()){
            JOptionPane.showMessageDialog(null,"","",JOptionPane.ERROR_MESSAGE);
        }

        EmpWordDetailDto empdto = new EmpWordDetailDto(id,date);

        try {
            String rsp = empWorkDetailsBO.addEmpDetails(empdto);//me code ek hadann oni
            showAlert(Alert.AlertType.INFORMATION, "Save Status", "Data saved successfully: " + rsp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erroe occurride" + e.getMessage());
        }

        clearEmpDetails();
    }
    private void clearEmpDetails() {

        txtEmpId.setText("");

    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void btnclickADD(ActionEvent event) {
        String id = CustomerId.getText();
        String date = Date.getText();
        String  rate = GoldLeaf.getText();
        String rate1 =GoodLeafAmount.getText();


        int rat ;
        int rat1;
        try {
            rat = Integer.parseInt(rate);
            rat1 = Integer.parseInt(rate1);

        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,"Gold Leaf OR Good Leaf not integerNumber!!","Validaton Error",JOptionPane.ERROR_MESSAGE);
            return;
        }


        DailyHomePageDto dto = new DailyHomePageDto(id,date, rat, rat1);

        try {
            String response = dailyHomePageBO.btnSaveDailyCount(dto);
            loadTableDaily();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NoT Save Details Please Check And Try Again!!", "Validaton Error", JOptionPane.ERROR_MESSAGE);

        }


        clearDailyDetails();

    }


    public void OnActionGoodLeaf(ActionEvent event) {
        btnclickADD(event);
    }

    public void CustomerIdOnAction(ActionEvent event) {
        String id = CustomerId.getText();
        try {
            String nm = dailyHomePageBO.getNam(id);
            if(nm.equals(null) || nm.isEmpty()){
                CustomerId.setStyle(CustomerId.getStyle()+";-fx-border-color: red;");
            }
            else {
                CustomerName.setText("" + nm);
                CustomerId.setStyle(";-fx-border-color: null;");
                GoldLeaf.requestFocus();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Not Suport Customer id!","Validation Error",JOptionPane.ERROR_MESSAGE);
        }

    }

    public void GoldOnAction(ActionEvent event) {
        GoodLeafAmount.requestFocus();
    }

    public void btnclickUPDATE(ActionEvent event) {
        String id = CustomerId.getText();
        String date = Date.getText();


        if (id == null || id.isEmpty() || date == null || date.isEmpty()) {
            JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
            return;
        }


        DailyHomePageDto dto = new DailyHomePageDto(id, date);

        try {
            String response = dailyHomePageBO.btnUpdateDailyCount(dto);
            showAlert(Alert.AlertType.INFORMATION, "Save Status", "Data saved successfully: " + response);
            loadTableDaily();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NoT Save Details Please Check And Try Again!!", "Validaton Error", JOptionPane.ERROR_MESSAGE);

        }
        clearDailyDetails();
    }

    private void clearDailyDetails() {
       // CustomerId.setText("");
       // CustomerName.setText("");
        GoldLeaf.setText("");
        GoodLeafAmount.setText("");
    }

    public void btnBack(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.setResizable(true);
    }


    public void btnRefersh(ActionEvent event) throws IOException {
        DailyBtn(event);
    }

    public void DateOnAction(ActionEvent actionEvent) {
        GoldLeaf.requestFocus();
    }
}
