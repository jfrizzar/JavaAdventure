package characters;
import location.Location;

public class MainCharacter extends Character{
    private int characterLevel;
    private int characterStrength;
    private int characterDefense;

    //Constructor
    public MainCharacter(String characterName, int characterCurrentHP, int characterMaxHP,
                         int characterCurrentMP, int characterMaxMP, int characterGP, 
                         int characterCurrentXP, int characterMaxXP, int inventorySize, 
                         Location characterLocation, int characterLevel, int characterStrength, 
                         int characterDefense){
        super(characterName, characterCurrentHP, characterMaxHP, 
              characterCurrentMP, characterMaxMP, characterGP, 
              characterCurrentXP, characterMaxXP, inventorySize,
              characterLocation);
        setCharacterLevel(characterLevel);
        setCharacterStrength(characterStrength);
        setCharacterDefense(characterDefense);
    }

    //Get Methods
    public int getCharacterLevel(){
        return this.characterLevel;
    }

    public int getCharacterStrength(){
        return this.characterStrength;
    }

    public int getCharacterDefense(){
        return this.characterDefense;
    }

    //Set Methods
    public void setCharacterLevel(int characterLevel){
        if(characterLevel < 1){
            throw new IllegalArgumentException("Character level cannot be lower than 1.");
        }
        this.characterLevel = characterLevel;
    }

    public void setCharacterStrength(int characterStrength){
        if(characterStrength < 1){
            throw new IllegalArgumentException("Character strength cannot be lower than 1.");
        }
        this.characterStrength = characterStrength;
    }

    public void setCharacterDefense(int characterDefense){
        if(characterDefense < 1){
            throw new IllegalArgumentException("Character defense cannot be lower than 1.");
        }
        this.characterDefense = characterDefense;
    }

    //Special Purpose Method
    @Override
    public String toString(){
        return super.toString() + "\n" + 
        "Level: " + getCharacterLevel() + "\n" + 
        "Strength: " + getCharacterStrength() + "\n" + 
        "Defense: " + getCharacterDefense();
    }
}