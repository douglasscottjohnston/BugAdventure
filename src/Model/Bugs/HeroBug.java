package Model.Bugs;

abstract class HeroBug extends Bug {
    protected HeroBug(final Attack theAttack, final Attack theSpecialAttack, final int theHealth, final int theDefense, final int theSpeed) {
        super(theAttack, theSpecialAttack, theHealth, theDefense, theSpeed);
    }
}
