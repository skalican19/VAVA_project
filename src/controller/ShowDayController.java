package controller;

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
import javafx.stage.Stage;
import model.days.Activity;
import model.days.Day;
import model.Main;

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

    public void btnAddOnAction(){
        Activity selected = tableActivities.getSelectionModel().getSelectedItem();
        if (selected == null){
            AlertBox.show("Zvoľte prosím aktivitu", "warning");
            return;
        }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/chooseactivity.fxml"));
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
        stage.show();
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
