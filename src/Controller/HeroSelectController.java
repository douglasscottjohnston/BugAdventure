package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class HeroSelectController extends Controller {

    @FXML
    private RadioButton myLadyBug, myPillBug, myAnt;

    @FXML
    private TextField myHeroName;

    @FXML
    private Label myNameWarning, myHeroWarning;

    @FXML
    private void onOKButtonClick(final ActionEvent theEvent) {
        //get the name from the name box
        //get the hero selection from radio button list
        //pass the hero to the model using Model.createDungeon(theHero)

        myNameWarning.setVisible(false);
        myHeroWarning.setVisible(false);
        String heroName = myHeroName.getText().strip();
        boolean goToNext = false;

        if(heroName.isBlank()) {
            myNameWarning.setVisible(true);
        } else {
            if(myLadyBug.isSelected()) {
                Model.createLadyBug(heroName);
                goToNext = true;
            } else if(myPillBug.isSelected()) {
                Model.createPillBug(heroName);
                goToNext = true;
            } else if(myAnt.isSelected()) {
                Model.createAnt(heroName);
                goToNext = true;
            } else {
                myHeroWarning.setVisible(true);
            }
        }

        //if a name was entered and a hero was selected go to the next scene
        if(goToNext) {
            Model.createDungeon();
            nextScene(theEvent, "../View/Room.fxml");

        }
    }

    @FXML
    private void displayHero(final ActionEvent theEvent) {
        if(myLadyBug.isSelected()) {
            //display the ladybug sprite
        } else if(myPillBug.isSelected()) {
            //display the pillbug sprite
        } else if(myAnt.isSelected()) {
            //display the ant sprite
        }
    }
}
