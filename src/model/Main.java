package model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.user.Settings;
import model.user.User;

import java.util.Locale;

public class Main extends Application {
    public static Stage primaryStage = new Stage();
    public static Locale currentLocale;
    public static Settings settings = new Settings();
    public static User user = new User();
    public static String city = Settings.getInstance().getCity();



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        currentLocale = new Locale("sk", "SK");
        mainScene();
    }

    public static void mainScene() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/view/mainscene.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}