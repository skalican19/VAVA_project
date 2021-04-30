package controller.flowcontrol;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Main;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * Author Dušan
 */

public interface INewWindowScene {
    Logger LOG = Logger.getLogger(INewWindowScene.class.getName());

    default void createScene(String view){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/" + view + ".fxml"),
                ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale()));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
           e.printStackTrace();
            LOG.log(Level.SEVERE, "Scene could not be loaded.");
        }
        stage.getIcons().add(new Image("/Images/Logo.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
