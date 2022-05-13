package Model.Items;

public abstract class Item {
    private String myName;

    protected Item(final String theName) {
        myName = theName;
    }

    public String getName() {
        return myName;
    }

    @Override
    public String toString() {
        return myName;
    }
}
