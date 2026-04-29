package Items;
public class Weapon extends Item {
    private int damage;

    //Constructor
    public Weapon(String itemName, int damage) {
        super(itemName);
        setDamage(damage);
    }

    //Get methods
    public int getDamage() {
        return this.damage;
    }

    //Set methods
    public void setDamage(int damage) {
        if (damage < 1) {
            throw new IllegalArgumentException("Weapon damage must be positive.");
        }
        this.damage = damage;
    }

    //Special purpose methods
    public void attack(int damage) {
        System.out.println(getItemName() + " attacks for " + damage + " HP.");
    }

    @Override
    public String toString() {
        return super.toString() + "\nDamage: " + getDamage();
    }
}
