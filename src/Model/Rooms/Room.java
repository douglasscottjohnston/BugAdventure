package Model.Rooms;

import Model.Directions;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The abstract type Room.
 */
public abstract class Room implements Directions {

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

    Hashtable<Direction, Object> directionTable = new Hashtable<>();


    private static final int MAX_DOORS = 4;
    private static final int MAX_WALLS = 3;

    private ArrayList<Object> myContents;

    public Room(final Object theNorth, final Object theSouth, final Object theEast, final Object theWest, ArrayList<Object> theContents) {
        directionTable.put(Direction.NORTH, theNorth);
        directionTable.put(Direction.SOUTH, theSouth);
        directionTable.put(Direction.EAST, theEast);
        directionTable.put(Direction.WEST, theWest);
        myContents = theContents;
    }

    public boolean isDoor(final Direction theDirection) {
        return directionTable.get(theDirection) instanceof Door;
    }

    public Direction getDoor() {
        for (Direction key : directionTable.keySet()) {
            if(directionTable.get(key) instanceof Door) return key;
        }
        return null;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Direction key : directionTable.keySet()) {
            Object val = directionTable.get(key);
            if(val instanceof Wall) {
                Wall wall = (Wall)directionTable.get(key);
                sb.append(wall.values());
            } else {
                Door door = (Door)directionTable.get(key);
                sb.append(door.name());
            }
            sb.append("\n");
        }

        sb.delete(sb.lastIndexOf("\n"), sb.length());

        return sb.toString();
    }
}