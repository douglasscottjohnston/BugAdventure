package Controller;

/**
 * The interface Directions.
 */
public interface Directions {
    /**
     * Gets direction.
     *
     * @param theDirectionNum the direction num
     * @return the direction
     */
    static Direction getDirection(final int theDirectionNum) {
        return Direction.getDirection(theDirectionNum);
    }

    /**
     * The enum Direction.
     */
    enum Direction {
        /**
         * North direction.
         */
        NORTH("0"),
        /**
         * South direction.
         */
        SOUTH("1"),
        /**
         * East direction.
         */
        EAST("2"),
        /**
         * West direction.
         */
        WEST("3");

        /**
         * The My direction.
         */
        String myDirection;

        /**
         * sets the direction.
         * @param theDirection
         */
        Direction(final String theDirection) {
            myDirection = theDirection;
        }

        /**
         * Gets direction.
         *
         * @param theDirection the the direction
         * @return the direction
         */
        static Direction getDirection(final int theDirection) {
            switch (theDirection) {
                case 0 -> {
                    return NORTH;
                }
                case 1 -> {
                    return SOUTH;
                }
                case 2 -> {
                    return EAST;
                }
                case 3 -> {
                    return WEST;
                }
                default -> throw new IllegalArgumentException(theDirection + " is not a possible direction");
            }
        }

        /**
         * Gets opposite direction.
         *
         * @param theDirection the the direction
         * @return the opposite direction
         */
        static Direction getOppositeDirection(final Direction theDirection) {
            switch(theDirection) {
                case NORTH -> {
                    return SOUTH;
                }
                case SOUTH -> {
                    return NORTH;
                }
                case EAST -> {
                    return WEST;
                }
                default -> {
                    return EAST;
                }
            }
        }
    }
}
