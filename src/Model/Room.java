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

    /**
     * Instantiates a new Room.
     *
     * @param theContents the contents
     */
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

    /**
     * Has max doors boolean.
     *
     * @return the boolean
     */
    public boolean hasMaxDoors() {
        return !hasNorth() || !hasSouth() || !hasEast() || !hasWest();
    }

    /**
     * Has north boolean.
     *
     * @return the boolean
     */
    public boolean hasNorth() {
        return myNorth != null;
    }

    /**
     * Has south boolean.
     *
     * @return the boolean
     */
    public boolean hasSouth() {
        return mySouth != null;
    }

    /**
     * Has east boolean.
     *
     * @return the boolean
     */
    public boolean hasEast() {
        return myEast != null;
    }

    /**
     * Has west boolean.
     *
     * @return the boolean
     */
    public boolean hasWest() {
        return myWest != null;
    }

    /**
     * Has direction boolean.
     *
     * @param direction the direction
     * @return the boolean
     */
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

    /**
     * Gets contents.
     *
     * @return the contents
     */
    public RoomContentsList getContents() {
        return myContents;
    }

    /**
     * Gets north.
     *
     * @return the north
     */
    public Room getNorth() {
        return myNorth;
    }

    /**
     * Gets south.
     *
     * @return the south
     */
    public Room getSouth() {
        return mySouth;
    }

    /**
     * Gets east.
     *
     * @return the east
     */
    public Room getEast() {
        return myEast;
    }

    /**
     * Gets west.
     *
     * @return the west
     */
    public Room getWest() {
        return myWest;
    }


    /**
     * Get doors boolean [ ].
     *
     * @return the boolean [ ]
     */
    public boolean[] getDoors() {
        return myDoors;
    }

    /**
     * Sets north.
     *
     * @param theNorth the the north
     */
    public void setNorth(final Room theNorth) {
        myNorth = theNorth;
        myDoors[0] = true;
    }

    /**
     * Sets south.
     *
     * @param theSouth the the south
     */
    public void setSouth(final Room theSouth) {
        mySouth = theSouth;
        myDoors[1] = true;
    }

    /**
     * Sets east.
     *
     * @param theEast the the east
     */
    public void setEast(final Room theEast) {
        myEast = theEast;
        myDoors[2] = true;
    }

    /**
     * Sets west.
     *
     * @param theWest the the west
     */
    public void setWest(final Room theWest) {
        myWest = theWest;
        myDoors[3] = true;
    }
}