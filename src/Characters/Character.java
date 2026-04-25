package Characters;
import Items.Item;

public abstract class Character{
    private String characterName;
    private int characterHP;
    private int characterMP;
    private int characterGP;
    private Item[] inventory;

    //Constructors
    public Character(){}

    public Character(String characterName, int characterHP, 
        int characterMP, int characterGP, int inventorySize){
        setCharacterName(characterName);
        setCharacterHP(characterHP);
        setCharacterMP(characterMP);
        setCharacterGP(characterGP);
        this.inventory = new Item[inventorySize];
    }

    //Get methods
    public String getCharacterName(){
        return this.characterName;
    }

    public int getCharacterHP(){
        return this.characterHP;
    }

    public int getCharacterMP(){
        return this.characterMP;
    }

    public int getCharacterGP(){
        return this.characterGP;
    }

    public Item getCharacterInventoryItem(int index){
        return this.inventory[index];
    }

    public int getCharacterInventoryLength(){
        return this.inventory.length;
    }

    //Set methods
    public void setCharacterName(String characterName){
        if(characterName.isBlank() || characterName.isEmpty()){
            throw new IllegalArgumentException("Character name cannot be an empty string.");
        }
        this.characterName = characterName;
    }

    public void setCharacterHP(int characterHP){
        if(characterHP < 1){
            throw new IllegalArgumentException("The HP of a character must be positive.");
        }
        this.characterHP = characterHP;
    }

    public void setCharacterMP(int characterMP){
        if(characterMP < 1){
            throw new IllegalArgumentException("The MP of a character must be positive.");
        }
        this.characterMP = characterMP;
    }

    public void setCharacterGP(int characterGP){
        if(characterGP < 0){
            throw new IllegalArgumentException("The GP of a character cannot be negative.");
        }
        this.characterGP = characterGP;
    }

    public void setInventoryItem(int index, Item newItem){
        this.inventory[index] = newItem;
    }

    //Special purpose methods
    public String toString(){
        return "Character name: " + getCharacterName() + "\n" +
        "Current HP: " + getCharacterHP() + "\n" +
        "Current MP: " + getCharacterMP() + "\n" +
        "Current GP: " + getCharacterGP();
    }
}