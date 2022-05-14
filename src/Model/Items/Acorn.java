package Model.Items;

import Model.Bugs.Bug;

public class Acorn extends Item {
    private static final int MY_DEFENSE_INCREASE = 15;
    private static final String MY_NAME = "Acorn";

    private static final boolean MY_FRIENDLY = true;

    public Acorn() {
        super(MY_NAME, MY_FRIENDLY);
    }

    @Override
    public void effect(Bug theHero) {
        theHero.setDefense(theHero.getDefense() + MY_DEFENSE_INCREASE);
        System.out.println("Defense now at: " + theHero.getDefense());
    }
}
