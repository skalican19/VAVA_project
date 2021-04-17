package controller.flowcontrol;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Main;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface INewWindowScene {
    Logger LOG = Logger.getLogger(IChangeScene.class.getName());

    default void createScene(String view){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/" + view + ".fxml"));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Súbor s danou cestou neexistuje.");
        }
        stage.setScene(scene);
        stage.show();
    }

}
