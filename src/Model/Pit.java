package Model;

import Model.Bugs.HeroBug;

public class Pit {
    private static final int DAMAGE = 10;


    public void dealDamage(HeroBug theHero) {
        theHero.takeDamage(DAMAGE);
    }
}
