package Model;

import Controller.Directions;
import Model.Bugs.*;
import Model.Database.SaveManager;
import Model.Items.Item;

/**
 * The type Model.
 */
public abstract class Model {
    private static final ModelUtility UTILITY = new ModelUtility();
    private static Dungeon myDungeon;
    private static HeroBug myHero;
    private static MonsterBug myMonster;
    private static final int MOVEMENTS = 1;

    /**
     * Create lady bug.
     *
     * @param theName the name
     */
    public static void createLadyBug(final String theName) {
        myHero = new LadyBug(theName);
    }

    /**
     * Create pill bug.
     *
     * @param theName the name
     */
    public static void createPillBug(final String theName) {
        myHero = new PillBug(theName);
    }

    /**
     * Create ant.
     *
     * @param theName the name
     */
    public static void createAnt(final String theName) {
        myHero = new Ant(theName);
    }

    /**
     * Create dungeon.
     */
    public static void createDungeon() {
        myDungeon = new Dungeon(myHero);
        myMonster = null;
    }

    /**
     * Move through the dungeon.
     *
     * @param theDirection the the direction
     */
    public static void move(final Directions.Direction theDirection) {
        myDungeon.moveInDirection(theDirection);
        if(myDungeon.getCurrent().getContents().containsMonster()) {
            myMonster = myDungeon.getCurrent().getContents().getAndRemoveMonster();
        } else if(myMonster != null && !myMonster.isAlive()) {
            myMonster = null;
        }
    }

    /**
     * Current dungeon is empty check.
     *
     * @return the boolean
     */
    public static boolean currentIsEmpty() {
        return myDungeon.getCurrent().getContents().isEmpty();
    }

    /**
     * Current room has monster boolean.
     *
     * @return the boolean
     */
    public static boolean currentHasMonster() {
        return myMonster != null || myDungeon.getCurrent().getContents().containsMonster();
    }

    /**
     * Current room has item boolean.
     *
     * @return the boolean
     */
    public static boolean currentHasItem() {
        return myDungeon.getCurrent().getContents().containsItem();
    }

    /**
     * Is first play boolean.
     *
     * @return the boolean
     */
    public static boolean isFirstPlay() {
        return !SaveManager.hasSavedOnce();
    }

    /**
     * Gets dungeon.
     *
     * @return the dungeon
     */
    public static Dungeon getDungeon() {
        return myDungeon;
    }

    /**
     * Gets hero.
     *
     * @return the hero
     */
    public static HeroBug getHero() {
        return myHero;
    }

    /**
     * Gets current monster.
     *
     * @return the current monster
     */
    public static MonsterBug getCurrentMonster() {
        return myMonster;
    }

    /**
     * check if monster died.
     */
    public static void monsterDied() {
        myMonster = null;
    }

    /**
     * Next monster.
     */
    public static void nextMonster() {
        myMonster = myDungeon.getCurrent().getContents().getAndRemoveMonster();
    }

    /**
     * Gets item.
     *
     * @return the item
     */
    public static Item getItem() {
        return myDungeon.getCurrent().getContents().getAndRemoveItem();
    }

    /**
     * Run boolean.
     *
     * @return the boolean
     */
    public static boolean run() {
        return myHero.getSpeed() < myMonster.getSpeed();
    }

    /**
     * Save the game.
     */
    public static void save() {
        SaveManager.saveDungeon(myDungeon);
    }

    /**
     * Load previous game.
     */
    public static void load() {
        myDungeon = SaveManager.loadDungeon();
        myHero = myDungeon.getHero();
    }

}