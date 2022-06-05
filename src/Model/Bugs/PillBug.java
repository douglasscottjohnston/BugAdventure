package Model.Bugs;

public class PillBug extends HeroBug {
    private static final int MY_HEALTH = 450;
    private static final int MY_ORIGINAL_HEALTH = 450;
    private static final int MY_DEFENCE = 50;
    private static final Attack MY_ATTACK = new Attack("bite",60, 100, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Roll Slam",80, 85, false);
    private static final int MY_SPEED = 3;
    private static final int MY_CHANCE_TO_DODGE = 20;
    private static final String MY_HERO_TYPE = "PillBug";
    public PillBug(final String theName) {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_CHANCE_TO_DODGE, theName, MY_HERO_TYPE);
    }
}
