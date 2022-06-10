package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Bug adventure game.
 */
public class BugAdventureGame extends Application {

    /**
     * Main.
     *
     * @param theArgs the args
     */
    public static void main(final String[] theArgs) {
        launch(theArgs);
    }

    @Override
    public void start(final Stage thePrimaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Start.fxml"));
            Parent root = loader.load();

            StartController start = loader.getController();
            thePrimaryStage.setResizable(false);

            Scene scene = new Scene(root, 400, 400);
            thePrimaryStage.getIcons().add(new Image("View/Resources/Characters/LadyBug.png"));
            thePrimaryStage.setTitle("Bug Adventure");
            thePrimaryStage.setScene(scene);
            start.setStage(thePrimaryStage);
            thePrimaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}