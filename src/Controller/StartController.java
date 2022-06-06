package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController extends Controller {

    @FXML
    Button myNewGame, myLoadGame, myHelp, myExit;

    @FXML
    private void initialize() {
        myLoadGame.setDisable(Model.isFirstPlay());
    }

    @FXML
    private void onNewGameButtonClick(final ActionEvent theEvent) {
        nextScene(theEvent, "/View/HeroSelect.fxml");
    }

    @FXML
    private void onLoadGameButtonClick(final ActionEvent theEvent) {
        if(!myLoadGame.isDisable()) {
            Model.load();
            loadRoomScene(theEvent);
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
