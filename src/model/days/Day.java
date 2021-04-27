package model.days;
import javafx.util.converter.LocalTimeStringConverter;
import model.Main;
import model.user.Settings;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/***
 * Author Dušan
 */
public class Day {
    private final LocalDate date;
    private ArrayList<PerformedActivity> activities;
    private String comment;

    public Day(LocalDate date) {
        this.date = date;
        initializeActivities();
    }

    private void initializeActivities(){
        ArrayList<LocalTime> validhours = Settings.getInstance().getValidHours();
        activities = new ArrayList<>();

        for(LocalTime hour: validhours){
            activities.add(new PerformedActivity(hour,hour.plusHours(1)));
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<PerformedActivity> getActivities() {
        return activities;
    }

    public String getComment() {
        return comment;
    }
}
