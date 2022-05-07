package Bugs;

public class PillBug extends HeroBug {
    private static final int MY_HEALTH = 100;
    private static final int MY_DEFENCE = 70;
    private static final Attack MY_ATTACK = new Attack("bite","",40, 100, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Roll Slam","",80, 70, false);
    private static final int MY_SPEED = 5;
    private static final int MY_CHANCE_TO_DODGE = 20;
    private static final boolean MY_RUN_AWAY = false;
    public PillBug(final String theName) {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_DEFENCE, MY_SPEED, MY_CHANCE_TO_DODGE, MY_RUN_AWAY, theName);
    }
}
