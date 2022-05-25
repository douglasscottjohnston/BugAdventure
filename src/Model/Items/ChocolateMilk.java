package Model.Items;

import Model.Bugs.Bug;

//name of this item is tentative
public class ChocolateMilk extends Item {

    private static final int DAMAGE_INCREASE = 50;
    private static final String MY_NAME = "Chocolate Milk";

    private static final boolean MY_FRIENDLY = true;

    public ChocolateMilk() {
        super(MY_NAME, MY_FRIENDLY);
    }
    @Override
    public void effect(Bug theHero) {

//        theHero.getAttack().setPower(theHero.getAttack().getPower() + DAMAGE_INCREASE);

        System.out.println("Attack Power now at: " + theHero.getAttack().getPower());

//        System.out.println("Attack Power now at: " + theHero.getAttack().getPower());

    }

}
