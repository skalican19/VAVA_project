package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Days.Activity;
import model.Days.Day;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 * Author Dušan
 */
public class ShowDayController implements Initializable {

    public static Day day;
    @FXML
    private TableView<Activity> tableActivities;
    @FXML
    private TableColumn<Activity, LocalTime> columnStart;
    @FXML
    private TableColumn<Activity, LocalTime> columnEnd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTable();
        populateTable();
    }

    private void setTable(){
        tableActivities.getItems().clear();
        columnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        columnEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
    }

    private void populateTable(){
        if (ShowDayController.day == null) day = new Day(LocalDate.now());
        ArrayList<Activity> activities = day.getActivities();
        ObservableList<Activity> obsList = FXCollections.observableArrayList(activities);
        tableActivities.getItems().setAll(obsList);
    }
}
