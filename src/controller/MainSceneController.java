package controller;

import controller.databases.DatabaseManager;
import controller.flowcontrol.IChangeScene;
import controller.flowcontrol.IPopupMethod;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.user.User;
import controller.flowcontrol.AlertBox;

import java.io.IOException;

public class MainSceneController implements IChangeScene, IPopupMethod {
    @FXML TextField nameRegistration;
    @FXML TextField emailRegistration;
    @FXML PasswordField passwordRegistration;

    @FXML TextField nameLogin;
    @FXML PasswordField passwordLogin;


    public void btnCloseOnAction() throws IOException {
        sceneChanger("welcomescreen");
    }

    @FXML
    public void login(){
        try {
            DatabaseManager manager = DatabaseManager.getInstance();
            User user = manager.getUser(nameLogin.getText());

            if(user != null && user.verifyLogin(passwordLogin.getText())){
                sceneChanger("welcomescreen");
            }

            else {
                AlertBox.show("Zlé meno alebo heslo.", "Warning");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void register() {
        User user = new User();
        if (user.registerUser(nameRegistration.getText(), emailRegistration.getText(), passwordRegistration.getText())) {
            AlertBox.show("Boli ste úspešne zaregistrovaný.", "Success");
        }
        else {
            AlertBox.show("Neplatná emailová adresa.", "Warning");
        }
    }

}
