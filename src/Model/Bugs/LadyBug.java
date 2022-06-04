package Model.Bugs;

public class LadyBug extends HeroBug {
    private static final int MY_HEALTH = 500;
    private static final int MY_ORIGINAL_HEALTH = 500;
    private static final int MY_DEFENCE = 20;
    private static final Attack MY_ATTACK = new Attack("bite",50, 100, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Wing Attack",75, 70, false);
    private static final int MY_SPEED = 6;
    private static final int MY_CHANCE_TO_DODGE= 20;
    public LadyBug(final String theName) {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_CHANCE_TO_DODGE, theName);
    }
}
