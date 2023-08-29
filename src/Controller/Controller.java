package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class.
 */
abstract class Controller {

    private static Stage myStage;

    /**
     * loads the Next scene.
     *
     * @param theEvent     the event
     * @param theScenePath the scene path
     */
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

    /**
     * loads the Next scene.
     *
     * @param theStage     the stage
     * @param theScenePath the scene path
     */
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

    /**
     * Load room scene.
     *
     * @param theEvent the event
     */
    void loadRoomScene(final ActionEvent theEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Room.fxml"));
            Parent parent = loader.load();
            Stage stage = (Stage) ((Node) theEvent.getSource()).getScene().getWindow();
            setStage(stage);
            Scene scene = new Scene(parent);
            scene.addEventFilter(KeyEvent.KEY_PRESSED, ((RoomController)loader.getController()).getKeyHandler());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Sets stage.
     *
     * @param theStage the stage
     */
    static void setStage(final Stage theStage) {
        myStage = theStage;
    }

    /**
     * Gets stage.
     *
     * @return the stage
     */
    static Stage getStage() {
        return myStage;
    }
}