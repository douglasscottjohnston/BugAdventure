package Model.Bugs;

public class Centipede extends MonsterBug {
    private static final int MY_HEALTH = 150;
    private static final int MY_DEFENCE = 20;
    private static final Attack MY_ATTACK = new Attack("Pinch","",40, 100, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("100 Leg Roundhouse","",100, 70, false);
    private static final int MY_SPEED = 6;
    private static final String MY_NAME = "Centipede";
    public Centipede() {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_DEFENCE, MY_SPEED, MY_NAME);
    }
}
