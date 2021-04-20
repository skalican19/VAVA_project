package model.Days;
import java.time.LocalDate;

/***
 * Author Dušan
 */
public class Task extends Activity {
    private LocalDate dueDate;
    private int progress;
    private boolean goal;
    private boolean done;

    public Task(String name, Priority priority, String description, LocalDate dueDate) {
        super(name, priority, description);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isGoal() {
        return goal;
    }

    public boolean isDone() {
        return done;
    }
}