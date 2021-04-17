package model.Days;

import java.time.LocalDate;

public class Activity {
    private String name;
    private LocalDate lastDone;      // when was the activity last done for the user
    private Priority priority;
    private String description;


    public Activity(String name, Priority priority, String description) {
        this.name = name;
        this.priority = priority;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLastDone() {
        return lastDone;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }
}
