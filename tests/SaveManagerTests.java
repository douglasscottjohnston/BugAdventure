import Controller.Directions;
import Model.Bugs.HeroBug;
import Model.Bugs.LadyBug;
import Model.Database.SaveManager;
import Model.Dungeon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaveManagerTests {

    private Dungeon myD;
    private final HeroBug myHero = new LadyBug("Bobfrey");

    @Test
    public void testSavedOnce() {
        myD = new Dungeon(myHero);

        SaveManager.saveDungeon(myD);

        assertTrue(SaveManager.hasSavedOnce());
    }

    @Test
    public void testSaveAndLoad() {
        myD = new Dungeon(myHero);


        myD.moveInDirection(getDirection());
        Directions.Direction dir = getDirection();
        SaveManager.saveDungeon(myD);
        myD.moveInDirection(dir);
        String contents = myD.getCurrent().getContents().toString();
        myD = SaveManager.loadDungeon();
        myD.moveInDirection(dir);

        assertEquals(myD.getCurrent().getContents().toString(), contents);
    }

    @Test
    public void testSameExitPath() {
        myD = new Dungeon(myHero);

        String exit = myD.getExitPathString();

        SaveManager.saveDungeon(myD);

        myD = SaveManager.loadDungeon();

        assertEquals(myD.getExitPathString(), exit);
    }

    private Directions.Direction getDirection() {
        if(myD.getCurrent().hasNorth()) {
            return Directions.Direction.NORTH;
        } else if(myD.getCurrent().hasSouth()) {
            return Directions.Direction.SOUTH;
        } else if(myD.getCurrent().hasEast()) {
            return Directions.Direction.EAST;
        } else if(myD.getCurrent().hasWest()){
            return Directions.Direction.WEST;
        }
        return Directions.Direction.NORTH;
    }
}
