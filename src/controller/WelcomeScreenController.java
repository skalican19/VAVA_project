package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeScreenController implements Initializable {
    @FXML Pane paneView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            paneView.getChildren().add(FXMLLoader.load(getClass().getResource("/view/showday.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
