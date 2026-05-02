import java.util.ArrayList;
import java.util.Scanner;

public class Main {

   
    static String playerName;
    static int playerHealth    = 100;
    static int playerMaxHealth = 100;
    static int playerAttack    = 15;
    static int playerGold      = 0;
    static boolean bossDefeated = false;
    
    static ArrayList<String> inventory = new ArrayList<String>();

   
    static Player player;
    static boolean bossDefeated1 = false;
    static Scanner scanner = new Scanner(System.in);
    
    static int mapRow = 0;
    static int mapCol = 0;
    static String[][] map = {
        { "Town Square",  "Dark Forest",  "Mystic Cave"  },
        { "Old Bridge",   "Abandoned Hut","Rocky Cliffs" },
        { "River Crossing","Haunted Ruins","Dragon's Lair"}
    };

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║   WELCOME TO JAVA QUEST RPG  ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("\nEnter your hero's name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Hero";
        player = new Player(name);

        System.out.println("\nWelcome, " + player.getName() + "! Your adventure begins at the " + map[mapRow][mapCol] + ".");

     
        boolean playing = true;
        while (playing) {
            showStatus();
            System.out.println("\n╔══════════ MAIN MENU ══════════╗");
            System.out.println("║  1. Explore (move on the map) ║");
            System.out.println("║  2. Fight a random enemy      ║");
            System.out.println("║  3. Rest and heal             ║");
            System.out.println("║  4. View inventory            ║");
            System.out.println("║  5. View map                  ║");
            System.out.println("║  6. Visit shop                ║");
            System.out.println("║  7. Fight boss                ║");
            System.out.println("║  8. Quit                      ║");
            System.out.println("╚═══════════════════════════════╝");
            System.out.print("Choose an option: ");

            String input = "";
            try {
                input = scanner.nextLine().trim();
            } catch (Exception e) {
                System.out.println("Something went wrong with your input. Please try again.");
            }

            switch (input) {
                case "1": explore();       break;
                case "2": startFight();    break;
                case "3": rest();          break;
                case "4": viewInventory(); break;
                case "5": viewMap();       break;
                case "6": visitShop();    break;
                case "7": fightBoss();   break;
                case "8": playing = false; break;
                default:
                    System.out.println("Invalid choice. Please enter 1–6.");
            }

         
            if (!player.isAlive()) {
                System.out.println("\n You have been defeated! Game over, " + player.getName() + ".");
                playing = false;
            }

        
            if (bossDefeated) {
                showVictory();
                playing = false;
            }
        }

        System.out.println("\nThanks for playing Java Quest! Final gold: " + playerGold);
        scanner.close();
    }

 
    static void showStatus() {
        System.out.println("\n── " + player.getName() + " ──  HP: " + player.getHealth() + "/" + player.getMaxHealth()
                + "  |  ATK: " + player.getAttack()
                + "  |  Gold: " + player.getGold()
                + "  |  Location: " + map[mapRow][mapCol]);
    }


