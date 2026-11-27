package items;

public class Consumable extends Item{
    private int itemHP;
    private int itemMP;

    //Constructor
    public Consumable(String itemName, int itemHP, int itemMP){
        super(itemName);
        setItemHP(itemHP);
        setItemMP(itemMP);
    }

    //Get Methods
    public int getItemHP(){
        return this.itemHP;
    }

    public int getItemMP(){
        return this.itemMP;
    }

    //Set methods
    public void setItemHP(int itemHP){
        if (itemHP < 0) {
            throw new IllegalArgumentException("Item HP cannot be negative.");
        }
        this.itemHP = itemHP;
    }

    public void setItemMP(int itemMP){
        if (itemMP < 0) {
            throw new IllegalArgumentException("Item MP cannot be negative.");
        }
        this.itemMP = itemMP;
    }

    //Special purpose methods
    public void consumeItem(){
        System.out.println(getItemName() + " restores " + getItemHP() + " HP and " + getItemMP() + " MP.");
    }

    @Override
    public String toString(){
        return super.toString() + "\nHealth Points: " + getItemHP() + "\nMana Points: " + getItemMP();
    }
}