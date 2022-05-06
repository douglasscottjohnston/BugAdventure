
//import Model.Dungeon;


import Bugs.*;

public class Main {
    public static void main(String[] args) {
        //Dungeon dungeon = new Dungeon();
        System.out.println("Yes");

        HeroBug test = new Ant("ant1");

        HeroBug test2 = new Ant("ant2");
        System.out.println(test.getHealth());
        System.out.println(test2.getHealth());
        test2.attack(test);
        //System.out.println(dungeon.toString());
    }
}
