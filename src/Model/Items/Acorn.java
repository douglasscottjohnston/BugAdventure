package Model.Items;

import Model.Bugs.Bug;

/**
 * The type Acorn.
 */
public class Acorn extends Item {
    private static final String MY_NAME = "Acorn";
    private static final int MY_DEFENSE_INCREASE = 15;
    private static final String MY_MESSAGE = "Defence increased by " + MY_DEFENSE_INCREASE;

    private static final boolean MY_FRIENDLY = true;

    /**
     * Instantiates a new Acorn.
     */
    public Acorn() {
        super(MY_NAME, MY_FRIENDLY, MY_MESSAGE);
    }

    /**
     * effects of acorn item.
     * @param theHero the hero
     */
    @Override
    public void effect(Bug theHero) {
        theHero.setDefense(theHero.getDefense() + MY_DEFENSE_INCREASE);
    }
}
