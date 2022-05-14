package Model.Items;

import Model.Bugs.Bug;

public class Sugar extends Item {

    private final int MY_SPEED_INCRESASE = 1;
    private static final String MY_NAME = "Sugar";

    private static final boolean MY_FRIENDLY = true;

    public Sugar() {
        super(MY_NAME, MY_FRIENDLY);
    }

    @Override
    public void effect(Bug theHero) {
        theHero.setSpeed(theHero.getSpeed() + MY_SPEED_INCRESASE);
        System.out.println("Speed now at: " + theHero.getSpeed());
    }
}
