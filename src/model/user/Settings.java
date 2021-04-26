package model.user;
import java.time.LocalTime;

/***
 * Author Dušan
 */
public class Settings {
    private LocalTime dayStart;
    private LocalTime dayEnd;
    private LocalTime activityLength;

    public Settings() {
        this.dayStart = LocalTime.of(8,0);
        this.dayEnd = LocalTime.of(21,59);
        this.activityLength = LocalTime.of(1,0);
    }

    public LocalTime getDayStart() {
        return dayStart;
    }

    public void setDayStart(LocalTime dayStart) {
        this.dayStart = dayStart;
    }

    public LocalTime getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(LocalTime dayEnd) {
        this.dayEnd = dayEnd;
    }

    public LocalTime getActivityLength() {
        return activityLength;
    }

    public void setActivityLength(LocalTime activityLength) {
        this.activityLength = activityLength;
    }
}
