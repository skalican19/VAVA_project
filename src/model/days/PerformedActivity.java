package model.days;

import java.io.Serializable;
import java.time.LocalTime;

/***
 * author: together
 */


public class PerformedActivity implements Serializable {
    private LocalTime start;
    private LocalTime end;
    private Activity activity;

    public PerformedActivity(LocalTime start, LocalTime end){
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
