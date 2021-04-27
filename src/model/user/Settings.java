package model.user;
import controller.databases.DatabaseManager;
import model.Main;
import model.days.PerformedActivity;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

/***
 * Author Dušan
 */
public class Settings {
    public static Settings instance = null;
    private LocalTime dayStart;
    private LocalTime dayEnd;
    private LocalTime activityLength;

    public static Settings getInstance(){
        if (instance == null)
            instance = new Settings();

        return instance;
    }

    public Settings() {
        this.dayStart = LocalTime.of(8,0);
        this.dayEnd = LocalTime.of(22,0);
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

    public ArrayList<LocalTime> getValidHours(){
        ArrayList<LocalTime> validHours = new ArrayList<>();
        LocalTime startTime = Main.settings.getDayStart();
        LocalTime currentTime = LocalTime.of(startTime.getHour(), startTime.getMinute());
        LocalTime endTime = Main.settings.getDayEnd();
        int counter = 0;

        while (currentTime.getHour() < endTime.getHour()) {
            validHours.add(currentTime);
            currentTime = moveCurrentTime(currentTime);
            counter++;
            if (counter == 24) break;
        }

        return validHours;
    }

    private LocalTime moveCurrentTime(LocalTime current){
        current = current.plusHours(Main.settings.getActivityLength().getHour());
        current = current.plusMinutes(Main.settings.getActivityLength().getMinute());
        return current;
    }
}
