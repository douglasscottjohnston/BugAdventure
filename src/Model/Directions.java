package Model;

public interface Directions {
    enum Direction {
        NORTH(0),
        SOUTH(1),
        EAST(2),
        WEST(3);

        int myDirection;

        Direction(final int theDirection) {
            myDirection = theDirection;
        }
    }

    static Direction getDirection(final int theDirection) {
        switch(theDirection) {
            case 0:
                return Direction.NORTH;
            case 1:
                return Direction.SOUTH;
            case 3:
                return Direction.EAST;
            case 4:
                return Direction.WEST;
            default:
                throw new IllegalArgumentException(theDirection + " is not a Direction");
        }
    }

    static Direction getDirection(final String theDirection) {
        switch(theDirection.toUpperCase().strip()) {
            case "NORTH":
                return Direction.NORTH;
            case "SOUTH":
                return Direction.SOUTH;
            case "EAST":
                return Direction.EAST;
            case "WEST":
                return Direction.WEST;
            default:
                throw new IllegalArgumentException(theDirection + " is not a direction");
        }
    }


}