package Controller;

import Model.Model;
import Model.Room;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class RoomController extends Controller {

    @FXML
    ImageView myNorthDoor, mySouthDoor, myEastDoor, myWestDoor;

    @FXML
    Button myNorthButton, mySouthButton, myEastButton, myWestButton;


    @FXML
    private void initialize() {
        setDoors(Model.getDungeon().getCurrent());
        setButtons(Model.getDungeon().getCurrent());
    }

    @FXML
    private void onRunButtonClick() {
        if(Model.run()) {
            //successfully ran away
        }
    }

    @FXML
    private void onSaveButtonClick() {
        Model.save();
    }



    @FXML
    private void onNorthButtonClick() {
        Model.move(Directions.Direction.NORTH);
        initialize();
    }

    @FXML
    private void onSouthButtonClick() {
        Model.move(Directions.Direction.SOUTH);
        initialize();
    }

    @FXML
    private void onEastButtonClick() {
        Model.move(Directions.Direction.EAST);
        initialize();
    }

    @FXML
    private void onWestButtonClick() {
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

    private void setButtons(final Room theRoom) {
        myNorthButton.setVisible(theRoom.hasNorth());
        mySouthButton.setVisible(theRoom.hasSouth());
        myEastButton.setVisible(theRoom.hasEast());
        myWestButton.setVisible(theRoom.hasWest());
    }
}
