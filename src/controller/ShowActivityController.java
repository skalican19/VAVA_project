package controller;

import controller.databases.DatabaseManager;
import controller.flowcontrol.IChangeScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.days.Activity;
import model.days.Hobby;
import model.days.Task;

import java.io.IOException;
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

    public void btnSaveOnAction() throws IOException {
        current.setName(tfName.getText());
        current.setDescription(taDescription.getText());
        if(current instanceof Task){
            ((Task) current).setProgress(progress.getProgress());
            ((Task) current).setDueDate(dpDueDate.getValue());
        }
        DatabaseManager.getInstance().saveUsers();
    }

    public void btnBackOnAction(){
        // TODO zalezi kde sa bude picovinka zobrazovat
        sceneChanger("welcomescreen");
    }

    public void btnAddOnAction(){
        if(progress.getProgress() < 100){
            progressbar.setProgress(progressbar.getProgress() + 0.05);
            progress.setProgress(progress.getProgress() + 0.05);
        }
    }

    public void btnSubOnAction(){
        if(progress.getProgress() > 0) {
            progressbar.setProgress(progressbar.getProgress() - 0.05);
            progress.setProgress(progress.getProgress() - 0.05);
        }
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
            dpDueDate.setVisible(false);
            tfAcName.setText("Voľnočasová aktivita");
            //TODO nastav obrazok
        }
        else{
            tfAcName.setText("Úloha");
            double progressVal = ((double) ((Task) current).getProgress());
            progressbar.setProgress(progressVal);
            progress.setProgress(progressVal);
            dpDueDate.setValue(((Task) current).getDueDate());
            progressbar.setProgress(progressVal);
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
