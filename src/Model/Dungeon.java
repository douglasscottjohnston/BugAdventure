package Model;

import Model.Bugs.*;
import Model.Items.Apple;
import Model.Items.Item;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Dungeon {
    private static final int NUM_ROOMS_LOW = 10;
    private static final int NUM_ROOMS_HIGH = 30;
    private static final int CONTAIN_LOW = 0;
    private static final int CONTAIN_HIGH = 4;
    private static final int MONSTER_LOW = 0;
    private static final int MONSTER_HIGH = 3;
    private static final int ITEM_LOW = 0;
    private static final int ITEM_HIGH = 0;
    private Room myEntrance;
    private Room myCurrent;
    private Room myExit;
    private HeroBug myHero;
    private Utility myUtility;
    private ArrayList<ArrayList<Room>> myMatrixRep;
    private int myNumRooms;

    public Dungeon(final HeroBug theHero) {
        myHero = theHero;
        ArrayList<Object> entContents = new ArrayList<>();
        entContents.add(theHero);
        myEntrance = new Room(entContents);
        myCurrent = myEntrance;
        myExit = myEntrance;
        myUtility = new Utility();
        createRandomDungeon();
    }

    public Room getCurrent() {
        return myCurrent;
    }

    private void createRandomDungeon() {
        createPathToExit();
    }

    private void createPathToExit() {
        myNumRooms = myUtility.getRandomInRange(NUM_ROOMS_LOW, NUM_ROOMS_HIGH);
        myMatrixRep = new ArrayList<>();
        ArrayList<Room> columns;

        for (int i = 0; i < myNumRooms; i++) {
            columns = new ArrayList<>();
            myExit = generateRoomInRandomDirection(myExit);
            columns.add(myExit);
            myMatrixRep.add(columns);
        }
    }

    private Room generateRoomInRandomDirection(final Room theTargetRoom) {
        // 0 = North, 1 = South, 2 = East, 3 = West
        int directionNum = myUtility.getRandomInRange(0, 3);
        Room newRoom = generateRandomRoom();

        switch(directionNum){
            case 0:
                theTargetRoom.setNorth(newRoom);
                newRoom.setSouth(theTargetRoom);
            case 1:
                theTargetRoom.setSouth(newRoom);
                newRoom.setNorth(theTargetRoom);
            case 2:
                theTargetRoom.setEast(newRoom);
                newRoom.setWest(theTargetRoom);
            case 3:
                theTargetRoom.setWest(newRoom);
                newRoom.setEast(theTargetRoom);
        }
        return newRoom;
    }

    private Room generateRandomRoom() {
        // 1 in 5 chance of having a random monster, a random item, a pit, nothing,
        // or a random monster and a random item
        ArrayList<Object> contents = new ArrayList<Object>();
        
        switch(myUtility.getRandomInRange(CONTAIN_LOW, CONTAIN_HIGH)) {
            default:
                contents.add(generateRandomMonster());
            case 1:
                contents.add(generateRandomItem());
            case 2:
                contents.add(new Pit());
            case 4:
                contents.add(generateRandomMonster());
                contents.add(generateRandomItem());
        }
        return new Room(contents);
    }

    public void moveNorth() {
        myCurrent = myCurrent.getNorth();
        System.out.println(myCurrent.toString() + "N");
    }

    public void moveSouth() {
        myCurrent = myCurrent.getSouth();
        System.out.println(myCurrent.toString() + "S");
    }

    public void moveEast() {
        myCurrent = myCurrent.getEast();
        System.out.println(myCurrent.toString() + "E");
    }

    public void moveWest() {
        myCurrent = myCurrent.getWest();
        System.out.println(myCurrent.toString() + "W");
    }

    private void addNorthRoom(final Room theTarget, final Room theNorth) {
        theTarget.setNorth(theNorth);
        theNorth.setSouth(theTarget);
    }

    private void addSouthRoom(final Room theTarget, final Room theSouth) {
        theTarget.setSouth(theSouth);
        theSouth.setNorth(theTarget);
    }

    private void addEastRoom(final Room theTarget, final Room theEast) {
        theTarget.setEast(theEast);
        theEast.setWest(theTarget);
    }

    private void addWestRoom(final Room theTarget, final Room theWest) {
        theTarget.setWest(theWest);
        theWest.setEast(theTarget);
    }
    
    private MonsterBug generateRandomMonster() {
        switch(myUtility.getRandomInRange(MONSTER_LOW, MONSTER_HIGH)) {
            default:
                return new Spider();
            case 1:
                return new Centipede();
            case 2:
                return new Maggot();
            case 3:
                return new Mosster();
        }
    }

    private Item generateRandomItem() {
        switch(myUtility.getRandomInRange(ITEM_LOW, ITEM_HIGH)) {
            default:
                return new Apple();
        }
    }
}
