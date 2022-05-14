package Model.Bugs;

public class LadyBug extends HeroBug {
    private static final int MY_HEALTH = 100;
    private static final int MY_ORIGINAL_HEALTH = 100;
    private static final int MY_DEFENCE = 30;
    private static final Attack MY_ATTACK = new Attack("bite","",40, 100, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Super bite","",80, 70, false);
    private static final int MY_SPEED = 5;
    private static final int MY_CHANCE_TO_DODGE= 20;
    public LadyBug(final String theName) {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_CHANCE_TO_DODGE,  theName);
    }
}
