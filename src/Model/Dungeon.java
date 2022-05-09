package Model;

import Model.Bugs.*;
import Model.Items.Apple;
import Model.Items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * The type Dungeon.
 *
 * @author Douglas Johnston
 * @version 1.0  The dungeon class is a graph which organises rooms in the dungeon. The graph is represented by an adjacency list where the list (myListRep) holds all the nodes in the graph and each node (Room) holds a reference to the four other nodes that it has edges connecting to. A new dungeon is randomly generated every time the dungeon class is instantiated.
 */
public class Dungeon implements Serializable {
    //constants
    private static final int NUM_ROOMS_LOW = 10;
    private static final int NUM_ROOMS_HIGH = 30;
    private static final int CONTAIN_LOW = 0;
    private static final int CONTAIN_HIGH = 4;
    private static final int BRANCH_CHANCE_LOW = 0;
    private static final int BRANCH_CHANCE_HIGH = 4;
    private static final int BRANCH_LEN_LOW = 3;
    private static final int BRANCH_LEN_HIGH = 10;
    private static final int MONSTER_LOW = 0;
    private static final int MONSTER_HIGH = 3;
    private static final int ITEM_LOW = 0;
    private static final int ITEM_HIGH = 0;
    private enum Directions {
        NORTH("0"),
        SOUTH("1"),
        EAST("2"),
        WEST("3");

        String myDirection;

        Directions(final String theDirection) {
            myDirection = theDirection;
        }

        static Directions getDirection(final int theDirection) {
            switch (theDirection) {
                case 0 -> {
                    return NORTH;
                }
                case 1 -> {
                    return SOUTH;
                }
                case 2 -> {
                    return EAST;
                }
                case 3 -> {
                    return WEST;
                }
                default -> {
                    throw new IllegalArgumentException(theDirection + " is not a possible direction");
                }
            }
        }
    }

    //rooms
    private final Room myEntrance;
    private Room myCurrent;
    private Room myExit;

    //msc.
    private HeroBug myHero;
    private final Utility myUtility;
    private LinkedList<Room> myExitPath;
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
        myNumRooms = myUtility.getRandomInRange(NUM_ROOMS_LOW, NUM_ROOMS_HIGH);
        myExitPath = new LinkedList<>();
        for (int i = 0; i < myNumRooms/3; i++) {
            myExitPath.add(myExit);
            if(!myExit.hasMaxDoors()) {
                myExit = generateRoomInRandomDirection(myExit);
            } else {
                myExit = myExit.getNorth();
            }
        }
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
            curr = generateRoomInRandomDirection(curr);
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

    /**
     * Creates a room in a random direction of the target room
     *
     * @param theTargetRoom The room to generate a random room on
     * @return The newly generated room
     */
    private Room generateRoomInRandomDirection(final Room theTargetRoom) {
        int directionNum = myUtility.getRandomInRange(0, 3);

        if(!theTargetRoom.hasNorth() && !theTargetRoom.hasSouth() && !theTargetRoom.hasEast() && !theTargetRoom.hasWest()) {
            return generateRoomInDirection(theTargetRoom, Directions.getDirection(directionNum));
        } else if(!theTargetRoom.hasMaxDoors()){
            //generate a new random number until we find a direction without a door, this is really dumb
            while(theTargetRoom.getDoors()[directionNum]) {
                directionNum = myUtility.getRandomInRange(0, 3);
            }
            return generateRoomInDirection(theTargetRoom, Directions.getDirection(directionNum));
        } else { //the room has the max amount of doors, so we can't generate a new room
            throw myUtility.getNewIllegal("The room" + theTargetRoom + " has the maximum amount of doors");
        }
    }

    private Room generateRoomInDirection(final Room theTargetRoom, final Directions theDirection) {
        // 0 = North, 1 = South, 2 = East, 3 = West

        Room newRoom = generateRandomRoom();

        switch(theDirection){
            case NORTH:
                theTargetRoom.setNorth(newRoom);
                newRoom.setSouth(theTargetRoom);
                break;
            case SOUTH:
                theTargetRoom.setSouth(newRoom);
                newRoom.setNorth(theTargetRoom);
                break;
            case EAST:
                theTargetRoom.setEast(newRoom);
                newRoom.setWest(theTargetRoom);
                break;
            case WEST:
                theTargetRoom.setWest(newRoom);
                newRoom.setEast(theTargetRoom);
                break;
        }
        return newRoom;
    }

    private Room generateRandomRoom() {
        // 1 in 5 chance of having a random monster, a random item, a pit, nothing,
        // or a random monster and a random item
        ArrayList<Object> contents = new ArrayList<>();
        
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

    // commented out for potential future use
//    private void addNorthRoom(final Room theTarget, final Room theNorth) {
//        theTarget.setNorth(theNorth);
//        theNorth.setSouth(theTarget);
//    }
//
//    private void addSouthRoom(final Room theTarget, final Room theSouth) {
//        theTarget.setSouth(theSouth);
//        theSouth.setNorth(theTarget);
//    }
//
//    private void addEastRoom(final Room theTarget, final Room theEast) {
//        theTarget.setEast(theEast);
//        theEast.setWest(theTarget);
//    }
//
//    private void addWestRoom(final Room theTarget, final Room theWest) {
//        theTarget.setWest(theWest);
//        theWest.setEast(theTarget);
//    }
    
    private MonsterBug generateRandomMonster() {
        return switch (myUtility.getRandomInRange(MONSTER_LOW, MONSTER_HIGH)) {
            default -> new Spider();
            case 1 -> new Centipede();
            case 2 -> new Maggot();
            case 3 -> new Mosster();
        };
    }

    private Item generateRandomItem() {
        //Add items as more are created
        switch(myUtility.getRandomInRange(ITEM_LOW, ITEM_HIGH)) {
            default:
                return new Apple();
        }
    }
}
