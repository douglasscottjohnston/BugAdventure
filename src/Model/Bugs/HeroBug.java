package Model.Bugs;

abstract class HeroBug extends Bug {

    private int myChanceToDodge;
    private boolean myRunAway;
    private int mySpecialSkillChance;
    private String myName;
    protected HeroBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theDefense,
                      final int theSpeed, final int theChanceToDodge, final boolean theRunAway, final int theSpecialSkillChance, final String theName) {
        super(theAttack, theSpecialAttack, theHealth, theDefense, theSpeed);

        setChanceToDodge(theChanceToDodge);
        setRunAway(theRunAway);
        setSpecialSkillChance(theSpecialSkillChance);
        setName(theName);
    }

    public void attack(final Bug theEnemy) {
        int attackChoice;
        int numberOfAttacks = getSpeed() / theEnemy.getSpeed();

        if (numberOfAttacks == 0) {
            numberOfAttacks = 1;
        }
        while (numberOfAttacks > 0 && theEnemy.isAlive()) {
            attackChoice = getChoice();

            if (attackChoice == 1) {
                super.attack(theEnemy);
            }
            else if (attackChoice == 2) {
                if (MY_RANDOM.nextInt(101) <= mySpecialSkillChance) {
                    super.specialAttack(theEnemy);
                }
            }
        }
    }

    protected void setChanceToDodge(final int theChanceToDodge) {
        if (theChanceToDodge <= 0 || theChanceToDodge > 100) {
            throw new IllegalArgumentException("Chance to dodge must be greater than 0 and less than 100. It was" + theChanceToDodge);
        }
        myChanceToDodge = theChanceToDodge;
    }

    protected void setSpecialSkillChance(final int theSpecialSkillChance) {
        if (theSpecialSkillChance <= 0 || theSpecialSkillChance > 100) {
            throw new IllegalArgumentException("Chance hit special attack must be greater than 0 and less than 100. It was" + theSpecialSkillChance);
        }
        mySpecialSkillChance = theSpecialSkillChance;
    }

    protected void setRunAway(final boolean theRunAway) {
        myRunAway = theRunAway;
    }

    protected void setName(final String theName) {
        myName = theName;
    }

    private final int getChoice() {
        System.out.println("Choose (1) for normal attack, choose (2) for special attack, choose (3) to run away.");
        return input.nextInt();
    }

}
