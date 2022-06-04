package Controller;

import Model.Bugs.MonsterBug;
import Model.Model;
import Model.Room;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RoomController extends Controller {
    private final Utility myUtility = new Utility();
    private static final String GAME_OVER_PATH = "../View/GameOver.fxml";
    private static final String WIN_PATH = "../View/Win.fxml";
    private static final String IMAGE_PATH = "src/View/Resources/";
    private static final String PNG = ".png";

    @FXML
    AnchorPane myAnchorPane;

    @FXML
    ImageView myNorthDoorOpen, myNorthDoorClosed, mySouthDoorOpen, mySouthDoorClosed, myEastDoorOpen, myEastDoorClosed, myWestDoorOpen, myWestDoorClosed;

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
        setKeyListeners();
        setDoors(Model.getDungeon().getCurrent());
        disableMoveButtons();
        setHeroHealth();
        myItemButton.setDisable(false);
        myDialogue.setText("");
        try {
            System.out.println(System.getProperty("user.dir"));
            myHeroImage.setImage(new Image(new FileInputStream(Model.getHero().getHeroImagePath())));
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

    private void setKeyListeners() {
        System.out.println("listeners set");
        System.out.println(getStage());
        getStage().getScene().addEventFilter(KeyEvent.ANY, e -> {
            if(e.getCode().equals(KeyCode.UP) && myNorthButton.isVisible()) {
                move(Directions.Direction.NORTH);
            } else if(e.getCode().equals(KeyCode.DOWN) && mySouthButton.isVisible()) {
                move(Directions.Direction.SOUTH);
            } else if(e.getCode().equals(KeyCode.RIGHT) && myEastButton.isVisible()) {
                move(Directions.Direction.EAST);
            } else if(e.getCode().equals(KeyCode.LEFT) && myWestButton.isVisible()) {
                move(Directions.Direction.WEST);
            } else if(e.getCode().equals(KeyCode.K)) { //cheats
                heroDies();
            } else if(e.getCode().equals(KeyCode.W) && Model.getCurrentMonster().isAlive()) {
                Model.getCurrentMonster().setHealth(0);
                monsterDies();
            } else if(e.getCode().equals(KeyCode.P)) {
                Model.getDungeon().printExitPath();
            }
                }
        );
    }

    @FXML
    private void onAttackButtonPress() {
        if(!myAttackButton.isDisable()) {
            if(Model.getCurrentMonster().isAlive()) {
                myUtility.appendToBuilder(Model.getHero().getName());
                myUtility.appendToBuilder(" used ");
                myUtility.appendToBuilder(Model.getHero().getAttack().getName());
                myUtility.appendToBuilder("\nIt dealt ");
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
    private void onSpecialAttackButtonPress() {
        if(!mySpecialAttackButton.isDisable()) {
            if(Model.getCurrentMonster().isAlive()) {
                myUtility.appendToBuilder(Model.getHero().getName());
                myUtility.appendToBuilder(" used ");
                myUtility.appendToBuilder(Model.getHero().getSpecialAttack().getName());
                myUtility.appendToBuilder("\nIt dealt ");
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
        myDialogue.appendText("Game Saved\n");
    }



    @FXML
    private void onNorthButtonClick() {
        move(Directions.Direction.NORTH);
    }

    @FXML
    private void onSouthButtonClick() {
        move(Directions.Direction.SOUTH);
    }

    @FXML
    private void onEastButtonClick() {
        move(Directions.Direction.EAST);
    }

    @FXML
    private void onWestButtonClick() {
        move(Directions.Direction.WEST);
    }

    private void move(final Directions.Direction theDirection) {
        Model.move(theDirection);
        initialize();
    }

    private void directionalImage() {
        getStage().getScene().addEventFilter(KeyEvent.ANY, e -> {
            if(!myNorthButton.isVisible()) {
                myNorthDoorClosed.setVisible(true);
            } if (myNorthButton.isVisible()) {
                myNorthDoorClosed.setVisible(false);
            } if (!mySouthButton.isVisible()) {
                    mySouthDoorClosed.setVisible(true);
            } if (mySouthButton.isVisible()) {
                mySouthDoorClosed.setVisible(false);
            } if(!myEastButton.isVisible()) {
                myEastDoorClosed.setVisible(true);
            } if (myEastButton.isVisible()) {
                myEastDoorClosed.setVisible(false);
            } if(!myWestButton.isVisible()) {
                myWestDoorClosed.setVisible(true);
            } if (myWestButton.isVisible()) {
                myWestDoorClosed.setVisible(false);
            }
        });
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
            myMonsterImage.setImage(new Image(new FileInputStream(IMAGE_PATH + theMonster.getName() + PNG)));
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
        if(Model.getDungeon().inExit()) {
            nextScene(getStage(), WIN_PATH);
        }
        movementPhase();
    }

    private void heroDies() {
        nextScene(getStage(), GAME_OVER_PATH);
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
