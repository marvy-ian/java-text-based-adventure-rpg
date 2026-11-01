package rpg;

import rpg.engine.GameState;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=================================");
            System.out.println("    JAVA TEXT ADVENTURE RPG      ");
            System.out.println("=================================");
            System.out.println("1. Start New Game");
            System.out.println("2. Load Saved Game");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("\nEnter your hero's name: ");
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) name = "Hero";
                    GameState game = new GameState(name);
                    game.play(scanner);
                }
                case "2" -> {
                    GameState game = GameState.loadGame();
                    if (game != null) {
                        game.play(scanner);
                    }
                }
                case "3" -> {
                    System.out.println("Thanks for playing! Farewell, adventurer.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}