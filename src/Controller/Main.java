package Controller;

import Model.Bugs.*;
import Model.Dungeon;
import Model.Pit;
import Model.Room;
import Model.Utility;

public class Main {
    private static final Utility UTILITY = new Utility();
    private static boolean myRunning = true;
    private static HeroBug myHero;
    private static MonsterBug myMonster;
    private static Room myRoom;
    private static Dungeon myDungeon;


    public static void main(String[] args) {
        startGame();
    }
    private static void startGame() {
        //just a test run
        System.out.print("Pick your heroes name:");
        String name = UTILITY.scanNextLine();
        myHero = heroSelect(name);

        //CLI version of the game

        myDungeon = new Dungeon(myHero);


        while(myRunning) {
            myRoom = myDungeon.getCurrent();
            System.out.println(myHero.getName() + " enters a room");
            if(myRoom.isEmpty()) {
                System.out.println("The room is empty");
            } else {
                System.out.println("The room contains" + myRoom.getContents());

                if(myRoom.containsPit()) {
                    Pit myPit = myRoom.getPit();
                    System.out.println(myHero.getName() + " fell down a pit!");
                    myPit.dealDamage(myHero);
                    myRoom.remove(myPit);
                }

                if(myRoom.containsMonster()) {
                    myMonster = myRoom.getMonster();
                    attackPhase();
                }

                if(myRoom.containsItem()) {
                    myHero.pickUpItem(myRoom.getItem());
                    myRoom.remove(myRoom.getItem());
                }

                movementPhase();
                if(myDungeon.inExit()) {
                    System.out.println(myHero.getName() + " found the exit!");
                    endGame();
                }
            }
        }

        System.out.println("Play again?: \n(Y) yes\n(N) no");

        switch(UTILITY.scanNext().toUpperCase()) {
            case "Y", "YES" -> startGame();
            case "N", "NO" -> endProgram();
            default -> throw UTILITY.getNewIllegal("Not a valid response");
        }
    }

    private static void endGame() {
        System.out.println("GAME OVER");
        myRunning = false;
    }

    private static void endProgram() {
        UTILITY.closeScanner();
        System.exit(0);
    }

    private static HeroBug heroSelect(final String theName) {

        HeroBug yourHero;

        System.out.println("Select your hero bug: \n(1) Ladybug\n(2) Ant\n(3) Pillbug");

        switch(UTILITY.scanNextInt()) {
        case 1 -> {
            return new LadyBug(theName);
        }
        case 2 -> {
            return new Ant(theName);
        }
        case 3 -> {
            return new PillBug(theName);
        }
        default -> throw UTILITY.getNewIllegal("Must pick 1, 2, or 3");
        }

    }

    private static void attackPhase() {
        System.out.println("A " + myMonster.getName() + " has appeared!");
        while(myMonster.getHealth() > 0) {
            myHero.attack(myMonster);

            if(myMonster.getHealth() > 0) {
                myMonster.attack(myHero);
            }

            if(myHero.getHealth() <= 0) {
                System.out.println(myHero.getName() + " was defeated");
                endGame();
            }
        }

        myRoom.remove(myMonster);

        System.out.println(myHero.getName() + " defeated the " + myMonster.getName());
    }

    private static void movementPhase() {
        printDirectionOptions();

        Directions.Direction direction = Directions.getDirection(UTILITY.scanNextInt());

        if(myRoom.hasDirection(direction)) {
            myDungeon.moveInDirection(direction);
        } else {
            throw UTILITY.getNewIllegal("The room does not have a door to the " + direction);
        }
    }

    private static void printDirectionOptions() {
        System.out.println("Select Direction: ");

        if(myRoom.hasNorth()) {
            System.out.println("(0) North");
        }

        if(myRoom.hasSouth()) {
            System.out.println("(1) South");
        }

        if(myRoom.hasEast()) {
            System.out.println("(2) East");
        }

        if(myRoom.hasWest()) {
            System.out.println("(3) West");
        }
    }

}