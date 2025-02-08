package lk.ijse.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.EventObject;

public class BtnTeaOrderController  extends DashboardFormController{
    private AnchorPane anchorPane;



    public void PohoraOrderButtonClick(javafx.event.ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/OrderManage.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Pohora Order Management ");

    }
}
