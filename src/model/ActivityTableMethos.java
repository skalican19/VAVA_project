package model;

import controller.ShowActivityController;
import controller.flowcontrol.AlertBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.days.Activity;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivityTableMethos {
    private static final Logger LOG = Logger.getLogger(ActivityTableMethos.class.getName());

    public void showActivity(Activity a){
        if (a == null){
            AlertBox.show(Translations.TranslateAlertBox("alert_choose_activity"), "warning");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/showactivity.fxml"),
                ResourceBundle.getBundle("MessagesBundle", Main.currentLocale));
        try {
            Main.primaryStage.setScene(new Scene(loader.load()));
            ShowActivityController c = loader.getController();
            c.setCurrent(a);
            Main.primaryStage.show();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Unable to load file.");
        }
    }

    public void removeActivity(Activity a){
        if (a == null){
            AlertBox.show(Translations.TranslateAlertBox("alert_choose_activity"), "warning");
            return;
        }
        Main.user.removeActivity(a);
    }
}
