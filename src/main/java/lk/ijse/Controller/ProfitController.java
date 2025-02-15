package lk.ijse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.MonthlyRateBO;
import lk.ijse.bo.custom.impl.MonthlyRateBoimpl;
import lk.ijse.dto.MonthlyRateDto;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class ProfitController {

    private String[] dateArray = {"JANUARY","FEBRUARY","MARCH","APRILL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOMBER","NOVEMBER","DESEMBER"};


    @FXML
    private Label LBLCheack;
    @FXML
    private ComboBox<String> cmMonth;

    @FXML
    private ComboBox<String> cmyear;

    @FXML
    private Label Total1;
    @FXML
    private Label lblTotal1;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private Label l4;

    @FXML
    private Label l5;

    @FXML
    private Label l6;

    @FXML
    private Label l7;

    @FXML
    private Label l8;

    @FXML
    private Label l9;

    @FXML
    private Label l10;

    @FXML
    private TextField txthoda;

    @FXML
    private TextField txtran;

    @FXML
    private TextField txthDalu;

    @FXML
    private TextField txtRDalu;

    @FXML
    private Label lblHtotal;

    @FXML
    private Label lblRtotal;

    @FXML
    private Label l11;

    @FXML
    private Label lblH;

    @FXML
    private Label lblR;

    @FXML
    private TextField txtOther;

    @FXML
    private Label lblNetPrice;


    MonthlyRateBO monthlyRateBO = new MonthlyRateBoimpl();

    public void initialize(){

        ObservableList<String> moth = FXCollections.observableArrayList();
        ObservableList<String> year = FXCollections.observableArrayList();
        String[] ar = {"2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040","2041","2042","2043","2044","2045","2046","2047","2048","2049","2050"};
        moth.addAll(dateArray);
        cmMonth.setItems(moth);
        year.addAll(ar);
        cmyear.setItems(year);

    }

    @FXML
    void OkOnAction(ActionEvent event) {

    }

    public void OkOnAction(javafx.event.ActionEvent event) {
        String yy = cmyear.getValue();
        String MM = cmMonth.getValue();
        String date = yy+MM;
        String month = MM+yy;
        String endDate = "";
        for (int i = 0; i < dateArray.length; i++) {
            if (MM.equals(dateArray[i])){
                date = yy+"-"+(i+1);
            }
        }
        SetRate(date,month);

    }

    private void setDatilas(String date, String month) {
        double hodaRate = Double.parseDouble(lblH.getText());
        double ranRate = Double.parseDouble(lblR.getText());
        try {
            List<Integer> ss = monthlyRateBO.getallMonthlyLeafCount(date);
            int Ran = ss.get(0);
            int Hod = ss.get(1);
            l3.setText(""+Ran);
            l1.setText(""+Hod);
            l2.setText(""+(Hod*hodaRate));
            l4.setText(""+(Ran*ranRate));
            double tt = (Hod*hodaRate+Ran*ranRate);
            Total1.setText(""+tt);
            double get = monthlyRateBO.getPurchase(date);
            l7.setText(""+get);
            Double ad = monthlyRateBO.getAdvance(month);
            l5.setText(""+ad);
            Double FF = monthlyRateBO.getPohora(month);
            l6.setText(""+FF);
            tt = get+ad+FF;
            lblTotal1.setText(""+tt);
            double[] ar = new double[2];
            ar = monthlyRateBO. getAllCustomerHiga(month,hodaRate,ranRate);
            l8.setText(""+ar[0]);
            tt = ar[1];
            tt = tt*1;
            l9.setText(""+tt);
            ad = monthlyRateBO.get100tganan(month);
            l10.setText(""+ad);
            ad = ad*hodaRate;
            l11.setText(""+ad);

        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {

        }

    }

    private void SetRate(String date, String month) {
        String yy = cmyear.getValue();
        String MM = cmMonth.getValue();
        String date1 = yy+MM;
        try {
            List<MonthlyRateDto> ss =  monthlyRateBO.getLeafPrice(date1);

            if (ss.isEmpty() || ss ==null){

            }else {

                MonthlyRateDto data = ss.get(0);
                lblH.setText(String.valueOf(data.getRate()));
                lblR.setText(String.valueOf(data.getRate1()));
                setDatilas(date,month);
            }
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {

        }
    }

    public void OnActionbtn1(javafx.event.ActionEvent event) {
        LBLCheack.setText("");
        Double hod = 0.0;
        Double ran = 0.0;
        int hodDalu = 0;
        int ranDalu = 0;

        try {
            hod = Double.parseDouble(txthoda.getText());
            ran = Double.parseDouble(txtran.getText());
            hodDalu = Integer.parseInt(txthDalu.getText());
            ranDalu = Integer.parseInt(txtRDalu.getText());
        } catch (NumberFormatException e) {
            LBLCheack.setText("Please Chack Details ");
        }

        Double hh = hod*hodDalu;
        Double rr = ran*ranDalu;
        lblHtotal.setText(""+hh);
        lblRtotal.setText(""+rr);

    }
}
