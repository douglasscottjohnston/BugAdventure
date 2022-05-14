package Model.Items;

import Model.Bugs.Bug;

public class Bomb extends Item{

    private final int MY_DAMAGE = 200;
    private static final String MY_NAME = "Bomb";

    private static final boolean MY_FRIENDLY = false;

    public Bomb() {
        super(MY_NAME, MY_FRIENDLY);
    }
    @Override
    public void effect(Bug theEnemy) {
        theEnemy.takeDamage(MY_DAMAGE);
    }
}
