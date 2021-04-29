package controller;
import controller.databases.DatabaseManager;
import controller.flowcontrol.IChangeScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import model.Main;
import model.Translations;
import model.user.User;
import controller.flowcontrol.AlertBox;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainSceneController implements IChangeScene, Initializable {
    @FXML TextField nameRegistration;
    @FXML TextField emailRegistration;
    @FXML PasswordField passwordRegistration;
    @FXML TextField nameLogin;
    @FXML PasswordField passwordLogin;
    @FXML Button btnSvk;
    @FXML Button btnEng;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        double r=28;
        btnSvk.setShape(new Circle(r));
        btnSvk.setMinSize(2*r, 2*r);
        btnSvk.setMaxSize(2*r, 2*r);
        btnEng.setShape(new Circle(r));
        btnEng.setMinSize(2*r, 2*r);
        btnEng.setMaxSize(2*r, 2*r);
    }

    public void btnEngOnAction(){
        Main.user.setLocale(new Locale("en", "US"));
        sceneChanger("welcomescreen");
    }

    public void btnSvkOnAction(){
        Main.user.setLocale(new Locale("sk", "SK"));
        sceneChanger("welcomescreen");
    }

    public void btnCloseOnAction(){
        Main.primaryStage.close();
    }

    @FXML
    public void login(){
        try {
            DatabaseManager manager = DatabaseManager.getInstance();
            User user = manager.getUser(nameLogin.getText());

            if(user != null && user.verifyLogin(passwordLogin.getText())){
                Main.user = user;
                sceneChanger("welcomescreen");
            }

            else {
                AlertBox.show(Translations.TranslateAlertBox("alert_invalid_credentials"), "Warning");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void register() {
        User user = new User();
        if (user.registerUser(nameRegistration.getText(), emailRegistration.getText(), passwordRegistration.getText())) {
            AlertBox.show(Translations.TranslateAlertBox("alert_successful_registration"), "Success");
            clearAll();
        }
        else {
            AlertBox.show(Translations.TranslateAlertBox("alert_invalid_e-mail"), "Warning");
        }
    }

    private void clearAll(){
        nameRegistration.clear();
        emailRegistration.clear();
        passwordRegistration.clear();
    }
}
