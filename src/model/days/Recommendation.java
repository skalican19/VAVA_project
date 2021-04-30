package model.days;

import controller.WelcomeScreenController;
import model.Main;
import model.weather.WeatherDay;
import model.weather.WeatherWeek;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/***
 * author: Michal
 */

public class Recommendation {
    private ArrayList<Activity> recommendations;
    private int tasks;
    private LocalDate date;

    public Recommendation() {
        this.tasks = 0;
        this.date = WelcomeScreenController.displayDate;
        this.recommendations = new ArrayList<>();
    }

    public ArrayList<Activity> getRecommendations() {
        return recommendations;
    }

    public void makeRecommendations(){
        for (Activity activity: Main.user.getActivities()) {
            if (activity instanceof Task) {
                if (recommendTask((Task) activity)) {
                    recommendations.add(activity);
                    this.tasks++;
                }
            }
        }

        WeatherWeek weatherWeek = new WeatherWeek();
        if (weatherWeek.parseWeatherXml(Main.user.getSettings().getDefaultCity())) {
            for (Activity activity : Main.user.getActivities()) {
                if (activity instanceof Hobby) {
                    if (recommendHobby((Hobby) activity, weatherWeek)) {
                        recommendations.add(activity);
                    }
                }
            }
        }
    }

    private boolean recommendTask(Task activity){
        long daysBetween = Duration.between(WelcomeScreenController.displayDate.atStartOfDay(),
                activity.getDueDate().atStartOfDay()).toDays();

        if (activity.getPriority() == Priority.HIGH || activity.getPriority() == Priority.MEDIUM) {
            return true;
        }
        else if (daysBetween < activity.getDuration()/2 && activity.getProgress() < 0.5) {
            return true;
        }
        else if (daysBetween < activity.getDuration()/3 && activity.getProgress() < 0.65) {
            return true;
        }
        else if (daysBetween < activity.getDuration()/5 && activity.getProgress() < 0.80) {
            return true;
        }
        else return daysBetween < activity.getDuration() / 10 && activity.getProgress() < 0.90;

    }

    private boolean recommendHobby(Hobby activity, WeatherWeek week){
        if (activity.isOutdoor()) {
            if (week.getDayFromDate(date).isBadWeather()) {
                return false;
            }
            if (this.tasks > 2) {
                LocalDate tomorrow = date.plusDays(1);
                return week.getDayFromDate(tomorrow).isBadWeather() || this.tasks == 2;
            }
        }
        return this.tasks <= 2;
    }
}