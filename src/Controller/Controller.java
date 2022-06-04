package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

abstract class Controller {

    private static Stage myStage;

    void nextScene(final ActionEvent theEvent, final String theScenePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(theScenePath));

            Parent parent = loader.load();
            Stage stage = (Stage) ((Node) theEvent.getSource()).getScene().getWindow();
            setStage(stage);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    void nextScene(final Stage theStage, final String theScenePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(theScenePath));
            Parent parent = loader.load();
            setStage(theStage);
            Scene scene = new Scene(parent);
            theStage.setScene(scene);
            theStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    static void setStage(final Stage theStage) {
        myStage = theStage;
    }

    static Stage getStage() {
        return myStage;
    }
}