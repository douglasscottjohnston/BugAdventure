package Model.Items;

import Model.Bugs.Bug;

import java.io.Serializable;

public abstract class Item implements Serializable {
    private final String myName;
    private final String myMessage;

    private final boolean myFriendly;

    protected Item(final String theName, final boolean theFriendly, final String theMessage) {
        myName = theName;
        myMessage = theMessage;
        myFriendly = theFriendly;
    }

    public String getName() {
        return myName;
    }


    public boolean isFriendly() {
        return myFriendly;
    }

    public abstract void effect(Bug theHero);

    @Override
    public String toString() {
        return myName;
    }

    @Override
    public boolean equals(final Object theObject) {
        return theObject instanceof Item && myName.equals(((Item)theObject).getName());
    }

    public String getMessage() {
        return myMessage;
    }
}
