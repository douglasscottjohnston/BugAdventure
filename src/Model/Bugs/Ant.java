package Model.Bugs;

/**
 * The Hero Bug type Ant.
 */
public class Ant extends HeroBug {
    /** health for ant*/
    private static final int MY_HEALTH = 600;
    /** orginal Health for ant*/
    private static final int MY_ORIGINAL_HEALTH = 600;
    /** Defense for ant */
    private static final int MY_DEFENCE = 30;
    /** Ants attack*/
    private static final Attack MY_ATTACK = new Attack("bite",70, 100, false);
    /** Ants special attack */
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Super bite", 90, 80, false);
    /** Ants speed*/
    private static final int MY_SPEED = 4;
    /** Ants chance to dodge an attack*/
    private static final int MY_CHANCE_TO_DODGE = 30;
    /** hero type*/
    private static final String MY_HERO_TYPE = "Ant";

    /**
     * Instantiates a new Ant.
     *
     * @param theName the name of the hero
     */
    public Ant(final String theName) {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH,MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_CHANCE_TO_DODGE, theName, MY_HERO_TYPE);
    }

}
