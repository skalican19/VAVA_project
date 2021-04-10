package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.weather.WeatherDay;
import model.weather.WeatherHour;
import model.weather.WeatherWeek;


import java.net.URL;
import java.util.ResourceBundle;

public class WeatherDemoController implements Initializable{
    @FXML private AnchorPane day1;
    @FXML private ImageView image1;
    @FXML private Label dayName1;
    @FXML private Label date1;
    @FXML private Label dayTemp1;
    @FXML private Label nightTemp1;

    @FXML private AnchorPane day2;
    @FXML private ImageView image2;
    @FXML private Label dayName2;
    @FXML private Label date2;
    @FXML private Label dayTemp2;
    @FXML private Label nightTemp2;

    @FXML private AnchorPane day3;
    @FXML private ImageView image3;
    @FXML private Label dayName3;
    @FXML private Label date3;
    @FXML private Label dayTemp3;
    @FXML private Label nightTemp3;

    @FXML private AnchorPane day4;
    @FXML private ImageView image4;
    @FXML private Label dayName4;
    @FXML private Label date4;
    @FXML private Label dayTemp4;
    @FXML private Label nightTemp4;

    @FXML private AnchorPane day5;
    @FXML private ImageView image5;
    @FXML private Label dayName5;
    @FXML private Label date5;
    @FXML private Label dayTemp5;
    @FXML private Label nightTemp5;

    @FXML private GridPane gridpane;

    private WeatherWeek week = new WeatherWeek();

    @FXML
    public void firstClicked(){
        showWeatherDay(week.getWeatherDays().get(0));
    }

    @FXML
    public void secondClicked(){
        showWeatherDay(week.getWeatherDays().get(1));
    }

    @FXML
    public void thirdClicked(){
        showWeatherDay(week.getWeatherDays().get(2));
    }

    @FXML
    public void fourthClicked(){
        showWeatherDay(week.getWeatherDays().get(3));
    }

    @FXML
    public void fifthClicked(){
        showWeatherDay(week.getWeatherDays().get(4));
    }

    public void showWeatherDay(WeatherDay day){
        gridpane.getChildren().clear();
        for(WeatherHour hour: day.getWeatherHours()) {
            ImageView image = new ImageView(hour.getWeatherState());
            image.setFitHeight(50);
            image.setFitWidth(50);
            image.setStyle("-fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
            gridpane.add(image, hour.getIndex(), 0);
            GridPane.setHalignment(image, HPos.CENTER);

            for (int row_index = 1; row_index < 5; row_index++) {
                String text = "";
                switch (row_index) {
                    case 1:
                        text = hour.getTime().toString();
                        break;
                    case 2:
                        text = hour.getTemperature();
                        break;
                    case 3:
                        text = hour.getWind();
                        break;
                    case 4:
                        text = hour.getPrecipitation();
                        break;
                }
                Label label = new Label(text);
                label.setStyle("-fx-alignment:  center;" +
                        "-fx-text-fill: #ffffff;"
                        + "-fx-font-weight: bold;"
                        + "-fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
                gridpane.add(label, hour.getIndex(), row_index);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        week.parseWeatherXml("Komárno");

        WeatherDay day = week.getWeatherDays().get(0);
        image1.setImage(day.getNoonImage());
        dayName1.setText(day.getDay());
        date1.setText(day.getDate().toString());
        dayTemp1.setText(day.getNoonTemperature());
        nightTemp1.setText(day.getNightTemperature());

        day = week.getWeatherDays().get(1);
        image2.setImage(day.getNoonImage());
        dayName2.setText(day.getDay());
        date2.setText(day.getDate().toString());
        dayTemp2.setText(day.getNoonTemperature());
        nightTemp2.setText(day.getNightTemperature());

        day = week.getWeatherDays().get(2);
        image3.setImage(day.getNoonImage());
        dayName3.setText(day.getDay());
        date3.setText(day.getDate().toString());
        dayTemp3.setText(day.getNoonTemperature());
        nightTemp3.setText(day.getNightTemperature());

        day = week.getWeatherDays().get(3);
        image4.setImage(day.getNoonImage());
        dayName4.setText(day.getDay());
        date4.setText(day.getDate().toString());
        dayTemp4.setText(day.getNoonTemperature());
        nightTemp4.setText(day.getNightTemperature());

        day = week.getWeatherDays().get(4);
        image5.setImage(day.getNoonImage());
        dayName5.setText(day.getDay());
        date5.setText(day.getDate().toString());
        dayTemp5.setText(day.getNoonTemperature());
        nightTemp5.setText(day.getNightTemperature());
    }
}
