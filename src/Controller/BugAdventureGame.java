package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BugAdventureGame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
<<<<<<< HEAD
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Start.fxml"));
            Parent root = loader.load();
=======
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Start.fxml"));
>>>>>>> sql

            StartController start = loader.getController();
            primaryStage.setResizable(false);

            Scene scene = new Scene(root, 400, 400);
            primaryStage.setTitle("Bug Adventure");
            primaryStage.setScene(scene);
            start.setStage(primaryStage);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}