package controller.creates;

import controller.databases.DatabaseManager;
import controller.flowcontrol.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.days.Activity;
import model.days.Priority;
import model.days.Hobby;
import model.days.Task;
import model.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/***
 * Author Dušan
 */
public class CreateActivityController implements Initializable{

    @FXML ComboBox<String> cbType;
    @FXML TextField tfName;
    @FXML TextField tfDesc;
    @FXML CheckBox choiceOutdoor;
    @FXML Label lblPriority;
    @FXML Label lblDue;
    @FXML DatePicker dpDueDate;
    @FXML ComboBox<Priority> cbPriority;
    private TableView<Activity> tableActivities = null;
    private String type;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboboxes();
    }

    public void cbTypeOnAction(){
        String selected = cbType.getValue();
        if (selected == null) return;
        type = selected;
        if(selected.equals("Úloha")){
            choiceOutdoor.setVisible(false);
            lblPriority.setVisible(true);
            lblDue.setVisible(true);
            dpDueDate.setVisible(true);
            cbPriority.setVisible(true);
        }
        else{
            choiceOutdoor.setVisible(true);
            lblPriority.setVisible(false);
            lblDue.setVisible(false);
            dpDueDate.setVisible(false);
            cbPriority.setVisible(false);
        }

    }

    public void btnSaveOnAction() throws IOException {
        Activity a;
        if(!validate()) return;
        if (type.equals("Úloha")){
            a = new Task(tfName.getText(),cbPriority.getValue(),tfDesc.getText(), dpDueDate.getValue());
        }
        else{
            a = new Hobby(tfName.getText(),cbPriority.getValue(),tfDesc.getText(), choiceOutdoor.isSelected());
        }
        clearAll();
        Main.user.addActivity(a);
        if (tableActivities != null){
            tableActivities.getItems().add(a);
        }
        DatabaseManager.getInstance().saveUsers();
    }

    private void initComboboxes(){
        ObservableList<String> types = FXCollections.observableArrayList("Úloha", "Záľuba");
        cbType.getItems().setAll(types);
        ObservableList<Priority> priorities = FXCollections.observableArrayList(Priority.values());
        cbPriority.getItems().setAll(priorities);
    }

    private boolean validate(){
        if (cbType.getValue() == null){
            AlertBox.show("Zvoľte prosím typ aktivity.", "warning");
            return false;
        }
        if (tfName.getText().isEmpty() || tfDesc.getText().isEmpty()){
            AlertBox.show("Vyplňte prosím polia názov a popis.", "warning");
            return false;
        }
        if (type.equals("Úloha")) {
            if (cbPriority.getValue() == null) {
                AlertBox.show("Zvoľte prosím prioritu danej úlohy.", "warning");
                return false;
            }
            if (dpDueDate.getValue() == null || dpDueDate.getValue().isBefore(LocalDate.now())) {
                AlertBox.show("Zvoľte prosím platný dátum.", "warning");
                return false;
            }
        }
        return true;
    }

    private void clearAll(){
        cbType.setValue(null);
        tfName.setText(null);
        tfDesc.setText(null);
        choiceOutdoor.setVisible(false);
        choiceOutdoor.setSelected(false);
        lblPriority.setVisible(false);
        lblDue.setVisible(false);
        dpDueDate.setVisible(false);
        dpDueDate.setValue(null);
        cbPriority.setVisible(false);
        cbPriority.setValue(null);
    }

    public void setTableActivities(TableView<Activity> tableActivities) {
        this.tableActivities = tableActivities;
    }
}