    static void explore() {
        System.out.println("\nWhich direction do you want to travel?");
        System.out.println("  W = North  |  S = South  |  A = West  |  D = East");
        System.out.print("Direction: ");
        String dir = scanner.nextLine().trim().toUpperCase();

        int newRow = mapRow;
        int newCol = mapCol;

        switch (dir) {
            case "W": newRow--; break;
            case "S": newRow++; break;
            case "A": newCol--; break;
            case "D": newCol++; break;
            default:
                System.out.println("Unknown direction.");
                return;
        }

  
        if (newRow < 0 || newRow >= map.length || newCol < 0 || newCol >= map[0].length) {
            System.out.println("There's nothing in that direction. You stay at " + map[mapRow][mapCol] + ".");
        } else {
            mapRow = newRow;
            mapCol = newCol;
            System.out.println("You travel to the " + map[mapRow][mapCol] + ".");

      
            if (Math.random() < 0.4) {
                int found = (int)(Math.random() * 15) + 5;
                player.addGold(found);
                System.out.println("You found " + found + " gold on the ground!");
            }
        }
     
        if (map[mapRow][mapCol].equals("Dragon's Lair") && !bossDefeated) {
            System.out.println("\n The ground shakes... the Dragon awakens!");
            pressEnter();
            fightBoss();
        }
    }
    static void visitShop() {
        boolean inShop = true;
        while (inShop) {
            System.out.println("\n╔══════════ SHOP ═══════════════╗");
            System.out.println("║  Your gold: " + player.getGold());
            System.out.println("║                               ║");
            System.out.println("║  1. Health Potion      (15g)  ║");
            System.out.println("║  2. Strong Potion      (30g)  ║");
            System.out.println("║  3. Attack Upgrade     (40g)  ║");
            System.out.println("║  4. Full Heal          (50g)  ║");
            System.out.println("║  5. Leave shop                ║");
            System.out.println("╚═══════════════════════════════╝");
            System.out.print("What would you like to buy? ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
            case "1":
                if (player.getGold() >= 15) {
                    player.spendGold(15);
                    player.addItem("Health Potion");
                    System.out.println("You bought a Health Potion!");
                } else { System.out.println("Not enough gold!"); }
                break;
            case "2":
                if (player.getGold() >= 30) {
                    player.spendGold(30);
                    player.addItem("Strong Potion");
                    System.out.println("You bought a Strong Potion!");
                } else { System.out.println("Not enough gold!"); }
                break;
            case "3":
                if (player.getGold() >= 40) {
                    player.spendGold(40);
                    player.upgradeAttack(5);
                    System.out.println("Your attack increased to " + player.getAttack() + "!");
                } else { System.out.println("Not enough gold!"); }
                break;
            case "4":
                if (player.getGold() >= 50) {
                    player.spendGold(50);
                    player.fullHeal();
                    System.out.println("You are fully healed!");
                } else { System.out.println("Not enough gold!"); }
                break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    static void startFight() {
        
        String[] names   = { "Goblin", "Skeleton", "Troll", "Dark Knight" };
        int[]    healths = {  40,       60,          90,      120          };
        int[]    attacks = {  8,        12,          18,       22          };

        int index = (int)(Math.random() * names.length);
        Enemy enemy = new Enemy(names[index], healths[index], attacks[index]);

        System.out.println("\n⚔  A " + enemy.getName() + " appears! (HP: " + enemy.getHealth() + " | ATK: " + enemy.getAttack() + ")");
        pressEnter();

        while (enemy.isAlive() && player.isAlive()) {
            System.out.println("\n" + enemy.getName() + " HP: " + enemy.getHealth() + "  |  Your HP: " + player.getHealth());
            System.out.println("  1. Attack    2. Use Health Potion    3. Run");
            System.out.print("Your move: ");
            String move = "";
            try {
                move = scanner.nextLine().trim();
            } catch (Exception e) {
                System.out.println("Something went wrong. Try again.");
            }

            if (move.equals("2")) { usePotion(); continue; }
            if (move.equals("3")) { System.out.println("You ran away safely!"); return; }

            int playerDmg = calcDamage(player.getAttack());
            enemy.takeDamage(playerDmg);
            System.out.println("You hit the " + enemy.getName() + " for " + playerDmg + " damage!");

            if (!enemy.isAlive()) break;

            int enemyDmg = calcDamage(enemy.getAttack());
            player.takeDamage(enemyDmg);
            System.out.println("The " + enemy.getName() + " hits you for " + enemyDmg + " damage!");
        }

        if (player.isAlive()) {
            int goldReward = (int)(Math.random() * 20) + 10;
            player.addGold(goldReward);
            System.out.println("\n You defeated the " + enemy.getName() + " and earned " + goldReward + " gold!");
            if (Math.random() < 0.5) findItem();
        }
    }
    static void fightBoss() {
        String bossName   = "Ancient Dragon";
        int    bossHP     = 250;
        int    bossAttack = 35;

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║     BOSS FIGHT: ANCIENT DRAGON   ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.println(bossName + " | HP: " + bossHP + " | ATK: " + bossAttack);
        pressEnter();

        while (bossHP > 0 && playerHealth > 0) {
            System.out.println("\n " + bossName + " HP: " + bossHP + "  |  Your HP: " + playerHealth);
            System.out.println("  1. Attack    2. Use Health Potion    3. Run");
            System.out.print("Your move: ");
            String move = scanner.nextLine().trim();
            if (move.equals("2")) {
                usePotion();
                continue;
            }
            if (move.equals("3")) {
                System.out.println("You flee from the Dragon's Lair!");
                mapRow = 0;
                mapCol = 0;
                return;
            }

        
            int playerDmg = calcDamage(playerAttack);
            bossHP -= playerDmg;
            System.out.println("You hit the " + bossName + " for " + playerDmg + " damage!");

            if (bossHP <= 0) break;

            // Boss attacks
            int bossDmg = calcDamage(bossAttack);
            playerHealth -= bossDmg;
            System.out.println(" The " + bossName + " breathes fire! You take " + bossDmg + " damage!");
        }

        if (playerHealth > 0) {
            bossDefeated = true;
            playerGold += 150;
            inventory.add("Dragon Scale");
            System.out.println("\n You slayed the Ancient Dragon!");
            System.out.println("You earned 150 gold and found a Dragon Scale!");
        }
    }
   
    static int calcDamage(int baseAttack) {
        double variance = 0.8 + (Math.random() * 0.4); 
        return (int)(baseAttack * variance);
    }


    static void rest() {
        if (player.getHealth() == player.getMaxHealth()) {
            System.out.println("You're already at full health!");
            return;
        }

        int cost = 10;
        if (player.getGold() >= cost) {
            player.spendGold(cost);
            int healed = (int)(Math.random() * 20) + 20;
            player.heal(healed);
            System.out.println("You rest at a campfire and recover " + healed + " HP. (-" + cost + " gold)");
        } else {
            int healed = (int)(Math.random() * 10) + 5;
            player.heal(healed);
            System.out.println("You rest on the cold ground and recover " + healed + " HP.");
        }
    }
    static void usePotion() {
        if (player.hasItem("Strong Potion") && player.hasItem("Health Potion")) {
            System.out.println("  1. Use Health Potion    2. Use Strong Potion");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                player.removeItem("Health Potion");
                int healed = (int)(Math.random() * 20) + 30;
                player.heal(healed);
                System.out.println(" You drink a Health Potion and recover " + healed + " HP!");
            } else if (choice.equals("2")) {
                player.removeItem("Strong Potion");
                int healed = (int)(Math.random() * 20) + 60;
                player.heal(healed);
                System.out.println(" You drink a Strong Potion and recover " + healed + " HP!");
            } else {
                System.out.println("Invalid choice, no potion used.");
            }
        } else if (player.hasItem("Health Potion")) {
            player.removeItem("Health Potion");
            int healed = (int)(Math.random() * 20) + 30;
            player.heal(healed);
            System.out.println(" You drink a Health Potion and recover " + healed + " HP!");
        } else if (player.hasItem("Strong Potion")) {
            player.removeItem("Strong Potion");
            int healed = (int)(Math.random() * 20) + 60;
            player.heal(healed);
            System.out.println(" You drink a Strong Potion and recover " + healed + " HP!");
        } else {
            System.out.println("You have no potions!");
        }
    }

    static void findItem() {
        String[] possibleItems = { "Health Potion", "Iron Sword", "Leather Armor",
                                   "Magic Scroll", "Old Coin", "Mysterious Key" };
        String item = possibleItems[(int)(Math.random() * possibleItems.length)];
        player.addItem(item);
        System.out.println(" You found a " + item + "! Added to inventory.");
    }

    static void viewInventory() {
        player.showInventory();
    }

    
    static void viewMap() {
        System.out.println("\n── World Map ──  (* = you are here)");
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                String marker = (r == mapRow && c == mapCol) ? "*" : " ";
                System.out.printf(" %s%-16s", marker, map[r][c]);
            }
            System.out.println();
        }
    }

    static void pressEnter() {
        System.out.print("(Press Enter to continue...)");
        scanner.nextLine();
    }
    static void showVictory() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║            YOU WIN!                  ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("\nCongratulations, " + player.getName() + "!");
        System.out.println("You slayed the Ancient Dragon and saved the kingdom!");
        System.out.println("\n── Final Stats ──");
        System.out.println("  Gold collected : " + player.getGold());
        System.out.println("  HP remaining   : " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("  Attack power   : " + player.getAttack());
        System.out.println("  Items collected: " + player.getInventory().size());
        player.showInventory();
    }
}
