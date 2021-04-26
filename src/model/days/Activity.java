package model.days;

import java.time.LocalDate;
import java.time.LocalTime;

/***
 * Author Dušan
 */
public class Activity {
    private String name;
    private Priority priority;
    private String description;
    private String type;
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



    public LocalDate getLastDone() {
        return lastDone;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public String getType(){
        if (this instanceof Task) return "Task";
        if (this instanceof Hobby) return "Recreation";
        return "Activity";
    }
}
