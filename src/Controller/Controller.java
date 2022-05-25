package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public abstract class Controller {
    void nextScene(final ActionEvent theEvent, final String theScenePath) {
        try {
            Parent heroSelectParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(theScenePath)));
            Scene heroSelectScene = new Scene(heroSelectParent);
            Stage stage = (Stage) ((Node) theEvent.getSource()).getScene().getWindow();
            stage.setScene(heroSelectScene);
            stage.show();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
