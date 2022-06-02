package Model.Bugs;

public class MonsterBug extends Bug {


    public MonsterBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theOriginalHealth, final int theDefense,
                      final int theSpeed, final String theName) {
        super(theAttack, theSpecialAttack, theHealth, theOriginalHealth, theDefense, theSpeed, theName);


    }


    public void attack(final HeroBug theEnemy) {
        int numberOfAttacks = getSpeed() / theEnemy.getSpeed();

        if (numberOfAttacks == 0) {
            numberOfAttacks = 1;
        }
        while (numberOfAttacks > 0 && theEnemy.isAlive()) {
            if(MY_RANDOM.nextInt(101) > theEnemy.getChanceToDodge()) {
                if (MY_RANDOM.nextInt(101) <= 50) {
                    super.attack(theEnemy);
                } else {
                    super.specialAttack(theEnemy);
                }

            }
            else {
                System.out.println(theEnemy.getName() + " dodged the attack");
            }
            numberOfAttacks--;
        }
    }

    @Override
    public boolean equals(final Object theObject) {
        return theObject instanceof MonsterBug;
    }
}
