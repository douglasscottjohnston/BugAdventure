package Model;

import Model.Bugs.MonsterBug;
import Model.Bugs.Spider;

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
    private final boolean[] myDoors;

    private final ArrayList<Object> myContents;
    private final Utility myUtility;

    public Room(ArrayList<Object> theContents) {
        myContents = theContents;
        myDoors = generateMyDoors();
        myUtility = new Utility();
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

    public boolean containsMonster() {
        for (Object o : myContents) {
            if (o instanceof MonsterBug) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Object> getContents() {
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

    public MonsterBug getMonster() {
        if(!containsMonster()) {
            throw myUtility.getNewIllegal("The room does not contain a monster " + myContents.toString());
        }
        MonsterBug out = new Spider(); //this is bad, we should think of a better way to return the monster in the list

        for (Object o : myContents) {
            if(o instanceof MonsterBug) {
                out = (MonsterBug)o;
            }
        }
        return out;
    }
}