package Model.Bugs;

import java.io.Serializable;

/**
 * The type Attack.
 */
public class Attack implements Serializable {

    private final String myName;
    private int myPower;
    private final int myAttackChance;
    private final boolean myHasLifesteal;


    /**
     * Instantiates a new Attack.
     *
     * @param theName    the name of the attack
     * @param thePower   the attack power
     * @param theAttackChance the chance to hit attack
     * @param theLifesteal true if attack has lifesteal
     */
    public Attack(final String theName, final int thePower, final int theAttackChance, final boolean theLifesteal) {
        myName = theName;
        myPower = thePower;
        myAttackChance = theAttackChance;
        myHasLifesteal = theLifesteal;

    }

    /**
     * Gets the name of the attack.
     *
     * @return the name
     */
    public String getName() {
        return myName;
    }

    /**
     * Gets the attack power.
     *
     * @return the power
     */
    public int getPower() {
        return myPower;
    }

    public void setPower(final int thePower) {
        myPower = thePower;
    }

    public int getAttackChance() {
        return myAttackChance;
    }

    public boolean getLifeSteal() { return myHasLifesteal; }
}
