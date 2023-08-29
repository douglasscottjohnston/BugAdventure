package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Start screen controller.
 */
public class StartController extends Controller {

    @FXML
    Button myNewGame, myLoadGame, myHelp, myExit;

    /**
     * initialized the title screen.
     */
    @FXML
    private void initialize() {
        myLoadGame.setDisable(Model.isFirstPlay());
    }

    /**
     * action for clicking on 'new game'.
     * @param theEvent
     */
    @FXML
    private void onNewGameButtonClick(final ActionEvent theEvent) {
        nextScene(theEvent, "/View/HeroSelect.fxml");
    }

    /**
     * action for clicking on 'load game'.
     * @param theEvent
     */
    @FXML
    private void onLoadGameButtonClick(final ActionEvent theEvent) {
        if(!myLoadGame.isDisable()) {
            Model.load();
            loadRoomScene(theEvent);
        }
    }

    /**
     * action for clicking on help button
     */
    @FXML
    private void onHelpButtonClick() {

    }

    /**
     * action for clicking on 'exit' button.
     */
    @FXML
    private void onExitButtonClick() {
        System.exit(0);
    }


}
