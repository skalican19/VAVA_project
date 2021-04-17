package controller.creates;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Days.Task;
import java.time.LocalTime;

public class CreateDayTasks {

    @FXML TableView<Task> tableTasks;
    @FXML TableColumn<Task, LocalTime> columnStart;
    @FXML TableColumn<Task, LocalTime> columnEnd;
    @FXML TableColumn<Task, String> columnTask;

    public void btnAddOnAction(){

    }

    public void btnDeleteOnAction(){

    }
}
