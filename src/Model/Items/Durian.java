package Model.Items;

import Model.Bugs.Bug;

/**
 * The type Durian.
 */
//name of this item is tentative
public class Durian extends Item {

    private static final String MY_NAME = "Durian";
    private static final int DAMAGE_INCREASE = 50;
    private static final String MY_MESSAGE = "Increased attack and special attack damage by " + DAMAGE_INCREASE;

    private static final boolean MY_FRIENDLY = true;

    /**
     * Instantiates a new Durian item.
     */
    public Durian() {
        super(MY_NAME, MY_FRIENDLY, MY_MESSAGE);
    }

    /**
     * effects of the durian item.
     * @param theHero the hero
     */
    @Override
    public void effect(Bug theHero) {
        theHero.getAttack().setPower(theHero.getAttack().getPower() + DAMAGE_INCREASE);
        theHero.getSpecialAttack().setPower(theHero.getSpecialAttack().getPower() + DAMAGE_INCREASE);
    }

}
