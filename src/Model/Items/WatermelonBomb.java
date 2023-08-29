package Model.Items;

import Model.Bugs.Bug;

/**
 * The type Watermelon bomb.
 */
public class WatermelonBomb extends Item{

    private static final String MY_NAME = "Watermelon Bomb";
    private static final int MY_DAMAGE = 200;
    private static final String MY_MESSAGE = "BOOM! Dealt " + MY_DAMAGE + " points of damage!";

    /**
     * This is a non friendly item. only usable in combat.
     */
    private static final boolean MY_FRIENDLY = false;

    /**
     * Instantiates a new Watermelon bomb.
     */
    public WatermelonBomb() {
        super(MY_NAME, MY_FRIENDLY, MY_MESSAGE);
    }

    /**
     * effects of watermelon bomb on enemy.
     * @param theEnemy the hero
     */
    @Override
    public void effect(Bug theEnemy) {
        theEnemy.takeDamage(MY_DAMAGE);
    }
}
