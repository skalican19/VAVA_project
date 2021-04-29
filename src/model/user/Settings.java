package model.user;
import model.Main;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

/***
 * Author Dušan
 */
public class Settings implements Serializable {
    private Locale currentLocale;
    private String defaultCity;
    private String shownCity;
    private LocalTime dayStart;
    private LocalTime dayEnd;
    private LocalTime activityLength;

    public Settings() {
        this.defaultCity = "Bratislava";
        this.currentLocale = new Locale("sk", "SK");
        this.dayStart = LocalTime.of(5,0);
        this.dayEnd = LocalTime.of(23,0);
        this.activityLength = LocalTime.of(1,0);
    }

    public LocalTime getDayStart() {
        return dayStart;
    }


    public LocalTime getDayEnd() {
        return dayEnd;
    }


    public LocalTime getActivityLength() {
        return activityLength;
    }

    public ArrayList<LocalTime> getValidHours(){
        ArrayList<LocalTime> validHours = new ArrayList<>();
        LocalTime startTime = Main.user.getSettings().getDayStart();
        LocalTime currentTime = LocalTime.of(startTime.getHour(), startTime.getMinute());
        LocalTime endTime = Main.user.getSettings().getDayEnd();
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
        current = current.plusHours(Main.user.getSettings().getActivityLength().getHour());
        current = current.plusMinutes(Main.user.getSettings().getActivityLength().getMinute());
        return current;
    }

    /**
     * City shown if no other city is requested to be shown, also the city upon which the recommendation
     * of outdoor activities is made. May be changed in settings, default is Bratislava.
     */
    public void setDefaultCity(String city) {
        this.defaultCity = city;
    }

    public String getDefaultCity() {
        return defaultCity;
    }



    public void setShownCity(String shownCity) {
        this.shownCity = shownCity;
    }

    /***
     * City you are currently requesting the weather for
     */
    public String getShownCity() {
        return shownCity == null? defaultCity : shownCity;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }
}
