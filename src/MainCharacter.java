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
    int level, int strengthLevel, int defenseLevel){
        super(characterName, characterHP, characterMP, characterGP);
        setExperiencePoints(experiencePoints);
        setLevel(level);
        setStrengthLevel(strengthLevel);
        setDefenseLevel(defenseLevel);
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
        return this.Location;
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

    //Special purpose methods
    @Override
    public String characterDescription(){
        return super.characterDescription() + "\nExperience Points: " + getExperiencePoints() +
        "\nLevel :" + getLevel() + "\nStrength level: " + getStrengthLevel() + "\nDefense level: " +
        "Current location: " + getCurrentLocation();
    }

    //TODO
    public void gainExperience(int experiencePoints){

    };

    //TODO
    public void useItem(Item item){

    };
}
