package Controller;

import Model.Model;
import Model.Room;
import javafx.fxml.FXML;

public class RoomController {



    @FXML
    private void initialize() {
        setWalls(Model.getDungeon().getCurrent());
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
    private void onLoadButtonClick() {
        Model.load();
    }

    @FXML
    private void onNorthButtonClick() {
        Model.move(Directions.Direction.NORTH);
    }

    @FXML
    private void onSouthButtonClick() {
        Model.move(Directions.Direction.SOUTH);
    }

    @FXML
    private void onEastButtonClick() {
        Model.move(Directions.Direction.EAST);
    }

    @FXML
    private void onWestButtonClick() {
        Model.move(Directions.Direction.WEST);
    }

    private void setWalls(final Room theRoom) {
        /**
         * Based on the boolean array theRoom.getDoors(), set the north, south, east and west
         * doors to be visible or not, then we can determine the north, south,
         * east or west buttons should be visible based on if the doors are visible
         *
         * */
    }
}
