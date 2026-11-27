import java.util.Scanner;
import characters.*;
import items.*;
import location.*;

public class Game{
    /*
        These static final variables permanently associated the key words 
        for directions with the number used in our arrays
    */
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    /*
        Declaring all objects to be initialized globally so that they don't dissapear
        due to being declared locally within a method
    */
   //Locations
    public Location forest;
    public Location town;
    public Location cave;
    public Location desert;
    public Location castle;
    public Location inn;
    //Main Character
    public MainCharacter player;
    //NPCs
    public NPCharacter NPC;
    public Gambler gambler;
    public Merchant merchant;
    //Items
    public Item emptySlot;
    public Consumable healthPotion;
    public Consumable manaPotion;
    public Weapon sword;
    public Weapon goblinSword;
    public Weapon darkKnightSword;
    public EnemyCharacter draxus;
    public EnemyCharacter goblin;


    //Set is running to true to enable the gameplay loop
    public boolean gameRunning = true;

    public static void main(String[] args){
        /*
            To follow object oriented programming concepts we create an object
            called myGame to work with non-static variables and it makes our 
            code less messy myGame is our engine
        */
        Game javaAdventure = new Game();
        javaAdventure.start();
    }

    /*
        This method starts the game by trigerring the initialize and play methods
    */
    public void start(){
        Scanner gameScanner = new Scanner(System.in);
        initialize();
        play();
        while(gameRunning == true){
            System.out.print(">");
            String userInput = gameScanner.nextLine();
            System.out.println(handlePlayerInput(userInput));
        }
        gameScanner.close();
    }

    /*
        This method initializes everything within game
        like locations, characters, items, etc.
    */
    public void initialize(){
        //Initializing all locations
        forest = new Location("Forest of Dreams", "A quaint forest, there is nobody around except for yourself.\n","North - Brimswick\n West - Goopy Cave", 0, 0, 0);
        town = new Location("The Town of Brimswick", "The town of Brimswick, established in the golden era home to many of the noble family.\n", "North - Corrupted Castle\nEast - Stove Pipe Inn\nSouth - Forest of Dreams\n", 0, 0, 0);
        cave = new Location("Goopy Cave", "A dank cave with a weirdly sweet smell, there is an item behind a rock to your right, and what look to be a goblin 20 feet ahead!\n", "The only way out of here is East.", 1, 2, 2);
        inn = new Location("The Stove Pipe Inn", "A nice warm inn, a hotspot for travelling merchants, somebody left something here on the table\n", "The only way out of here is West.", 0, 0, 0);
        castle = new Location("Corrupted Castle", "You get a bad feeling from this castle, a dark knight named draxus comes up to you menacingly.\n", "The only way out is South", 1, 1, 1);

        
        //Initializing all items
        emptySlot = new Item("Empty");
        healthPotion = new Consumable("Health Potion", 50, 0);
        manaPotion = new Consumable("Mana Potion", 0, 50);
        sword = new Weapon("Sword", 10);
        goblinSword = new Weapon("Goblin Sword",10);
        darkKnightSword = new Weapon("Dark Knight Sword", 20);
        draxus = new EnemyCharacter("Draxus", 60, 60, 20, 20, 1000, 100, 100, 4, castle);
        goblin = new EnemyCharacter( "Goblin", 25, 25, 1, 1, 10, 10, 10, 4, cave);

        //Initializing all characters
        player = new MainCharacter("Player1", 100, 100, 100, 100, 0, 0, 100, 4, forest, 1, 10, 5);
        player.setInventoryItem(0, emptySlot);
        player.setInventoryItem(1, emptySlot);
        player.setInventoryItem(2, emptySlot);
        player.setInventoryItem(3, emptySlot);

        gambler = new Gambler("Dommermac", 100, 100, 100, 100, 100, 1, 1, 4, inn,
            new String[]{"Haven't seen you around before.", "Care to play some dice with a stranger, Stranger?"}, 
            new String[]{"Now don't go getting lost or I'll have one less person to wager with, ya?", "Hope your luck is better out there than it was in here, Stranger."}, 
            new String[]{"Ask where you are", "Play Dice","Exit"});
        
        merchant = new Merchant("Wizlo", 100, 100, 100, 100, 3000, 1, 1, 4, town,
            new String[]{"Welcome traveler!", "Welcome " + "to my shop, friend!"},
            new String[]{"Bye bye, friend.", "Let's not be strangers now. Goodbye!"},
            new String[]{"Talk", "Shop", "Leave"},
            new Item[]{healthPotion, manaPotion, sword}, 
            new int[]{10, 10, 40});


        /*
            Here we create all of the exits associated with the location. The first parameter is the
            cardinal direction which is the final int we declared at the beginning of our code and
            the second parameter is the location object associated with that exit based on our map
        */
        forest.setLocationExit(NORTH, town);
        forest.setLocationExit(EAST, null);
        forest.setLocationExit(SOUTH, null);
        forest.setLocationExit(WEST, cave);

        cave.setLocationExit(NORTH, null); 
        cave.setLocationExit(EAST, forest); 
        cave.setLocationExit(SOUTH, null);        
        cave.setLocationExit(WEST, null);
        cave.setLocationItem(0, healthPotion);
        cave.setLocationItemDescription(0, "Behind a boulder.");
        cave.setLocationItem(1, sword);
        cave.setLocationItemDescription(1, "In a shallow puddle.");
        cave.setLocationEnemy(0, goblin);

        town.setLocationExit(NORTH, castle);
        town.setLocationExit(EAST, inn);
        town.setLocationExit(SOUTH, forest);
        town.setLocationExit(WEST, null);

        inn.setLocationExit(NORTH, null);
        inn.setLocationExit(EAST, null);
        inn.setLocationExit(SOUTH, null);
        inn.setLocationExit(WEST, town);

        castle.setLocationExit(NORTH, null);
        castle.setLocationExit(EAST, null);
        castle.setLocationExit(SOUTH, town);
        castle.setLocationExit(WEST, null);
        castle.setLocationEnemy(0, draxus);
    }

