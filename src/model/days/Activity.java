package model.days;
import controller.WelcomeScreenController;
import model.Main;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

/***
 * Author Dušan
 */
public class Activity implements Serializable {
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
        HashMap<LocalDate, Day> recordedDays =  Main.user.getRecordedDays();
        LocalDate maxDate = null;

        for(Day day: recordedDays.values()) {

            for (PerformedActivity activity: day.getActivities()) {
                if (activity.getActivity() == null) { continue; }
                if (activity.getActivity().getName().equals(this.getName())) {

                    if (maxDate == null || maxDate.isBefore(day.getDate())) {

                        if (day.getDate().isBefore(WelcomeScreenController.displayDate)) {
                            maxDate = day.getDate();
                        }
                    }
                }
            }
        }
        return maxDate;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLastDone(LocalDate lastDone) {
        this.lastDone = lastDone;
    }
}
