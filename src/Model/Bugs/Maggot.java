package Model.Bugs;

public class Maggot extends MonsterBug {
    private static final int MY_HEALTH = 250;
    private static final int MY_ORIGINAL_HEALTH = 250;
    private static final int MY_DEFENCE = 10;
    private static final Attack MY_ATTACK = new Attack("bite","",40, 100, false);
    private static final Attack MY_SPECIAL_ATTACK = new Attack("Super bite","",80, 70, false);
    private static final int MY_SPEED = 2;
    private static final String MY_IMAGE_NAME = "maggot.png";
    private static final String MY_NAME = "Maggot";
    public Maggot() {
        super(MY_ATTACK, MY_SPECIAL_ATTACK, MY_HEALTH, MY_ORIGINAL_HEALTH, MY_DEFENCE, MY_SPEED, MY_IMAGE_NAME, MY_NAME);
    }
}
