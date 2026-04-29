package Location;
import Characters.EnemyCharacter;
import Items.Item;

public class Location{
    private String locationName;
    private String locationDescription;
    private String roadSign;
    //This array will store values 0,1,2,3 which correspond to N,E,S,W
    private Location[] exit;
    private EnemyCharacter[] enemies;
    private int enemyCount;
    private Item[] itemInLocation;
    private String[] itemSpot;

    public Location(String locationName, String locationDescription, String roadSign, int maxLocationItems){
        setLocationName(locationName);
        setLocationDescription(locationDescription);
        setRoadSign(roadSign);
        this.itemInLocation = new Item[maxLocationItems];
        this.itemSpot = new String[maxLocationItems];
        /*
            This locations array is now size 4 so that it can hold the 
            locations associated with N,E,S,W
        */
        this.exit = new Location[4];
        this.enemies = new EnemyCharacter[5];
        this.enemyCount = 0;
    }

    //Get methods
    public String getLocationName(){
        return this.locationName;
    }

    public String getLocationDescription(){
        return this.locationDescription;
    }

    public String getRoadSign(){
        return this.roadSign;
    }

    public Location getExit(int index){
        /*
            Example would be: "castle.getExit(2)" would
            return the exit to the South back to town
            because the number 2 is mapped to SOUTH
            This method returns Location because we store
            Location objects in our array
        */
        return exit[index];
    }

    public Item getLocationItem(int index){
        return this.itemInLocation[index];
    }

    public String getItemSpot(int index){
        return this.itemSpot[index];
    }

    public int getLocationItemLength(){
        return itemInLocation.length;
    }

    //Set methods
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

    public void setRoadSign(String roadSign){
        if(roadSign.isEmpty() || roadSign.isBlank()){
            throw new IllegalArgumentException("Road sign cannot be an empty string.");
        }
        this.roadSign = roadSign;
    }

    /*
        This method sets the index of an array associated 
        with a location object to the location object in that
        particular direction.
    */
    public void setExit(int index, Location destination){
        if(index < 0){
            throw new IllegalArgumentException("The index of the exit must be 0 or greater");
        }
        exit[index] = destination;
    }
    public EnemyCharacter[] getEnemies(){
    return this.enemies;
}

public void setEnemy(EnemyCharacter enemy){
    if(enemyCount < enemies.length){
        enemies[enemyCount] = enemy;
        enemyCount++;
    }
}

public EnemyCharacter getEnemyByName(String name){
    for(int i = 0; i < enemyCount; i++){
        if(enemies[i].getCharacterName().equalsIgnoreCase(name)){
            return enemies[i];
        }
    }
    return null;
}

public void removeEnemy(EnemyCharacter enemy){
    for(int i = 0; i < enemyCount; i++){
        if(enemies[i] == enemy){
            enemies[i] = enemies[enemyCount - 1];
            enemies[enemyCount - 1] = null;
            enemyCount--;
            return;
        }
    }
}
    
    public void setItem(int index, Item newItem, String itemSpot){
        if(index < 0){
            throw new IllegalArgumentException("The index of the exit must be 0 or greater");
        }
        this.itemInLocation[index] = newItem;
        this.itemSpot[index] = itemSpot;
    }
}