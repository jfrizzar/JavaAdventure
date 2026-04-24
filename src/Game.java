import java.util.Scanner;
import javax.swing.JOptionPane;
import Characters.*;
import Items.*;
import Location.*;

public class Game{
    /*These final variables permanently associated the key words 
    for directions with the number used in our arrays*/
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    /*Declaring all objects to be initialized globally so that they don't dissapear
    due to being declared locally within a method*/
    public Location forest;
    public Location town;
    public Location cave;
    public Location desert;
    public Location castle;
    public Location inn;
    public MainCharacter hero;
    public Gambler gambler;
    public Item emptySlot;
    public Consumable healthPotion;
    public Consumable manaPotion;
    public Weapon sword;

    //*helper variables
    private boolean hasVisitedInn = false;

    public static void main(String[] args){
        /*
            To follow object oriented programming concepts we create an object
            called myGame wus to work with non-static variables and it makes our 
            code less messy myGame is our engine
        */
        Game javaAdventure = new Game();
        javaAdventure.start();
    }



















    /*
        This method starts the game by trigerring the initialize and play methods
    */
    public void start(){
        initialize();
        play();
    }

    /*
        This method initializes everything within game
        like locations, characters, items, etc.
    */
    public void initialize(){
        //Creating all of our locations
        forest = new Location("Forest of Dreams", "A quaint forest, there is nobody around except for yourself.","North - Brimswick\n West - Goopy Cave");
        town = new Location("The Town of Brimswick", "The town of Brimswick, established in the golden era home to many of the noble family.", "North - Corrupted Castle\nEast - Stove Pipe Inn\nSouth - Forest of Dreams\n");
        cave = new Location("Goopy Cave", "A dank cave with a weirdly sweet smell, there is an item behind a rock to your right, and what look to be a goblin 20 feet ahead!", "The only way out of here is East.");
        inn = new Location("The Stove Pipe Inn", "A nice warm inn, a hotspot for travelling merchants, somebody left something here on the table", "The only way out of here is West.");
        castle = new Location("Corrupted Castle", "You get a bad feeling from this castle, a dark night comes up to you menacingly.", "The only way out is South");

        
        //Creating all of our items
        emptySlot = new Item("Empty");
        healthPotion = new Consumable("Health Potion", 50, 0);
        manaPotion = new Consumable("Mana Potion", 0, 50);
        sword = new Weapon("Sword", 10);

        //Creating all of our characters
        hero = new MainCharacter("Hero", 100, 100, 0, 0, 1, 1, 1, forest, new Item[4]);
        hero.setCharacterInventoryItem(0, emptySlot);
        hero.setCharacterInventoryItem(1, emptySlot);
        hero.setCharacterInventoryItem(2, emptySlot);
        hero.setCharacterInventoryItem(3, emptySlot);

        gambler = new Gambler("Dommermac", 100, 100, 100, 
            new String[]{"Haven't seen you around before.", "Care to play some dice with a stranger, Stranger?"}, 
            new String[]{"Now don't go getting lost or I'll have one less person to wager with, ya?", "Hope your luck is better out there than it was in here, Stranger."}, 
            new String[]{"Ask where you are", "Play Dice","Exit"}, 
            new Item[4]);
        

        /*Here we create all of the exits associated with the location. The first parameter is the
        cardinal direction which is the final int we declared at the beginning of our code and
        the second parameter is the location object associated with that exit based on our map*/
        forest.setExit(NORTH, town);
        forest.setExit(EAST, null);
        forest.setExit(SOUTH, null);
        forest.setExit(WEST, cave);

        cave.setExit(NORTH, null); 
        cave.setExit(EAST, forest); 
        cave.setExit(SOUTH, null);        
        cave.setExit(WEST, null);
        cave.setItem(0, healthPotion, "Behind a large boulder.");
        cave.setItem(1, sword, "In a shallow puddle.");

        town.setExit(NORTH, castle);
        town.setExit(EAST, inn);
        town.setExit(SOUTH, forest);
        town.setExit(WEST, null);

        inn.setExit(NORTH, null);
        inn.setExit(EAST, null);
        inn.setExit(SOUTH, null);
        inn.setExit(WEST, town);

        castle.setExit(NORTH, null);
        castle.setExit(EAST, null);
        castle.setExit(SOUTH, town);
        castle.setExit(WEST, null);

    }

