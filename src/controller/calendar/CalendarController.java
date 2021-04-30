package controller.calendar;
import controller.flowcontrol.IChangeScene;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import model.Main;
import model.Translations;
import controller.calendar.AnchorPaneNode;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Author: GitHub user SirGoose3432
 * Modified by: Dušan
 */
public class CalendarController implements Initializable, IChangeScene {

    @FXML Pane calendarPane;
    @FXML GridPane gridCalendar;
    @FXML GridPane gridDays;
    @FXML Label lblMonth;
    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale());
    private YearMonth currentYearMonth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currentYearMonth = YearMonth.now();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                gridCalendar.add(ap,j,i);
                allCalendarDays.add(ap);
            }
        }
        Label[] dayNames = Translations.TranslateDays();
        int col = 0;
        for (Label lbl : dayNames) {
            lbl.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;");
            HBox ap = new HBox();
            ap.setAlignment(Pos.CENTER);
            ap.setPrefSize(200, 10);
            ap.getChildren().add(lbl);
            ap.setStyle("-fx-border-color: black;");
            gridDays.add(ap, col++, 0);
        }

        populateCalendar(currentYearMonth);
    }


    /**
     * Set the days of the controller.calendar to correspond to the appropriate date
     * @param yearMonth year and month of month to render
     */
    public void populateCalendar(YearMonth yearMonth) {

        // Get the date we want to start with on the controller.calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);

        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("MONDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }

        // Populate the controller.calendar with day numbers
        for (AnchorPaneNode ap : allCalendarDays) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().clear();
            }
            Label lbl = new Label(String.valueOf(calendarDate.getDayOfMonth()));
            lbl.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;");
            if(calendarDate.equals(LocalDate.now())) {
                ap.setStyle("-fx-background-color: #d35f3f;" + "-fx-border-color: black;");
            }
            else{
                ap.setStyle("-fx-background-color:  #f5b752;" + "-fx-border-color: black;");
            }
            ap.setDate(calendarDate);
            addNote(ap, calendarDate);
            AnchorPane.setTopAnchor(lbl, 5.0);
            AnchorPane.setLeftAnchor(lbl, 5.0);
            ap.getChildren().add(lbl);
            calendarDate = calendarDate.plusDays(1);
        }
        // Change the title of the controller.calendar
        lblMonth.setText(Translations.TranslateMonth(yearMonth.getMonth().toString()) + " " + yearMonth.getYear());
    }

    /**
     * Move the month back by one. Repopulate the controller.calendar with the correct dates.
     */
    public void btnPrevOnAction() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Move the month forward by one. Repopulate the controller.calendar with the correct dates.
     */
    public void btnNextOnAction() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public void btnBackOnAction() {
        sceneChanger("welcomescreen");
    }

    public ArrayList<AnchorPaneNode> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }

    public void setCurrentYearMonth(YearMonth currentYearMonth) {
        this.currentYearMonth = currentYearMonth;
    }


    private void addNote(AnchorPaneNode ap, LocalDate calendarDate){
        if(!Main.user.isActivitiesDue(calendarDate)) return;
        Label lbl = new Label(Translations.Translate("due_activity"));
        lbl.setStyle("-fx-text-fill: white;" + "-fx-font-weight: bold;");
        lbl.setWrapText(true);
        ap.getChildren().add(lbl);
        lbl.setMaxWidth(Double.MAX_VALUE);
        ap.setLeftAnchor(lbl, 0.0);
        ap.setRightAnchor(lbl, 0.0);
        ap.setTopAnchor(lbl, 30.0);
        ap.setBottomAnchor(lbl, 30.0);
        lbl.setAlignment(Pos.CENTER);

    }
}