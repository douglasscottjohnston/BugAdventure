package Model.Bugs;

public abstract class MonsterBug extends Bug {


    protected MonsterBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theOriginalHealth, final int theDefense,
                         final int theSpeed, final String theImageName, final String theName) {
        super(theAttack, theSpecialAttack, theHealth, theOriginalHealth, theDefense, theSpeed, theImageName, theName);


    }


    public int attack(final HeroBug theEnemy) {
        int numberOfAttacks = getSpeed() / theEnemy.getSpeed();
        int damageDealt = 0;

        if (numberOfAttacks == 0) {
            numberOfAttacks = 1;
        }
        while (numberOfAttacks > 0 && theEnemy.isAlive()) {
            if(MY_RANDOM.nextInt(101) > theEnemy.getChanceToDodge()) {
                if (MY_RANDOM.nextInt(101) <= 50) {
                    damageDealt += super.attack(theEnemy);
                } else {
                    super.specialAttack(theEnemy);
                }
            }
            numberOfAttacks--;
        }
        return damageDealt;
    }

    @Override
    public boolean equals(final Object theObject) {
        return theObject instanceof MonsterBug;
    }
}
