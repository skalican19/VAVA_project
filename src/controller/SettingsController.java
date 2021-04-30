package controller;

import controller.creates.CreateActivityController;
import controller.flowcontrol.AlertBox;
import controller.flowcontrol.IChangeScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Main;
import model.Translations;
import model.days.Activity;
import model.user.Settings;
import model.weather.WeatherWeek;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/***
 * author: Michal
 */

public class SettingsController implements Initializable, IChangeScene {

    @FXML private TableView<Activity> tableActivities;
    @FXML private TableColumn<Activity, String> columnName;
    @FXML private TableColumn<Activity, String> columnDesc;
    @FXML private TableColumn<Activity, String> columnType;
    @FXML private TableColumn<Activity, String> columnPriority;
    @FXML private TableColumn<Activity, LocalDate> columnLast;
    @FXML Button btnSvk;
    @FXML Button btnEng;
    @FXML TextField tfCity;
    private final ArrayList<Activity> activities = Main.user.getActivities();
    private final ObservableList<Activity> obsList = FXCollections.observableArrayList(activities);
    private Locale locale = null;

    public void btnSvkOnAction(){
        locale = new Locale("sk", "SK");
    }

    public void btnEngOnAction(){
        locale = new Locale("en", "US");
    }

    public void btnSaveOnAction(){
        Settings settings = Main.user.getSettings();
        if (locale != null) {
            Main.user.setLocale(locale);
        }
        if (validate() && !tfCity.getText().equals("")) {
            settings.setDefaultCity(tfCity.getText());
        }
        else {
            AlertBox.show(Translations.TranslateAlertBox("alert_unknown_location"), "Warning");
        }
        sceneChanger("settings");
    }

    public void btnCloseOnAction(){
        sceneChanger("welcomescreen");
    }

    public void btnNewOnAction(){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/createactivity.fxml"),
                ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale()));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
            CreateActivityController c = loader.getController();
            c.setTableActivities(tableActivities);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.getIcons().add(new Image("/Images/Logo.png"));
        stage.setScene(scene);
        stage.sizeToScene();
        stage.showAndWait();
    }

    public void btnDeleteOnAction(){
        Activity a = tableActivities.getSelectionModel().getSelectedItem();
        tableActivities.getItems().remove(a);
        new ActivityTableMethods().removeActivity(a);
    }

    public boolean validate() {
        WeatherWeek week = new WeatherWeek();
        return week.parseWeatherXml(tfCity.getText());
    }

    public void refresh() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        columnLast.setCellValueFactory(new PropertyValueFactory<>("lastDone"));

        tableActivities.getItems().setAll(obsList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfCity.setText(Main.user.getSettings().getDefaultCity());
        double r=30;

        btnSvk.setShape(new Circle(r));
        btnSvk.setMinSize(2*r, 2*r);
        btnSvk.setMaxSize(2*r, 2*r);

        btnEng.setShape(new Circle(r));
        btnEng.setMinSize(2*r, 2*r);
        btnEng.setMaxSize(2*r, 2*r);

        refresh();
    }

}
