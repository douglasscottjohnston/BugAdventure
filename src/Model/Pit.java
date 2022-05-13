package Model;

import Model.Bugs.HeroBug;

import java.io.Serializable;

public class Pit implements Serializable {
    private static final int DAMAGE = 10;


    public void dealDamage(HeroBug theHero) {
        theHero.takeDamage(DAMAGE);
    }

    @Override
    public String toString() {
        return "pit";
    }
}
