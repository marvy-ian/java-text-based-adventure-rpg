package rpg.entity;

import rpg.item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player extends Entity {
    private static final long serialVersionUID = 1L;

    private int level;
    private int exp;
    private int expToNextLevel;
    private final List<Item> inventory;
    private Item equippedWeapon;

    public Player(String name) {
        super(name, 100, 15, 5);
        this.level = 1;
        this.exp = 0;
        this.expToNextLevel = 50;
        this.inventory = new ArrayList<>();
        // Default starting items
        this.inventory.add(new Item("Health Potion", Item.ItemType.HEALTH_POTION, 30));
        this.inventory.add(new Item("Iron Sword", Item.ItemType.WEAPON, 8));
    }

    @Override
    public int calculateAttackDamage() {
        Random rand = new Random();
        int bonus = (equippedWeapon != null) ? equippedWeapon.getValue() : 0;
        double variance = 0.9 + (0.2 * rand.nextDouble());
        return (int) ((baseAttack + bonus) * variance);
    }

    public void gainExp(int amount) {
        this.exp += amount;
        System.out.printf(" You gained %d EXP!\n", amount);
        if (this.exp >= expToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        exp -= expToNextLevel;
        expToNextLevel = (int) (expToNextLevel * 1.5);
        maxHp += 20;
        hp = maxHp; // Full heal on level up
        baseAttack += 5;
        defense += 2;
        System.out.println("\n LEVEL UP! You reached Level " + level + "!");
        System.out.println("   HP restored and stats increased (+20 HP, +5 ATK, +2 DEF)\n");
    }

    public void usePotion() {
        Item potion = inventory.stream()
                .filter(i -> i.getType() == Item.ItemType.HEALTH_POTION)
                .findFirst()
                .orElse(null);

        if (potion == null) {
            System.out.println(" You don't have any Health Potions!");
            return;
        }

        int healed = Math.min(potion.getValue(), maxHp - hp);
        hp += healed;
        inventory.remove(potion);
        System.out.printf(" You drank a %s and restored %d HP! (%d/%d HP)\n", potion.getName(), healed, hp, maxHp);
    }

    public void equipWeapon(Item weapon) {
        if (weapon.getType() != Item.ItemType.WEAPON) {
            System.out.println(" That is not a weapon!");
            return;
        }
        this.equippedWeapon = weapon;
        System.out.println(" You equipped " + weapon.getName() + " (+" + weapon.getValue() + " ATK)!");
    }

    public void showStatus() {
        System.out.println("\n=== PLAYER STATUS ===");
        System.out.println(" Name:  " + name);
        System.out.println(" Level: " + level + " (" + exp + "/" + expToNextLevel + " EXP)");
        System.out.println(" HP:    " + hp + "/" + maxHp);
        System.out.println(" ATK:   " + baseAttack + (equippedWeapon != null ? " (+" + equippedWeapon.getValue() + ")" : ""));
        System.out.println(" DEF:   " + defense);
        System.out.println(" Weapon:" + (equippedWeapon != null ? equippedWeapon.getName() : " None"));
        System.out.println("=====================");
    }

    public List<Item> getInventory() { return inventory; }
}