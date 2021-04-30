package controller;

import controller.creates.CreateActivityController;
import controller.flowcontrol.AlertBox;
import controller.flowcontrol.INewWindowScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Translations;
import model.days.*;
import model.Main;
import model.user.Settings;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    @FXML private ComboBox<Activity> cbActivities;
    @FXML private ComboBox<LocalTime> cbStart;
    @FXML private ComboBox<LocalTime> cbEnd;
    private final ArrayList<Activity> activities = Main.user.getActivities();
    private final ObservableList<Activity> obsList = FXCollections.observableArrayList(activities);
    private TableView<PerformedActivity> tableOfDay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initComboboxes();
        fillTable();
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
        initComboboxes();
    }

    public void btnShowOnAction(){
        Activity a = tableActivities.getSelectionModel().getSelectedItem();
        new ActivityTableMethods().showActivity(a, false);
    }

    public void btnDeleteOnAction(){
        Activity a = tableActivities.getSelectionModel().getSelectedItem();
        tableActivities.getItems().remove(a);
        new ActivityTableMethods().removeActivity(a);
    }

    /***
     * author: Michal
     */

    public void btnChooseOnAction(){
        if (!validate()) return;

        Activity activity = cbActivities.getValue();
        if (activity == null) {
            activity = tableActivities.getSelectionModel().getSelectedItem();
        }

        for(PerformedActivity a : tableOfDay.getItems()){
            if(a.getStart().isBefore(cbEnd.getValue()) &&
                    (a.getStart().isAfter(cbStart.getValue()) || a.getStart().equals(cbStart.getValue()))){
                a.setActivity(activity);
            }
        }
        tableOfDay.refresh();
        AlertBox.show(Translations.Translate("alert_activity_added"), "Success");
    }

    public void tableOnClick(){
        cbActivities.valueProperty().set(null);
    }

    public void boxOnClick(){
        tableActivities.getSelectionModel().clearSelection();
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
        Recommendation r = new Recommendation();
        r.makeRecommendations();
        ObservableList<Activity> recommendations = FXCollections.observableArrayList(r.getRecommendations());
        ObservableList<LocalTime> times = FXCollections.observableArrayList(Main.user.getSettings().getValidHours());

        clearComboboxes();
        cbActivities.getItems().addAll(recommendations);
        for(LocalTime hour: times){
            cbStart.getItems().add(hour);
            cbEnd.getItems().add(hour.plusHours(1));
        }
    }

    private void fillTable(){
        tableActivities.getItems().addAll(obsList);
    }

    public void setTableOfDay(TableView<PerformedActivity> tableOfDay) {
        this.tableOfDay = tableOfDay;
    }

    private boolean validate(){

        if(cbStart.getValue() == null || cbEnd.getValue() == null){
            AlertBox.show(Translations.TranslateAlertBox("alert_choose_time"),"Warning");
            return false;
        }
        if(cbEnd.getValue().isBefore(cbStart.getValue())){
            AlertBox.show(Translations.TranslateAlertBox("alert_choose_valid_time"),"Warning");
            return false;
        }
        if (tableActivities.getSelectionModel().getSelectedItem() == null){
            AlertBox.show(Translations.TranslateAlertBox("alert_choose_activity"),"Warning");
            return false;
        }
        return true;
    }

    private void clearComboboxes(){
        cbActivities.getItems().clear();
        cbStart.getItems().clear();
        cbEnd.getItems().clear();
    }

}