    /*
        This is the game engine which contains the while loop
        which keeps the game running.
    */
    public void play(){
        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type \"man\" to access the manual.\n\n");
        System.out.println("You awaken in a forest unsure of where you are.");

        //Need to print instructions here for the user

        while(isRunning){
            
            System.out.print("> ");
            String input = scanner.nextLine();

            /*This function will process user input and respond accordingly*/
            input = processInput(input);

            if(input.equals("quit") || input.equals("q")){
                isRunning = false;
                scanner.close();
            }
            else if(input.equals("man")){
                manual();
            }
            else if(input.equals("character")){
                JOptionPane.showMessageDialog(null, hero.toString());
            }
            else if(input.equals("go north") || input.equals("go east") || input.equals("go south") || input.equals("go west")){
                int direction = parseDirection(input);
                System.out.println(moveMainCharacter(direction));
            }
            else if(input.equals("location")){
                System.out.println(hero.getCurrentLocation().getLocationDescription());
            }
            else if(input.equals("view inventory")){
                viewInventory();
            }
            else if(input.equals("search area")){
                searchArea();
            }
            else if(input.startsWith("pick up")){
                pickupItem(input);
            }
        }

    }


    /*
        This method is in charge of taking user input trimming off any white space and converting
        it to lowercase for processing purposes
    */
    public String processInput(String input){
        input = input.toLowerCase().trim();
        return input;
    }

    public void manual(){
        String man = """
        Manual:

        To quit the game type "quit" or "q".

        To move your character type “Go” followed by North, East, South, or West.

        To get a description of your character type “Character”.

        To get a description of your current location type “Location”.

        To view your inventory type “View Inventory”.

        To search an area for useful items type "Search area".

        To pick up an item type "Pick up <item name>".
                
                    """;
        JOptionPane.showMessageDialog(null, man);
    }


    /*
        If our user input is any cardinal direction this method will be triggered
        it will take the string the user input and return the final static int variables
        we have declared at the beginning of the code that we associasted with a direction
    */
    public static int parseDirection(String input){
        if(input.equals("go north")){
            return NORTH; //0
        }
        else if(input.equals("go east")){
            return EAST; //1
        }
        else if(input.equals("go south")){
            return SOUTH; //2
        }
        else if(input.equals("go west")){
            return WEST;// 3
        }
        else{
            return -1;
        }
    }

    /*
        This method actually takes care of the logic of assigning the new location to our 
        main characters currentLocation variable
    */

    public String moveMainCharacter(int direction){
        //This line assigns a new nextLocation object with the exit of the current location object
        Location nextLocation = hero.getCurrentLocation().getExit(direction);

        //If there is no exit return -1 and state that the user cannot go there
        if(nextLocation == null){
            return "You cannot go into that direction";
        }
        else{
            //This line assigns the nextLocation object to the new current location
            hero.setCurrentLocation(nextLocation);
            //Building movement message
            String output = "Moving to " + nextLocation.getLocationName();
            //Printing message
            System.out.println(output);

            if(nextLocation == inn){
                if(!hasVisitedInn){
                       System.out.println("""
                        You approach the inn. It isn't a very welcoming place by the looks of it. A mixture of cold stone and old, 
                        seasoned wooden planks comprise its structure. As you open the creaking wooden door and walk in, however, 
                        an amber glow emanates warmth and comfort from the middle of the inn, where a fire is being kept by a young 
                        woman with an iron poker. 
                        
                        Before you can observe much else, you see a mysterious man. He has a dark hood covering his features. 
                        You can't help but wonder if this feeling of comfort in the inn betrays you. 
                        Nonetheless, you feel drawn to speak with him. You approach.
                                        """);
                hasVisitedInn = true; //Shows inn text only once 
                }
             
                gambler.interact(hero);//passing player object to NPC to interact with player methods
            }
            return "";//returning this because output already happened here
        }
    }

    /*
        This method respond to the location commands and gives a 
        brief description about the players current location.
    */
    public void viewInventory(){
        for(int i = 0; i < hero.getCharacterInventory().length; i++){
            System.out.println("Item " + (i + 1) + ": " + hero.getCharacterInventoryItem(i));
        }
    }

    /*
        This method allows the player to search an area for items. It loops over all items within
        the players current location and lists where the items and where they are located.
    */
    public boolean searchArea(){
    boolean itemFound = false;
        for(int i = 0; i < hero.getCurrentLocation().getAllItems().length; i++){
            if(hero.getCurrentLocation().getItem(i) != null){
                itemFound = true;
                System.out.println("You found: " + hero.getCurrentLocation().getItem(i).getItemName() + " " + hero.getCurrentLocation().getItemLocation(i));
            }
            else{
                continue;
            }
        }

        if(itemFound == false){
            System.out.println("You search but cannot find anything.");
        }
        return itemFound;
    }

    /*
        This method allows the player to pickup and item. It first checks what item the player wants to pickup.
        Then it adds the item to the players inventory checking from empty slots. After it finds an empty slot
        it updates that slot and then it removes the item for the location. 
    */
    public void pickupItem(String input){
        int i;
        try{
            if(input.equals("pick up health potion")){
                for(i = 0; i < hero.getCurrentLocation().getAllItems().length; i++){
                    if(hero.getCurrentLocation().getItem(i).getItemName().equals("Health Potion")){
                        
                        for(i = 0; i < hero.getCharacterInventory().length; i++){
                            if(hero.getCharacterInventoryItem(i) == null || hero.getCharacterInventoryItem(i) == emptySlot){
                                hero.setCharacterInventoryItem(i, healthPotion);
                                System.out.println("You pick up a health potion.");
                                
                                for(i = 0; i < hero.getCurrentLocation().getAllItems().length; i++){
                                    if(hero.getCurrentLocation().getItem(i).getItemName().equals("Health Potion")){
                                        hero.getCurrentLocation().setItem(i, null, null);
                                    }
                                }
                                return;
                            }
                        }
                        System.out.println("Inventory Full.");
                        return;
                    }
                }
            }
            else if(input.equals("pick up mana potion")){
                for(i = 0; i < hero.getCurrentLocation().getAllItems().length; i++){
                    if(hero.getCurrentLocation().getItem(i).getItemName().equals("Mana Potion")){
                        for(i = 0; i < hero.getCharacterInventory().length; i++){
                            
                            if(hero.getCharacterInventoryItem(i) == null || hero.getCharacterInventoryItem(i) == emptySlot){
                                hero.setCharacterInventoryItem(i, manaPotion);
                                System.out.println("You pick up a mana potion.");
                                
                                for(i = 0; i < hero.getCurrentLocation().getAllItems().length; i++){
                                    if(hero.getCurrentLocation().getItem(i).getItemName().equals("Mana Potion")){
                                        hero.getCurrentLocation().setItem(i, null, null);
                                    }
                                }
                                return;
                            }
                        }
                        System.out.println("Inventory Full.");
                        return;
                    }
                }
            }
            else if(input.equals("pick up sword")){
                for(i = 0; i < hero.getCurrentLocation().getAllItems().length; i++){
                    if(hero.getCurrentLocation().getItem(i).getItemName().equals("Sword")){
                        for(i = 0; i < hero.getCharacterInventory().length; i++){
                            
                            if(hero.getCharacterInventoryItem(i) == null || hero.getCharacterInventoryItem(i) == emptySlot){
                                hero.setCharacterInventoryItem(i, sword);
                                System.out.println("You pick up a sword.");
                                
                                for(i = 0; i < hero.getCurrentLocation().getAllItems().length; i++){
                                    if(hero.getCurrentLocation().getItem(i).getItemName().equals("Sword")){
                                        hero.getCurrentLocation().setItem(i, null, null);
                                    }
                                }
                                return;
                            }
                        }
                        System.out.println("Inventory Full.");
                        return;
                    }
                }
            } 
        }catch(NullPointerException npe){
            return;
        }
    }
}
