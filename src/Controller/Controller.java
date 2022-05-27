package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

abstract class Controller {
    void nextScene(final ActionEvent theEvent, final String theScenePath) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(theScenePath)));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) theEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    void nextScene(final Stage theStage, final String theScenePath) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(theScenePath)));
            Scene scene = new Scene(parent);
            theStage.setScene(scene);
            theStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}