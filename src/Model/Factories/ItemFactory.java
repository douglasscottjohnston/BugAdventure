package Model.Factories;

import Model.Items.*;
import Model.Utility;

public class ItemFactory {
    private static final int ITEM_HIGH = 3; // The high bound to generate an item
    private final Utility myUtility;

    public ItemFactory() {
        myUtility = new Utility();
    }

    public Apple makeApple() {
        return new Apple();
    }

    public Bomb makeBomb() {
        return new Bomb();
    }

    public Sugar makeSugar() { return new Sugar(); }

    public Acorn makeAcorn() { return new Acorn(); }

    public Item makeRandomItem() {
        return switch(myUtility.getRandom(ITEM_HIGH)) {
            default -> makeApple();
            case 1 -> makeBomb();
            case 2 -> makeSugar();
            case 3 -> makeAcorn();

        };
    }
}
