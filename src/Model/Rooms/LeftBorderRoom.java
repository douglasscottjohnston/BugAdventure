package Model.Rooms;

import java.util.ArrayList;

public class LeftBorderRoom extends Room {
    private static Door myNorth = Door.NORTH;
    private static Door mySouth = Door.SOUTH;
    private static Door myEast = Door.EAST;
    private static Wall myWest = Wall.WEST;

    public LeftBorderRoom(final ArrayList<Object> theContents) {
        super(myNorth, mySouth, myEast, myWest, theContents);
    }
}
