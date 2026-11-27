package items;

public class Weapon extends Item{
    private int itemDamage;

    //Constructor
    public Weapon(String itemName, int itemDamage){
        super(itemName);
        setDamage(itemDamage);
    }

    //Get Method
    public int getItemDamage(){
        return this.itemDamage;
    }

    //Set Method
    public void setDamage(int itemDamage){
        if (itemDamage < 1){
            throw new IllegalArgumentException("Weapon damage must be positive.");
        }
        this.itemDamage = itemDamage;
    }

    //Special Purpose Methods
    public void attack(int itemDamage){
        System.out.println(getItemName() + " attacks for " + getItemDamage() + " HP.");
    }

    @Override
    public String toString(){
        return super.toString() + "\nDamage: " + getItemDamage();
    }
}
