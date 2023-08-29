package Model.Bugs;

import java.io.Serializable;
import java.util.Random;


/**
 * The type Bug.
 */
public abstract class Bug implements Serializable {

    /** The constant MY_RANDOM.*/
    protected final static Random MY_RANDOM = new Random();
    /** the attack of the bug*/
    private final Attack myAttack;
    /** the special attack for the bug*/
    private final Attack mySpecialAttack;
    /** the Bugs current health*/
    private int myHealth;
    /** the bugs original health*/
    private final int myOriginalHealth;
    /** the bugs defense */
    private int myDefense;
    /** the bugs speed*/
    private int mySpeed;
    /** the bugs name*/
    private String myName;


    /**
     * Instantiates a new Bug.
     *
     * @param theAttack         the attack
     * @param theSpecialAttack  the special attack
     * @param theHealth         the health
     * @param theOriginalHealth the original health
     * @param theDefense        the defense
     * @param theSpeed          the speed
     * @param theName           the name
     */
    public Bug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theOriginalHealth, final int theDefense, final int theSpeed, final String theName) {
        myAttack = theAttack;
        mySpecialAttack = theSpecialAttack;
        myHealth = theHealth;
        myOriginalHealth = theOriginalHealth;
        myDefense = theDefense;
        mySpeed = theSpeed;
        myName = theName;
    }

    /**
     * Attacks the passed enemy using the normal attack.
     *
     * @param theEnemy the enemy
     * @return the damage dealt
     */
    public int attack(final Bug theEnemy) {
        //might not be the best way to calculate the damage taken
        int damageDealt = myAttack.getPower() - (myAttack.getPower() * theEnemy.getDefense() / 100);
        System.out.println(damageDealt);
        theEnemy.takeDamage(damageDealt);

        if(myAttack.hasLifeSteal()) {
            heal(damageDealt);
        }
        return damageDealt;
    }

    /**
     * Special attack int.
     *
     * @param theEnemy the enemy
     * @return the damage dealt
     */
    public int specialAttack(final Bug theEnemy) {
        int damageDealt = mySpecialAttack.getPower() - (mySpecialAttack.getPower() * theEnemy.getDefense() / 100);
        if(mySpecialAttack.getAttackChance() > MY_RANDOM.nextInt(101)) {
            theEnemy.takeDamage(damageDealt);

            if(myAttack.hasLifeSteal()) {
                heal(damageDealt);
            }
        } else {
            damageDealt = 0;
        }
        return damageDealt;
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
     * Gets original health.
     *
     * @return the original health
     */
    public int getOriginalHealth() {
        return myOriginalHealth;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() { return myName; }

    /**
     * Sets speed.
     *
     * @param theSpeed the speed
     */
    public void setSpeed(final int theSpeed) {
        mySpeed = theSpeed;
    }

    /**
     * Sets defense.
     *
     * @param theDefense the defense
     */
    public void setDefense(final int theDefense) {
        myDefense = theDefense;
    }

    /**
     * Sets health.
     *
     * @param theHealth the health
     */
    public void setHealth(final int theHealth) {
        myHealth = theHealth;
        if(myHealth <= 0) {
            myHealth = 0;
        }
    }

    /**
     * Is alive boolean.
     *
     * @return the boolean
     */
    public final boolean isAlive() {
        return myHealth > 0;
    }

    /**
     * Heal.
     *
     * @param theAmount the amount to heal
     */
    public void heal(final int theAmount) {
        if (theAmount < 0) {
            throw new IllegalArgumentException("cannot add negative hitpoints" + theAmount);
        }
        if((myHealth + theAmount) > myOriginalHealth) {
            System.out.println(myName + " healed for " + (myOriginalHealth - myHealth) + " health");
            myHealth = myOriginalHealth;
        } else {
            System.out.println(myName + " healed for " + theAmount + " health");
            myHealth += theAmount;
        }
    }

    /**
     * Take damage.
     *
     * @param theAmount the amount of damage
     */
    public void takeDamage(final int theAmount) {
        if (theAmount < 0) {
            throw new IllegalArgumentException("cannot subtract negative hitpoints" + theAmount);
        }

        myHealth -= theAmount;
        System.out.println(myName + " took " + theAmount + " points of damage!");

        if(myHealth <= 0) {
            myHealth = 0;
        }
    }

    /**
     * get users name.
     *
     *
     * @return users name as string
     */
    @Override
    public String toString() {
        return myName;
    }
}


