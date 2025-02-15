

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Dao.DAOFactory;
import lk.ijse.Dao.custom.CustomerManageDAO;
import lk.ijse.Dao.custom.InvoiceCustomerDAO;
import lk.ijse.Dao.custom.impl.DailyHomePageDAOimpl;
import lk.ijse.Dao.custom.impl.InvoiceCustomerDAOimpl;
import lk.ijse.bo.custom.*;
import lk.ijse.bo.custom.impl.*;
import lk.ijse.dto.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardFormController extends LoginFormController {

    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};

    EmpWorkDetailsBO empWorkDetailsBO = new EmpWorkDetailBoimpl();
    DailyHomePageBO dailyHomePageBO = new DailyhomepageBoimpl();
    MonthlyRateBO monthlyRateBO = new MonthlyRateBoimpl();
    AdvanceBo advanceBo = new AdvanceBoimpl();
    InvoiceCustomerBO invoiceCustomerBO = new InvoiceCustomerBoimpl();

    @FXML
    private Label TotPurchase1;
    private int count =0;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label lblTotalAdvance;

    @FXML
    private Label lblTeaLeafCount;


    @FXML
    private Label TxtDateTime;

    @FXML
    private Label lblOtherTotal;

    @FXML
    private Label lblPohora;

    @FXML
    private Label TotPurchase;

    @FXML
    private Button LogOutButton;

    public DashboardFormController(){

    }


    @FXML
    public void initialize() {
      //  LogOutButton.setOnAction(null);
        //LogOutButton.setDisable(true);
        setDateAndTimeToday();
        try {
            loadDeatils();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

    }

    private void setDateAndTimeToday() {
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
    }

    public void loadDeatils() throws SQLException, ClassNotFoundException {
        double advance = advanceBo.getTotalAdvance();
        String[] ar = getMonthToday();
        int teleafcount = dailyHomePageBO.getTeaLeafCoutn(ar);
        double pohora = dailyHomePageBO.getPohoraTotal(ar);
        double others = dailyHomePageBO.getOtherTotal();


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
        //ObservableList<InvoiceCustomerDto> nrts = FXCollections.observableArrayList();

        String passDate = formattedDate1 + dateMonth;
        double tt = 0;
        double lblGoodLea = 0;
        double lblGoldLeaf = 0;
        try {
            List<MonthlyRateDto> dto = monthlyRateBO.getLeafPrice(passDate);
            nrt.addAll(dto);

            if (!dto.isEmpty()) {
                MonthlyRateDto data = dto.get(0); // Get the first entry
                 lblGoodLea = data.getRate();
                 lblGoldLeaf = data.getRate1();
                List<InvoiceCustomerDto> dfg = invoiceCustomerBO.getAllTeaLeafCount();
                if(dfg.size() > 0){
                    InvoiceCustomerDto dd = dfg.get(0);
                        int  s = dd.getPrice();
                        int k = dd.getPrice1();

                        tt = (s*lblGoldLeaf)+(k*lblGoodLea);
                }

            } else {
                //System.out.println("No data found for customer purchase details.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            lblTotalAdvance.setText(lblTotalAdvance.getText()+" "+advance);
            lblTeaLeafCount.setText(""+teleafcount);
            lblPohora.setText(lblPohora.getText()+" "+pohora);
            lblOtherTotal.setText(lblOtherTotal.getText()+" "+others);

            double mm = (tt-(advance+pohora+others));

        } catch (Exception e) {
            //System.out.println(e);
        }
        String passDate1 = dateMonth+formattedDate1;
        System.out.println(passDate1);
        double[] ganna = new double[2];
//        try {
//
//            ganna = monthlyRateBO.getAllCustomerHiga(passDate1,lblGoodLea,lblGoldLeaf);
//
//            TotPurchase.setText(TotPurchase.getText()+" "+ganna[0]);
//            TotPurchase1.setText(TotPurchase1.getText()+" "+ganna[1]);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } catch (ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//        }


    }

    public String[] getMonthToday() {
        String[] ar = new String[2];
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() >= 1 && now.getDayOfMonth() <= 10) {
            now = now.minusMonths(1);
            if (now.getDayOfMonth() == 1) {
                now = now.minusYears(1);
            }
        }


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = now.format(dateFormatter);

        String startDate = formattedDate + "-01";


        String endDate = now.withDayOfMonth(now.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ar[0] = startDate;
        ar[1] = endDate;

        return ar;
    }

    public void LogOutButtonClick(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    public void ManageCetegoryBTNClick(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/ManagemeCetegory.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Customer Management Page");
    }


    public void StockManageBTNClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StockManagement.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Stock Management");
    }

    public void InvoiceManageBTNClick(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/InvoiceManagement.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.setTitle("Invoice Management");
    }


    public void ViewManageBtnClick(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/ViewManagement.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("View Management");
    }


    public void DailyBtn(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/Daily.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("View Management");
    }
}
