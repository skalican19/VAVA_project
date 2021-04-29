package controller;

import controller.databases.DatabaseManager;
import controller.flowcontrol.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.days.Activity;
import model.days.Day;
import model.Main;
import model.days.PerformedActivity;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 * Author Dušan
 */
public class ShowDayController implements Initializable {

    private Day day = Main.user.getDay(WelcomeScreenController.displayDate);
    @FXML private TableView<PerformedActivity> tableActivities;
    @FXML private TableColumn<PerformedActivity, LocalTime> columnStart;
    @FXML private TableColumn<PerformedActivity, LocalTime> columnEnd;
    @FXML private TableColumn<PerformedActivity, LocalTime> columnActivity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTable();
        populateTable();
    }

    public void btnAddOnAction(){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/chooseactivity.fxml"),
                ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale()));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
            ChooseActivityController c = loader.getController();
            c.setTableOfDay(tableActivities);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/Images/Logo.png"));
        stage.sizeToScene();
        stage.setOnHiding( event -> {
            try {
                DatabaseManager.getInstance().saveUsers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );
        stage.show();
    }


    private void setTable(){
        tableActivities.getItems().clear();
        columnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        columnEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        columnActivity.setCellValueFactory(new PropertyValueFactory<>("activity"));
    }

    private void populateTable(){
        if (day == null) day = new Day(LocalDate.now());
        ArrayList<PerformedActivity> activities = day.getActivities();
        ObservableList<PerformedActivity> obsList = FXCollections.observableArrayList(activities);
        tableActivities.getItems().setAll(obsList);
    }

    public void setDay(Day day) {
        this.day = day;
        setTable();
        populateTable();
    }
}
