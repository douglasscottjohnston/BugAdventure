package Model.Items;

import Model.Bugs.Bug;

public class WatermelonBomb extends Item{

    private static final String MY_NAME = "Watermelon Bomb";
    private static final int MY_DAMAGE = 200;
    private static final String MY_MESSAGE = "BOOM! Dealt " + MY_DAMAGE + " points of damage!";

    private static final boolean MY_FRIENDLY = false;

    public WatermelonBomb() {
        super(MY_NAME, MY_FRIENDLY, MY_MESSAGE);
    }
    @Override
    public void effect(Bug theEnemy) {
        theEnemy.takeDamage(MY_DAMAGE);
    }
}
