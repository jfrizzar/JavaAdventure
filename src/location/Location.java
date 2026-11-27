package location;
import characters.EnemyCharacter;
import items.Item;
import location.Location;

public class Location{
    private String locationName;
    private String locationDescription;
    //Want to create ASCII art for this variable
    private String locationRoadSign;
    private Location[] locationExits;
    private EnemyCharacter[] locationEnemies;
    private Item[] locationItems;
    private String[] locationItemSpotDescriptions;
    private int locationEnemyCount;
    private int locationItemCount;
    private int locationItemDescriptionCount;

    //Want to be able to set the exit count, enemy count, and item count here but actual exits, enemies, and items in game.java

    public Location(String locationName, String locationDescription, String locationRoadSign, 
                    int locationEnemyCount, int locationItemCount, int locationItemSpotDescriptionCount){
        setLocationName(locationName);
        setLocationDescription(locationDescription);
        setLocationRoadSign(locationRoadSign);
        //For 0, 1, 2, 3 which equals North, East, South, West
        this.locationExits = new Location[4];
        setLocationEnemyCount(locationEnemyCount);
        setLocationItemCount(locationItemCount);
        setLocationItemDescriptionCount(locationItemSpotDescriptionCount);
    }

    //Get Method
    public String getLocationName(){
        return this.locationName;
    }

    public String getLocationDescription(){
        return this.locationDescription;
    }

    public String getLocationRoadSign(){
        return this.locationRoadSign;
    }

    public Location getLocationExit(int index){
        /*
            Example would be: "castle.getExit(2)" would
            return the exit to the South back to town
            because the number 2 is mapped to SOUTH
            This method returns Location because we store
            Location objects in our array
        */
        return this.locationExits[index];
    }

    public EnemyCharacter getLocationEnemy(int index){
        return this.locationEnemies[index];
    }

    public int getLocationEnemyCount(){
        return this.locationEnemyCount;
    }

    public Item getLocationItem(int index){
        return this.locationItems[index];
    }

    public int getLocationItemCount(){
        return this.locationItemCount;
    }

    public String getLocationItemSpotDescription(int index){
        return this.locationItemSpotDescriptions[index];
    }

    public int getLocationItemSpotDescriptionCount(){
        return this.locationItemDescriptionCount;
    }

    //Set Methods
    public void setLocationName(String locationName){
        if(locationName.isEmpty() || locationName.isBlank()){
            throw new IllegalArgumentException("Location name cannot be an empty string.");
        }
        this.locationName = locationName;
    }

    public void setLocationDescription(String locationDescription){
        if(locationDescription.isEmpty() || locationDescription.isBlank()){
            throw new IllegalArgumentException("Location description cannot be an empty string.");
        }
        this.locationDescription = locationDescription;
    }

    public void setLocationRoadSign(String roadSign){
        if(roadSign.isEmpty() || roadSign.isBlank()){
            throw new IllegalArgumentException("Road sign cannot be an empty string.");
        }
        this.locationRoadSign = roadSign;
    }

    /*
        This method sets the index of an array associated 
        with a location object to the location object in that
        particular direction.
    */
    public void setLocationExit(int index, Location destination){
        if(index < 0){
            throw new IllegalArgumentException("The index of the exit must be 0 or greater");
        }
        this.locationExits[index] = destination;
    }

    public void setLocationEnemyCount(int locationEnemyCount){
        this.locationEnemies = new EnemyCharacter[locationEnemyCount];        
    }

    //To be used in game.java to set enemies for a location
    public void setLocationEnemy(int index, EnemyCharacter enemy){
        this.locationEnemies[index] = enemy;
    }

    public void setLocationItemCount(int locationItemCount){
        this.locationItems = new Item[locationItemCount];
    }

    //To be used in game.java to set items for a location
    public void setLocationItem(int index, Item item){
        this.locationItems[index] = item;
    }

    public void setLocationItemDescriptionCount(int locationItemDescriptionCount){
        this.locationItemSpotDescriptions = new String[locationItemDescriptionCount];
    }

    //To be used in game.java to set spot descriptions for items in a location
    public void setLocationItemDescription(int index, String description){
        this.locationItemSpotDescriptions[index] = description;
    }
}