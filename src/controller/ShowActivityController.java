package controller;

import controller.flowcontrol.IChangeScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.days.Activity;
import model.days.Hobby;
import model.days.Task;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * Author Dušan
 */
public class ShowActivityController implements Initializable, IChangeScene {
    Logger LOG = Logger.getLogger(IChangeScene.class.getName());
    private Activity current;
    @FXML TextField tfName;
    @FXML TextField tfLastDate;
    @FXML TextField tfAcName;
    @FXML TextArea taDescription;
    @FXML DatePicker dpDueDate;
    @FXML ProgressBar progressbar;
    @FXML ProgressIndicator progress;
    @FXML Button btnAdd;
    @FXML Button btnSub;
    @FXML Label lblDueDate;
    @FXML Label lblProgress;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnSaveOnAction(){
        //TODO ulozit customera
    }

    public void btnBackOnAction(){
        sceneChanger("welcomescreen");
    }

    public void btnAddOnAction(){

    }

    public void btnSubOnAction(){

    }

    private void setActivity(){
        if (current == null){
            LOG.log(Level.SEVERE, "Nesprávne inicializovaná aktivita.");
            return;
        }
        if(current instanceof Hobby){
            btnAdd.setVisible(false);
            btnSub.setVisible(false);
            progress.setVisible(false);
            progressbar.setVisible(false);
            lblDueDate.setVisible(false);
            lblProgress.setVisible(false);
            tfAcName.setText("Voľnočasová aktivita");
            //TODO nastav obrazok
        }
        else{
            tfAcName.setText("Úloha");
            double progressVal = ((double) ((Task) current).getProgress()) / 100;
            progressbar.setProgress(progressVal);
            progress.setProgress(progressVal);
            dpDueDate.setValue(((Task) current).getDueDate());
            //TODO nastav obrazok
        }
        tfLastDate.setText(current.getLastDone()==null ? null : current.getLastDone().toString());
        tfName.setText(current.getName());
        taDescription.setText(current.getDescription());
    }

    public void setCurrent(Activity current) {
        this.current = current;
        setActivity();
    }

}
