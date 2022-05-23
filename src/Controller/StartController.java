package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML
    private Label myInfoText;

    @FXML
    private void onPlayButtonClick(ActionEvent event) {
        try {
            Parent heroSelectParent = FXMLLoader.load(getClass().getResource("../View/HeroSelectView.fxml"));
            Scene heroSelectScene = new Scene(heroSelectParent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(heroSelectScene);
            stage.show();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void onHelpButtonClick() {
        myInfoText.setText("Needing help?");
    }

    @FXML
    private void onExitButtonClick() {
        myInfoText.setText("Leaving so soon?");
        System.exit(0);
    }

    @FXML
    private void onRunButtonClick() {
        if(Model.run()) {
            //successfully ran away
        }
    }

    @FXML
    private void onSaveButtonClick() {
        Model.save();
    }

    @FXML
    private void onLoadButtonClick() {
        Model.load();
    }

    @FXML
    private void onNorthButtonClick() {
        Model.move(Directions.Direction.NORTH);
    }

    @FXML
    private void onSouthButtonClick() {
        Model.move(Directions.Direction.SOUTH);
    }

    @FXML
    private void onEastButtonClick() {
        Model.move(Directions.Direction.EAST);
    }

    @FXML
    private void onWestButtonClick() {
        Model.move(Directions.Direction.WEST);
    }
}
