package controller.flowcontrol;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Main;

import java.io.IOException;

public interface INewWindowScene {

    default void createScene(String view) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/" + view + ".fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

}
