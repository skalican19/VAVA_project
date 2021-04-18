package model.Days;

import java.time.LocalDate;
import java.time.LocalTime;

public class Activity {
    private String name;
    private Priority priority;
    private String description;
    private LocalTime start;
    private LocalTime end;
    private LocalDate lastDone;      // when was the activity last done for the user

    public Activity(String name, Priority priority, String description) {
        this.name = name;
        this.priority = priority;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public LocalDate getLastDone() {
        return lastDone;
    }
}
