package Controller;

import Model.Bugs.*;
import Model.Dungeon;
import Model.Room;

import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        //just a test run
        System.out.print("Pick your heroes name:");
        String name = input.next();
        HeroBug myHero = heroSelect(name);
        MonsterBug myMonster;
        Room myRoom;

        //CLI version of the game

        Dungeon dungeon = new Dungeon(myHero);

        while(!dungeon.inExit()) {
            myRoom = dungeon.getCurrent();
            System.out.println(myHero.getName() + " enters a room");
            if(myRoom.isEmpty()) {
                System.out.println("The room is empty");
            } else {
                System.out.println("The room contains" + myRoom.getContents());

                if(myRoom.containsMonster()) {
                    myMonster = myRoom.getMonster();
                    attackPhase(myHero, myMonster);
                }
            }
        }


    }

    private static HeroBug heroSelect(final String theName) {

        HeroBug yourHero;

        System.out.println("Select your hero bug: \n(1) Ladybug\n(2) Ant\n(3) Pillbug");
        int choice = input.nextInt();

        if (choice == 1) {
            yourHero = new LadyBug(theName);
        }
        else if (choice == 2) {
            yourHero = new Ant(theName);
        }
        else if (choice == 3) {
            yourHero = new PillBug(theName);
        }
        else {
            throw new IllegalArgumentException("Must pick 1, 2, or 3");
        }

        return yourHero;
    }

    private static void attackPhase(HeroBug theHero, MonsterBug theMonster) {

    }
}