package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartController {

    @FXML
    Button myNewGame, myLoadGame, myHelp, myExit;

    @FXML
    private void initialize() {
        myLoadGame.setDisable(Model.isFirstPlay());
    }

    @FXML
    private void onNewGameButtonClick(ActionEvent event) {
        try {
            Parent heroSelectParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/HeroSelect.fxml")));
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
    private void onLoadGameButtonClick(ActionEvent event) {
        if(!myLoadGame.isDisable()) {
            //go to the room view
        }
    }

    @FXML
    private void onHelpButtonClick() {

    }

    @FXML
    private void onExitButtonClick() {
        System.exit(0);
    }
}
