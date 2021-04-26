package model;

import javafx.scene.text.Text;

import java.util.ResourceBundle;

/***
 * Author Dušan
 */
public class Translations {

    public static ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", Main.currentLocale);

    public static Text[] translateDays(){
        return new Text[]{
                new Text(bundle.getString("monday")),
                new Text(bundle.getString("tuesday")),
                new Text(bundle.getString("wednesday")),
                new Text(bundle.getString("thursday")),
                new Text(bundle.getString("friday")),
                new Text(bundle.getString("saturday")),
                new Text(bundle.getString("sunday"))
        };
    }

    public static String translateMonth(String month){
        switch (month){
            case "JANUARY":
                return bundle.getString("january");
            case "FEBRUARY":
                return bundle.getString("february");
            case "MARCH":
                return bundle.getString("march");
            case "APRIL":
                return bundle.getString("april");
            case "MAY":
                return bundle.getString("may");
            case "JUNE":
                return bundle.getString("june");
            case "JULY":
                return bundle.getString("july");
            case "AUGUST":
                return bundle.getString("august");
            case "SEPTEMBER":
                return bundle.getString("september");
            case "OCTOBER":
                return bundle.getString("october");
            case "NOVEMBER":
                return bundle.getString("november");
            case "DECEMBER":
                return bundle.getString("december");
            default:
                return "Error loading month format.";
        }
    }
}
