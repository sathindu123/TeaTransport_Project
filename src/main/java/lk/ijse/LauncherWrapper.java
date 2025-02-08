package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class LauncherWrapper extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        stage.setScene(new Scene(anchorPane));
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }
}
