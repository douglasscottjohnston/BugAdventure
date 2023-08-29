package Model.Bugs;

/**
 * The type Monster bug.
 */
public class MonsterBug extends Bug {


    /**
     * Instantiates a new Monster bug.
     *
     * @param theAttack         the  attack
     * @param theSpecialAttack  the  special attack
     * @param theHealth         the  health
     * @param theOriginalHealth the  original health
     * @param theDefense        the  defense
     * @param theSpeed          the  speed
     * @param theName           the  name
     */
    public MonsterBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theOriginalHealth, final int theDefense,
                      final int theSpeed, final String theName) {
        super(theAttack, theSpecialAttack, theHealth, theOriginalHealth, theDefense, theSpeed, theName);
    }
    /**
     * This method allows monsters to attack.
     *
     * @param theEnemy the  enemy
     * @return the damage dealt
     */
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

    /**
     * this method check if object is a monsterBug
     * @param theObject
     * @return true if monsterbug
     */
    @Override
    public boolean equals(final Object theObject) {
        return theObject instanceof MonsterBug;
    }
}
