package Model.Rooms;

import java.util.ArrayList;

/**
 * The abstract type Room.
 */
public abstract class Room {

    enum Door {
        NORTH("*-*"),
        SOUTH("*-*"),
        EAST("|"),
        WEST("|");

        private String myDoorType;

        Door(final String theDoorType) {
            myDoorType = theDoorType;
        }
    }

    enum Wall {
        NORTH("***"),
        SOUTH("***"),
        EAST("I"),
        WEST("I");

        private String myWallType;

        Wall(final String theWallType) {
            myWallType = theWallType;
        }
    }


    private static final int MAX_DOORS = 4;
    private static final int MAX_WALLS = 3;


    private Door[] myDoors;
    private Wall[] myWalls;

    private ArrayList<Object> myContents;

    public Room(Door[] theDoors, Wall[] theWalls, ArrayList<Object> theContents) {
        if(theDoors.length > MAX_DOORS) {
            throw new IllegalArgumentException("A room cannot have more than " + MAX_DOORS + " doors, the passed array contains " + theDoors.length + " doors");
        }
        if(theWalls.length > MAX_WALLS) {
            throw new IllegalArgumentException("A room cannot have more than " + MAX_WALLS + " walls, the passed array contains " + theWalls.length + " walls");
        }
        myDoors = theDoors;
        myWalls = theWalls;
        myContents = theContents;
    }

    /**
     * Checks if the room contains the object.
     *
     * @param theObject the object
     * @return the boolean
     */
    public boolean contains(final Object theObject) {
        return myContents.contains(theObject);
    }
}