package Model.Items;

import Model.Bugs.Bug;

public class Apple extends Item {

    private static final String MY_NAME = "Apple";
    private static final int HEAL_AMOUNT = 50;
    private static final String MY_MESSAGE = "Healed " + HEAL_AMOUNT + " points of health";

    private static final boolean MY_FRIENDLY = true;

    public Apple() {
        super(MY_NAME, MY_FRIENDLY, MY_MESSAGE);
    }

    @Override
    public void effect(Bug theHero) {
        theHero.heal(HEAL_AMOUNT);
    }

}
