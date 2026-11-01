package rpg.engine;

import rpg.entity.Enemy;
import rpg.entity.Player;
import rpg.item.Item;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SAVE_FILE = "savegame.dat";

    private final Player player;
    private int currentRoom;

    public GameState(String playerName) {
        this.player = new Player(playerName);
        this.currentRoom = 1;
    }

    public void play(Scanner scanner) {
        boolean playing = true;

        while (playing && player.isAlive()) {
            System.out.println("\n================================================");
            System.out.printf(" DUNGEON ROOM %d\n", currentRoom);
            System.out.println("================================================");
            System.out.println("1. Explore Deeper");
            System.out.println("2. View Player Stats");
            System.out.println("3. Inventory & Equipment");
            System.out.println("4. Save Game");
            System.out.println("5. Exit to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> explore(scanner);
                case "2" -> player.showStatus();
                case "3" -> manageInventory(scanner);
                case "4" -> saveGame();
                case "5" -> {
                    System.out.println("Returning to main menu...");
                    playing = false;
                }
                default -> System.out.println("Invalid selection. Try again.");
            }
        }

        if (!player.isAlive()) {
            System.out.println("\n GAME OVER! Your journey ended in Room " + currentRoom + ".");
        }
    }

    private void explore(Scanner scanner) {
        Random rand = new Random();
        int event = rand.nextInt(100);

        currentRoom++;

        if (event < 65) {
            // Combat Event
            Enemy enemy = generateEnemyForRoom(currentRoom);
            boolean victory = CombatEngine.startBattle(player, enemy, scanner);

            if (victory && rand.nextBoolean()) {
                // Drop loot on victory
                Item loot = new Item("Health Potion", Item.ItemType.HEALTH_POTION, 30);
                player.getInventory().add(loot);
                System.out.println(" The monster dropped a " + loot.getName() + "!");
            }
        } else if (event < 85) {
            // Treasure Event
            System.out.println(" You found a dusty treasure chest!");
            Item drop = new Item("Greatsword", Item.ItemType.WEAPON, 15);
            player.getInventory().add(drop);
            System.out.println(" You received: " + drop.getName() + "!");
        } else {
            // Safe Room Event
            System.out.println(" You entered a quiet sanctuary. You sit down and rest.");
            player.usePotion();
        }
    }

    private Enemy generateEnemyForRoom(int room) {
        int scale = room / 2;
        String[] names = {"Goblin", "Skeleton", "Orc Berserker", "Cave Troll", "Shadow Knight"};
        String name = names[Math.min(room / 3, names.length - 1)];

        return new Enemy(
                name,
                30 + (scale * 10),
                8 + (scale * 3),
                2 + scale,
                25 + (scale * 15)
        );
    }

    private void manageInventory(Scanner scanner) {
        System.out.println("\n--- INVENTORY ---");
        if (player.getInventory().isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (int i = 0; i < player.getInventory().size(); i++) {
            System.out.printf("%d. %s\n", i + 1, player.getInventory().get(i));
        }
        System.out.println("-----------------");
        System.out.print("Enter item number to equip/use (or 0 to back): ");

        try {
            int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (idx >= 0 && idx < player.getInventory().size()) {
                Item selected = player.getInventory().get(idx);
                if (selected.getType() == Item.ItemType.WEAPON) {
                    player.equipWeapon(selected);
                } else if (selected.getType() == Item.ItemType.HEALTH_POTION) {
                    player.usePotion();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    public void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(this);
            System.out.println(" Game successfully saved to " + SAVE_FILE);
        } catch (IOException e) {
            System.out.println(" Failed to save game: " + e.getMessage());
        }
    }

    public static GameState loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            System.out.println(" Save file loaded successfully!");
            return (GameState) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" No valid save file found.");
            return null;
        }
    }
}