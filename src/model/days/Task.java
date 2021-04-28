package model.days;
import java.time.Duration;
import java.time.LocalDate;

/***
 * Author Dušan
 */
public class Task extends Activity {
    private LocalDate dueDate;
    private long duration;
    private int progress;
    private boolean goal;
    private boolean done;

    public Task(String name, Priority priority, String description, LocalDate dueDate) {
        super(name, priority, description);
        this.dueDate = dueDate;
        this.duration = Duration.between(LocalDate.now(), dueDate).toDays();
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getProgress() {
        return progress;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isGoal() {
        return goal;
    }

    public boolean isDone() {
        return done;
    }
}