package Model.Bugs;

import Model.Items.Item;
import Controller.Utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class HeroInventory implements Serializable {
    private final Hashtable<Item, Integer> myItems;

    public HeroInventory() {
        myItems = new Hashtable<>();
    }

    public void addItem(final Item theItem) {
        myItems.put(theItem, myItems.getOrDefault(theItem, 0) + 1);
    }

    public boolean containsItem(final Item theItem) {
        return myItems.contains(theItem);
    }

    public void useItem(final Item theItem, final Bug theBug) {
        if(myItems.getOrDefault(theItem, 0) > 0){
            theItem.effect(theBug);
            myItems.put(theItem, myItems.get(theItem) - 1);
            if(myItems.get(theItem) == 0) {
                myItems.remove(theItem);
            }
        } else {
            System.out.println("There is no " + theItem.getName() + " in the inventory");
        }
    }

    public Item selectItem() {
        Utility util = new Utility();
        System.out.println("Which Item would you like to use?");
        int selection = 1;
        ArrayList<Item> itemList = new ArrayList<>(myItems.keySet());
        for(Item i : itemList) {
            util.appendToBuilder("(");
            util.appendToBuilder(Integer.toString(selection));
            util.appendToBuilder(") ");
            util.appendToBuilder(i.getName());
            util.appendToBuilder("[in inventory: ");
            util.appendToBuilder(myItems.get(i).toString());
            util.appendToBuilder("]");
            System.out.println(util.builderToStringClear());
            selection++;
        }
        return itemList.get(util.scanNextInt() - 1);
    }

    public boolean isEmpty() {
        return myItems.isEmpty();
    }
}
