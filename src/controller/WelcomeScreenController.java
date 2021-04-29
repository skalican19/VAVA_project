package controller;
import controller.flowcontrol.IChangeScene;
import controller.flowcontrol.INewWindowScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.Main;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class WelcomeScreenController implements Initializable, IChangeScene, INewWindowScene {

    @FXML
    Pane paneView;
    @FXML
    Label date;
    @FXML
    Label lblWelcome;
    @FXML
    TextField city;


    public static LocalDate displayDate;

    private int pane_no;

    @FXML
    public void changePane() {
        if (pane_no == 1) {
            setPane("/view/weather_pane.fxml", 2);
        } else if (pane_no == 2) {
            setPane("/view/showday.fxml", 1);
        }
    }

    public void setPane(String fxml, int pane) {
        paneView.getChildren().clear();
        try {
            paneView.getChildren().add(FXMLLoader.load(getClass().getResource(fxml),
                    ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale())));
            pane_no = pane;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeLocation() {
        Main.user.getSettings().setShownCity(city.getText());
        setPane("/view/weather_pane.fxml", 2);
    }

    @FXML
    public void addDay() {
        displayDate = displayDate.plusDays(1);
        refresh();
    }

    @FXML
    public void subDay() {
        displayDate = displayDate.minusDays(1);
        refresh();
    }

    public void refresh() {
        if (pane_no == 1) {
            setPane("/view/showday.fxml", 1);
        } else if (pane_no == 2) {
            setPane("/view/weather_pane.fxml", 2);
        }
        date.setText(displayDate.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayDate = LocalDate.now();
        pane_no = 1;
        city.setText(Main.user.getSettings().getShownCity());
        lblWelcome.setText(lblWelcome.getText() + " " + Main.user.getUserName());
        refresh();
    }

    public void btnAddActivityOnAction() {
        createScene("createactivity");
    }

    public void btnSummarizeDayOnAction() {
        sceneChanger("daysummary");
    }


    public void btnSettingsOnAction(){
        sceneChanger("settings");


    }

    public void btnShowCalendarOnAction() {
        sceneChanger("full_calendar");
    }
}
