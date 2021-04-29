package controller;

import controller.databases.DatabaseManager;
import controller.flowcontrol.AlertBox;
import controller.flowcontrol.IChangeScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Translations;
import model.days.Activity;
import model.days.Hobby;
import model.days.Task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * Author Dušan
 */
public class ShowActivityController implements Initializable, IChangeScene {
    Logger LOG = Logger.getLogger(IChangeScene.class.getName());
    private Activity current;
    private boolean backBtn;
    @FXML TextField tfName;
    @FXML TextField tfLastDate;
    @FXML TextField tfAcName;
    @FXML TextArea taDescription;
    @FXML DatePicker dpDueDate;
    @FXML ProgressBar progressbar;
    @FXML ProgressIndicator progress;
    @FXML Button btnAdd;
    @FXML Button btnSub;
    @FXML Button btnBack;
    @FXML Label lblDueDate;
    @FXML Label lblProgress;
    @FXML ImageView activityImage;


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
        AlertBox.show(Translations.TranslateAlertBox("alert_successfully_saved"), "Success");
    }

    public void btnBackOnAction(){
        sceneChanger("daysummary");
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
            if(progress.getProgress() < 0){
                progress.setProgress(0);
                progressbar.setProgress(0);
            }
        }
    }

    private void setActivity(){
        if (current == null){
            LOG.log(Level.SEVERE, "Activity was not initialized successfully");
            return;
        }
        if(current instanceof Hobby){
            tfAcName.setText(Translations.Translate("hobby"));
            btnAdd.setVisible(false);
            btnSub.setVisible(false);
            progress.setVisible(false);
            progressbar.setVisible(false);
            lblDueDate.setVisible(false);
            lblProgress.setVisible(false);
            dpDueDate.setVisible(false);
            activityImage.setImage(new Image("/images/hobbys.png"));
        }
        else{
            tfAcName.setText(Translations.Translate("task"));
            double progressVal = ((Task) current).getProgress();
            progressbar.setProgress(progressVal);
            progress.setProgress(progressVal);
            dpDueDate.setValue(((Task) current).getDueDate());

            progressbar.setProgress(progressVal);
            activityImage.setImage(new Image("/images/tasks.png"));
        }
        tfName.setText(current.getName());
        LocalDate lastDate = current.getLastDone();
        if (lastDate != null) { tfLastDate.setText(lastDate.toString()); }

        taDescription.setText(current.getDescription());
        dpDueDate.setEditable(false);
    }

    public void setCurrent(Activity current, Boolean backButton) {
        this.current = current;
        setActivity();
        btnBack.setVisible(backButton);
    }

}
