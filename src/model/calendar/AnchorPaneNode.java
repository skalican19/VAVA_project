package model.calendar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Main;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        this.setOnMouseClicked(event -> {
            try {
                Parent root;
                FXMLLoader loader =  new FXMLLoader(Main.class.getResource("/view/showday.fxml"),
                        ResourceBundle.getBundle("MessagesBundle", Main.currentLocale));
                root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Day");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
