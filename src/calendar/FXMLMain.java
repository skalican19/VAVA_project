package calendar;
import javafx.application.Application;
import model.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.YearMonth;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMLMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.currentLocale = new Locale("sk", "SK");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/fullCalendar.fxml"),
                ResourceBundle.getBundle("MessagesBundle", Main.currentLocale));
        primaryStage.setTitle("Full Calendar FXML Example");
        primaryStage.setScene(new Scene(loader.load()));

        // Get the controller and add the calendar view to it
        CalendarController calendarController = loader.getController();
        calendarController.setCurrentYearMonth(YearMonth.now());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
