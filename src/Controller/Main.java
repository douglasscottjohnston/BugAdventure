package Controller;

import Model.Bugs.*;
import Model.Dungeon;
import Model.Items.Item;
import Model.Items.Sugar;
import Model.Pit;
import Model.Room;

public class Main {
    private static final Utility UTILITY = new Utility();
    private static boolean myRunning;
    private static HeroBug myHero;
    private static MonsterBug myMonster;
    private static Room myRoom;
    private static Dungeon myDungeon;
    private static int myMovements = 1;


    public static void main(String[] args) {
        startGame();
    }
    private static void startGame() {
        myRunning = true;

        myHero = heroSelect(nameSelect());

        //CLI version of the game

        myDungeon = new Dungeon(myHero);



        while(myRunning) {
            myRoom = myDungeon.getCurrent();
            System.out.println(myHero.getName() + " enters a room");
            myDungeon.printExitPath();
            if(myRoom.getContents().isEmpty()) {
                System.out.println("The room is empty");
            } else {
                System.out.println("The room contains " + myRoom.getContents());

                if(myRoom.getContents().containsPit()) {
                    Pit myPit = myRoom.getContents().getAndRemovePit();
                    System.out.println(myHero.getName() + " fell down a pit!");
                    myPit.dealDamage(myHero);
                }

                if(myRoom.getContents().containsMonster()) {
                    myMonster = myRoom.getContents().getAndRemoveMonster();
                    System.out.print("A ");
                    System.out.print(myMonster.getName());
                    System.out.println(" has appeared!");
                    System.out.println("Would you like to fight or run away?\n(1) fight\n(2) run away");

                    switch(UTILITY.scanNextInt()) {
                        case 1 -> attackPhase();
                        case 2 -> runPhase();
                        case 3 -> System.out.println("you cheater"); //chat skips fight
                        default -> attackPhase();
                    }
                }

                if(myRoom.getContents().containsItem()) {
                    myHero.pickUpItem(myRoom.getContents().getAndRemoveItem());
                }
            }

            if(!myHero.getInventory().isEmpty()) {
                System.out.println("Would you like to use an item?\n(Y) yes\n(N) no");

                String response = UTILITY.scanNext().toUpperCase();

                if(response.equals("Y") || response.equals("YES")) {
                    itemPhase();
                }
            }

            movementPhase();
            if(myDungeon.inExit()) {
                System.out.println(myHero.getName() + " found the exit!");
                endGame();
            }
        }

        playAgain();
    }

    private static void endGame() {
        System.out.println("GAME OVER");
        myRunning = false;
        playAgain();
    }

    private static void endProgram() {
        UTILITY.closeScanner();
        System.exit(0);
    }

    private static void playAgain() {
        System.out.println("Play again?: \n(Y) yes\n(N) no");

        switch(UTILITY.scanNext().toUpperCase()) {
        case "Y", "YES" -> {
            System.out.println();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            startGame();
        }
        case "N", "NO" -> endProgram();
        default -> { // loop until a valid response is given
            System.out.println("Not a valid response, please enter either y, yes or n, no");
            playAgain();
        }
        }
    }

    private static String nameSelect() {
        System.out.print("Pick your hero's name: ");
        String name = UTILITY.scanNextLine();
        if(name.isEmpty()) {
            name = UTILITY.scanNextLine();
        }
        System.out.println();
        return name;
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
        while(myMonster.isAlive()) {
            if(myMonster.isAlive()) {
                if (myHero.getSpeed() > myMonster.getSpeed()) {
                    printHeroAttacksFirstMessage();
                    myHero.attack(myMonster, myHero);
                    myMonster.attack(myHero);
                } else {
                    printMonsterAttacksFirstMessage();
                    myMonster.attack(myHero);
                    myHero.attack(myMonster, myHero);
                }
            }

            if(myHero.getHealth() <= 0) {
                System.out.print(myHero.getName());
                System.out.println(" was defeated");
                endGame();
                return;
            }
        }

        System.out.print(myHero.getName());
        System.out.print(" defeated the ");
        System.out.println(myMonster.getName());
    }

    private static void runPhase() {
        if(myHero.getSpeed() < myMonster.getSpeed()) {
            System.out.print(myHero.getName());
            System.out.print(" is slower than the ");
            System.out.println(myMonster.getName());
            System.out.print(myHero.getName());
            System.out.println(" failed to run away!");
            attackPhase();
        } else {
            System.out.print(myHero.getName());
            System.out.print(" is faster than the ");
            System.out.println(myMonster.getName());
            System.out.print(myHero.getName());
            System.out.print(" succeeded in running away from the ");
            System.out.println(myMonster.getName());
        }
    }

    private static void itemPhase() {
        Sugar compareSugar = new Sugar();

        Item selection = myHero.getInventory().selectItem();

        if(selection.equals(compareSugar)) {
            myHero.getInventory().useItem(compareSugar, myHero);
            myMovements = Sugar.getNumMovements();
            UTILITY.appendToBuilder(myHero.getName());
            UTILITY.appendToBuilder(" can now move ");
            UTILITY.appendToBuilder(Integer.toString(Sugar.getNumMovements()));
            UTILITY.appendToBuilder(" times in a row!");
            System.out.println(UTILITY.builderToStringClear());
        } else if(selection.isFriendly()) {
            myHero.getInventory().useItem(selection, myHero);
        } else { // loop until an item that can be used is selected
            System.out.println(selection.getName() + " cannot be used at the moment, please select another item");
            itemPhase();
        }
    }

    private static void movementPhase() {

        boolean printMovements = false;

        if(myMovements > 1) {
            printMovements = true;
        }

        for (int i = myMovements; i > 0; i--) {
            if(printMovements) {
                System.out.print(i);
                System.out.println(" movements left");
            }
            printDirectionOptions();
            int choice = UTILITY.scanNextInt();

            if(choice < 0 || choice > 3) {
                System.out.println(choice + " is not a valid direction, please pick a valid direction");
                movementPhase();
                return;
            }
            Directions.Direction direction = Directions.getDirection(choice);
            if (myRoom.hasDirection(direction)) {
                myDungeon.moveInDirection(direction);
            } else { //loop forever until a correct direction is picked
                System.out.println("The room does not have a door in that direction, please pick a viable direction");
                i++;
            }
        }
        myMovements = 1;
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