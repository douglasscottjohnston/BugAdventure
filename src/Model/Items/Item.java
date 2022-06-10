package Model.Items;

import Model.Bugs.Bug;

import java.io.Serializable;

/**
 * The type Item.
 */
public abstract class Item implements Serializable {
    /**name of item*/
    private final String myName;
    /** message of using item*/
    private final String myMessage;

    /** if the item is friendly you can use it whenever, if not, only in battle against an enemy. */
    private final boolean myFriendly;

    /**
     * Instantiates a new Item.
     *
     * @param theName     the name
     * @param theFriendly the friendly
     * @param theMessage  the message
     */
    protected Item(final String theName, final boolean theFriendly, final String theMessage) {
        myName = theName;
        myMessage = theMessage;
        myFriendly = theFriendly;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return myName;
    }


    /**
     * Is friendly boolean.
     *
     * @return the boolean (true if friendly item)
     */
    public boolean isFriendly() {
        return myFriendly;
    }

    /**
     * Effect of the item.
     *
     * @param theHero the the hero
     */
    public abstract void effect(Bug theHero);

    /**
     * returns string of item name
     * @return name of item
     */
    @Override
    public String toString() {
        return myName;
    }

    /**
     * checks if object is an item
     * @param theObject
     * @return boolean
     */
    @Override
    public boolean equals(final Object theObject) {
        return theObject instanceof Item && myName.equals(((Item)theObject).getName());
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return myMessage;
    }
}
