package Model.Bugs;

import Model.Items.Item;
import Controller.Utility;

public abstract class HeroBug extends Bug {

    private int myChanceToDodge;
    private int mySpecialSkillChance;
    private final HeroInventory myInventory;

    public HeroBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theOriginalHealth,
                   final int theDefense, final int theSpeed, final int theChanceToDodge, final String theImageName, final String theName) {
        super(theAttack, theSpecialAttack, theHealth, theOriginalHealth, theDefense, theSpeed, theImageName, theName);

        setChanceToDodge(theChanceToDodge);
        setName(theName);
        myInventory = new HeroInventory();
    }


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

    public void pickUpItem(Item theItem) {
        myInventory.addItem(theItem);
        System.out.println(getName() + " picked up a " + theItem.getName());
    }

    public HeroInventory getInventory() {
        return myInventory;
    }

    @Override
    public boolean equals(final Object theObject) {
        return theObject instanceof HeroBug;
    }


    protected int getChanceToDodge() {
        return myChanceToDodge;
    }

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
}
