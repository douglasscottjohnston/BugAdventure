package Bugs;

public class Mosster extends MonsterBug {
    private static final int MY_HEALTH = 1000;
    private static final int MY_DEFENCE = 50;
    private static final Attack MY_ATTACK = new Attack("Moss Attack","",500, 100, true);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Ultra Moss Absorb","",600, 50, true);
    private static final int MY_SPEED = 20;
    private static final String MY_NAME = "Mosster";
    public Mosster() {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_DEFENCE, MY_SPEED, MY_NAME);
    }

}
