package controller.flowcontrol;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import models.Main;

import java.io.IOException;

public interface IChangeScene {

    default void sceneChanger(String view) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/" + view + ".fxml"));
        Scene scene = new Scene(loader.load());
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
}
