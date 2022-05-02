package Model.Rooms;

import java.util.ArrayList;

public class TopBorderRoom extends Room{

    private static Wall myNorth = Wall.NORTH;
    private static Door mySouth = Door.SOUTH;
    private static Door myEast = Door.EAST;
    private static Door myWest = Door.WEST;


    public TopBorderRoom(final ArrayList<Object> theContents) {
        super(myNorth, mySouth, myEast, myWest, theContents);
    }
}
