package rpg.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String name;
    protected int hp;
    protected int maxHp;
    protected int baseAttack;
    protected int defense;

    public Entity(String name, int maxHp, int baseAttack, int defense) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.baseAttack = baseAttack;
        this.defense = defense;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - this.defense);
        this.hp = Math.max(0, this.hp - actualDamage);
        System.out.printf("  -> %s took %d damage! (Remaining HP: %d/%d)\n", name, actualDamage, hp, maxHp);
    }

    public abstract int calculateAttackDamage();

    // Getters
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
}