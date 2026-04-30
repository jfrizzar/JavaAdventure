import Characters.*;
import Items.*;
import Location.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

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
    public Merchant merchant;
    public EnemyCharacter enemy1;
    public Item emptySlot;
    public Consumable healthPotion;
    public Consumable manaPotion;
    public Weapon sword;

    //*helper variables
    private boolean hasVisitedInn = false;
    private boolean hasVisitedTown = false; 

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
        forest = new Location("Forest of Dreams", "A quaint forest, there is nobody around except for yourself.","North - Brimswick\n West - Goopy Cave", 2);
        town = new Location("The Town of Brimswick", "The town of Brimswick, established in the golden era home to many of the noble family.", "North - Corrupted Castle\nEast - Stove Pipe Inn\nSouth - Forest of Dreams\n", 2);
        cave = new Location("Goopy Cave", "A dank cave with a weirdly sweet smell, there is an item behind a rock to your right, and what look to be a goblin 20 feet ahead!", "The only way out of here is East.", 2);
        inn = new Location("The Stove Pipe Inn", "A nice warm inn, a hotspot for travelling merchants, somebody left something here on the table", "The only way out of here is West.", 2);
        castle = new Location("Corrupted Castle", "You get a bad feeling from this castle, a dark knight named draxus comes up to you menacingly.", "The only way out is South", 2);

        
        //Creating all of our items
        emptySlot = new Item("Empty");
        healthPotion = new Consumable("Health Potion", 50, 0);
        manaPotion = new Consumable("Mana Potion", 0, 50);
        sword = new Weapon("Sword", 10);
        Weapon goblinSword = new Weapon("Goblin Sword",10);
        EnemyCharacter goblin = new EnemyCharacter( "Goblin", 30, 10, 5, goblinSword, 20,1);
        cave.setEnemy(goblin);
        Weapon darkKnightSword = new Weapon("Dark Knight Sword", 20);
        EnemyCharacter draxus = new EnemyCharacter("Draxus", 60,20,10,darkKnightSword,30, 1);

        castle.setEnemy(draxus);

        //Creating all of our characters
        hero = new MainCharacter("Hero", 100, 100, 100, 0, 1, 1, 1, forest, 4);
        hero.setInventoryItem(0, emptySlot);
        hero.setInventoryItem(1, emptySlot);
        hero.setInventoryItem(2, emptySlot);
        hero.setInventoryItem(3, emptySlot);

        gambler = new Gambler("Dommermac", 100, 100, 100, 
            new String[]{"Haven't seen you around before.", "Care to play some dice with a stranger, Stranger?"}, 
            new String[]{"Now don't go getting lost or I'll have one less person to wager with, ya?", "Hope your luck is better out there than it was in here, Stranger."}, 
            new String[]{"Ask where you are", "Play Dice","Exit"}, 4);
        
        merchant = new Merchant("Wizlo", 100, 100, 3000, new String[]{"Welcome traveler!", "Welcome " +
         "to my shop, friend!"}, new String[]{"Bye bye, friend.", "Let's not be strangers now. Goodbye!"},new String[]{"Talk", "Shop", "Leave"
         }, 5, new Item[]{healthPotion, manaPotion, sword}, new int[]{10, 10, 40});

        enemy1 = new EnemyCharacter("Draxus", 25, 50, 10, healthPotion, 10, 3); 

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

        castle.setEnemy(enemy1);
    }

    /*
        This is the game engine which contains the while loop
        which keeps the game running.
    */
    public void play(){
        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n\nType \"man\" to access the manual.");
        System.out.println("You awaken in a forest unsure of where you are.");

        //Need to print instructions here for the user

        while(isRunning){
            
            System.out.print("> ");
            String input = scanner.nextLine();

            /*This function will process user input and respond accordingly*/
            input = processInput(input);

            if(input.equals("quit") || input.equals("q")){
                isRunning = false;
                
            }
            else if(input.startsWith("fight ")){
                String enemyName = input.substring(6);
                boolean result = fightEnemy(enemyName);
                if (result == false){
                    isRunning = false;
                }
            }
            else if(input.equals("man")){
                manual();
            }
            else if(input.equals("character")){
                JOptionPane.showMessageDialog(null, hero.toString());
            }
            else if(input.equals("go north") || input.equals("go east") || input.equals("go south") || input.equals("go west")){
                int direction = parseDirection(input);
                System.out.println(moveMainCharacter(direction, scanner));
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
            else if(input.startsWith("use ")){
            useItem(input);
}
        }
        scanner.close();
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
        To fight an enemy type "fight <enemy name>".
        To use a potion type "use <potion name>".
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

    public String moveMainCharacter(int direction, Scanner scanner){
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
            System.out.println(nextLocation.getLocationDescription());

            if(nextLocation == town){
                if(!hasVisitedTown){
                    System.out.println("You make your way into Brimswick...");
                    hasVisitedTown = true;
                }
                System.out.println("'Wizlo's Wonders' is written in big letters on a plank, running vertical to the ground." +
                 " It marks a stall where a small, fat man with an exquisite outfit sits, and he waves you over.");
                 merchant.interact(hero, scanner);

            }

            if(nextLocation == inn){
                if(!hasVisitedInn){
                       System.out.println("You approach the inn. It isn't a very welcoming place by the looks of it. A mixture of cold stone and old," +
                        "seasoned wooden planks comprise its structure. As you open the creaking wooden door and walk in, however, " +
                        "an amber glow emanates warmth and comfort from the middle of the inn, where a fire is being kept by a young " +
                        "woman with an iron poker. Before you can observe much else, you see a mysterious man. He has a dark hood covering" +
                        " his features. You can't help but wonder if this feeling of comfort in the inn betrays you. Nonetheless, you feel drawn" +
                         " to speak with him. You approach.");
                hasVisitedInn = true; //Shows inn text only once 
                }
                gambler.interact(hero, scanner);//passing player object to NPC to interact with player methods
            }
            return "";//returning this because output already happened here
        }
    }

    /*
        This method respond to the location commands and gives a 
        brief description about the players current location.
    */
    public void viewInventory(){
        for(int i = 0; i < hero.getCharacterInventoryLength(); i++){
            System.out.println("Item " + (i + 1) + ": " + hero.getCharacterInventoryItem(i));
        }
    }

    /*
        This method allows the player to search an area for items. It loops over all items within
        the players current location and lists where the items and where they are located.
    */
    public boolean searchArea(){
    boolean itemFound = false;
        for(int i = 0; i < hero.getCurrentLocation().getLocationItemLength(); i++){
            if(hero.getCurrentLocation().getLocationItem(i) != null){
                itemFound = true;
                System.out.println("You found: " + hero.getCurrentLocation().getLocationItem(i).getItemName() + " " + hero.getCurrentLocation().getItemSpot(i));
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
        This method allows the player to pickup and item. It first checks to see if there
        are items in a location. If there are it calls the itemDecider method, if not it states
        so.
    */
    public void pickupItem(String input){
        boolean availableItems = false;
        try{
            for(int i = 0; i < hero.getCurrentLocation().getLocationItemLength(); i++){
                if(hero.getCurrentLocation().getLocationItem(i) != null){
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
                System.out.println("There are no items in this location.");
            }
        }catch(NullPointerException npe){
            System.out.println("There are no items in this location.");
        }
    }

    /*
        This method decides on the item type the player wants to pick up.
    */
    public void itemDecider(String input){
        int i;
        Consumable healthPotion = new Consumable("Health Potion", 50, 0);
        Consumable manaPotion = new Consumable("Mana Potion", 0, 50);
        Weapon sword = new Weapon("Sword", 10);


        for(i = 0; i < hero.getCurrentLocation().getLocationItemLength(); i++){
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
        for(i = 0; i < hero.getCharacterInventoryLength(); i++){
            if(hero.getCharacterInventoryItem(i) == null || hero.getCharacterInventoryItem(i) == emptySlot){
                hero.setInventoryItem(i, newItem);
                System.out.println("You pick up: " + newItem.getItemName());
                
                for(i = 0; i < hero.getCurrentLocation().getLocationItemLength(); i++){
                    if(hero.getCurrentLocation().getLocationItem(i) == null){
                        continue;
                    }
                    else if(hero.getCurrentLocation().getLocationItem(i).getItemName().equals(newItem.getItemName())){
                        hero.getCurrentLocation().setItem(i, null, null);
                    }
                }
                return;
            }
        }
        System.out.println("Inventory is full.");
        return;
    }
    public boolean fightEnemy(String enemyName){
    EnemyCharacter enemy = hero.getCurrentLocation().getEnemyByName(enemyName);

    if(enemy == null){
        System.out.println("That enemy is not here.");
        return true;
    }

    System.out.println("You are fighting " + enemy.getCharacterName());

    if(hero.getLevel() >= enemy.getExpReward() / 10){
        System.out.println("You defeated " + enemy.getCharacterName());

        Item loot = enemy.getLoot();

        if(loot != null){
            itemDecider(loot.getItemName());
        }

        hero.gainExperience(enemy.getExpReward());
        hero.getCurrentLocation().removeEnemy(enemy);

        return true;
    }
    else{
        System.out.println(enemy.getCharacterName() + " was too strong.");
        System.out.println("Game over.");
        return false;
    }
  }
  public void useItem(String input){
    String itemName = input.substring(4).trim();

    for(int i = 0; i < hero.getCharacterInventoryLength(); i++){
        Item item = hero.getCharacterInventoryItem(i);

        if(item != null && item.getItemName().equalsIgnoreCase(itemName)){

            if(item instanceof Consumable){
                Consumable potion = (Consumable) item;

                // apply effects
                hero.setCharacterHP(hero.getCharacterHP() + potion.getHealthPoints());
                hero.setCharacterMP(hero.getCharacterMP() + potion.getManaPoints());

                System.out.println("You used " + potion.getItemName());
                potion.consume();

                // remove item after use
                hero.setInventoryItem(i, null);

                return;
            }
            else{
                System.out.println("You cannot use that item.");
                return;
            }
        }
    }

    System.out.println("Item not found in inventory.");
}
 }

