package Model.Bugs;

import Model.Items.Item;
import Controller.Utility;

/**
 * The type Hero bug.
 */
public abstract class HeroBug extends Bug {

    /** heros chance to dodge*/
    private int myChanceToDodge;
    /** heros item inventory*/
    private final HeroInventory myInventory;
    /** my hero type*/
    private final String myHeroType;

    /**
     * Instantiates a new Hero bug.
     *
     * @param theAttack         the  attack
     * @param theSpecialAttack  the  special attack
     * @param theHealth         the  health
     * @param theOriginalHealth the  original health
     * @param theDefense        the  defense
     * @param theSpeed          the  speed
     * @param theChanceToDodge  the  chance to dodge
     * @param theName           the  name
     * @param theHeroType       the  hero type
     */
    public HeroBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theOriginalHealth,
                   final int theDefense, final int theSpeed, final int theChanceToDodge, final String theName, final String theHeroType) {
        super(theAttack, theSpecialAttack, theHealth, theOriginalHealth, theDefense, theSpeed, theName);

        myChanceToDodge = theChanceToDodge;
        myInventory = new HeroInventory();
        myHeroType = theHeroType;
    }

    /**
     * This
     *
     * @param theEnemy the enemy
     * @return damage dealt
     */
    @Override
    public int attack(final Bug theEnemy) {
        int numberOfAttacks = getSpeed() / theEnemy.getSpeed();
        int damageDealt = 0;

        if (numberOfAttacks == 0) {
            numberOfAttacks = 1;
        }
        while (numberOfAttacks > 0 && theEnemy.isAlive()) {
            damageDealt += super.attack(theEnemy);
            numberOfAttacks--;
        }
        return damageDealt;
    }

    /**
     * Pick up item.
     *
     * @param theItem the the item
     */
    public void pickUpItem(Item theItem) {
        myInventory.addItem(theItem);
        System.out.println(getName() + " picked up a " + theItem.getName());
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public HeroInventory getInventory() {
        return myInventory;
    }

    @Override
    public boolean equals(final Object theObject) {
        return theObject instanceof HeroBug;
    }


    /**
     * Gets chance to dodge.
     *
     * @return the chance to dodge
     */
    protected int getChanceToDodge() {
        return myChanceToDodge;
    }

    /**
     * Sets chance to dodge.
     *
     * @param theChanceToDodge the the chance to dodge
     */
    protected void setChanceToDodge(final int theChanceToDodge) {
        if (theChanceToDodge <= 0 || theChanceToDodge > 100) {
            throw new IllegalArgumentException("Chance to dodge must be greater than 0 and less than 100. It was" + theChanceToDodge);
        }
        myChanceToDodge = theChanceToDodge;
    }

    private int getChoice() {
        Utility util = new Utility();
        System.out.println("Choose (1) for normal attack, choose (2) for special attack, choose (3) to use an item.");
        return util.scanNextInt();
    }

    /**
     * Gets hero type.
     *
     * @return the hero type
     */
    public String getHeroType() {
        return myHeroType;
    }
}
