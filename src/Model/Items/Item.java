package Model.Items;

public abstract class Item {
    private String myName;

    protected Item(final String theName) {
        myName = theName;
    }


    public void useItem() {

    }

    public String getName() {
        return myName;
    }
}
