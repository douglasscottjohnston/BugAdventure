package Model.Bugs;

public class Ant extends HeroBug {
    private static final int MY_HEALTH = 600;
    private static final int MY_ORIGINAL_HEALTH = 600;
    private static final int MY_DEFENCE = 30;
    private static final Attack MY_ATTACK = new Attack("bite",70, 100, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Super bite", 90, 80, false);
    private static final int MY_SPEED = 4;
    private static final int MY_CHANCE_TO_DODGE = 30;
    private static final String MY_HERO_TYPE = "Ant";
    public Ant(final String theName) {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH,MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_CHANCE_TO_DODGE, theName, MY_HERO_TYPE);
    }

}
