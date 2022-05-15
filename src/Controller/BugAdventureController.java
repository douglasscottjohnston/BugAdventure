package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BugAdventureController {
    @FXML
    private Label infoText;

    @FXML
    protected void onPlayButtonClick() {
        infoText.setText("Let's get to it!");
    }

    @FXML
    protected void onHelpButtonClick() {
        infoText.setText("Needing help?");
    }

    @FXML
    protected void onExitButtonClick() {
        infoText.setText("Leaving so soon?");
    }
}