    public void play(){
        System.out.println("Type \"man\" to access the manual.");
        System.out.println("You awaken in a forest unsure of where you are.");
    }

    /*
        This method is in charge of taking user input trimming off any white space and converting
        it to lowercase for processing purposes
    */
    public String processInput(String input){
        input = input.toLowerCase().trim();
        return input;
    }

    public String handlePlayerInput(String rawInput){
        String input = processInput(rawInput);

        if(input.equals("quit")){
            gameRunning = false;
            return "Shutting Down";
        }
        if(input.startsWith("fight ")){
            String enemyName = input.substring(6);
            boolean result = fightEnemy(enemyName);
            if (result == false){
                return "Game Over. Refresh to restart";
            }
        }
        if(input.equals("man")){
            return manual();
        }
        if(input.equals("character")){
            return player.toString();
        }
        if(input.equals("go north") || input.equals("go east") || input.equals("go south") || input.equals("go west")){
            int direction = parseDirection(input);
            return moveMainCharacter(direction);
        }
        if(input.equals("location")){
            Location currentLocation = player.getCharacterLocation();
            String currentLocationName = currentLocation.getLocationName();
            String currentLocationDescription = currentLocation.getLocationDescription();
            return "\nYou are currently in the " + currentLocationName + "\n" + currentLocationDescription;
        }
        if(input.equals("view inventory")){
            return viewInventory();
        }
        if(input.equals("search area")){
            return searchArea();
        }
        if(input.startsWith("pick up") || input.startsWith("pickup")){
            System.out.println(pickupItem(input));
        }
        if(input.startsWith("use ")){
            return useItem(input);
        }
        if(input.startsWith("talk to ")){
            //talkToNPC(player, input, npc);
        }
        return "";
    }

