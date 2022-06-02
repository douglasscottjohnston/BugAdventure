package Model.Bugs;

import java.io.Serializable;

/**
 * The type Attack.
 */
public class Attack implements Serializable {
    private final String NAME;
    private final String MESSAGE;
    private int POWER;
    private final int ATTACK_CHANCE;
    private final boolean LIFESTEAL;

    /**
     * Instantiates a new Attack.
     *
     * @param theName    the name of the attack
     * @param theMessage the attack message
     * @param thePower   the attack power
     * @param theAttackChance the chance to hit attack
     * @param theLifesteal true if attack has lifesteal
     */
    public Attack(final String theName, final String theMessage, final int thePower, final int theAttackChance, final boolean theLifesteal) {
        NAME = theName;
        MESSAGE = theMessage;
        POWER = thePower;
        ATTACK_CHANCE = theAttackChance;
        LIFESTEAL = theLifesteal;

    }

    /**
     * Gets the name of the attack.
     *
     * @return the name
     */
    public String getName() {
        return NAME;
    }

    /**
     * Gets the attack message.
     *
     * @return the message
     */
    public String getMessage() {
        return MESSAGE;
    }

    /**
     * Gets the attack power.
     *
     * @return the power
     */
    public int getPower() {
        return POWER;
    }

    public void setPower(final int thePower) {
        POWER = thePower;
    }

    public int getAttackChance() {
        return ATTACK_CHANCE;
    }

    public boolean isLifeSteal() { return LIFESTEAL; }
}
