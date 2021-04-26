package controller;

import controller.flowcontrol.AlertBox;
import controller.flowcontrol.INewWindowScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.days.Activity;
import model.days.Hobby;
import model.days.Task;
import model.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;

/***
 * Author Dušan
 */
public class ChooseActivityController implements INewWindowScene, Initializable {

    @FXML private TableView<Activity> tableActivities;
    @FXML private TableColumn<Activity, String> columnName;
    @FXML private TableColumn<Activity, String> columnDesc;
    @FXML private TableColumn<Activity, String> columnType;
    @FXML private TableColumn<Activity, String> columnPriority;
    @FXML private TableColumn<Activity, LocalDate> columnLast;
    @FXML private ComboBox<Hobby> cbActivities;
    @FXML private ComboBox<Task> cbTasks;
    private final ArrayList<Activity> activities = Main.user.getActivities();
    private final ObservableList<Activity> obsList = FXCollections.observableArrayList(activities);
    private TableView<Activity> tableOfDay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initComboboxes();
        fillTable();
    }

    public void btnNewOnAction(){
        createScene("createactivity");
    }

    public void btnShowOnAction(){
        Activity a = tableActivities.getSelectionModel().getSelectedItem();
        if (a == null){
            AlertBox.show("Zvoľte prosím aktivitu", "warning");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/showactivity.fxml"));
        try {
            Main.primaryStage.setScene(new Scene(loader.load()));
            ShowActivityController c = loader.getController();
            c.setCurrent(a);
            Main.primaryStage.show();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Súbor s danou cestou sa mepodarilo načítať.");
        }
    }

    public void btnDeleteOnAction(){
        Activity a = tableActivities.getSelectionModel().getSelectedItem();
        if (a == null){
            AlertBox.show("Zvoľte prosím aktivitu", "warning");
            return;
        }
    }

    public void btnChooseOnAction(){
        Activity a = tableActivities.getSelectionModel().getSelectedItem();
        Activity selected  = tableOfDay.getSelectionModel().getSelectedItem();
        selected.setStart(a.getStart());
        selected.setEnd(a.getEnd());
        tableOfDay.refresh();
    }

    private void initTable(){
        tableActivities.getItems().clear();
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        columnLast.setCellValueFactory(new PropertyValueFactory<>("lastDone"));
    }

    private void initComboboxes(){
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Hobby> hobbies = new ArrayList<>();

        for(Activity a: activities){
            if (a instanceof Task) {
                tasks.add((Task) a);
                break;
            }
            if (a instanceof Hobby){
                hobbies.add((Hobby) a);
                break;
            }
        }

        obsList.sort(Comparator.comparing(Activity::getLastDone));
        cbActivities.getItems().addAll(hobbies);
        cbTasks.getItems().addAll(tasks);
        // TODO make this choose activities based on priorities and weather and shit
    }

    private void fillTable(){
        tableActivities.getItems().addAll(obsList);
    }

    public void setTableOfDay(TableView<Activity> tableOfDay) {
        this.tableOfDay = tableOfDay;
    }
}