    /*
        This is the games manual and it contains all the valid commands a player can use. 
    */
    public String manual(){
        return          """
                                    Game Manual:

        (1) To quit the game type "quit".
        (2) To move your character type “Go” followed by "North", "East", "South", or "West".
        (3) To get a description of your character type “Character”.
        (4) To get a description of your current location type “Location”.
        (5) To view your inventory type “View Inventory”.
        (6) To search an area for useful items type "Search Area".
        (7) To pick up an item type "Pick Up <Item Name>".
        (8) To fight an enemy type "Fight <Enemy Name>".
        (9) To use a potion type "Use <Potion Name>"
                        """;
    }

    /*
        This method actually takes care of the logic of assigning the new location to the main character.
    */

    public String moveMainCharacter(int direction){
        //This line assigns a new nextLocation object with the exit of the current location object
        Location nextLocation = player.getCharacterLocation().getLocationExit(direction);

        //If there is no exit associated with the direction return string stating so.
        if(nextLocation == null){
            return "\nThere is nothing noteworthy in that direction.\n";
        }
        else{
            //This line assigns the nextLocation object to the new current location.
            player.setCharacterLocation(nextLocation);

            //Printing the movement message and giving description of new location.
            return "\nMoving to " + nextLocation.getLocationName() + "\n" + nextLocation.getLocationDescription();
        }
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
        This method respond to the location commands and gives a 
        brief description about the players current location.
    */
    public String viewInventory(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < player.getCharacterInventoryLength(); i++){
            sb.append("Item ").append(i + 1).append(": ")
            .append(player.getCharacterInventoryItem(i)).append("\n");
        }
        return sb.toString();
    }

    /*
        This method allows the player to search an area for items. It loops over all items within
        the players current location and lists where the items and where they are located.
    */
    public String searchArea(){
    boolean itemFound = false;
        for(int i = 0; i < player.getCharacterLocation().getLocationItemCount(); i++){
            if(player.getCharacterLocation().getLocationItem(i) != null){
                itemFound = true;
                System.out.println("You found: " + player.getCharacterLocation().getLocationItem(i).getItemName());
            }
            else{
                continue;
            }
        }

        if(itemFound == true){
            return "You find some items you can pick up.";
        }
        else{
            return "You search but cannot find anything.";
        }
    }

    /*
        This method allows the player to pickup and item. It first checks to see if there
        are items in a location. If there are it calls the itemDecider method, if not it states
        so.
    */
    public String pickupItem(String input){
        boolean availableItems = false;
        try{
            for(int i = 0; i < player.getCharacterLocation().getLocationItemCount(); i++){
                if(player.getCharacterLocation().getLocationItem(i) != null){
                    availableItems = true;
                }
                else{
                    continue;
                }
            }
            if(availableItems == true){
                itemDecider(input);
            }
            else{
                return "There are no items in this location.";
            }
        }catch(NullPointerException npe){
            return "There are no items in this location.";
        }
        return "";
    }

    /*
        This method decides on the item type the player wants to pick up.
    */
    public void itemDecider(String input){
        int i;
        Consumable healthPotion = new Consumable("Health Potion", 50, 0);
        Consumable manaPotion = new Consumable("Mana Potion", 0, 50);
        Weapon sword = new Weapon("Sword", 10);


        for(i = 0; i < player.getCharacterLocation().getLocationItemCount(); i++){
            if(input.toLowerCase().endsWith("health potion")){
                itemTransfer(healthPotion);
                return;
            }
            else if(input.toLowerCase().endsWith("mana potion")){
                itemTransfer(manaPotion);
                return;
            }
            else if(input.toLowerCase().endsWith("sword")){
                itemTransfer(sword);
                return;
            }
        }
    }

    /*
        This method takes care of the logic of picking up and removing the item from the location.
    */
    public void itemTransfer(Item newItem){
        int i;
        for(i = 0; i < player.getCharacterInventoryLength(); i++){
            if(player.getCharacterInventoryItem(i) == null || player.getCharacterInventoryItem(i) == emptySlot){
                player.setInventoryItem(i, newItem);
                System.out.println("You pick up: " + newItem.getItemName());
                
                for(i = 0; i < player.getCharacterLocation().getLocationItemCount(); i++){
                    if(player.getCharacterLocation().getLocationItem(i) == null){
                        continue;
                    }
                    else if(player.getCharacterLocation().getLocationItem(i).getItemName().equals(newItem.getItemName())){
                        player.getCharacterLocation().setLocationItem(i, null);
                    }
                }
                return;
            }
        }
        System.out.println("Inventory is full.");
        return;
    }

