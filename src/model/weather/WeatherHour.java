package model.weather;

import javafx.scene.image.Image;

import java.time.LocalTime;

public class WeatherHour {
    private int index;
    private LocalTime time;
    private Image weatherImage;
    private String weatherState;
    private String precipitation;
    private String temperature;
    private String wind;

    public WeatherHour(LocalTime time, String weatherState, String weatherImage, String precipitation, String temperature, String wind) {
        this.time = time;
        this.weatherState = weatherState;
        setIndex();
        String url = "/Images/" + weatherImage + ".png";
        this.weatherImage = new Image(url);
        this.precipitation = Math.round(Double.parseDouble(precipitation) * 100) + " %";
        double temp = Double.parseDouble(temperature) - 273;
        this.temperature = Math.round(temp*100.0) / 100.0 + " °C";
        this.wind = wind + " m/s";
    }

    public void setIndex() {
        String time = this.time.toString();
        switch (time) {
            case "00:00":
                this.index = 7;
                break;
            case "03:00":
                this.index = 0;
                break;
            case "06:00":
                this.index = 1;
                break;
            case "09:00":
                this.index = 2;
                break;
            case "12:00":
                this.index = 3;
                break;
            case "15:00":
                this.index = 4;
                break;
            case "18:00":
                this.index = 5;
                break;
            case "21:00":
                this.index = 6;
                break;
        }
    }

    public int getIndex() {
        return index;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getTemperature() {
        return temperature;
    }

    public Image getWeatherImage() {
        return weatherImage;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getWind() {
        return wind;
    }
}
