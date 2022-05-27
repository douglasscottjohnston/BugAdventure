package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HeroSelectController extends Controller {

    @FXML
    private RadioButton myLadyBug, myPillBug, myAnt;

    @FXML
    private TextField myHeroName;

    @FXML
    private ImageView myHeroImage;

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
        FileInputStream heroFile = null;
        try {
            if(myLadyBug.isSelected()) {
                heroFile = new FileInputStream("src/View/Resources/ladybug.png");
            } else if(myPillBug.isSelected()) {
                heroFile = new FileInputStream("src/View/Resources/pillbug.png");
            } else if(myAnt.isSelected()) {
                heroFile = new FileInputStream("src/View/Resources/ant.png");
            }
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        myHeroImage.setImage(new Image(heroFile));
    }
}
