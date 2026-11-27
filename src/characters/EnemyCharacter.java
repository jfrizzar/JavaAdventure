package characters;
import location.Location;

public class EnemyCharacter extends Character{
    private boolean defeated;

    //Constructor
    public EnemyCharacter(String characterName, int characterCurrentHP, int characterMaxHP, 
                          int characterCurrentMP, int characterMaxMP, int characterGP, 
                          int characterCurrentXP, int characterMaxXP, int inventorySize,
                          Location characterLocation){
        super(characterName, characterCurrentHP, characterMaxHP, 
              characterCurrentMP, characterMaxMP, characterGP, 
              characterCurrentXP, characterMaxXP, inventorySize,
              characterLocation);
        setDefeated(defeated);
    }

    //Get Method
    public boolean getDefeated(){
        return this.defeated;
    }

    //Set Method
    public void setDefeated(boolean defeated){
        this.defeated = defeated;
    }

    //Special Purpose Method
    @Override
    public String toString(){ 
        return super.toString() + "\n" +
        "Defeated: " + getDefeated();
    }
}
