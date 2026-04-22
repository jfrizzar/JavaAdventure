package Characters;
import Location.Location;

public class MainCharacter extends Character{
    private int experiencePoints;
    private int level;
    private int strengthLevel;
    private int defenseLevel;
    private Location currentLocation;

    //Constructors
    public MainCharacter(){
        super();
    }

    public MainCharacter(String characterName, int characterHP, 
    int characterMP, int characterGP, int experiencePoints, 
    int level, int strengthLevel, int defenseLevel, Location currentLocation){
        super(characterName, characterHP, characterMP, characterGP);
        setExperiencePoints(experiencePoints);
        setLevel(level);
        setStrengthLevel(strengthLevel);
        setDefenseLevel(defenseLevel);
        setCurrentLocation(currentLocation);
    }

    //Get methods
    public int getExperiencePoints(){
        return this.experiencePoints;
    }

    public int getLevel(){
        return this.level;
    }

    public int getStrengthLevel(){
        return this.strengthLevel;
    }

    public int getDefenseLevel(){
        return this.defenseLevel;
    }

    public Location getCurrentLocation(){
        return this.currentLocation;
    }

    //Set methods
    public void setExperiencePoints(int experiencePoints){
        this.experiencePoints = experiencePoints;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setStrengthLevel(int strengthLevel){
        this.strengthLevel = strengthLevel;
    }

    public void setDefenseLevel(int defenseLevel){
        this.defenseLevel = defenseLevel;
    }

    public void setCurrentLocation(Location currentLocation){
        this.currentLocation = currentLocation;
    }

    //Special purpose methods
    @Override
    public String characterDescription(){
        return super.characterDescription() + "\nExperience Points: " + getExperiencePoints() +
        "\nLevel :" + getLevel() + "\nStrength level: " + getStrengthLevel() + "\nDefense level: " +
        "\nCurrent location: " + getCurrentLocation().getLocationName();
    }

    //TODO
    public void gainExperience(int experiencePoints){

    };

    /*TODO
    public void useConsumable(Item consumable){

    };*/
}
