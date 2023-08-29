package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * The type Game over controller.
 */
public class GameOverController extends Controller {
    private static final String HERO_SELECT_PATH = "/View/HeroSelect.fxml";

    /**
     * Method for clicking the yes button.
     * @param theEvent the event
     */
    @FXML
    private void onYesButtonPress(final ActionEvent theEvent) {
        nextScene(theEvent, HERO_SELECT_PATH);
    }

    /**
     * method for exiting on 'no' button press.
     */
    @FXML
    private void onNoButtonPress() {
        System.exit(0);
    }
}