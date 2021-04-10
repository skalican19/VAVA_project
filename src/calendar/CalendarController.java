package calendar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import model.Main;
import model.Translations;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    @FXML Pane calendarPane;
    @FXML GridPane gridCalendar;
    @FXML GridPane gridDays;
    @FXML Label lblMonth;
    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", Main.currentLocale);
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
        Text[] dayNames = Translations.translateDays();
        int col = 0;
        for (Text txt : dayNames) {
            HBox ap = new HBox();
            ap.setAlignment(Pos.CENTER);
            ap.setPrefSize(200, 10);
            ap.getChildren().add(txt);
            gridDays.add(ap, col++, 0);
        }

        populateCalendar(currentYearMonth);

    }

    /**
     * Set the days of the calendar to correspond to the appropriate date
     * @param yearMonth year and month of month to render
     */
    public void populateCalendar(YearMonth yearMonth) {

        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);

        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }

        // Populate the calendar with day numbers
        for (AnchorPaneNode ap : allCalendarDays) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            ap.setDate(calendarDate);
            AnchorPane.setTopAnchor(txt, 5.0);
            AnchorPane.setLeftAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
        }
        // Change the title of the calendar
        lblMonth.setText(Translations.translateMonth(yearMonth.getMonth().toString()) + " " + yearMonth.getYear());
    }

    /**
     * Move the month back by one. Repopulate the calendar with the correct dates.
     */
    public void btnPrevOnAction() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Move the month forward by one. Repopulate the calendar with the correct dates.
     */
    public void btnNextOnAction() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
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
}