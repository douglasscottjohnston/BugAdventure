package Model;

import java.util.Random;
import java.util.Scanner;

/**
 * The type Utility.
 */
public class Utility {
    private StringBuilder mySB;
    private Scanner myScan;
    private Random myRandom;
    private IllegalArgumentException myIllegal;

    /**
     * Instantiates a new Utility.
     */
    public Utility() {
        mySB = new StringBuilder();
        myScan = new Scanner(System.in);
        myRandom = new Random();
        myIllegal = new IllegalArgumentException();
    }

    /**
     * Gets the string builder.
     *
     * @return the string builder
     */
    public StringBuilder getStringBuilder() {
        return mySB;
    }

    public Scanner getScanner() {
        return myScan;
    }

    public String scanNext() {
        return myScan.next();
    }

    public String scanNextLine() {
        return myScan.nextLine();
    }

    public int scanNextInt() {
        return myScan.nextInt();
    }

    public void closeScanner() {
        myScan.close();
    }

    /**
     * Returns true if the percent chance is greater than or equal to the next random float between 0.0 and 1.0.
     *
     * @param theChance true or false if the percent chance was higher
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
