package Location;
public class Location{
    private String locationName;
    //This array will store values 0,1,2,3 which correspond to N,E,S,W
    private Location[] exit;
    public Location(){

    }

    public Location(String locationName){
        setLocationName(locationName);
        /*This locations array is now size 4 so that it can hold the 
        locations associated with N,E,S,W*/
        this.exit = new Location[4];
    }

    //Get methods
    public String getLocationName(){
        return this.locationName;
    }

    public Location getExit(int index){
        /*Example would be: "castle.getExit(2)" would
        return the exit to the South back to town
        because the number 2 is mapped to SOUTH
        
        This method returns Location because we store
        Location objects in our array*/
        return exit[index];
    }

    //Set methods
    public void setLocationName(String locationName){
        this.locationName = locationName;
    }

    /*This method sets the index of an array associated 
    with a location object to the location object in that
    particular direction */
    public void setExit(int index, Location destination){
        exit[index] = destination;
    }

}