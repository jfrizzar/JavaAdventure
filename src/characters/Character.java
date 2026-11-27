package characters;
import items.Item;
import location.Location;

public abstract class Character{
    private String characterName;
    private int characterCurrentHP;
    private int characterMaxHP;
    private int characterCurrentMP;
    private int characterMaxMP;
    private int characterGP;
    private int characterCurrentXP;
    private int characterMaxXP;
    private Item[] inventory;
    private Location characterLocation;

    //Constructor
    public Character(String characterName, int characterCurrentHP, int characterMaxHP, 
                     int characterCurrentMP, int characterMaxMP, int characterGP, 
                     int characterCurrentXP, int characterMaxXP, int inventorySize,
                     Location characterLocation){
        setCharacterName(characterName);
        setCharacterCurrentHP(characterCurrentHP);
        setCharacterMaxHP(characterMaxHP);
        setCharacterCurrentMP(characterCurrentMP);
        setCharacterMaxMP(characterMaxMP);
        setCharacterGP(characterGP);
        setCharacterCurrentXP(characterCurrentXP);
        setCharacterMaxXP(characterMaxXP);
        setCharacterInventorySize(inventorySize);
        setCharacterLocation(characterLocation);
    }

    //Get Methods
    public String getCharacterName(){
        return this.characterName;
    }

    public int getCharacterCurrentHP(){
        return this.characterCurrentHP;
    }

    public int getCharacterMaxHP(){
        return this.characterMaxHP;
    }

    public int getCharacterCurrentMP(){
        return this.characterCurrentMP;
    }

    public int getCharacterMaxMP(){
        return this.characterMaxMP;
    }

    public int getCharacterGP(){
        return this.characterGP;
    }

    public int getCharacterCurrentXP(){
        return this.characterCurrentXP;
    }

    public int getCharacterMaxXP(){
        return this.characterMaxXP;
    }

    public Item getCharacterInventoryItem(int index){
        return this.inventory[index];
    }

    public int getCharacterInventoryLength(){
        return this.inventory.length;
    }

    public Location getCharacterLocation(){
        return this.characterLocation;
    }

    //Set Methods
    public void setCharacterName(String characterName){
        if(characterName.isBlank() || characterName.isEmpty()){
            throw new IllegalArgumentException("Character name cannot be an empty string.");
        }
        this.characterName = characterName;
    }

    public void setCharacterCurrentHP(int characterCurrentHP){
        if(characterCurrentHP < 1){
            throw new IllegalArgumentException("The current HP of a character must be positive.");
        }
        this.characterCurrentHP = characterCurrentHP;
    }

    public void setCharacterMaxHP(int characterMaxHP){
        if(characterMaxHP < 1){
            throw new IllegalArgumentException("The max HP of a character must be positive.");
        }
        this.characterMaxHP = characterMaxHP;
    }

    public void setCharacterCurrentMP(int characterCurrentMP){
        if(characterCurrentMP < 1){
            throw new IllegalArgumentException("The MP of a character must be positive.");
        }
        this.characterCurrentMP = characterCurrentMP;
    }

    public void setCharacterMaxMP(int characterMaxMP){
        if(characterMaxMP < 1){
            throw new IllegalArgumentException("The max MP of a character must be positive.");
        }
        this.characterMaxMP = characterMaxMP;
    }

    public void setCharacterGP(int characterGP){
        if(characterGP < 0){
            throw new IllegalArgumentException("The GP of a character cannot be negative.");
        }
        this.characterGP = characterGP;
    }

    public void setCharacterCurrentXP(int characterCurrentXP){
        if(characterCurrentXP < 0){
            throw new IllegalArgumentException("The current XP of a character cannot be negative");
        }
        this.characterCurrentXP = characterCurrentXP;
    }

    public void setCharacterMaxXP(int characterMaxXP){
        if(characterMaxXP < 0){
            throw new IllegalArgumentException("The current XP of a character cannot be negative");
        }
        this.characterMaxXP = characterMaxXP;
    }

    public void setCharacterInventorySize(int inventorySize){
        if(inventorySize < 0){
            throw new IllegalArgumentException("The inventory size of a character cannot be negative.");
        }
        this.inventory = new Item[inventorySize];
    }

    public void setInventoryItem(int index, Item newItem){
        this.inventory[index] = newItem;
    }

    public void setCharacterLocation(Location characterLocation){
        this.characterLocation = characterLocation;
    }

    //Special purpose methods
    public String toString(){
        return "\nName: " + getCharacterName() + "\n" +
        "HP: " + getCharacterCurrentHP() + "/" + getCharacterMaxHP() + "\n" +
        "MP: " + getCharacterCurrentMP() + "/" + getCharacterMaxMP() +"\n" +
        "GP: " + getCharacterGP() + "\n" +
        "XP: " + getCharacterCurrentXP() + "/" + getCharacterMaxXP();
    }

    //Level up function
}