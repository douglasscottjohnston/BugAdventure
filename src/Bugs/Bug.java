package Bugs;
import java.util.Random;
import java.util.Scanner;


/**
 * The type Bug.
 */
public abstract class Bug {

    protected final static Random MY_RANDOM = new Random();
    public final static Scanner input = new Scanner(System.in);
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
    public Bug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theDefense, final int theSpeed) {
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
        int lostHealth = myAttack.getPower();
        theEnemy.subtractHitPoints(lostHealth);
    }

    protected void specialAttack(final Bug theEnemy) {
        int damageTaken = mySpecialAttack.getPower();
        if(mySpecialAttack.getAttackChance() > MY_RANDOM.nextInt(101)) {
            theEnemy.subtractHitPoints(damageTaken);
        } else {
            System.out.println("missed lol");
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

    protected void addHitPoints(final int theAmount) {
        if (theAmount < 0) {
            throw new IllegalArgumentException("cannot add negative hitpoints" + theAmount);
        }

        myHealth += theAmount;
    }

    protected void subtractHitPoints(final int theAmount) {
        if (theAmount < 0) {
            throw new IllegalArgumentException("cannot subtract negative hitpoints" + theAmount);
        }

        myHealth -= theAmount;

        if(myHealth <= 0) {
            myHealth = 0;
        }
    }
}


