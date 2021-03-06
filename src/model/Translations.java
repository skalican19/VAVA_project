package model;

import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 * Author Dušan
 */
public class Translations {

    public static ResourceBundle bundle;


    public static String Translate(String key){
        bundle = ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale());
        return bundle.getString(key);
    }



    public static Label[] TranslateDays(){
        bundle = ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale());
        return new Label[]{
                new Label(bundle.getString("monday")),
                new Label(bundle.getString("tuesday")),
                new Label(bundle.getString("wednesday")),
                new Label(bundle.getString("thursday")),
                new Label(bundle.getString("friday")),
                new Label(bundle.getString("saturday")),
                new Label(bundle.getString("sunday"))
        };
    }

    public static String TranslateMonth(String month){
        bundle = ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale());
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


    public static ArrayList<String> ActivityTypesStrings(){
        bundle = ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale());
        return new ArrayList<String>() {
            {
                add(bundle.getString("hobby"));
                add(bundle.getString("task"));
            }
        };
    }

    public static String TranslateAlertBox(String key){
        bundle = ResourceBundle.getBundle("MessagesBundle", Main.user.getLocale());
        return bundle.getString(key);
    }

}
