import Characters.*;
import Items.Item;
import Items.Weapon;
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


    public static void main(String[] args){
        /*To follow object oriented programming concepts we create an object
        called myGame wus to work with non-static variables and it makes our 
        code less messy myGame is our engine*/
        Game myGame = new Game();
        myGame.start();
    }



















    /*This method starts the game by trigerring the initialize and play methods*/
    public void start(){
        initialize();
        play();
    }

    /*This method initializes everything within game
    like locations, characters, items, etc.*/
    public void initialize(){
        //Creating all of our objects
        forest = new Location("Forest of Dreams");
        town = new Location("The Town of Brimswick");
        cave = new Location("Goopy Cave");
        inn = new Location("The Stove Pipe Inn");
        castle = new Location("Corrupted Castle");
        hero = new MainCharacter("Hero", 100, 100, 0, 0, 1, 1, 1, forest);
        Weapon goblinSword = new Weapon("Goblin Sword",10);
        EnemyCharacter goblin = new EnemyCharacter("Goblin", 30,10,5,goblinSword,20);
        cave.setEnemy(goblin);

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

    /*This is the game engine which contains the while loop
    which keeps the game running.*/
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

            if(input.equals("quit")){
                isRunning = false;
                scanner.close();
            }
            else if(input.startsWith("fight ")){
                String enemyName = input.substring(6);
                isRunning = fightEnemy(enemyName);
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
        }

    }


    /*This method is in charge of taking user input trimming off any white space and converting
    it to lowercase for processing purposes*/
    public String processInput(String input){
        input = input.toLowerCase().trim();
        return input;
    }

    public void manual(){
        JOptionPane.showMessageDialog(null, "Manual:\n" +

        "To move your character type “go” followed by any of the four cardinal" +
        "directions (north, east, south, west)\n\n"+
    
        "To get a description of your character type \"character\"");
    }


    /*If our user input is any cardinal direction this method will be triggered
    it will take the string the user input and return the final static int variables
    we have declared at the beginning of the code that we associasted with a direction*/
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

    /*This method actually takes care of the logic of assigning the new location to our 
    main characters currentLocation variable*/
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
            return "Moving to " + hero.getCurrentLocation().getLocationName();
        }
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

        Item loot = enemy.dropLoot();

        if(loot != null){
            hero.addItem(loot);
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
 }

