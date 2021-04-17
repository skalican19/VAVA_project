package controller.creates;

import controller.flowcontrol.IPopupMethod;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Days.Activity;
import model.Days.Priority;
import model.Days.Recreation;
import model.Days.Task;
import model.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateActivityController implements Initializable, IPopupMethod {

    @FXML ComboBox<String> cbType;
    @FXML TextField tfName;
    @FXML TextField tfDesc;
    @FXML CheckBox choiceOutdoor;
    @FXML Label lblPriority;
    @FXML Label lblDue;
    @FXML DatePicker dpDueDate;
    @FXML ComboBox<Priority> cbPriority;
    private String type;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboboxes();
    }

    public void cbTypeOnAction(){
        String selected = cbType.getValue();
        if (selected == null) return;
        if(selected.equals("Úloha")){
            type = selected;
            choiceOutdoor.setVisible(false);
            lblPriority.setVisible(true);
            lblDue.setVisible(true);
            dpDueDate.setVisible(true);
            cbPriority.setVisible(true);
        }
        else{
            type = selected;
            choiceOutdoor.setVisible(true);
            lblPriority.setVisible(false);
            lblDue.setVisible(false);
            dpDueDate.setVisible(false);
            cbPriority.setVisible(false);
        }

    }

    public void btnSaveOnAction(){
        Activity a;
        if(!validate()) return;
        if (type.equals("Úloha")){
            a = new Task(tfName.getText(),cbPriority.getValue(),tfDesc.getText(), dpDueDate.getValue());
        }
        else{
            a = new Recreation(tfName.getText(),cbPriority.getValue(),tfDesc.getText(), choiceOutdoor.isSelected());
        }
        Main.activity = a;
    }

    private void initComboboxes(){
        ObservableList<String> types = FXCollections.observableArrayList("Úloha", "Záľuba");
        cbType.getItems().setAll(types);
        ObservableList<Priority> priorities = FXCollections.observableArrayList(Priority.values());
        cbPriority.getItems().setAll(priorities);
    }

    private boolean validate(){
        if (cbType.getValue() == null){
            popup("Zvoľte prosím typ aktivity.");
            return false;
        }
        if (tfName.getText().isEmpty() || tfDesc.getText().isEmpty()){
            popup("Vyplňte prosím polia názov a popis.");
            return false;
        }
        if (type.equals("Task") && (cbPriority.getValue() == null || dpDueDate.getValue() == null)){
            popup("Zvoľte prosím prioritu danej úlohy a dátum, dokedy ju dokončiť.");
            return false;
        }
        return true;
    }
}
