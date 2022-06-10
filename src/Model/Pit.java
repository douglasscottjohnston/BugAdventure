package Model;

import Model.Bugs.HeroBug;

import java.io.Serializable;

/**
 * The type Pit.
 */
public class Pit implements Serializable {
    private static final int DAMAGE = 10;


    /**
     * Deal damage to hero.
     *
     * @param theHero the hero
     */
    public void dealDamage(HeroBug theHero) {
        theHero.takeDamage(DAMAGE);
    }

    /**
     * gets the name of the room
     * @return pit
     */
    @Override
    public String toString() {
        return "Pit";
    }

    /**
     * checks if object passes is a pit.
     * @param theObject
     * @return boolean
     */
    @Override
    public boolean equals(final Object theObject) {
        return theObject instanceof Pit;
    }
}
