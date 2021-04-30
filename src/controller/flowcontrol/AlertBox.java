package controller.flowcontrol;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import model.Translations;

/***
 * author: Michal
 */

public class AlertBox {

    public static void show(String text, String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(Translations.TranslateAlertBox(title));
        window.setMinWidth(300);
        window.setMinHeight(150);
        window.setResizable(false);
        Image image = new Image("/Images/" + title + ".png");
        window.getIcons().add(image);

        HBox hBox = new HBox(35);

        Label label = new Label(text);
        label.setStyle("-fx-font-size: 20");

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        VBox vBox = new VBox(10);
        Button button = new Button("Ok");

        button.setOnAction(event -> {
            window.close();
        });

        vBox.getChildren().addAll(label, button);
        vBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(imageView, vBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20,20,20,20));
        Scene scene = new Scene(hBox);
        window.setScene(scene);
        window.showAndWait();
    }
}