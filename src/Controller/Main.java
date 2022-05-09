package Controller;

import Model.Bugs.*;
import Model.Dungeon;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        //just a test run
        System.out.print("Pick your heroes name:");
        String name = input.next();
        HeroBug myHero = heroSelect(name);
        MonsterBug myEnemy = new Mosster();

        System.out.println("You are fighting a " + myEnemy.getName());

        while(myEnemy.isAlive() && myHero.isAlive()) {
            myHero.attack(myEnemy);
            myEnemy.attack(myHero);
        }
        //can delete if needed


        //testing dungeon
        Dungeon dungeon = new Dungeon(myHero);

        System.out.println(Arrays.toString(dungeon.getCurrent().getDoors()));
        dungeon.moveNorth();
        System.out.println(Arrays.toString(dungeon.getCurrent().getDoors()));
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
}