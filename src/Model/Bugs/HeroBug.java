package Model.Bugs;

import Model.HeroInventory;
import Model.Items.Item;
import Model.Utility;

public abstract class HeroBug extends Bug {

    private int myChanceToDodge;
    private int mySpecialSkillChance;
    private final HeroInventory myInventory;

    public HeroBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theOriginalHealth, final int theDefense,
                      final int theSpeed, final int theChanceToDodge, final String theName) {
        super(theAttack, theSpecialAttack, theHealth, theOriginalHealth, theDefense, theSpeed, theName);

        setChanceToDodge(theChanceToDodge);
        setName(theName);
        myInventory = new HeroInventory();
    }


    public void attack(final Bug theEnemy, final Bug theHero) {
        int numberOfAttacks = getSpeed() / theEnemy.getSpeed();

        if (numberOfAttacks == 0) {
            numberOfAttacks = 1;
        }
        while (numberOfAttacks > 0 && theEnemy.isAlive()) {
            switch(getChoice()) {
                case 1 -> {
                    super.attack(theEnemy);
                    numberOfAttacks--;
                }
                case 2 -> {
                    super.specialAttack(theEnemy);
                    numberOfAttacks--;
                }
                case 3 -> {
                    if(myInventory.isEmpty()) {
                        System.out.println("You have no items.");
                    } else {
                        Item item = myInventory.selectItem();
                        if (item.isFriendly()) {
                            myInventory.useItem(item, theHero);
                        } else {
                            myInventory.useItem(item, theEnemy);
                        }
                    }
                }
                default -> theEnemy.setHealth(0); //cheat
            }
        }
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
