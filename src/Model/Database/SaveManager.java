package Model.Database;

import Model.Dungeon;

import java.io.*;

public abstract class SaveManager {
    private static final String SAVE_FILE_NAME = "Dungeon.bin";
    private static boolean mySavedOnce = false;
    
    public static void saveDungeon(final Dungeon theDungeon) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_NAME, false));
            oos.writeObject(theDungeon);
            mySavedOnce = true;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static Dungeon loadDungeon() {
        Dungeon out = null;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE_NAME));
            out = (Dungeon)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return out;
    }

    public static boolean hasSavedOnce() {
        return mySavedOnce;
    }
}
