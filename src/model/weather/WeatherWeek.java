package model.weather;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class WeatherWeek {
    private ArrayList<WeatherDay> weatherDays;

    public ArrayList<WeatherDay> getWeatherDays() {
        return weatherDays;
    }

    public WeatherDay getDayFromDate(LocalDate date) {
        for (WeatherDay day: weatherDays){
            if (day.getDate().isEqual(date)) {
                return day;
            }
        }
        return null;
    }

    public void parseWeatherXml(String city) {
        Document doc = loadWeatherXml(city);
        NodeList hourForecast = doc.getElementsByTagName("time");
        Node hour;

        ArrayList<WeatherDay> week = new ArrayList<>();
        WeatherDay weatherDay = null;

        DateTimeFormatter formatter = DateTimeFormatter. ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate date = LocalDateTime. parse("2000-01-01 00:00:00", formatter).toLocalDate();

        for (int i = 0; i < hourForecast.getLength(); i++) {
            hour = hourForecast.item(i);
            NodeList hourData = hour.getChildNodes();
            LocalDateTime dateTime = LocalDateTime. parse(hour.getAttributes().item(0).getNodeValue().replace('T', ' '), formatter);
            LocalDateTime displayTime = LocalDateTime. parse(hour.getAttributes().item(1).getNodeValue().replace('T', ' '), formatter);
            LocalTime time = displayTime.toLocalTime();
            String weatherImage = hourData.item(0).getAttributes().getNamedItem("var").getNodeValue();
            String weatherState = hourData.item(0).getAttributes().getNamedItem("name").getNodeValue();
            String precipitation = hourData.item(1).getAttributes().getNamedItem("probability").getNodeValue();
            String wind = hourData.item(3).getAttributes().getNamedItem("mps").getNodeValue();
            String temperature = hourData.item(4).getAttributes().getNamedItem("value").getNodeValue();

            WeatherHour weatherHour = new WeatherHour(time, weatherState, weatherImage, precipitation, temperature, wind);
            if (!date.equals(dateTime.toLocalDate())){
                date = dateTime.toLocalDate();
                weatherDay = new WeatherDay(date);
                weatherDay.setDay();
                week.add(weatherDay);
            }
            assert weatherDay != null;
            weatherDay.addToWeatherHours(weatherHour);
        }
        this.weatherDays = week;
    }

    private Document loadWeatherXml(String city){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + ",sk&mode=xml&APPID=67a8a7b7f5444aaa03b228f1f4e68b32";
        DocumentBuilder db = null;
        try{
            db = dbf.newDocumentBuilder();
            return db.parse(new URL(url).openStream());
        } catch(ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
