package models;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage = new Stage();

    public static void main(String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        mainScene();
    }

    public static void mainScene() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/views/mainscene.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}