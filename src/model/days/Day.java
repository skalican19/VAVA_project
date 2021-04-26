package model.days;
import model.Main;

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
        LocalTime startTime = Main.settings.getDayStart();
        LocalTime currentTime = LocalTime.of(startTime.getHour(), startTime.getMinute());
        LocalTime endTime = Main.settings.getDayEnd();

        activities = new ArrayList<>();

        int counter = 0;
        while (currentTime.getHour() <= endTime.getHour()) {
            activities.add(new PerformedActivity(currentTime,moveCurrentTime(currentTime)));
            currentTime = moveCurrentTime(currentTime);
            counter++;
            if (counter == 24) break;
        }
    }

    private LocalTime moveCurrentTime(LocalTime current){
        current = current.plusHours(Main.settings.getActivityLength().getHour());
        current = current.plusMinutes(Main.settings.getActivityLength().getMinute());
        return current;
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
