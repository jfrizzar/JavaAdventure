public class Location{
    private String locationName;
    //This array will store values 0,1,2,3 which correspond to N,E,S,W
    private Location[] exit;
    public Location(){

    }

    public Location(String name){
        setLocationName(name);
        this.exit = new Location[4];
    }

    public String getLocationName(){
        return this.locationName;
    }

    public Location getExit(int index){
        return exit[index];
    }

    public void setLocationName(String locationName){
        this.locationName = locationName;
    }

    public void setExit(int index, Location destination){
        exit[index] = destination;
    }

}