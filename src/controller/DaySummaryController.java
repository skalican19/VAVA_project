package controller;
import controller.databases.DatabaseManager;
import controller.flowcontrol.IChangeScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ActivityTableMethos;
import model.Main;
import model.days.Activity;
import model.days.Day;
import model.days.PerformedActivity;
import model.days.Task;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DaySummaryController implements Initializable, IChangeScene {
    @FXML private TableView<Task> tableTasks;
    @FXML private TableColumn<Task, String> columnTask;
    @FXML private TableColumn<Task, Double> columnProgress;

    @FXML private TableView<PerformedActivity> tableActivities;
    @FXML private TableColumn<PerformedActivity, LocalTime> columnStart;
    @FXML private TableColumn<PerformedActivity, LocalTime> columnEnd;
    @FXML private TableColumn<PerformedActivity, LocalTime> columnActivity;

    @FXML private TextArea taComment;
    @FXML private TextField tfTasks;
    @FXML private TextField tfDate;
    @FXML private TextField tfHobby;
    @FXML private TextField tfFree;

    private Day day = Main.user.getDay(WelcomeScreenController.displayDate);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfDate.setText(day.getDate().toString());
        taComment.setText(day.getComment());
        setHours();
        setTables();
        populateTables();
    }


    private void setTables(){
        tableActivities.getItems().clear();

        columnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        columnEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        columnActivity.setCellValueFactory(new PropertyValueFactory<>("activity"));


        tableTasks.getItems().clear();
        columnTask.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnProgress.setCellValueFactory(new PropertyValueFactory<>(
                "progress"));
        columnProgress
                .setCellFactory(ProgressBarTableCell.forTableColumn());
    }

    private void populateTables(){
        ArrayList<PerformedActivity> activities = day.getActivities();
        ObservableList<PerformedActivity> obactivities = FXCollections.observableArrayList(activities);
        ObservableList<Task> obtasks = FXCollections.observableArrayList();
        Activity activity;

        for(PerformedActivity a : activities){
            activity = a.getActivity();
            if((activity instanceof Task) && (!obtasks.contains(activity))){
                obtasks.add(((Task) a.getActivity()));
            }
        }

        tableTasks.getItems().setAll(obtasks);
        tableActivities.getItems().setAll(obactivities);
    }

    private void setHours(){
        tfFree.setText(String.valueOf(day.getFreeHours()));
        tfHobby.setText(String.valueOf(day.getHobbyHours()));
        tfTasks.setText(String.valueOf(day.getTaskHours()));
    }

    public void btnShowActivityOnAction(){
        Activity a = tableActivities.getSelectionModel().getSelectedItem().getActivity();
        new ActivityTableMethos().showActivity(a);
    }

    public void btnDeleteActivityOnAction(){
        PerformedActivity a = tableActivities.getSelectionModel().getSelectedItem();
        day.getActivities().remove(a);
        tableActivities.getItems().remove(a);
    }

    public void btnSaveOnAction() throws IOException {
        DatabaseManager.getInstance().saveUsers();
    }

    public void btnBackOnAction(){
        sceneChanger("welcomescreen");
    }
}
