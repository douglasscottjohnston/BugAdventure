package Bugs;

abstract class MonsterBug extends Bug {

    private int myChanceToHeal;
    protected MonsterBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theDefense,
                         final int theSpeed, final int theChanceToDodge, final boolean theRunAway, final String theName) {
        super(theAttack, theSpecialAttack, theHealth, theDefense, theSpeed);


    }
}
