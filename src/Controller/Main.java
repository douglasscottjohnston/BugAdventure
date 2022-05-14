package Controller;

import Model.Bugs.*;
import Model.Dungeon;
import Model.Items.Sugar;
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
            if(myRoom.getContents().isEmpty()) {
                System.out.println("The room is empty");
            } else {
                System.out.println("The room contains" + myRoom.getContents());

                if(myRoom.getContents().containsPit()) {
                    Pit myPit = myRoom.getContents().getAndRemovePit();
                    System.out.println(myHero.getName() + " fell down a pit!");
                    myPit.dealDamage(myHero);
                }

                if(myRoom.getContents().containsMonster()) {
                    myMonster = myRoom.getContents().getAndRemoveMonster();
                    attackPhase();
                }

                if(myRoom.getContents().containsItem()) {
                    myHero.pickUpItem(myRoom.getContents().getAndRemoveItem());
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

        System.out.println("Select your hero bug: \n(1) Ladybug\n(2) Ant\n(3) PillBug");

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
        default -> { //loop forever until a correct option is picked
            System.out.println("Must pick 1, 2, or 3");
            return heroSelect(theName);
        }
        }

    }

    private static void attackPhase() {
        System.out.println("A " + myMonster.getName() + " has appeared!");
        while(myMonster.getHealth() > 0) {
            if(myMonster.getHealth() > 0) {
                if (myHero.getSpeed() > myMonster.getSpeed()) {
                    printHeroAttacksFirstMessage();
                    myHero.attack(myMonster);
                } else {
                    printMonsterAttacksFirstMessage();
                    myMonster.attack(myHero);
                }
            }


            if(myHero.getHealth() <= 0) {
                System.out.println(myHero.getName() + " was defeated");
                endGame();
            }
        }

        System.out.println(myHero.getName() + " defeated the " + myMonster.getName());
    }

    private static void movementPhase() {
        Sugar compareSugar = new Sugar();
        int movements = 1;

        if(myHero.getInventory().containsItem(compareSugar)) {
            System.out.println("Use a Sugar?: \n(Y) Yes\n(N) No");
            String selection = UTILITY.scanNext().toUpperCase();
            if(selection.equals("Y") || selection.equals("YES")) {
                myHero.getInventory().useItem(compareSugar, myHero);
                movements = Sugar.getNumMovements();
                UTILITY.appendToBuilder(myHero.getName());
                UTILITY.appendToBuilder(" can now move ");
                UTILITY.appendToBuilder(Integer.toString(Sugar.getNumMovements()));
                UTILITY.appendToBuilder(" times in a row!");
                System.out.println(UTILITY.builderToStringClear());
            }
        }

        for (int i = movements; i > 0; i--) {
            System.out.print(i);
            System.out.println(" movements left");
            printDirectionOptions();
            Directions.Direction direction = Directions.getDirection(UTILITY.scanNextInt());
            if (myRoom.hasDirection(direction)) {
                myDungeon.moveInDirection(direction);
            } else { //loop forever until a correct direction is picked
                System.out.println("The room does not have a door in that direction, please pick a viable direction");
                i++;
            }
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

    private static void printHeroAttacksFirstMessage() {
        UTILITY.appendToBuilder(myHero.getName());
        UTILITY.appendToBuilder(" was faster than the ");
        UTILITY.appendToBuilder(myMonster.getName());
        UTILITY.appendToBuilder(" so ");
        UTILITY.appendToBuilder(myHero.getName());
        UTILITY.appendToBuilder(" attacks first!");
        System.out.println(UTILITY.builderToStringClear());
    }

    private static void printMonsterAttacksFirstMessage() {
        UTILITY.appendToBuilder(myMonster.getName());
        UTILITY.appendToBuilder(" was faster than ");
        UTILITY.appendToBuilder(myHero.getName());
        UTILITY.appendToBuilder(" so it attacks first!");
        System.out.println(UTILITY.builderToStringClear());
    }

}