package model.Days;
import java.time.LocalDate;
import java.util.ArrayList;

public class Day {
    private LocalDate date;
    private ArrayList<Activity> activities;
    private String comment;

    public Day(LocalDate date, String comment) {
        this.date = date;
        this.comment = comment;
    }


}
