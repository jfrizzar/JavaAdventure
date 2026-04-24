package Location;

import Items.Item;

public class Location{
    private String locationName;
    private String locationDescription;
    private String roadSign;
    //This array will store values 0,1,2,3 which correspond to N,E,S,W
    private Location[] exit;
    private Item[] item;
    private String[] itemLocation = new String [2];

    public Location(){
    }

    public Location(String locationName, String locationDescription, String roadSign){
        setLocationName(locationName);
        setLocationDescription(locationDescription);
        setRoadSign(roadSign);
        this.item = new Item[2];
        /*This locations array is now size 4 so that it can hold the 
        locations associated with N,E,S,W*/
        this.exit = new Location[4];
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
        /*Example would be: "castle.getExit(2)" would
        return the exit to the South back to town
        because the number 2 is mapped to SOUTH
        
        This method returns Location because we store
        Location objects in our array*/
        return exit[index];
    }

    public Item getItem(int index){
        return this.item[index];
    }

    public String getItemLocation(int index){
        return this.itemLocation[index];
    }

    public Item[] getAllItems(){
        return item;
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

    /*This method sets the index of an array associated 
    with a location object to the location object in that
    particular direction */
    public void setExit(int index, Location destination){
        if(index < 0){
            throw new IllegalArgumentException("The index of the exit must be 0 or greater");
        }
        exit[index] = destination;
    }
    
    public void setItem(int index, Item newItem, String itemLocation){
        if(index < 0){
            throw new IllegalArgumentException("The index of the exit must be 0 or greater");
        }
        this.item[index] = newItem;
        this.itemLocation[index] = itemLocation;
    }
}