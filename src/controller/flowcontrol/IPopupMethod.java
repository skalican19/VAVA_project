package controller.flowcontrol;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public interface IPopupMethod {

    default void warning(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,text, ButtonType.CLOSE);
        alert.show();
    }
}
