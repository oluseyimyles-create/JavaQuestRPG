public class Enemy {

 
    private String name;
    private int health;
    private int attack;

 
    public Enemy(String name, int health, int attack) {
        this.name   = name;
        this.health = health;
        this.attack = attack;
    }

  
    public String getName()   { return name;   }
    public int    getHealth() { return health; }
    public int    getAttack() { return attack; }

   
    public void setHealth(int health) { this.health = health; }

    
    public boolean isAlive() { return health > 0; }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public String toString() {
        return name + " | HP: " + health + " | ATK: " + attack;
    }
}