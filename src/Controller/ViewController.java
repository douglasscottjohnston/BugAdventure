package Controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

abstract class ViewController {
    static private HashMap<String, Pane> myViewMap = new HashMap<>();
    static private Scene myMain;

    protected void setMain(final Scene theMain) {
        myMain = theMain;
    }

    protected void addScreen(final String theName, final Pane thePane) {
        myViewMap.put(theName, thePane);
    }

    protected void removeScreen(final String theName) {
        myViewMap.remove(theName);
    }

    protected void activate(final String theName) {
        myMain.setRoot(myViewMap.get(theName));
    }
}
