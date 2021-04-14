package controller;

import controller.flowcontrol.IChangeScene;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.user.User;

import java.io.IOException;

public class MainSceneController implements IChangeScene {
    @FXML TextField nameRegistration;
    @FXML TextField emailRegistration;
    @FXML PasswordField passwordRegistration;


    public void btnCloseOnAction() throws IOException {
        sceneChanger("welcomescreen");
    }

    @FXML
    public void register() {
        System.out.println(User.validate(emailRegistration.getText()));
    }

}
