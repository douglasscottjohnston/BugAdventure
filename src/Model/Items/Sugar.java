package Model.Items;

import Model.Bugs.Bug;

/**
 * The type Sugar.
 */
public class Sugar extends Item {

    private static final String MY_NAME = "Sugar";
    private static final int MY_SPEED_INCREASE = 1;
    private static final String MY_MESSAGE = "Speed increased by " + MY_SPEED_INCREASE;

    private static final boolean MY_FRIENDLY = true;

    /**
     * Instantiates a new Sugar.
     */
    public Sugar() {
        super(MY_NAME, MY_FRIENDLY, MY_MESSAGE);
    }

    @Override
    public void effect(Bug theHero) {
        theHero.setSpeed(theHero.getSpeed() + MY_SPEED_INCREASE);
    }
}
