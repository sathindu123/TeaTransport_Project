package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    private AnchorPane anchorPane;

    private LoginPAgeDAOimpl lg;
    public TextField txtUserName;
    public PasswordField txtPassword;


    public void initialize(){
        lg = new LoginPAgeDAOimpl();
    }

    public void ligin(ActionEvent event) throws IOException{
        String un = txtUserName.getText();
        String up = txtPassword.getText();
        try {
            String userName = lg.getUderName();
            String password = lg.getPAssword();

           // Boolean ssss = lg.cheack(un,up);
            if(userName.equals(un) && password.equals(up)){
               // System.out.println("login");
                anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Dashboard Form");
                stage.setResizable(true);
            }else {
                JOptionPane.showMessageDialog(null,"Incorect USerName OR Password","Validation Eroor",JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

       // System.out.println("Login successfully");

    }

    public void loginButttonClick(ActionEvent event) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
        stage.setResizable(true);

    }

    public void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent event) throws IOException {
        ligin(event);
    }

    public void MouseClickForeign(MouseEvent mouseEvent) throws IOException {
        anchorPane = FXMLLoader.load(getClass().getResource("/view/Forign.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
        stage.setResizable(true);
    }
}
