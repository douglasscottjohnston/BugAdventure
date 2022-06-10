package Model.Bugs;

/**
 * The type Pill bug.
 */
public class PillBug extends HeroBug {
    /** current health for pillbug*/
    private static final int MY_HEALTH = 450;
    /** original health for pillbug*/
    private static final int MY_ORIGINAL_HEALTH = 450;
    /** current defence for pillbug*/
    private static final int MY_DEFENCE = 50;
    /** attack for pillbug*/
    private static final Attack MY_ATTACK = new Attack("bite",60, 100, false);
    /** special attack for pillbug*/
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Roll Slam",80, 85, false);
    /** speed for pillbug*/
    private static final int MY_SPEED = 3;
    /** chance to dodge for pillbug*/
    private static final int MY_CHANCE_TO_DODGE = 20;
    /** hero type pillbug */
    private static final String MY_HERO_TYPE = "PillBug";

    /**
     * Instantiates a new Pill bug.
     *
     * @param theName the the name
     */
    public PillBug(final String theName) {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_CHANCE_TO_DODGE, theName, MY_HERO_TYPE);
    }
}
