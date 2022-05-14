package Model.Bugs;

public class Ant extends HeroBug {
    private static final int MY_HEALTH = 10000;
    private static final int MY_ORIGINAL_HEALTH = 10000;
    private static final int MY_DEFENCE = 30;
    private static final Attack MY_ATTACK = new Attack("bite","",65, 100, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Super bite","",80, 80, false);
    private static final int MY_SPEED = 5;
    private static final int MY_CHANCE_TO_DODGE = 30;
    private static final boolean MY_RUN_AWAY = false;
    public Ant(final String theName) {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH,MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_CHANCE_TO_DODGE, MY_RUN_AWAY, theName);
    }

}
