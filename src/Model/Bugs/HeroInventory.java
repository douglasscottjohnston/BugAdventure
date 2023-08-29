package Model.Bugs;

import Controller.Utility;
import Model.Items.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The type Hero inventory.
 */
public class HeroInventory implements Serializable {
    private final Hashtable<ITEM_KEY, Integer> myItems;

    /**
     * The enum Item key.
     */
    public enum ITEM_KEY {
        /**
         * Acorn item key.
         */
        ACORN("Acorn"),
        /**
         * Apple item key.
         */
        APPLE("Apple"),
        /**
         * Durian item key.
         */
        DURIAN("Durian"),
        /**
         * Sugar item key.
         */
        SUGAR("Sugar"),
        /**
         * The Water melon.
         */
        WATER_MELON("Watermelon Bomb");

        private String myName;
        ITEM_KEY(final String theName) {
            myName = theName;
        }

        /**
         * Get item key.
         *
         * @param theName the name
         * @return the item key
         */
        public static ITEM_KEY get(final String theName) {
            for(ITEM_KEY key : ITEM_KEY.values()) {
                if(key.myName.equals(theName)) {
                    return key;
                }
            }
            return null;
        }

        /**
         * Gets item.
         *
         * @return the item
         */
        public Item getItem() {
            switch(this) {
            case ACORN:
                return new Acorn();
            case APPLE:
                return new Apple();
            case DURIAN:
                return new Durian();
            case SUGAR:
                return new Sugar();
            default:
                return new WatermelonBomb();
            }
        }

        @Override
        public String toString() {
            return myName;
        }
    }

    /**
     * Instantiates a new Hero inventory.
     */
    public HeroInventory() {
        myItems = new Hashtable<>();
    }

    /**
     * Add item to hero inventory.
     *
     * @param theItem the the item
     */
    public void addItem(final Item theItem) {
        myItems.put(ITEM_KEY.get(theItem.getName()), myItems.getOrDefault(ITEM_KEY.get(theItem.getName()), 0) + 1);
    }

    /**
     * Contains item boolean.
     *
     * @param theItem the item
     * @return the boolean
     */
    public boolean containsItem(final Item theItem) {
        return myItems.contains(ITEM_KEY.get(theItem.getName()));
    }

    /**
     * Use item.
     *
     * @param theItem the item
     * @param theBug  the bug
     */
    public void useItem(final Item theItem, final Bug theBug) {
        if(myItems.getOrDefault(ITEM_KEY.get(theItem.getName()), 0) > 0){
            theItem.effect(theBug);
            myItems.put(ITEM_KEY.get(theItem.getName()), myItems.get(ITEM_KEY.get(theItem.getName())) - 1);
            if(myItems.get(ITEM_KEY.get(theItem.getName())) == 0) {
                myItems.remove(ITEM_KEY.get(theItem.getName()));
            }
        } else {
            System.out.println("There is no " + theItem.getName() + " in the inventory");
        }
    }

    /**
     * Select item.
     *
     * @return the item
     */
    public Item selectItem() {
        Utility util = new Utility();
        System.out.println("Which Item would you like to use?");
        int selection = 1;
        ArrayList<ITEM_KEY> itemList = new ArrayList<>(myItems.keySet());
        for(ITEM_KEY i : itemList) {
            util.appendToBuilder("(");
            util.appendToBuilder(Integer.toString(selection));
            util.appendToBuilder(") ");
            util.appendToBuilder(i.getItem().getName());
            util.appendToBuilder("[in inventory: ");
            util.appendToBuilder(myItems.get(i).toString());
            util.appendToBuilder("]");
            System.out.println(util.builderToStringClear());
            selection++;
        }
        return itemList.get(util.scanNextInt() - 1).getItem();
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return myItems.isEmpty();
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public Hashtable<ITEM_KEY, Integer> getItems() {
        return myItems;
    }
}
