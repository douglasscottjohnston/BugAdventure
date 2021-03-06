package Controller;

public interface Directions {
    static Direction getDirection(final int theDirectionNum) {
        return Direction.getDirection(theDirectionNum);
    }

    enum Direction {
        NORTH("0"),
        SOUTH("1"),
        EAST("2"),
        WEST("3");

        String myDirection;

        Direction(final String theDirection) {
            myDirection = theDirection;
        }

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
