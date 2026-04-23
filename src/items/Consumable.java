package Items;

public class Consumable extends Item {
    private int healthPoints;
    private int manaPoints;

    // Constructors
    public Consumable() {
    }

    public Consumable(String itemName, int healthPoints, int manaPoints) {
        super(itemName);
        setHealthPoints(healthPoints);
        setManaPoints(manaPoints);
    }

    // Get methods
    public int getHealthPoints() {
        return this.healthPoints;
    }

    public int getManaPoints() {
        return this.manaPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints < 0) {
            throw new IllegalArgumentException("Health points cannot be negative.");
        }
        this.healthPoints = healthPoints;
    }

    public void setManaPoints(int manaPoints) {
        if (manaPoints < 0) {
            throw new IllegalArgumentException("Mana points cannot be negative.");
        }
        this.manaPoints = manaPoints;
    }

    // Special purpose methods
    public void consume() {
    System.out.println(getItemName() + " restores " + getHealthPoints()
            + " HP and " + getManaPoints() + " MP.");
}

    @Override
    public String toString() {
        return super.toString() + "\nHealth Points: " + getHealthPoints()
                + "\nMana Points: " + getManaPoints();
    }
}