import characters.AttackType;
import characters.Character;
import characters.Mage;
import characters.Warrior;
import characters.actions.AttackAction;
import characters.actions.BattleAction;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String name = readName(scanner);
            Character player = chooseCharacter(scanner);
            Character enemy = createRandomEnemy();

            IO.println("Welcome, " + name + "!");
            printAttacks(player);

            Queue<BattleAction> pendingActions = new PriorityQueue<>(
                    Comparator.comparing(BattleAction::getActionPhase));

            while (player.isAlive() && enemy.isAlive()) {
                for (int i = 1; i <= 2; i++) {
                    AttackType type = chooseAttack(scanner, i);
                    pendingActions.add(createAction(type, player, enemy));
                    pendingActions.add(createRandomEnemyAction(enemy, player));
                }

                while (!pendingActions.isEmpty()) {
                    BattleAction action = pendingActions.poll();
                    var isPlayerAction = action.getOwner() == player;
                    action.execute();
                    IO.println(String.format("%s %s", isPlayerAction ? "Player" : "Computer", action.getActionPhase()));
                }

                IO.println(String.format("Player stats: %s", player.getHealthStats()));
                IO.println(String.format("Enemy stats: %s", enemy.getHealthStats()));
            }

            IO.println(String.format("%s. Thanks for playing!", player.isAlive() ? "WIN" : "LOSE"));
        }
    }

    private static String readName(Scanner scanner) {
        while (true) {
            IO.print("What is your name (length: 3-30)? ");
            String name = scanner.nextLine().trim();

            if (name.length() >= 3 && name.length() <= 30) {
                return name;
            }

            IO.println("Name must be between 3 and 30 characters.");
        }
    }

    private static Character chooseCharacter(Scanner scanner) {
        while (true) {
            IO.println("""
                    Choose a character:
                        1. Warrior
                        2. Mage
                    """);
            IO.print("Your selection: ");

            switch (scanner.nextLine().trim().toLowerCase()) {
                case "1", "warrior" -> {
                    return new Warrior(100, 5, 15);
                }
                case "2", "mage" -> {
                    return new Mage(100, 5, 15);
                }
                default -> IO.println("Please choose Warrior or Mage.");
            }
        }
    }

    private static BattleAction createRandomEnemyAction(Character enemy, Character player) {
        AttackType type = ThreadLocalRandom.current().nextBoolean() ? AttackType.Regular : AttackType.Special;
        return createAction(type, enemy, player);
    }

    private static Character createRandomEnemy() {
        return ThreadLocalRandom.current().nextBoolean()
                ? new Warrior(100, 5, 15)
                : new Mage(100, 5, 15);
    }

    private static void printAttacks(Character player) {
        var attacks = player.getAttacks();

        IO.println("Choose your attacks:");
        IO.println("1. Regular: " + attacks.get(AttackType.Regular));
        IO.println("2. Special: " + attacks.get(AttackType.Special));
    }

    private static AttackType chooseAttack(Scanner scanner, int number) {
        String order = number == 1 ? "first" : "second";

        while (true) {
            IO.print("Choose your " + order + " attack: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> {
                    return AttackType.Regular;
                }
                case "2" -> {
                    return AttackType.Special;
                }
                default -> IO.println("Invalid choice. Enter 1 or 2.");
            }
        }
    }

    private static BattleAction createAction(
            AttackType type,
            Character character,
            Character target
    ) {
        return switch (type) {
            case Regular -> new AttackAction(character, target);
            case Special -> character.createSpecialAction();
        };
    }
}
