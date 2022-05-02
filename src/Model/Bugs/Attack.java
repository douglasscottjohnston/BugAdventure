package Model.Bugs;

/**
 * The type Attack.
 */
public class Attack {
    private final String NAME;
    private final String MESSAGE;
    private final int POWER;

    /**
     * Instantiates a new Attack.
     *
     * @param theName    the name of the attack
     * @param theMessage the attack message
     * @param thePower   the attack power
     */
    public Attack(final String theName, final String theMessage, final int thePower) {
        NAME = theName;
        MESSAGE = theMessage;
        POWER = thePower;
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
}
