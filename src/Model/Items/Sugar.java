package Model.Items;

import Model.Bugs.Bug;

public class Sugar extends Item {

    private static final int MY_SPEED_INCREASE = 1;
    private static final int NUM_MOVEMENTS = 3;
    private static final String MY_NAME = "Sugar";

    private static final boolean MY_FRIENDLY = true;

    public Sugar() {
        super(MY_NAME, MY_FRIENDLY);
    }

    public static int getNumMovements() {
        return NUM_MOVEMENTS;
    }

    @Override
    public void effect(Bug theHero) {
        theHero.setSpeed(theHero.getSpeed() + MY_SPEED_INCREASE);
        System.out.println("Speed now at: " + theHero.getSpeed());
    }
}
