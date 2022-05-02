package Model.Rooms;

import java.util.ArrayList;

public class BottomBorderRoom extends Room {
    private static Door myNorth = Door.NORTH;
    private static Wall mySouth = Wall.SOUTH;
    private static Door myEast = Door.EAST;
    private static Door myWest = Door.WEST;

    public BottomBorderRoom(final ArrayList<Object> theContents) {
        super(myNorth, mySouth, myEast, myWest, theContents);
    }
}