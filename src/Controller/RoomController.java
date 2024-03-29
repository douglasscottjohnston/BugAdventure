package Controller;

import Model.Bugs.Bug;
import Model.Bugs.HeroInventory;
import Model.Items.Item;
import Model.Model;
import Model.Room;
import View.ResourceManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The type Room controller.
 */
public class RoomController extends Controller {
    private final Utility myUtility = new Utility();
    private static final String GAME_OVER_PATH = "/View/GameOver.fxml";
    private static final String WIN_PATH = "/View/Win.fxml";

    @FXML
    AnchorPane myAnchorPane, myItemPane;
    @FXML
    SubScene myItemScene;
    @FXML
    HBox myItemHBox;
    @FXML
    ImageView myNorthDoorClosed, mySouthDoorClosed, myEastDoorClosed, myWestDoorClosed;
    @FXML
    ImageView myNorthDoor, mySouthDoor, myEastDoor, myWestDoor, myHeroImage, myMonsterImage, myItemView;
    @FXML
    Label myDialogueLabel,
    myHealthLabel,
    myMonsterHealthLabel;
    @FXML
    TextArea myDialogue;

    @FXML
    Button mySaveButton, myNorthButton, mySouthButton, myEastButton, myWestButton, myRunButton, myAttackButton, mySpecialAttackButton, myItemButton, myCancelItemsButton;

