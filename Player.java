import java.util.ArrayList;

public class Player {


    private String name;
    private int health;
    private int maxHealth;
    private int attack;
    private int gold;
    private ArrayList<String> inventory;

  
    public Player(String name) {
        this.name      = name;
        this.health    = 100;
        this.maxHealth = 100;
        this.attack    = 15;
        this.gold      = 0;
        this.inventory = new ArrayList<String>();
    }

 
    public String getName()             { return name;      }
    public int    getHealth()           { return health;    }
    public int    getMaxHealth()        { return maxHealth; }
    public int    getAttack()           { return attack;    }
    public int    getGold()             { return gold;      }
    public ArrayList<String> getInventory() { return inventory; }

 
    public void setHealth(int health)   { this.health = health; }
    public void setAttack(int attack)   { this.attack = attack; }
    public void setGold(int gold)       { this.gold = gold;     }

 
    public boolean isAlive() { return health > 0; }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
    }

    public void fullHeal() {
        health = maxHealth;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void spendGold(int amount) {
        gold -= amount;
    }

    public void addItem(String item) {
        inventory.add(item);
    }

    public void removeItem(String item) {
        inventory.remove(item);
    }

    public boolean hasItem(String item) {
        return inventory.contains(item);
    }

    public void upgradeAttack(int amount) {
        attack += amount;
    }

    public void showStats() {
        System.out.println("\n── " + name + " ──  HP: " + health + "/" + maxHealth
                + "  |  ATK: " + attack
                + "  |  Gold: " + gold);
    }

    public void showInventory() {
        System.out.println("\n── Inventory ──");
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + inventory.get(i));
            }
        }
    }
}