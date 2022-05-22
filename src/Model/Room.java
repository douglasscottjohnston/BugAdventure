package Model;

import Controller.Directions;

import java.io.Serializable;

/**
 * The abstract type Room.
 */
public class Room implements Serializable {
    private Room myNorth;
    private Room mySouth;
    private Room myEast;
    private Room myWest;
    private final boolean[] myDoors;

    private final RoomContentsList myContents;
    private final ModelUtility myUtility;

    public Room(RoomContentsList theContents) {
        myContents = theContents;
        myDoors = generateMyDoors();
        myUtility = new ModelUtility();
    }

    private boolean[] generateMyDoors() {
        boolean[] doors = {false, false, false, false};

        if(myNorth != null) {
            doors[0] = true;
        }

        if(mySouth != null) {
            doors[1] = true;
        }

        if(myEast != null) {
            doors[2] = true;
        }

        if(myWest != null) {
            doors[3] = true;
        }

        return doors;
    }

    public boolean hasMaxDoors() {
        return !hasNorth() || !hasSouth() || !hasEast() || !hasWest();
    }

    public boolean hasNorth() {
        return myNorth != null;
    }

    public boolean hasSouth() {
        return mySouth != null;
    }

    public boolean hasEast() {
        return myEast != null;
    }

    public boolean hasWest() {
        return myWest != null;
    }

    public boolean hasDirection(Directions.Direction direction) {

        switch(direction) {
        case NORTH -> {
            return hasNorth();
        }
        case SOUTH -> {
            return hasSouth();
        }
        case EAST -> {
            return hasEast();
        }
        case WEST -> {
            return hasWest();
        }
        default -> throw myUtility.getNewIllegal(direction + " is not a valid direction");

        }
    }

    public RoomContentsList getContents() {
        return myContents;
    }

    public Room getNorth() {
        return myNorth;
    }

    public Room getSouth() {
        return mySouth;
    }

    public Room getEast() {
        return myEast;
    }

    public Room getWest() {
        return myWest;
    }


    public boolean[] getDoors() {
        return myDoors;
    }

    public void setNorth(final Room theNorth) {
        myNorth = theNorth;
        myDoors[0] = true;
    }

    public void setSouth(final Room theSouth) {
        mySouth = theSouth;
        myDoors[1] = true;
    }

    public void setEast(final Room theEast) {
        myEast = theEast;
        myDoors[2] = true;
    }

    public void setWest(final Room theWest) {
        myWest = theWest;
        myDoors[3] = true;
    }
}