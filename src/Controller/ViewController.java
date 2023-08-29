package Controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

/**
 * The type View controller.
 */
abstract class ViewController {
    static private HashMap<String, Pane> myViewMap = new HashMap<>();
    static private Scene myMain;

    /**
     * Sets main.
     *
     * @param theMain the the main
     */
    protected void setMain(final Scene theMain) {
        myMain = theMain;
    }

    /**
     * Add screen.
     *
     * @param theName the name
     * @param thePane the pane
     */
    protected void addScreen(final String theName, final Pane thePane) {
        myViewMap.put(theName, thePane);
    }

    /**
     * Remove screen.
     *
     * @param theName the  name
     */
    protected void removeScreen(final String theName) {
        myViewMap.remove(theName);
    }

    /**
     * Activate.
     *
     * @param theName the  name
     */
    protected void activate(final String theName) {
        myMain.setRoot(myViewMap.get(theName));
    }
}
