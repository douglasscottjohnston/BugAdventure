package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GameOverController extends Controller {
    private static final String HERO_SELECT_PATH = "../View/HeroSelect.fxml";

    @FXML
    private void onYesButtonPress(final ActionEvent theEvent) {
        nextScene(theEvent, HERO_SELECT_PATH);
    }

    @FXML
    private void onNoButtonPress() {
        System.exit(0);
    }
}