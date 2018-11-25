package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.utils.FXRouter;
import sample.utils.UserSessionManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField email;
    @FXML private PasswordField password;
    @FXML private Button loginBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                boolean isConnected = false;
                try {
                    isConnected = UserSessionManager.getInstance().connection(email.getText(), password.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(isConnected) {
                    try {
                        FXRouter.goTo("skeleton");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Refuser");
                }
            }
        });

    }
}
