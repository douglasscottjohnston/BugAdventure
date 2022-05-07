package Model.Bugs;

abstract class MonsterBug extends Bug {

    protected MonsterBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theDefense, final int theSpeed) {
        super(theAttack, theSpecialAttack, theHealth, theDefense, theSpeed);
    }
}
