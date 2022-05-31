package Model.Bugs;

public class Mosster extends MonsterBug {
    private static final int MY_HEALTH = 300;
    private static final int MY_ORIGINAL_HEALTH = 300;
    private static final int MY_DEFENCE = 40;
    private static final Attack MY_ATTACK = new Attack("Moss Attack","",75, 85, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Ultra Moss Absorb","",100, 60, true);
    private static final int MY_SPEED = 3;
    private static final String MY_NAME = "Mosster";
    public Mosster() {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_NAME);
    }

}
