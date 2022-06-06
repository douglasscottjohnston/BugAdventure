package Model;

import Controller.Directions;
import Model.Bugs.HeroBug;
import Model.Factories.MonsterFactory;
import Model.Factories.RoomFactory;

import java.io.Serializable;
import java.util.LinkedList;


/**
 * The type Dungeon.
 *
 * The dungeon class is a graph which organises rooms in the dungeon.
 * The graph is represented by an adjacency list where the list (myListRep) holds all
 * the nodes in the graph and each node (Room) holds a reference to the four other
 * nodes that it has edges connecting to. A new dungeon is randomly generated every
 * time the dungeon class is instantiated.
 *
 * @author Douglas Johnston
 * @version 1.2
 */
public class Dungeon implements Serializable, Directions {
    //constants
    private static final int NUM_ROOMS_LOW = 10;
    private static final int NUM_ROOMS_HIGH = 30;
    private static final int BRANCH_CHANCE_LOW = 0;
    private static final int BRANCH_CHANCE_HIGH = 4;
    private static final int BRANCH_LEN_LOW = 3;
    private static final int BRANCH_LEN_HIGH = 10;

    //rooms
    private final Room myEntrance;
    private Room myCurrent;
    private Room myExit;

    //msc.
    private final HeroBug myHero;
    private final ModelUtility myUtility;
    private final RoomFactory myRoomFactory;
    private LinkedList<Room> myExitPath;
    private String myExitString;
    private int myNumRooms;

    /**
     * Instantiates a new Dungeon.
     *
     * @param theHero the hero
     */
    public Dungeon(final HeroBug theHero) {
        myHero = theHero;
        RoomContentsList entContents = new RoomContentsList();
        entContents.addHero(theHero);
        myEntrance = new Room(entContents);
        myCurrent = myEntrance;
        myExit = myEntrance;
        myUtility = new ModelUtility();
        myRoomFactory = new RoomFactory();
        createRandomDungeon();
    }

    /**
     * Moves the hero in the direction it is passed.
     *
     * @param theDirection the direction to move in
     */
    public void moveInDirection(final Direction theDirection) {
        switch (theDirection) {
            case NORTH -> moveNorth();
            case SOUTH -> moveSouth();
            case EAST -> moveEast();
            case WEST -> moveWest();
        }
    }

    /**
     * Gets the current room the hero is in.
     *
     * @return the current room
     */
    public Room getCurrent() {
        return myCurrent;
    }

    /**
     * Gets the entrance room
     *
     * @return the entrance room
     */
    public Room getEntrance() {
        return myEntrance;
    }

    /**
     * Returns the hero bug
     *
     * @return the hero bug
     */
    public HeroBug getHero() {
        return myHero;
    }

    /**
     * Returns true if the current room the hero is in is the exit room.
     *
     * @return true if the hero is in the exit room, false otherwise
     */
    public boolean inExit() {
        return myExit.getContents().containsHero();
    }

    public void moveToEntrance() {
        myCurrent.getContents().removeHero();
        myEntrance.getContents().addHero(myHero);
        myCurrent = myEntrance;
    }

    public void printExitPath() {
        System.out.println("The path to the exit is: ");
        System.out.println(myExitString);
    }

    public String getExitPathString() {
        return myExitString;
    }

    /**
     * Creates the randomly generated dungeon
     */
    private void createRandomDungeon() {
        createPathToExit();
        createRandomBranches();
    }

    /**
     * Creates the path from the entrance to the exit.
     * This guarantees that there is a path to the exit.
     * The path is a third of the number of rooms
     */
    private void createPathToExit() {
        MonsterFactory mf = new MonsterFactory();
        myNumRooms = myUtility.getRandomInRange(NUM_ROOMS_LOW, NUM_ROOMS_HIGH);
        myExitPath = new LinkedList<>();
        for (int i = 0; i < myNumRooms/3; i++) {
            myExitPath.add(myExit);
            if(myExit.hasMaxDoors()) {
                myExit = myRoomFactory.makeRoomInRandomDirection(myExit);
            } else {
                myExit = myExit.getNorth();
            }
            myUtility.appendToBuilder(directionMovedToString(myExit));
            myUtility.appendToBuilder(",");
        }
        myExit.getContents().addMonster(mf.makeMosster());
        System.out.println(myExit.getContents());

        myUtility.getStringBuilder().deleteCharAt(myUtility.getStringBuilder().lastIndexOf(","));
        myExitString = myUtility.builderToStringClear();
    }

    /**
     * Helper method that returns a string of the direction the exit was moved in
     * @param theExit the exit room
     * @return A string of the direction the exit was moved in
     */
    private String directionMovedToString(final Room theExit) {
        if (theExit.hasNorth()) {
            return "South";
        }

        if(theExit.hasSouth()) {
            return "North";
        }

        if(theExit.hasEast()) {
            return "West";
        }

        return "East";
    }

    /**
     * Creates random branches along the path to the exit
     */
    private void createRandomBranches() {
        int branchChance;

        for (Room room : myExitPath) {
            branchChance = myUtility.getRandomInRange(BRANCH_CHANCE_LOW, BRANCH_CHANCE_HIGH);

            if(branchChance >= (BRANCH_CHANCE_HIGH + BRANCH_CHANCE_LOW) / 2) {
                createRandomBranchInRandomDirections(room, myUtility.getRandomInRange(BRANCH_LEN_LOW, BRANCH_LEN_HIGH));
            }
        }
    }

    private void createRandomBranchInRandomDirections(final Room theStart, final int theLen) {
        Room curr = theStart;
        for (int i = 0; i < theLen; i++) {
            curr = myRoomFactory.makeRoomInRandomDirection(curr);
        }
    }

    // commented out for potential future use
//    private Room createBranchInDirection(final Room theStart, final int theLen, final Directions theDirection) {
//        Room curr = theStart;
//
//        for (int i = 0; i < theLen; i++) {
//            curr = generateRoomInDirection(curr, theDirection);
//        }
//
//        return curr;
//    }

    private void moveNorth() {
        myCurrent.getContents().removeHero();
        myCurrent = myCurrent.getNorth();
        myCurrent.getContents().addHero(myHero);
        System.out.println(myCurrent.toString() + " N");
    }

    private void moveSouth() {
        myCurrent.getContents().removeHero();
        myCurrent = myCurrent.getSouth();
        myCurrent.getContents().addHero(myHero);
        System.out.println(myCurrent.toString() + " S");
    }

    private void moveEast() {
        myCurrent.getContents().removeHero();
        myCurrent = myCurrent.getEast();
        myCurrent.getContents().addHero(myHero);
        System.out.println(myCurrent.toString() + " E");
    }

    private void moveWest() {
        myCurrent.getContents().removeHero();
        myCurrent = myCurrent.getWest();
        myCurrent.getContents().addHero(myHero);
        System.out.println(myCurrent.toString() + " W");
    }
}