    /**
     * initialize the dungeon.
     */
    @FXML
    private void initialize() {
        setDoors(Model.getDungeon().getCurrent());
        disableMoveButtons();
        setHeroHealth();
        myItemView.setVisible(false);
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

    /**
     * Gets key handler.
     *
     * @return the key handler
     */
    public EventHandler<KeyEvent> getKeyHandler() {
        return theEvent -> {
            switch(theEvent.getCode()) {
            case UP -> {
                if(myNorthButton.isVisible()) {
                    move(Directions.Direction.NORTH);
                }
            }
            case DOWN -> {
                if(mySouthButton.isVisible()) {
                    move(Directions.Direction.SOUTH);
                }
            }
            case LEFT -> {
                if(myWestButton.isVisible()) {
                    move(Directions.Direction.WEST);
                }
            }
            case RIGHT -> {
                if(myEastButton.isVisible()) {
                    move(Directions.Direction.EAST);
                }
            }
            case K -> heroDies();
            case W -> {
                if(Model.currentHasMonster() && Model.getCurrentMonster().isAlive()) {
                    Model.getCurrentMonster().setHealth(0);
                    monsterDies();
                }
            }
            case P -> Model.getDungeon().printExitPath();
            case E -> {
                Model.getDungeon().moveToEntrance();
                System.out.println("Moved hero to entrance");
                initialize();
            }


            }
        };
    }

    /**
     * action for when Attack button is pressed.
     */
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

    /**
     * action for when special attack button is pressed
     */
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

    /**
     * action for when item button is pressed.
     */
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
                useItemButton = new Button();
                ImageView buttonGraphic = new ImageView(ResourceManager.getItemImage(item.getItem().getName()));
                buttonGraphic.setFitHeight(50);
                buttonGraphic.setFitWidth(50);
                useItemButton.setGraphic(buttonGraphic);
                Label itemLabel = new Label(Integer.toString(Model.getHero().getInventory().getItems().get(item)));
                useItemButton.setOnAction(e -> onUseItemPress(e, item.getItem(), itemLabel));
                itemVBox = new VBox();
                itemVBox.getChildren().addAll(useItemButton, itemLabel);
                itemVBox.setAlignment(Pos.CENTER);
                myItemHBox.getChildren().add(itemVBox);
            }
        }
    }

    /**
     * action on when the use item button is pressed
     * @param theEvent what event
     * @param theItem what item
     * @param theItemLabel the item label
     */
    private void onUseItemPress(final ActionEvent theEvent, final Item theItem, final Label theItemLabel) {
        Button itemButton = (Button)(theEvent.getSource());
        if(!theItem.isFriendly() && Model.currentHasMonster()) {
            useItem(itemButton, theItem, theItemLabel, Model.getCurrentMonster());
            if(!Model.getCurrentMonster().isAlive()) {
                monsterDies();
            }
            setMonsterHealth();
        } else if(!theItem.isFriendly()) {
            myDialogue.appendText("The Item cannot be used at the moment\n");
            itemButton.setDisable(true);
        } else {
            useItem(itemButton, theItem, theItemLabel, Model.getHero());
            setHeroHealth();
        }
    }

    /**
     * What happens when an item is used
     * @param theButton
     * @param theItem
     * @param theItemLabel
     * @param theBug
     */
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

    /**
     * action for the cancel item button.
     */
    @FXML
    private void onCancelItemsButtonClick() {
        myItemPane.setVisible(false);
        enableToolBar(true);
    }

    /**
     * action for the run button. =
     */
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

    /**
     * action for the save button.
     */
    @FXML
    private void onSaveButtonClick() {
        Model.save();
        myDialogue.appendText("Game Saved\n");
    }
    /**
     * action for the north button
     */
    @FXML
    private void onNorthButtonClick() {
        move(Directions.Direction.NORTH);
    }
    /**
     * action for the south button
     */
    @FXML
    private void onSouthButtonClick() {
        move(Directions.Direction.SOUTH);
    }
    /**
     * action for the east button
     */
    @FXML
    private void onEastButtonClick() {
        move(Directions.Direction.EAST);
    }
    /**
     * action for the west button
     */
    @FXML
    private void onWestButtonClick() {
        move(Directions.Direction.WEST);
    }

    /**
     * method for moving between rooms.
     * @param theDirection
     */
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

    /**
     * set the move buttons to be visible.
     * @param theRoom
     */
    private void setMoveButtons(final Room theRoom) {
        myDialogue.appendText("Select a direction to move in\n");
        myNorthButton.setVisible(theRoom.hasNorth());
        mySouthButton.setVisible(theRoom.hasSouth());
        myEastButton.setVisible(theRoom.hasEast());
        myWestButton.setVisible(theRoom.hasWest());
    }

    /**
     * set the move buttons to disabled.
     */
    private void disableMoveButtons() {
        myNorthButton.setVisible(false);
        mySouthButton.setVisible(false);
        myEastButton.setVisible(false);
        myWestButton.setVisible(false);
    }

    /**
     * method for when the monster attacks first.
     */
    private void monsterAttacksFirst() {
        myDialogue.appendText("The ");
        myDialogue.appendText(Model.getCurrentMonster().getName());
        myDialogue.appendText(" is faster so it attacks first!\n");
        monsterAttack();
    }

    /**
     * This method is the functionality of the monsters attacks.
     */
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

    /**
     * This method is for when the hero is faster and attacks first.
     */
    private void heroAttacksFirst() {
        myDialogue.appendText(Model.getHero().getName());
        myDialogue.appendText(" was faster than the ");
        myDialogue.appendText(Model.getCurrentMonster().getName());
        myDialogue.appendText(" so ");
        myDialogue.appendText(Model.getHero().getName());
        myDialogue.appendText(" attacks first\nSelect an option\n");
        enableToolBar(true);
    }

    /**
     * Phase for when a monster has appeared. calls either the hero or monster attack first method.
     */
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

    /**
     * this method deals with what happens when the moster dies.
     */
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

    /**
     * game over method if the hero dies.
     */
    private void heroDies() {
        nextScene(getStage(), GAME_OVER_PATH);
    }

    /**
     * Item phase allows user to use item before combat.
     */
    private void itemPhase() {
        if(Model.currentHasItem()) {
            while(Model.currentHasItem()) {
                Item i = Model.getItem();
                myItemView.setImage(ResourceManager.getItemImage(i.getName()));
                myItemView.setVisible(true);
                myDialogue.appendText(Model.getHero().getName());
                myDialogue.appendText(" found a ");
                myDialogue.appendText(i.getName());
                Model.getHero().pickUpItem(i);
                myDialogue.appendText("\n");
            }
        }
        movementPhase();
    }

    /**
     * this method activates when the user is moving.
     */
    private void movementPhase() {
        myMonsterHealthLabel.setText("");
        myMonsterImage.setImage(null);
        setMoveButtons(Model.getDungeon().getCurrent());
        mySaveButton.setDisable(false);
        enableToolBar(false);
        myItemButton.setDisable(false);
    }

    /**
     * this method enables the tool bar.
     * @param theValue
     */
    private void enableToolBar(final boolean theValue) {
        myAttackButton.setDisable(!theValue);
        myRunButton.setDisable(!theValue);
        mySpecialAttackButton.setDisable(!theValue);
        myItemButton.setDisable(!theValue);
    }

    /**
     * text display for setting the heroes health.
     */
    private void setHeroHealth() {
        myUtility.appendToBuilder(Model.getHero().getName());
        myUtility.appendToBuilder("'s Health: ");
        myUtility.appendToBuilder(Integer.toString(Model.getHero().getHealth()));
        myUtility.appendToBuilder("/");
        myUtility.appendToBuilder(Integer.toString(Model.getHero().getOriginalHealth()));
        myUtility.appendToBuilder(" Defense: ");
        myUtility.appendToBuilder(Integer.toString(Model.getHero().getDefense()));
        myUtility.appendToBuilder(" Speed: ");
        myUtility.appendToBuilder(Integer.toString(Model.getHero().getSpeed()));
        myHealthLabel.setText(myUtility.builderToStringClear());
    }

    /**
     * text display for setting the monsters health.
     */
    private void setMonsterHealth() {
        myUtility.appendToBuilder(Integer.toString(Model.getCurrentMonster().getHealth()));
        myUtility.appendToBuilder("/");
        myUtility.appendToBuilder(Integer.toString(Model.getCurrentMonster().getOriginalHealth()));
        myMonsterHealthLabel.setText(myUtility.builderToStringClear());
    }
}
