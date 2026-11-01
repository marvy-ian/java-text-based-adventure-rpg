package rpg.engine;

import rpg.entity.Enemy;
import rpg.entity.Player;

import java.util.Scanner;

public class CombatEngine {

    public static boolean startBattle(Player player, Enemy enemy, Scanner scanner) {
        System.out.println("\n------------------------------------------------");
        System.out.printf("  A wild %s appeared! (HP: %d | ATK: %d)\n", enemy.getName(), enemy.getHp(), enemy.calculateAttackDamage());
        System.out.println("------------------------------------------------");

        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\nYour HP: " + player.getHp() + "/" + player.getMaxHp() + " | Enemy HP: " + enemy.getHp() + "/" + enemy.getMaxHp());
            System.out.println("1. Attack");
            System.out.println("2. Use Potion");
            System.out.println("3. Attempt to Flee");
            System.out.print("Choose an action: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    // Player turns
                    int pDmg = player.calculateAttackDamage();
                    System.out.println("\n You strike the " + enemy.getName() + "!");
                    enemy.takeDamage(pDmg);

                    // Enemy turns if still alive
                    if (enemy.isAlive()) {
                        int eDmg = enemy.calculateAttackDamage();
                        System.out.println(" The " + enemy.getName() + " attacks back!");
                        player.takeDamage(eDmg);
                    }
                }
                case "2" -> {
                    player.usePotion();
                    // Enemy gets a turn when player uses potion
                    if (enemy.isAlive()) {
                        int eDmg = enemy.calculateAttackDamage();
                        System.out.println(" The " + enemy.getName() + " attacks while you were healing!");
                        player.takeDamage(eDmg);
                    }
                }
                case "3" -> {
                    if (Math.random() < 0.5) {
                        System.out.println(" You successfully escaped the battle!");
                        return false;
                    } else {
                        System.out.println(" Escape failed! The " + enemy.getName() + " blocks your path!");
                        int eDmg = enemy.calculateAttackDamage();
                        player.takeDamage(eDmg);
                    }
                }
                default -> System.out.println("Invalid option! You lost your concentration.");
            }
        }

        if (player.isAlive()) {
            System.out.println("\n You defeated the " + enemy.getName() + "!");
            player.gainExp(enemy.getExpValue());
            return true;
        } else {
            System.out.println("\n You were defeated in battle...");
            return false;
        }
    }
}