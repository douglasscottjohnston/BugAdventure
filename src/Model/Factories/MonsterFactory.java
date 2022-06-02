package Model.Factories;

import Model.Bugs.*;
import Model.Database.Connect;
import Model.ModelUtility;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Monster factory.
 */
public class MonsterFactory implements Serializable {
    private static final int MONSTER_HIGH = 3; // The high chance to generate a certain type of monster
    private final ModelUtility myUtility;
    private ResultSet myResult;
    private static final String SPIDER = "Spider";
    private static final String MOSSTER = "Mosster";
    private static final String MAGGOT = "Maggot";
    private static final String CENTIPEDE = "Centipede";

    /**
     * Instantiates a new Monster factory.
     */
    public MonsterFactory() {
        myUtility = new ModelUtility();
    }

    private MonsterBug makeMonster(final String theName) {
        myResult = Connect.retrieveRow(SPIDER);
        MonsterBug monster = null;
        try {
            Attack attack = new Attack(myResult.getString("attackName"), myResult.getInt("attackPower"), myResult.getInt("attackChance"), myResult.getInt("attackHasLifeSteal") == 1);
            Attack specialAttack = new Attack(myResult.getString("specialAttackName"), myResult.getInt("specialAttackPower"), myResult.getInt("specialAttackChance"), myResult.getInt("specialAttackHasLifeSteal") == 1);
            monster = new MonsterBug(attack, specialAttack, myResult.getInt("health"), myResult.getInt("health"), myResult.getInt("defense"), myResult.getInt("speed"), myResult.getString("name"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return monster;
    }

    /**
     * Makes a new spider object.
     *
     * @return the spider
     */
    public MonsterBug makeSpider() {
        return makeMonster(SPIDER);
    }

    /**
     * Makes a new maggot object.
     *
     * @return the maggot
     */
    public MonsterBug makeMaggot() {
        return makeMonster(MAGGOT);
    }

    /**
     * Makes a new centipede object.
     *
     * @return the centipede
     */
    public MonsterBug makeCentipede() {
        return makeMonster(CENTIPEDE);
    }

    /**
     * Makes a new mosster object.
     *
     * @return the mosster
     */
    public MonsterBug makeMosster() {
        return makeMonster(MOSSTER);
    }

    /**
     * Makes a random monster bug.
     *
     * @return the monster bug
     */
    public MonsterBug makeRandomMonsterBug() {
        return switch (myUtility.getRandom(MONSTER_HIGH)) {
            default -> makeSpider();
            case 1 -> makeCentipede();
            case 2 -> makeMaggot();
            case 3 -> makeMosster();
        };
    }

}
