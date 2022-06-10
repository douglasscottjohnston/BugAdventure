package Model;

import java.io.Serializable;
import java.util.Random;

/**
 * The type Model utility.
 */
public class ModelUtility implements Serializable {
    private final StringBuilder mySB;
    private final Random myRandom;

    /**
     * Instantiates a new Model utility.
     */
    public ModelUtility() {
        mySB = new StringBuilder();
        myRandom = new Random();
    }


    /**
     * Gets the string builder.
     *
     * @return the string builder
     */
    public StringBuilder getStringBuilder() {
        return mySB;
    }

    /**
     * Append to the string builder.
     *
     * @param s the string
     */
    public void appendToBuilder(String s) {
        mySB.append(s);
    }

    /**
     * Converts the string builder to a string.
     *
     * @return the string
     */
    public String builderToString() {
        return mySB.toString();
    }

    /**
     * Converts the string builder to a string and clears the string builder.
     *
     * @return the string
     */
    public String builderToStringClear() {
        String out = builderToString();
        clearBuilder();
        return out;
    }

    /**
     * Clears the string builder.
     */
    public void clearBuilder() {
        mySB.setLength(0);
    }

    /**
     * Percent chance boolean.
     *
     * @param theChance the the chance
     * @return the boolean
     */
    public boolean percentChance(final float theChance) {
        return myRandom.nextFloat() <= theChance;
    }

    /**
     * Gets a random integer in the range between the two numbers.
     *
     * @param theLow  the low bound
     * @param theHigh the high bound
     * @return the random integer in range
     */
    public int getRandomInRange(final int theLow, final int theHigh) {
        return myRandom.nextInt(theHigh - theLow + 1) + theLow;
    }

    /**
     * Gets a random integer between zero and the passed integer.
     *
     * @param theHigh the high bound
     * @return the random integer
     */
    public int getRandom(int theHigh) {
        return getRandomInRange(0, theHigh);
    }

    /**
     * Gets a new illegal argument exception with the passed string as a message.
     *
     * @param theMessage the message to give the illegal argument exception
     * @return the new illegal
     */
    public IllegalArgumentException getNewIllegal(final String theMessage) {
        return new IllegalArgumentException(theMessage);
    }
}
