package Model.Bugs;

/**
 * The type Lady bug.
 */
public class LadyBug extends HeroBug {
    /** current health*/
    private static final int MY_HEALTH = 500;
    /** origianl health */
    private static final int MY_ORIGINAL_HEALTH = 500;
    /** ladybug defence */
    private static final int MY_DEFENCE = 20;
    /** attack for ladybug*/
    private static final Attack MY_ATTACK = new Attack("bite",50, 100, false);
    /** special attack for ladybug*/
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Wing Attack",75, 70, false);
    /**ladybug speed */
    private static final int MY_SPEED = 6;
    /** ladybug chance to dodge */
    private static final int MY_CHANCE_TO_DODGE= 20;
    /**hero tyoe ladybug*/
    private static final String MY_HERO_TYPE = "LadyBug";

    /**
     * Instantiates a new Lady bug.
     *
     * @param theName the name
     */
    public LadyBug(final String theName) {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_CHANCE_TO_DODGE, theName, MY_HERO_TYPE);
    }
}
