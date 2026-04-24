package Characters;
import Items.Item;

public abstract class Character{
    private String characterName;
    private int characterHP;
    private int characterMP;
    private int characterGP;
    private Item[] inventory;

    //Constructors
    public Character(){ 
    }

    public Character(String characterName, int characterHP, int characterMP, int characterGP, Item[] inventory){
        setCharacterName(characterName);
        setCharacterHP(characterHP);
        setCharacterMP(characterMP);
        setCharacterGP(characterGP);
        setCharacterInventory(inventory);
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

    public Item[] getCharacterInventory(){
        return this.inventory;
    }

    public Item getCharacterInventoryItem(int index){
        return this.inventory[index];
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

    public void setCharacterInventory(Item[] inventory){
        this.inventory = inventory;
    }

    public void setCharacterInventoryItem(int index, Item newItem){
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