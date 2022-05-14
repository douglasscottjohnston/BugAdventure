package Model.Bugs;

public class Spider extends MonsterBug {
    private static final int MY_HEALTH = 100;
    private static final int MY_ORIGINAL_HEALTH = 100;
    private static final int MY_DEFENCE = 50;
    private static final Attack MY_ATTACK = new Attack("bite","",40, 100, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Super bite","",80, 70, false);
    private static final int MY_SPEED = 6;
    private static final String MY_NAME = "Spider";
    public Spider() {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_NAME);
    }
}
