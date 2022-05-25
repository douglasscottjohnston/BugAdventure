package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class HeroSelectController {

    @FXML
    private RadioButton myLadyBug, myPillBug, myAnt;

    @FXML
    private TextField myHeroName;

    @FXML
    private Label myNameWarning, myHeroWarning;

    @FXML
    private void onOKButtonClick(ActionEvent event) {
        //get the name from the name box
        //get the hero selection from radio button list
        //pass the hero to the model using Model.createDungeon(theHero)

        myNameWarning.setVisible(false);
        myHeroWarning.setVisible(false);

        if(myHeroName.getText().strip().isBlank()) {
            myNameWarning.setVisible(true);
        } else {
            if(myLadyBug.isSelected()) {
                Model.createLadyBug(myHeroName.getText().strip());
            } else if(myPillBug.isSelected()) {
                Model.createPillBug(myHeroName.getText().strip());
            } else if(myAnt.isSelected()) {
                Model.createAnt(myHeroName.getText().strip());
            } else {
                myHeroWarning.setVisible(true);
            }
        }


        // to stage the next scene
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


    }

    @FXML
    private void displayHero(ActionEvent event) {
        if(myLadyBug.isSelected()) {
            //display the ladybug sprite
        } else if(myPillBug.isSelected()) {
            //display the pillbug sprite
        } else if(myAnt.isSelected()) {
            //display the ant sprite
        }
    }
}
