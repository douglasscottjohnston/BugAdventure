package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class HeroSelectController extends Controller {

    private static final String HERO_IMAGE_PATH = "src/View/Resources/Characters/";

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
            Model.getHero().setHeroImagePath(getHeroPath());
            Model.createDungeon();
            nextScene(theEvent, "../View/Room.fxml");
        }
    }

    private String getHeroPath() {
        if(myLadyBug.isSelected()) {
            return HERO_IMAGE_PATH + "ladybug.png";
        } else if(myPillBug.isSelected()) {
            return HERO_IMAGE_PATH + "pillbug.png";
        } else {
            return HERO_IMAGE_PATH + "ant.png";
        }
    }
}
