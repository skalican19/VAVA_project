package controller;

import controller.flowcontrol.AlertBox;
import controller.flowcontrol.IChangeScene;
import controller.flowcontrol.INewWindowScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WelcomeScreenController implements Initializable, IChangeScene, INewWindowScene {

    @FXML Pane paneView;
    @FXML Label date;
    @FXML TextField city;

    public static LocalDate displayDate;

    private int pane_no;

    @FXML
    public void changePane(){
        if (pane_no == 1) {
            setPane("/view/weather_pane.fxml", 2);
        }
        else if (pane_no == 2) {
            setPane("/view/showday.fxml", 1);
        }
    }

    public void setPane(String fxml, int pane) {
        paneView.getChildren().clear();
        try {
            paneView.getChildren().add(FXMLLoader.load(getClass().getResource(fxml)));
            pane_no = pane;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeLocation(){
        Main.city = city.getText();
        setPane("/view/weather_pane.fxml", 2);
    }

    @FXML
    public void addDay(){
        displayDate = displayDate.plusDays(1);
        refresh();
    }

    @FXML
    public void subDay(){
        displayDate = displayDate.minusDays(1);
        refresh();
    }

    public void refresh() {
        if(pane_no == 1){
            setPane("/view/showday.fxml", 1);
        }
        else if(pane_no == 2) {
            setPane("/view/weather_pane.fxml", 2);
        }
        date.setText(displayDate.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayDate = LocalDate.now();
        pane_no = 1;
        refresh();
    }

    public void btnAddActivityOnAction(){
        createScene("createactivity");
    }

    public void btnModifyDayOnAction(){

    }

    public void btnShowCalendarOnAction(){
        sceneChanger("full_calendar");
    }
}
