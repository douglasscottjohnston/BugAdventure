package Model;

import java.util.Random;

public class Utility {
    private StringBuilder mySB;
    private Random myRandom;
    private IllegalArgumentException myIllegal;

    public Utility() {
        mySB = new StringBuilder();
        myRandom = new Random();
        myIllegal = new IllegalArgumentException();
    }

    public StringBuilder getStringBuilder() {
        return mySB;
    }

    public boolean percentChance(final float theChance) {
        return myRandom.nextFloat() <= theChance;
    }

    public int getRandomInRange(final int theLow, final int theHigh) {
        return myRandom.nextInt(theHigh - theLow + 1) + theLow;
    }

    public IllegalArgumentException getNewIllegal(final String theMessage) {
        return new IllegalArgumentException(theMessage);
    }
}
