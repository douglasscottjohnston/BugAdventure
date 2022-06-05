package Model.Database;

import Model.Dungeon;

import java.io.*;

/**
 * The SaveManager class is an abstract class containing the methods to save and load the dungeon to
 * the Dungeon.bin save file
 *
 * @author Douglas Johnston
 * @version 1.0
 */
public abstract class SaveManager {
    private static final String SAVE_FILE_NAME = "Dungeon.bin";
    private static final File SAVE_FILE = new File(SAVE_FILE_NAME);

    /**
     * Saves the passed dungeon to the Dungeon.bin save file.
     *
     * @param theDungeon the dungeon
     */
    public static void saveDungeon(final Dungeon theDungeon) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_NAME, false));
            oos.writeObject(theDungeon);
            oos.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Loads the dungeon from the Dungeon.bin save file and returns it.
     *
     * @return the dungeon
     */
    public static Dungeon loadDungeon() {
        Dungeon out = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE_NAME));
            out = (Dungeon)ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return out;
    }

    /**
     * Returns true if the dungeon has already been saved once.
     *
     * @return true if the dungeon has already been saved once
     */
    public static boolean hasSavedOnce() {
        return SAVE_FILE.exists();
    }
}