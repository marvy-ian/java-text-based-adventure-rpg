package rpg.entity;

import java.util.Random;

public class Enemy extends Entity {
    private static final long serialVersionUID = 1L;
    private final int expValue;

    public Enemy(String name, int maxHp, int baseAttack, int defense, int expValue) {
        super(name, maxHp, baseAttack, defense);
        this.expValue = expValue;
    }

    @Override
    public int calculateAttackDamage() {
        Random rand = new Random();
        // Attack varies between 80% and 120% of base attack
        double variance = 0.8 + (0.4 * rand.nextDouble());
        return (int) (baseAttack * variance);
    }

    public int getExpValue() { return expValue; }
}