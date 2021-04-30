package controller;

import controller.flowcontrol.AlertBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Main;
import model.Translations;
import model.days.Activity;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivityTableMethods {
    private static final Logger LOG = Logger.getLogger(ActivityTableMethods.class.getName());

    public void showActivity(Activity a, Boolean backBtnVisible){
        if (a == null){
            AlertBox.show(Translations.TranslateAlertBox("alert_choose_activity"), "Warning");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/showactivity.fxml"),
                ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale()));
        if (backBtnVisible) {
            try {
                Main.primaryStage.setScene(new Scene(loader.load()));
                ShowActivityController c = loader.getController();
                Main.primaryStage.getIcons().add(new Image("/Images/Logo.png"));
                c.setCurrent(a, true);
                Main.primaryStage.show();
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Unable to load file.");
            }
        }
        else {
            try {
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                ShowActivityController c = loader.getController();
                stage.getIcons().add(new Image("/Images/Logo.png"));
                c.setCurrent(a, false);
                stage.show();
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Unable to load file.");
            }
        }
    }

    public void removeActivity(Activity a){
        if (a == null){
            AlertBox.show(Translations.TranslateAlertBox("alert_choose_activity"), "Warning");
            return;
        }
        Main.user.removeActivity(a);
    }
}
