package Model.Factories;

import Model.Items.*;
import Model.ModelUtility;

import java.io.Serializable;

public class ItemFactory implements Serializable {
    private static final int ITEM_HIGH = 4; // The high bound to generate an item
    private final ModelUtility myUtility;

    public ItemFactory() {
        myUtility = new ModelUtility();
    }

    public Apple makeApple() {
        return new Apple();
    }

    public Bomb makeBomb() {
        return new Bomb();
    }

    public Sugar makeSugar() { return new Sugar(); }

    public Acorn makeAcorn() { return new Acorn(); }

    public ChocolateMilk makeChocolateMilk() { return new ChocolateMilk(); }

    public Item makeRandomItem() {
        return switch(myUtility.getRandom(ITEM_HIGH)) {
            default -> makeApple();
            case 1 -> makeBomb();
            case 2 -> makeSugar();
            case 3 -> makeAcorn();
            case 4 -> makeChocolateMilk();

        };
    }
}
