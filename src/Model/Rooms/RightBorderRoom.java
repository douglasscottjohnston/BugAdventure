package Model.Rooms;

import java.util.ArrayList;

public class RightBorderRoom extends Room {
    private static Door myNorth = Door.NORTH;
    private static Door mySouth = Door.SOUTH;
    private static Wall myEast = Wall.EAST;
    private static Door myWest = Door.WEST;

    public RightBorderRoom(final ArrayList<Object> theContents) {
        super(myNorth, mySouth, myEast, myWest, theContents);
    }
}
