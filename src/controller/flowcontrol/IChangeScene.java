package controller.flowcontrol;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.Main;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface IChangeScene {
    Logger LOG = Logger.getLogger(IChangeScene.class.getName());

    default void sceneChanger(String view){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + view + ".fxml"),
                ResourceBundle.getBundle("MessagesBundle", Main.currentLocale));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Súbor s danou cestou neexistuje.");
        }
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
}


