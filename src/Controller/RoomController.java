package Controller;

import Model.Bugs.Bug;
import Model.Bugs.HeroInventory;
import Model.Items.Item;
import Model.Model;
import Model.Room;
import View.ResourceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RoomController extends Controller {
    private final Utility myUtility = new Utility();
    private static final String GAME_OVER_PATH = "../View/GameOver.fxml";
    private static final String WIN_PATH = "../View/Win.fxml";
    private static final String IMAGE_PATH = "src/View/Resources/";
    private static final String PNG = ".png";

    @FXML
    AnchorPane myAnchorPane, myItemPane;

    @FXML
    SubScene myItemScene;

    @FXML
    HBox myItemHBox;

    @FXML
    ImageView myNorthDoorClosed, mySouthDoorClosed, myEastDoorClosed, myWestDoorClosed;

    @FXML
    ImageView myNorthDoor, mySouthDoor, myEastDoor, myWestDoor, myHeroImage, myMonsterImage;

    @FXML
    Label myDialogueLabel, myHealthLabel, myMonsterHealthLabel;

    @FXML
    TextArea myDialogue;

    @FXML
    Button mySaveButton, myNorthButton, mySouthButton, myEastButton, myWestButton, myRunButton, myAttackButton, mySpecialAttackButton, myItemButton, myCancelItemsButton;

    @FXML
    private void initialize() {
        setKeyListeners();
        setDoors(Model.getDungeon().getCurrent());
        disableMoveButtons();
        setHeroHealth();
        myItemButton.setDisable(false);
        myDialogue.setText("");
        myHeroImage.setImage(ResourceManager.getCharacterImage(Model.getHero().getHeroType()));

        if(Model.currentHasMonster()) {
            mySaveButton.setDisable(true);
            monsterAppears();
        } else {
            myMonsterImage.setImage(null);
            itemPhase();
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
            } else if(e.getCode().equals(KeyCode.W) && Model.currentHasMonster() && Model.getCurrentMonster().isAlive()) {
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
                int damage = Model.getHero().specialAttack(Model.getCurrentMonster());
                myDialogue.appendText(Model.getHero().getName());
                myDialogue.appendText(" used ");
                myDialogue.appendText(Model.getHero().getSpecialAttack().getName());
                if(damage == 0) {
                    myDialogue.appendText("\n");
                    myDialogue.appendText(Model.getHero().getName());
                    myDialogue.appendText(" missed!\n");
                } else {
                    myDialogue.appendText("\nIt dealt ");
                    myDialogue.appendText(Integer.toString(Model.getHero().specialAttack(Model.getCurrentMonster())));
                    myDialogue.appendText(" points of damage!\n");
                    setMonsterHealth();
                }
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
        if(!myItemButton.isDisable()) {
            enableToolBar(false);
            myDialogue.appendText("Select an item\n");
            myItemPane.setVisible(true);
            Button useItemButton;
            VBox itemVBox;
            myItemHBox.getChildren().clear();

            for (HeroInventory.ITEM_KEY item : Model.getHero().getInventory().getItems().keySet()) {
                useItemButton = new Button(item.getItem().getName());
                Label itemLabel = new Label(Integer.toString(Model.getHero().getInventory().getItems().get(item)));
                useItemButton.setOnAction(e -> onUseItemPress(e, item.getItem(), itemLabel));
                itemVBox = new VBox();
                itemVBox.getChildren().addAll(useItemButton, itemLabel);
                myItemHBox.getChildren().add(itemVBox);
            }
        }
    }

    private void onUseItemPress(final ActionEvent theEvent, final Item theItem, final Label theItemLabel) {
        Button itemButton = (Button)(theEvent.getSource());
        if(!theItem.isFriendly() && Model.currentHasMonster()) {
            useItem(itemButton, theItem, theItemLabel, Model.getCurrentMonster());
        } else if(!theItem.isFriendly()) {
            myDialogue.appendText("The Item cannot be used at the moment\n");
            itemButton.setDisable(true);
        } else {
            useItem(itemButton, theItem, theItemLabel, Model.getHero());
        }
    }

    private void useItem(final Button theButton, final Item theItem, final Label theItemLabel, final Bug theBug) {
        Model.getHero().getInventory().useItem(theItem, theBug);
        int amount = Integer.parseInt(theItemLabel.getText()) - 1;
        myDialogue.appendText(theItem.getMessage());
        myDialogue.appendText("\n");
        if(amount < 1) {
            VBox vbox = (VBox)theButton.getParent();
            HBox hbox = (HBox)vbox.getParent();
            hbox.getChildren().remove(vbox);
            myDialogue.appendText(Model.getHero().getName());
            myDialogue.appendText(" ran out of ");
            myDialogue.appendText(theItem.getName());
            myDialogue.appendText("'s");
        } else {
            theItemLabel.setText(Integer.toString(amount));
        }
    }

    @FXML
    private void onCancelItemsButtonClick() {
        myItemPane.setVisible(false);
        enableToolBar(true);
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


    /**
     * Based on the boolean array theRoom.getDoors(), set the north, south, east and west
     * doors to be visible or not, then we can determine the north, south,
     * east or west buttons should be visible based on if the doors are visible
     *
     * */
    private void setDoors(final Room theRoom) {
        myNorthDoor.setVisible(theRoom.hasNorth());
        myNorthDoorClosed.setVisible(!theRoom.hasNorth());
        mySouthDoor.setVisible(theRoom.hasSouth());
        mySouthDoorClosed.setVisible(!theRoom.hasSouth());
        myEastDoor.setVisible(theRoom.hasEast());
        myEastDoorClosed.setVisible(!theRoom.hasEast());
        myWestDoor.setVisible(theRoom.hasWest());
        myWestDoorClosed.setVisible(!theRoom.hasWest());
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
        myDialogue.appendText(Model.getCurrentMonster().getName());
        if(damage == 0) {
            myDialogue.appendText(" missed!\n");
        } else {
            myDialogue.appendText(" dealt ");
            myDialogue.appendText(Integer.toString(damage));
            myDialogue.appendText(" points of damage!\n");
        }
        if(!Model.getHero().isAlive()) {
            heroDies();
        }
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

    private void monsterAppears() {
        myDialogue.appendText("A " + Model.getCurrentMonster().getName() + " has appeared!\n");
        myUtility.appendToBuilder(Integer.toString(Model.getCurrentMonster().getHealth()));
        myUtility.appendToBuilder("/");
        myUtility.appendToBuilder(Integer.toString(Model.getCurrentMonster().getOriginalHealth()));
        myMonsterHealthLabel.setText(myUtility.builderToStringClear());
        myMonsterImage.setImage(ResourceManager.getMonsterImage(Model.getCurrentMonster().getName()));
        setMonsterHealth();
        if(Model.getHero().getSpeed() < Model.getCurrentMonster().getSpeed()) {
            monsterAttacksFirst();
        } else {
            heroAttacksFirst();
        }
    }

    private void monsterDies() {
        myDialogue.appendText(Model.getHero().getName());
        myDialogue.appendText(" defeated the ");
        myDialogue.appendText(Model.getCurrentMonster().getName());
        myDialogue.appendText("\n");
        Model.monsterDied();
        if(Model.currentHasMonster()) {
            Model.nextMonster();
            monsterAppears();
        }
        if(Model.getDungeon().inExit()) {
            nextScene(getStage(), WIN_PATH);
        }
        itemPhase();
    }

    private void heroDies() {
        nextScene(getStage(), GAME_OVER_PATH);
    }

    private void itemPhase() {
        if(Model.currentHasItem()) {
            while(Model.currentHasItem()) {
                Item i = Model.getItem();
                myDialogue.appendText(Model.getHero().getName());
                myDialogue.appendText(" found a ");
                myDialogue.appendText(i.getName());
                Model.getHero().pickUpItem(i);
                myDialogue.appendText("\n");
            }
        }
        movementPhase();
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
