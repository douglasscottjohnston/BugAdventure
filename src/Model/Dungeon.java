package Model;

import Bugs.Attack;
import Bugs.Spider;
import Model.Rooms.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Dungeon {
    private Room[][] myDungeon;
    private static final int DUNGEON_HEIGHT = 10;
    private static final int DUNGEON_WIDTH = 10;

    public Dungeon() {
        myDungeon = generateDungeon();
    }

    private Room[][] generateDungeon() {
        Room[][] dungeon = new Room[DUNGEON_HEIGHT][DUNGEON_WIDTH];


        ArrayList<Object> contents = new ArrayList<Object>();
        contents.add(new Spider(new Attack("hi", "hi", 10), new Attack("hi", "hi", 10), 10, 10, 10));
        //generate the borders
        //generate the top border
        for (int i = 0; i < DUNGEON_WIDTH; i++) {
            dungeon[0][i] = new TopBorderRoom(contents);
        }

        //generate bottom border
        for (int i = 0; i < DUNGEON_WIDTH; i++) {
            dungeon[DUNGEON_HEIGHT - 1][i] = new BottomBorderRoom(contents);
        }

        //generate left border
        for (int i = 0; i < DUNGEON_HEIGHT; i++) {
            dungeon[i][0] = new LeftBorderRoom(contents);
        }

        //generate right border
        for (int i = 0; i < DUNGEON_HEIGHT; i++) {
            dungeon[i][DUNGEON_WIDTH - 1] = new RightBorderRoom(contents);
        }

        return dungeon;
    }

    /**
     * Returns a string representation of the dungeon
     * (needs to be implemented)
     *
     * @return the dungeon represented as a String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < DUNGEON_HEIGHT; i++) {
            sb.append(Arrays.toString(myDungeon[i]));
        }

        return sb.toString();
    }
}
