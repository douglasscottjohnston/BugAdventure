package Model.Bugs;

public class Spider extends MonsterBug {
    private static final int MY_HEALTH = 125;
    private static final int MY_ORIGINAL_HEALTH = 125;
    private static final int MY_DEFENCE = 40;
    private static final Attack MY_ATTACK = new Attack("bite",70, 90, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Super bite",85, 70, false);
    private static final int MY_SPEED = 5;
    private static final String MY_NAME = "Spider";
    public Spider() {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_NAME);
    }
}
