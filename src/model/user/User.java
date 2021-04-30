package model.user;

import controller.databases.DatabaseManager;
import controller.flowcontrol.AlertBox;
import controller.flowcontrol.INewWindowScene;
import model.Main;
import model.Translations;
import model.days.Activity;
import model.days.Day;
import model.days.Task;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Serializable {
    private String userName;
    private String email;
    private String password;
    private Settings settings;
    private HashMap<LocalDate, Day> recordedDays = new HashMap<>();
    private ArrayList<Activity> activities = new ArrayList<>();


    /***
     * author: Michal
     */

    public User() {
        this.settings = new Settings();
    }

    public boolean registerUser(String userName, String email, String password) {
        this.userName = userName;
        if (this.validate(email, userName, password)) {
            this.email = email;
            this.password = password;
            this.settings = new Settings();
            this.settings.setCurrentLocale(Main.user.getLocale());
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

    public boolean validate(String emailStr, String userName, String password) {
        /***
         * regex from http://emailregex.com
         */
        Pattern ptr = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        Matcher matcher = ptr.matcher(emailStr);
        if (!matcher.find()){
            AlertBox.show(Translations.Translate("alert_invalid_e-mail"), "Warning");
            return  false;}
        if (userName.equals("")) {
            AlertBox.show(Translations.TranslateAlertBox("alert_invalid_reg_credentials"), "Warning");
            return false;
        }
        if (password.equals("")) {
            AlertBox.show(Translations.TranslateAlertBox("alert_invalid_reg_credentials"), "Warning");
            return false;
        }
        try {
            ArrayList<User> users = DatabaseManager.getInstance().getUsersDatabase();
            for (User user: users){
                if (this.getUserName().equals(user.getUserName())){
                    AlertBox.show(Translations.TranslateAlertBox("alert_username_taken"), "Warning");
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /***
     * Author - Dušan
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

    public Activity getActivityDue(LocalDate date){
        if (activities == null) return null;
        for(Activity a: activities){
            if(a instanceof Task && (((Task) a).getDueDate().equals(date))){
                return a;
            }
        }
        return null;
    }

    public ArrayList<Activity> getActivitiesDue(LocalDate date){
        ArrayList<Activity> activitiesDue = new ArrayList<>();

        if (activities == null) return null;
        for(Activity a: activities){
            if(a instanceof Task && (((Task) a).getDueDate().equals(date))){
                activitiesDue.add(a);
            }
        }
        return activitiesDue;
    }

    public boolean isActivitiesDue(LocalDate date){
        if (activities == null) return false;
        for(Activity a: activities){
            if(a instanceof Task && (((Task) a).getDueDate().equals(date))){
                return true;
            }
        }
        return false;
    }

    public void removeActivity(Activity a){
        activities.remove(a);
    }

    public Settings getSettings() {
        return settings;
    }

    public Locale getLocale(){
        return this.settings.getCurrentLocale();
    }

    public void setLocale(Locale currentLocale) {
        this.settings.setCurrentLocale(currentLocale);
    }
}
