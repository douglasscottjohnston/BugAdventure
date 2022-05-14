package Model.Bugs;

import Model.Items.Apple;
import Model.Items.Item;

import java.util.ArrayList;

public abstract class HeroBug extends Bug {

    private int myChanceToDodge;
    private boolean myRunAway;
    private int mySpecialSkillChance;
    private final ArrayList<Item> myInvintory;

    public HeroBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theOriginalHealth, final int theDefense,
                      final int theSpeed, final int theChanceToDodge, final boolean theRunAway, final String theName) {
        super(theAttack, theSpecialAttack, theHealth, theOriginalHealth, theDefense, theSpeed, theName);

        setChanceToDodge(theChanceToDodge);
        setRunAway(theRunAway);
        setName(theName);
        myInvintory = new ArrayList<>();
    }


    public void attack(final Bug theEnemy, final Bug theHero) {
        int attackChoice;
        int numberOfAttacks = getSpeed() / theEnemy.getSpeed();

        if (numberOfAttacks == 0) {
            numberOfAttacks = 1;
        }
        while (numberOfAttacks > 0 && theEnemy.isAlive()) {
            attackChoice = getChoice();

            if (attackChoice == 1) {
                super.attack(theEnemy);
                numberOfAttacks--;
            } else if (attackChoice == 2) {
                super.specialAttack(theEnemy);
                numberOfAttacks--;
            }else if (attackChoice == 3) {
                if(myInvintory.isEmpty()) {
                    System.out.println("You have no items.");
                }else {
                    Item item = selectItem();
                    if (item.getMyFriendly()) {
                        useItem(item, theHero);
                    } else {
                        useItem(item, theEnemy);
                    }
                }

            } else { //cheat
                theEnemy.setHealth(0);
            }
        }
    }

    public void pickUpItem(Item theItem) {
        myInvintory.add(theItem);
        System.out.println(getName() + " picked up a " + theItem.getName());
    }
    public Item selectItem() {

        System.out.println("Which Item would you like to use?");
        for (int i = 0; i < myInvintory.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + myInvintory.get(i));
        }
        int choice = input.nextInt();
        Item item = myInvintory.get(choice - 1);

        return item;
    }
    public void useItem(final Item theItem, final Bug theBug) {

            theItem.effect(theBug);
            myInvintory.remove(theItem);

    }

    protected void setChanceToDodge(final int theChanceToDodge) {
        if (theChanceToDodge <= 0 || theChanceToDodge > 100) {
            throw new IllegalArgumentException("Chance to dodge must be greater than 0 and less than 100. It was" + theChanceToDodge);
        }
        myChanceToDodge = theChanceToDodge;
    }

    protected void setRunAway(final boolean theRunAway) {
        myRunAway = theRunAway;
    }

    protected int getChanceToDodge() {
        return myChanceToDodge;
    }

    private final int getChoice() {
        System.out.println("Choose (1) for normal attack, choose (2) for special attack, choose (3) to use an item.");
        return input.nextInt();
    }
    public boolean hasItem() {
        return !myInvintory.isEmpty();
    }


}
