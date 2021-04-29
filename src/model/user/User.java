package model.user;

import controller.databases.DatabaseManager;
import model.days.Activity;
import model.days.Day;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Serializable {
    private String userName;
    private String email;
    private String password;
    private HashMap<LocalDate, Day> recordedDays = new HashMap<>();
    private ArrayList<Activity> activities;

    public boolean registerUser(String userName, String email, String password) {
        if (this.validate(email)) {
            this.userName = userName;
            this.email = email;
            this.password = password;
            try {
                DatabaseManager manager = DatabaseManager.getInstance();
                manager.updateUsers(this);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public boolean verifyLogin(String password) {
        return this.password.equals(password);
    }

    public boolean validate(String emailStr) {
        Pattern ptr = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        Matcher matcher = ptr.matcher(emailStr);
        return matcher.find();
    }

    /***
     * Author - Dušan
     * @param date
     * @param d
     */
    public void addDay(LocalDate date, Day d){
        if (recordedDays == null) recordedDays = new HashMap<>();
        recordedDays.put(date,d);
    }

    public Day getDay(LocalDate date){
        if (recordedDays.containsKey(date)) return recordedDays.get(date);
        recordedDays.put(date, new Day(date));
        return recordedDays.get(date);
    }

    public HashMap<LocalDate, Day> getRecordedDays(){
        return recordedDays;
    }

    public ArrayList<Activity> getActivities() {
        if (activities == null) return new ArrayList<>();
        return activities;
    }

    public void addActivity(Activity a){
        if (activities == null) activities = new ArrayList<>();
        activities.add(a);
    }

    public void removeActivity(Activity a){
        activities.remove(a);
    }
}
