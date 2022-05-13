package Model.Factories;

import Model.Items.Apple;
import Model.Items.Item;
import Model.Utility;

public class ItemFactory {
    private static final int ITEM_HIGH = 0; // The high bound to generate an item
    private final Utility myUtility;

    public ItemFactory() {
        myUtility = new Utility();
    }

    public Apple makeApple() {
        return new Apple();
    }

    public Item makeRandomItem() {
        switch(myUtility.getRandom(ITEM_HIGH)) {
        default:
            return new Apple();
        }
    }
}
