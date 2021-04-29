package controller;

import controller.flowcontrol.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.Main;
import model.Translations;
import model.weather.WeatherDay;
import model.weather.WeatherHour;
import model.weather.WeatherWeek;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WeatherPaneController implements Initializable {
    @FXML private ImageView image;
    @FXML private Label dayName;
    @FXML private Label date;
    @FXML private Label dayTemp;
    @FXML private Label nightTemp;

    @FXML private GridPane gridpane;


    public void showWeatherDay(WeatherDay day){
        gridpane.getChildren().clear();

        for(WeatherHour hour: day.getWeatherHours()) {
            Label informationLabel = new Label();
            ImageView image = new ImageView(hour.getWeatherImage());
            image.setFitHeight(50);
            image.setFitWidth(50);
            image.setStyle("-fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
            gridpane.add(image, hour.getIndex(), 0);
            GridPane.setHalignment(image, HPos.CENTER);

            for (int row_index = 1; row_index < 5; row_index++) {
                String text = "";
                switch (row_index) {
                    case 1:
                        informationLabel = new Label(Translations.Translate("time"));
                        text = hour.getTime().toString();
                        break;
                    case 2:
                        informationLabel = new Label(Translations.Translate("temperature"));
                        text = hour.getTemperature();
                        break;
                    case 3:
                        informationLabel = new Label(Translations.Translate("wind"));
                        text = hour.getWind();
                        break;
                    case 4:
                        informationLabel = new Label(Translations.Translate("rainfall"));
                        text = hour.getPrecipitation();
                        break;
                }
                gridpane.add(informationLabel, 0, row_index);
                informationLabel.setStyle("-fx-alignment:  center;" +
                        "-fx-text-fill: #ffffff;"
                        + "-fx-font-weight: bold;"
                        + "-fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
                informationLabel.setFont(new Font("System", 9));
                GridPane.setHalignment(informationLabel, HPos.CENTER);

                Label label = new Label(text);
                label.setStyle("-fx-alignment:  center;" +
                        "-fx-text-fill: #ffffff;"
                        + "-fx-font-weight: bold;"
                        + "-fx-effect:  dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
                label.setFont(new Font("System", 9));
                gridpane.add(label, hour.getIndex(), row_index);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WeatherWeek week = new WeatherWeek();
        if(week.parseWeatherXml(Main.user.getSettings().getShownCity())) {
            try {
                WeatherDay day = week.getDayFromDate(WelcomeScreenController.displayDate);
                image.setImage(day.getNoonImage());
                dayName.setText(day.getDay());
                date.setText(day.getDate().toString());
                dayTemp.setText(day.getNoonTemperature());
                nightTemp.setText(day.getNightTemperature());
                showWeatherDay(day);
            }
            catch (Exception e) {
                AlertBox.show(Translations.TranslateAlertBox("alert_weather_unavailable"), "Warning");
            }
        }
        else {
            AlertBox.show(Translations.TranslateAlertBox("alert_unknown_location"), "Warning");
        }
    }
}
