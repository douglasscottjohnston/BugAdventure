package Model.Items;

import Model.Bugs.Bug;
import Model.Bugs.HeroBug;

public abstract class Item {
    private String myName;

    private boolean myFriendly;

    protected Item(final String theName, final boolean theFriendly) {

        myName = theName;
        myFriendly = theFriendly;
    }

    public String getName() {
        return myName;
    }

    @Override
    public String toString() {
        return myName;
    }

    public boolean isFriendly() {
        return myFriendly;
    }

    public abstract void effect(Bug theHero);
}
