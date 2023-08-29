package Model;


import Controller.Utility;
import Model.Bugs.HeroBug;
import Model.Bugs.MonsterBug;
import Model.Items.Item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The contents list for a room.
 * It is an aggregate of two arraylists of type MonsterBug and Item, as well as a hero bug and a pit.
 *
 * @author Douglas Johnston
 * @version 1.0
 */
public class RoomContentsList implements Serializable {

    private static final int FIRST = 0;

    private final ArrayList<MonsterBug> myMonsters;
    private final ArrayList<Item> myItems;
    private HeroBug myHero;
    private Pit myPit;


    /**
     * Instantiates a new Room contents list.
     */
    public RoomContentsList() {
        myMonsters = new ArrayList<>();
        myItems = new ArrayList<>();
    }

    /**
     * Adds the monster to the room contents list.
     *
     * @param theMonster the monster
     */
    public void addMonster(final MonsterBug theMonster) {
        myMonsters.add(theMonster);
    }

    /**
     * Adds an item to the room contents list.
     *
     * @param theItem the item
     */
    public void addItem(final Item theItem) {
        myItems.add(theItem);
    }

    /**
     * Adds the hero bug to the room.
     *
     * @param theHero the hero bug
     */
    public void addHero(final HeroBug theHero) {
        myHero = theHero;
    }

    /**
     * Adds a pit to the room contents list.
     */
    public void addPit() {
        myPit = new Pit();
    }

    /**
     * Gets the first monster in the room contents list then removes it from the list.
     *
     * @return the first monster in the list
     */
    public MonsterBug getAndRemoveMonster() {
        MonsterBug out = myMonsters.get(FIRST);
        myMonsters.remove(FIRST);
        return out;
    }

    /**
     * Gets the first item in the room contents list then removes it from the list.
     *
     * @return the first item in the list
     */
    public Item getAndRemoveItem() {
        Item out = myItems.get(FIRST);
        myItems.remove(FIRST);
        return out;
    }

    /**
     * Removes the hero from the list.
     */
    public void removeHero() {
        myHero = null;
    }

    /**
     * Gets the pit and removes it from the list.
     *
     * @return the pit and delete
     */
    public Pit getAndRemovePit() {
        Pit out = myPit;
        myPit = null;
        return out;
    }

    /**
     * Checks if the list contains a monster.
     *
     * @return true if the list contains a monster
     */
    public boolean containsMonster() {
        return myMonsters.size() > 0;
    }

    /**
     * Checks if the list contains an item.
     *
     * @return true if the list contains an item
     */
    public boolean containsItem() {
        return myItems.size() > 0;
    }

    /**
     * Checks if the list contains a hero.
     *
     * @return true if the list contains a hero
     */
    public boolean containsHero() {
        return myHero != null;
    }

    /**
     * Checks if the list contains a pit.
     *
     * @return true if the list contains a pit
     */
    public boolean containsPit() {
        return myPit != null;
    }


    /**
     * Checks if the room contains any monsters, items, and pits.
     *
     * @return true if the room doesn't contain any monsters, items, and pits
     */
    public boolean isEmpty() {
        return myMonsters.size() == 0 && myItems.size() == 0 && myPit == null;
    }

    @Override
    public String toString() {
        Utility util = new Utility();
        util.appendToBuilder("[");
        if(containsMonster()){
            String monstersStr = myMonsters.toString();
            util.appendToBuilder(monstersStr.substring(1, monstersStr.length() - 1));
            util.appendToBuilder(",");
        }

        if(containsHero()){
            util.appendToBuilder(myHero.toString());
            util.appendToBuilder(",");
        }

        if(containsItem()) {
            String itemsStr = myItems.toString();
            util.appendToBuilder(itemsStr.substring(1, itemsStr.length() - 1));
            util.appendToBuilder(",");
        }

        if(containsPit()) {
            util.appendToBuilder(myPit.toString());
        }

        if(util.getStringBuilder().lastIndexOf(",") == util.getStringBuilder().length() - 1) {
            util.getStringBuilder().deleteCharAt(util.getStringBuilder().lastIndexOf(","));
        }

        util.appendToBuilder("]");

        return util.builderToStringClear();
    }
}
