package controller.flowcontrol;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.Main;
import java.io.IOException;
import java.util.ResourceBundle;


/***
 * Author Dušan
 */

public interface IChangeScene {

    default void sceneChanger(String view){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + view + ".fxml"),
                ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale()));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
}


