package model.days;
import javafx.util.converter.LocalTimeStringConverter;
import model.Main;
import model.user.Settings;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/***
 * Author Dušan
 */
public class Day implements Serializable {
    private final LocalDate date;
    private ArrayList<PerformedActivity> activities;
    private String comment;

    public Day(LocalDate date) {
        this.date = date;
        initializeActivities();
    }

    private void initializeActivities(){
        ArrayList<LocalTime> validhours = Main.user.getSettings().getValidHours();
        activities = new ArrayList<>();

        for(LocalTime hour: validhours){
            activities.add(new PerformedActivity(hour,hour.plusHours(1)));
        }
    }

    public int getFreeHours(){
        int sum = 0;
        for(PerformedActivity a : activities){
            if (a.getActivity() == null){
                sum++;
            }
        }
        return sum;
    }

    public int getHobbyHours(){
        int sum = 0;
        for(PerformedActivity a : activities){
            if (a.getActivity() instanceof Hobby){
                sum++;
            }
        }
        return sum;
    }

    public int getTaskHours(){
        int sum = 0;
        for(PerformedActivity a : activities){
            if (a.getActivity() instanceof Task){
                sum++;
            }
        }
        return sum;
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
