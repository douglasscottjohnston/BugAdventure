package Model.Bugs;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;


/**
 * The type Bug.
 */
public abstract class Bug implements Serializable {

    protected final static Random MY_RANDOM = new Random();
    public final static Scanner input = new Scanner(System.in);
    private final Attack myAttack;
    private final Attack mySpecialAttack;
    private int myHealth;
    private int myOriginalHealth;
    private int myDefense;
    private int mySpeed;

    private String myName;


    /**
     * Instantiates a new Bug.
     *
     * @param theAttack        the attack
     * @param theSpecialAttack the special attack
     * @param theHealth        the health
     * @param theDefense       the defense
     * @param theSpeed         the speed
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
     */
    protected void attack(final Bug theEnemy) {
        //might not be the best way to calculate the damage taken
        int damageTaken = myAttack.getPower() - (myAttack.getPower() * theEnemy.getDefense() / 100);
        theEnemy.takeDamage(damageTaken);
        System.out.println(theEnemy.getName() + " Hp left: " + theEnemy.getHealth());

        if(myAttack.getLifeSteal()) {
            heal(damageTaken);
        }
    }

    public void specialAttack(final Bug theEnemy) {
        int damageTaken = mySpecialAttack.getPower() - (mySpecialAttack.getPower() * theEnemy.getDefense() / 100);
        if(mySpecialAttack.getAttackChance() > MY_RANDOM.nextInt(101)) {
            theEnemy.takeDamage(damageTaken);
            System.out.println(theEnemy.getName() + " Hp left: " + theEnemy.getHealth());

            if(myAttack.getLifeSteal()) {
                heal(damageTaken);
            }
        } else {
            System.out.println(getName() + " missed their Attack");
        }
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

    public final String getName() { return myName; }

    public void setName(final String theName) {
        myName = theName;
    }

    public void setSpeed(final int theSpeed) {
        mySpeed = theSpeed;
    }
    public void setDefense(final int theDefense) {
        myDefense = theDefense;
    }

    /**
     * Sets health.
     *
     * @param theHealth  the health
     */
    public void setHealth(final int theHealth) {
        this.myHealth = theHealth;
    }

    public final boolean isAlive() {
        return myHealth > 0;
    }

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

    @Override
    public String toString() {
        return myName;
    }


}


