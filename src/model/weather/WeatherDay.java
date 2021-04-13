package model.weather;

import javafx.scene.image.Image;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WeatherDay {
    private ArrayList<WeatherHour> weatherHours;
    private LocalDate date;
    private Image noonImage;
    private String noonTemperature;
    private String nightTemperature;
    private String day;

    public WeatherDay(LocalDate date) {
        weatherHours = new ArrayList<>();
        this.date = date;
    }

    public Image getNoonImage() {
        return noonImage;
    }

    public ArrayList<WeatherHour> getWeatherHours() {
        return weatherHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNoonTemperature() {
        if (noonTemperature == null) {
            return "-------";
        }
        return noonTemperature;
    }

    public String getNightTemperature() {
        if (nightTemperature == null) {
            return "-------";
        }
        return nightTemperature;
    }

    public String getDay() {
        return day;
    }

    public void setDay() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Calendar c = Calendar.getInstance();
        c.setTime(Date.from(this.date.atStartOfDay(defaultZoneId).toInstant()));
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                this.day = "Ne";
                break;
            case 2:
                this.day = "Po";
                break;
            case 3:
                this.day = "Ut";
                break;
            case 4:
                this.day = "St";
                break;
            case 5:
                this.day = "Št";
                break;
            case 6:
                this.day = "Pi";
                break;
            case 7:
                this.day = "So";
                break;
        }
    }

    public void addToWeatherHours(WeatherHour hour){
        weatherHours.add(hour);
        if (hour.getTime() == LocalTime.of(12, 0, 0)) {
            this.noonTemperature = hour.getTemperature();
            this.noonImage = hour.getWeatherImage();
        }
        else if (hour.getTime() == LocalTime.of(3, 0, 0)) {
            this.nightTemperature = hour.getTemperature();
        }
        if (hour.getTime() == LocalTime.of(0, 0, 0) && this.noonTemperature == null) {
            this.noonImage = hour.getWeatherImage();
        }
    }
}
