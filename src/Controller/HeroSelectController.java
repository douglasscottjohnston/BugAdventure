package Controller;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
            Model.createDungeon();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Room.fxml"));
                Parent parent = loader.load();
                Stage stage = (Stage) ((Node) theEvent.getSource()).getScene().getWindow();
                setStage(stage);
                Scene scene = new Scene(parent);
                scene.addEventFilter(KeyEvent.KEY_PRESSED, ((RoomController)loader.getController()).getKeyHandler());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
