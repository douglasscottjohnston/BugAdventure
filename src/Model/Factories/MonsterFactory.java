package Model.Factories;

import Model.Bugs.*;
import Model.Utility;

/**
 * The type Monster factory.
 */
public class MonsterFactory {
    private static final int MONSTER_HIGH = 3; // The high chance to generate a certain type of monster
    private final Utility myUtility;

    /**
     * Instantiates a new Monster factory.
     */
    public MonsterFactory() {
        myUtility = new Utility();
    }

    /**
     * Makes a new spider object.
     *
     * @return the spider
     */
    public Spider makeSpider() {
        return new Spider();
    }

    /**
     * Makes a new maggot object.
     *
     * @return the maggot
     */
    public Maggot makeMaggot() {
        return new Maggot();
    }

    /**
     * Makes a new centipede object.
     *
     * @return the centipede
     */
    public Centipede makeCentipede() {
        return new Centipede();
    }

    /**
     * Makes a new mosster object.
     *
     * @return the mosster
     */
    public Mosster makeMosster() {
        return new Mosster();
    }

    /**
     * Makes a random monster bug.
     *
     * @return the monster bug
     */
    public MonsterBug makeRandomMonsterBug() {
        return switch (myUtility.getRandom(MONSTER_HIGH)) {
            default -> makeSpider();
            case 1 -> makeCentipede();
            case 2 -> makeMaggot();
            case 3 -> makeMosster();
        };
    }

}
