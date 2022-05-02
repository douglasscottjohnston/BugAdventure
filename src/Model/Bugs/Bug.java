package Model.Bugs;



/**
 * The type Bug.
 */
abstract class Bug {
    private final Attack myAttack;
    private final Attack mySpecialAttack;

    private int myHealth;
    private int myDefense;
    private int mySpeed;


    /**
     * Instantiates a new Bug.
     *
     * @param theAttack        the attack
     * @param theSpecialAttack the special attack
     * @param theHealth        the health
     * @param theDefense       the defense
     * @param theSpeed         the speed
     */
    protected Bug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theDefense, final int theSpeed) {
        myAttack = theAttack;
        mySpecialAttack = theSpecialAttack;
        myHealth = theHealth;
        myDefense = theDefense;
        mySpeed = theSpeed;
    }

    /**
     * Attacks the passed enemy using the normal attack.
     *
     * @param theEnemy the enemy
     */
    protected void attack(final Bug theEnemy) {
        //might not be the best way to calculate the damage taken
        int lostHealth = theEnemy.getDefense() - myAttack.getPower();
        theEnemy.setHealth(theEnemy.getHealth() - lostHealth);
    }

    protected void specialAttack(final Bug theEnemy) {
        int damageTaken = theEnemy.getDefense() - mySpecialAttack.getPower();
        theEnemy.setHealth(theEnemy.getHealth() - damageTaken);
    }


    /**
     * Gets attack.
     *
     * @return the attack
     */
    public Attack getAttack() {
        return myAttack;
    }

    /**
     * Gets special attack.
     *
     * @return the special attack
     */
    public Attack getSpecialAttack() {
        return mySpecialAttack;
    }

    /**
     * Gets defense.
     *
     * @return the defense
     */
    public int getDefense() {
        return myDefense;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public int getHealth() {
        return myHealth;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public int getSpeed() {
        return mySpeed;
    }

    /**
     * Sets health.
     *
     * @param theHealth the the health
     */
    public void setHealth(final int theHealth) {
        this.myHealth = theHealth;
    }
}


