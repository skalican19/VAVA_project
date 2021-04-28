package model.days;
import java.time.Duration;
import java.time.LocalDate;

/***
 * Author Dušan
 */
public class Task extends Activity {
    private LocalDate dueDate;
    private long duration;
    private double progress;
    private boolean goal;
    private boolean done;

    public Task(String name, Priority priority, String description, LocalDate dueDate) {
        super(name, priority, description);
        this.dueDate = dueDate;
        this.duration = Duration.between(LocalDate.now(),dueDate).toDays();
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getProgress() {
        return progress;
    }

    public boolean isGoal() {
        return goal;
    }

    public boolean isDone() {
        return done;
    }

    public long getDuration() {
        return duration;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}