package Model;

import Controller.Directions;
import Model.Bugs.*;
import Model.Database.SaveManager;
import Model.Items.Item;

public abstract class Model {
    private static final ModelUtility UTILITY = new ModelUtility();
    private static Dungeon myDungeon;
    private static HeroBug myHero;
    private static MonsterBug myMonster;
    private static final int myMovements = 1;
    private static boolean myFirstPlay = true;

    public static void createLadyBug(final String theName) {
        myHero = new LadyBug(theName);
    }

    public static void createPillBug(final String theName) {
        myHero = new PillBug(theName);
    }

    public static void createAnt(final String theName) {
        myHero = new Ant(theName);
    }

    public static void createDungeon() {
        myDungeon = new Dungeon(myHero);
    }

    public static void move(final Directions.Direction theDirection) {
        myDungeon.moveInDirection(theDirection);
    }

    public static boolean isCurrentEmpty() {
        return myDungeon.getCurrent().getContents().isEmpty();
    }

    public static boolean currentHasMonster() {
        return myDungeon.getCurrent().getContents().containsMonster();
    }

    public static boolean isFirstPlay() {
        return myFirstPlay;
    }

    public static Dungeon getDungeon() {
        return myDungeon;
    }

    public static HeroBug getHero() {
        return myHero;
    }

    public static MonsterBug getCurrentMonster() {
        myMonster = myDungeon.getCurrent().getContents().getAndRemoveMonster();
        return myMonster;
    }

    public static Item getCurrentItem() {
        return myDungeon.getCurrent().getContents().getAndRemoveItem();
    }

    public static boolean run() {
        return myHero.getSpeed() < myMonster.getSpeed();
    }

    public static void save() {
        myFirstPlay = false;
        SaveManager.saveDungeon(myDungeon);
    }

    public static void load() {
        myDungeon = SaveManager.loadDungeon();
    }

}