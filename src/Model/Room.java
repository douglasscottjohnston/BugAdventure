package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The abstract type Room.
 */
public class Room implements Serializable {
    private Room myNorth;
    private Room mySouth;
    private Room myEast;
    private Room myWest;
    private Room[] myDirections;
    private int myLabel;
    private boolean[] myDoors;

    private ArrayList<Object> myContents;

    public Room(ArrayList<Object> theContents) {
        myContents = theContents;
        myDirections = new Room[4];
        myDoors = generateMyDoors();
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

    @Override
    public String toString() {
        return "+";
    }

    public boolean contains(final Object theObject) {
        return myContents.contains(theObject);
    }

    public void add(final Object theObject) {
        myContents.add(theObject);
    }

    public void remove(final Object theObject) {
        myContents.remove(theObject);
    }

    public boolean isEmpty() {
        return myContents.isEmpty();
    }

    public boolean hasMaxDoors() {
        return hasNorth() && hasSouth() && hasEast() && hasWest();
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

    public Room[] getDirections() {
        return myDirections;
    }

    public boolean[] getDoors() {
        return myDoors;
    }

    public int getLabel() {
        return myLabel;
    }

    public void setNorth(final Room theNorth) {
        myNorth = theNorth;
        myDirections[0] = myNorth;
        myDoors[0] = true;
    }

    public void setSouth(final Room theSouth) {
        mySouth = theSouth;
        myDirections[1] = mySouth;
        myDoors[1] = true;
    }

    public void setEast(final Room theEast) {
        myEast = theEast;
        myDirections[2] = myEast;
        myDoors[2] = true;
    }

    public void setWest(final Room theWest) {
        myWest = theWest;
        myDirections[3] = myWest;
        myDoors[3] = true;
    }

    public void setLabel(final int theLabel) {
        myLabel = theLabel;
    }
}