    /*
        This method handles the logic of fight between the main character and an enemy character within
        a patricular location.
    */
    public boolean fightEnemy(String enemyName){
        int locationEnemyCount = player.getCharacterLocation().getLocationEnemyCount();
        if(locationEnemyCount == 0){
            System.out.println("That enemy is not here.");
            return true;
        }

        EnemyCharacter enemy = null;
        int locationEnemyIndex = -1;

        for(int i = 0; i < locationEnemyCount; i++){
            if(player.getCharacterLocation().getLocationEnemy(i).getCharacterName() == enemyName){
                enemy = player.getCharacterLocation().getLocationEnemy(i);
                locationEnemyIndex = i;
                break;
            }
            else{
                continue;
            }
        }

        System.out.println("You are fighting " + enemy.getCharacterName());

        if(player.getCharacterLevel() >= enemy.getCharacterCurrentXP() / 10){
            System.out.println("You defeated " + enemy.getCharacterName());

            //NEED TO FIX GETTING LOOT MECHANISM
            Item loot = enemy.getCharacterInventoryItem(1);

            if(loot != null){
                itemDecider(loot.getItemName());
            }

            player.setCharacterCurrentXP(player.getCharacterCurrentXP() + enemy.getCharacterCurrentXP());
            //NEED TO FIX REMOVING ENEMY
            player.getCharacterLocation().setLocationEnemy(locationEnemyIndex, null);
            return true;
        }
        else{
            System.out.println(enemy.getCharacterName() + " was too strong.");
            System.out.println("Game over.");
            //End game if battle is lost
            return gameRunning = false;
        }
  }

  /*
    This method handles the logic for allowing the main character to use consumable items to gain 
    health an mana points.
  */
    public String useItem(String input){
        String itemToBeUsed = input.substring(4).trim();

        for(int i = 0; i < player.getCharacterInventoryLength(); i++){
            Item itemInInventory = player.getCharacterInventoryItem(i);

            if(itemInInventory != null && itemInInventory.getItemName().equalsIgnoreCase(itemToBeUsed)){

                if(itemInInventory instanceof Consumable){
                    Consumable potion = (Consumable) itemInInventory;

                    // apply effects
                    player.setCharacterCurrentHP(player.getCharacterCurrentHP() + potion.getItemHP());
                    player.setCharacterCurrentMP(player.getCharacterCurrentMP() + potion.getItemMP());

                    potion.consumeItem();

                    // remove item after use
                    player.setInventoryItem(i, emptySlot);

                    return "You used " + potion.getItemName();
                }
                else{
                    return "You cannot use that item";
                }
            }
        }
        return "Item not found in inventory.";
    }

    //Talk to NPC Function
    //This parameter allows the NPC character to play with the user character's methods
    public void talkToNPC(MainCharacter player, String Input, NPCharacter NPC){
        Scanner dialogueScanner = new Scanner(System.in);
        boolean talking = true;

        //Shows a random greeting
        NPC.showRandomGreeting();

        //Dialogue Loop
        while(talking){
            System.out.println("\nTalking to " + NPC.getCharacterName());
            NPC.showDialogueMenu();
            System.out.print("Choose an option: ");

            String optionSelected = dialogueScanner.nextLine();

            if(optionSelected.isEmpty() || !optionSelected.matches("\\d+")){
                System.out.println("You cannot choose that.");
                continue;
            }

            int choice = Integer.parseInt(optionSelected);
            talking = NPC.handleChoice(choice, player, optionSelected);
        }

        //Shows a random goodbye
        NPC.showRandomGoodbye();
    }
}