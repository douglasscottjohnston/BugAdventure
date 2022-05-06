package Bugs;

/**
 * The type Attack.
 */
public class Attack {
    private final String NAME;
    private final String MESSAGE;
    private final int POWER;
    private final int ATTACK_CHANCE;

    /**
     * Instantiates a new Attack.
     *
     * @param theName    the name of the attack
     * @param theMessage the attack message
     * @param thePower   the attack power
     */
    public Attack(final String theName, final String theMessage, final int thePower, final int theAttackChance) {
        NAME = theName;
        MESSAGE = theMessage;
        POWER = thePower;
        ATTACK_CHANCE = theAttackChance;
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

    public int getAttackChance() {
        return ATTACK_CHANCE;
    }
}
