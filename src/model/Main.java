package model;
import controller.databases.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.user.Settings;
import model.user.User;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    public static Stage primaryStage = new Stage();
    public static User user = new User();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainScene();
    }

    public static void mainScene() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/view/mainscene.fxml"),
                ResourceBundle.getBundle("MessagesBundle", user.getLocale()));
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.setOnHiding( event -> {
            try {
                DatabaseManager.getInstance().saveUsers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );
        primaryStage.show();
    }
}