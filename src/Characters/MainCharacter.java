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
        if(experiencePoints < 0){
            throw new IllegalArgumentException("Experience points cannot be negative.");   
        }
        this.experiencePoints = experiencePoints;
    }

    public void setLevel(int level){
        if(level < 1){
            throw new IllegalArgumentException("Level cannot be lower than 1.");
        }
        this.level = level;
    }

    public void setStrengthLevel(int strengthLevel){
        if(strengthLevel < 1){
            throw new IllegalArgumentException("Strength level cannot be lower than 1.");
        }
        this.strengthLevel = strengthLevel;
    }

    public void setDefenseLevel(int defenseLevel){
        if(defenseLevel < 1){
            throw new IllegalArgumentException("Defense level cannot be lower than 1.");
        }
        this.defenseLevel = defenseLevel;
    }

    public void setCurrentLocation(Location currentLocation){
        this.currentLocation = currentLocation;
    }

    //Special purpose methods
    @Override
    public String toString(){
        return super.toString() + "\nExperience Points: " + getExperiencePoints() +
        "\nLevel: " + getLevel() + "\nStrength level: " + getStrengthLevel() + "\nDefense level: " +
        getDefenseLevel() + "\n\nCurrent location: " + getCurrentLocation().getLocationName();
    }

    //This updates the main characters experience points and levels them up if experience points is 100 or more
    public void gainExperience(int experiencePoints){
        int newExperiencePoints = getExperiencePoints() + experiencePoints;
        if(newExperiencePoints >= 100){
            int newLevel = getLevel() + 1;
            int newDefenseLevel = getDefenseLevel() + 1;
            int newStrengthLevel = getStrengthLevel() + 1;
            setLevel(newLevel);
            setDefenseLevel(newDefenseLevel);
            setStrengthLevel(newStrengthLevel);
        }
        else{
            setExperiencePoints(newExperiencePoints);  
        }
    };

    /*TODO
    public void useConsumable(Item consumable){

    };*/
}
