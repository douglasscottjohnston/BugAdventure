package Controller;

import Model.Bugs.MonsterBug;
import Model.Model;
import Model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RoomController extends Controller {
    private final Utility myUtility = new Utility();
    private static final String GAME_OVER_PATH = "../View/GameOver.fxml";

    @FXML
    AnchorPane myAnchorPane;

    @FXML
    ImageView myNorthDoor, mySouthDoor, myEastDoor, myWestDoor, myHeroImage, myMonsterImage;

    @FXML
    Label myDialogueLabel, myHealthLabel, myMonsterHealthLabel;

    @FXML
    TextArea myDialogue;

    @FXML
    Button mySaveButton, myNorthButton, mySouthButton, myEastButton, myWestButton, myRunButton, myAttackButton, mySpecialAttackButton, myItemButton;

    @FXML
    private void initialize() {
        setDoors(Model.getDungeon().getCurrent());
        disableMoveButtons();
        setHeroHealth();
        myItemButton.setDisable(false);
        myDialogue.setText("");
        try {
            myHeroImage.setImage(new Image(new FileInputStream(Model.getHero().getImagePath())));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        if(Model.currentHasMonster()) {
            mySaveButton.setDisable(true);
            MonsterBug monster = Model.getCurrentMonster();
            monsterAppears(monster);
            setMonsterHealth();
            if(Model.getHero().getSpeed() < Model.getCurrentMonster().getSpeed()) {
                monsterAttacksFirst();
            } else {
                heroAttacksFirst();
            }
        } else {
            myMonsterImage.setImage(null);
            setMoveButtons(Model.getDungeon().getCurrent());
        }
    }

    @FXML
    private void onAttackButtonPress(final ActionEvent theEvent) {
        if(!myAttackButton.isDisable()) {
            if(Model.getCurrentMonster().isAlive()) {
                myUtility.appendToBuilder(Model.getHero().getName());
                myUtility.appendToBuilder(" dealt ");
                myUtility.appendToBuilder(Integer.toString(Model.getHero().attack(Model.getCurrentMonster())));
                myUtility.appendToBuilder(" points of damage!\n");
                myDialogue.appendText(myUtility.builderToStringClear());
                setMonsterHealth();
                if(!Model.getCurrentMonster().isAlive()) {
                    monsterDies();
                } else {
                    monsterAttack();
                }
            } else {
                monsterDies();
            }
        }
    }

    @FXML
    private void onSpecialAttackButtonPress(final ActionEvent theEvent) {
        if(!mySpecialAttackButton.isDisable()) {
            if(Model.getCurrentMonster().isAlive()) {
                myUtility.appendToBuilder(Model.getHero().getName());
                myUtility.appendToBuilder(" dealt ");
                myUtility.appendToBuilder(Integer.toString(Model.getHero().specialAttack(Model.getCurrentMonster())));
                myUtility.appendToBuilder(" points of damage!\n");
                myDialogue.appendText(myUtility.builderToStringClear());
                setMonsterHealth();
                if(!Model.getCurrentMonster().isAlive()) {
                    monsterDies();
                } else {
                    monsterAttack();
                }
            } else {
                monsterDies();
            }
        }
    }

    @FXML
    private void onItemButtonPress() {

    }

    @FXML
    private void onRunButtonClick() {
        if(Model.run()) {
            myDialogue.appendText(Model.getHero().getName());
            myDialogue.appendText(" successfully ran away\n");
            movementPhase();
        } else {
            myDialogue.appendText(Model.getHero().getName());
            myDialogue.appendText(" failed to run away\n");
            enableToolBar(false);
            monsterAttack();
        }
    }

    @FXML
    private void onSaveButtonClick() {
        Model.save();
    }



    @FXML
    private void onNorthButtonClick(final ActionEvent theEvent) {
        Model.move(Directions.Direction.NORTH);
        initialize();
    }

    @FXML
    private void onSouthButtonClick(final ActionEvent theEvent) {
        Model.move(Directions.Direction.SOUTH);
        initialize();
    }

    @FXML
    private void onEastButtonClick(final ActionEvent theEvent) {
        Model.move(Directions.Direction.EAST);
        initialize();
    }

    @FXML
    private void onWestButtonClick(final ActionEvent theEvent) {
        Model.move(Directions.Direction.WEST);
        initialize();
    }

    /**
     * Based on the boolean array theRoom.getDoors(), set the north, south, east and west
     * doors to be visible or not, then we can determine the north, south,
     * east or west buttons should be visible based on if the doors are visible
     *
     * */
    private void setDoors(final Room theRoom) {
        myNorthDoor.setVisible(theRoom.hasNorth());
        mySouthDoor.setVisible(theRoom.hasSouth());
        myEastDoor.setVisible(theRoom.hasEast());
        myWestDoor.setVisible(theRoom.hasWest());
    }

    private void setMoveButtons(final Room theRoom) {
        myDialogue.appendText("Select a direction to move in\n");
        myNorthButton.setVisible(theRoom.hasNorth());
        mySouthButton.setVisible(theRoom.hasSouth());
        myEastButton.setVisible(theRoom.hasEast());
        myWestButton.setVisible(theRoom.hasWest());
    }

    private void disableMoveButtons() {
        myNorthButton.setVisible(false);
        mySouthButton.setVisible(false);
        myEastButton.setVisible(false);
        myWestButton.setVisible(false);
    }

    private void monsterAttacksFirst() {
        myDialogue.appendText("The ");
        myDialogue.appendText(Model.getCurrentMonster().getName());
        myDialogue.appendText(" is faster so it attacks first!\n");
        monsterAttack();
    }

    private void monsterAttack() {
        int damage = Model.getCurrentMonster().attack(Model.getHero());
        setHeroHealth();
        myUtility.appendToBuilder(Model.getCurrentMonster().getName());
        myUtility.appendToBuilder(" dealt ");
        myUtility.appendToBuilder(Integer.toString(damage));
        myUtility.appendToBuilder(" points of damage!\n");
        if(!Model.getHero().isAlive()) {
            heroDies();
        }

        myDialogue.appendText(myUtility.builderToStringClear());
        myDialogue.appendText(Model.getHero().getName() + "'s turn\nSelect an option\n");
        enableToolBar(true);
    }

    private void heroAttacksFirst() {
        myDialogue.appendText(Model.getHero().getName());
        myDialogue.appendText(" was faster than the ");
        myDialogue.appendText(Model.getCurrentMonster().getName());
        myDialogue.appendText(" so ");
        myDialogue.appendText(Model.getHero().getName());
        myDialogue.appendText(" attacks first\nSelect an option\n");
        enableToolBar(true);
    }

    private void monsterAppears(MonsterBug theMonster) {
        myDialogue.appendText("A " + theMonster.getName() + " has appeared!\n");
        myUtility.appendToBuilder(Integer.toString(Model.getCurrentMonster().getHealth()));
        myUtility.appendToBuilder("/");
        myUtility.appendToBuilder(Integer.toString(Model.getCurrentMonster().getOriginalHealth()));
        myMonsterHealthLabel.setText(myUtility.builderToStringClear());
        try {
            myMonsterImage.setImage(new Image(new FileInputStream(theMonster.getImagePath())));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void monsterDies() {
        myDialogue.appendText(Model.getHero().getName());
        myDialogue.appendText(" defeated the ");
        myDialogue.appendText(Model.getCurrentMonster().getName());
        myDialogue.appendText("\n");
        movementPhase();
    }

    private void heroDies() {
        nextScene((Stage)myAnchorPane.getScene().getWindow(), GAME_OVER_PATH);
    }

    private void movementPhase() {
        myMonsterHealthLabel.setText("");
        myMonsterImage.setImage(null);
        setMoveButtons(Model.getDungeon().getCurrent());
        mySaveButton.setDisable(false);
        enableToolBar(false);
        myItemButton.setDisable(false);
    }


    private void enableToolBar(final boolean theValue) {
        myAttackButton.setDisable(!theValue);
        myRunButton.setDisable(!theValue);
        mySpecialAttackButton.setDisable(!theValue);
        myItemButton.setDisable(!theValue);
    }

    private void setHeroHealth() {
        myUtility.appendToBuilder(Model.getHero().getName());
        myUtility.appendToBuilder(": ");
        myUtility.appendToBuilder(Integer.toString(Model.getHero().getHealth()));
        myUtility.appendToBuilder("/");
        myUtility.appendToBuilder(Integer.toString(Model.getHero().getOriginalHealth()));
        myHealthLabel.setText(myUtility.builderToStringClear());
    }

    private void setMonsterHealth() {
        myUtility.appendToBuilder(Integer.toString(Model.getCurrentMonster().getHealth()));
        myUtility.appendToBuilder("/");
        myUtility.appendToBuilder(Integer.toString(Model.getCurrentMonster().getOriginalHealth()));
        myMonsterHealthLabel.setText(myUtility.builderToStringClear());
    }
}
