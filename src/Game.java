import java.util.Scanner;

public class Game{
    /*These final variables permanently associated the key words 
    for directions with the number used in our arrays*/
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    /*Declaring all objects to be initialized globally so that they don't dissapear
    due to being declared locally within a method*/
    public Location forrest;
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
        forrest = new Location("Forrest of Dreams");
        town = new Location("The Town of Brimswick");
        cave = new Location("Goopy Cave");
        desert = new Location("Tyri Desert");
        inn = new Location("The Stove Pipe Inn");
        castle = new Location("Corrupted Castle");
        hero = new MainCharacter("Hero", 100, 100, 0, 0, 1, 1, 1, forrest);

        /*Here we create all of the exits associated with the location. The first parameter is the
        cardinal direction which is the final int we declared at the beginning of our code and
        the second parameter is the location object associated with that exit based on our map*/
        forrest.setExit(NORTH, town);
        forrest.setExit(EAST, desert);
        forrest.setExit(SOUTH, null);
        forrest.setExit(WEST, cave);

        cave.setExit(NORTH, null); 
        cave.setExit(EAST, forrest); 
        cave.setExit(SOUTH, null);        
        cave.setExit(WEST, null);

        desert.setExit(NORTH, null);
        desert.setExit(EAST, null);
        desert.setExit(SOUTH, null);
        desert.setExit(WEST, forrest);

        town.setExit(NORTH, castle);
        town.setExit(EAST, inn);
        town.setExit(SOUTH, forrest);
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
        System.out.println("Welcome to a Java-based text adventure!");
        System.out.println(hero.toString());

        //Need to print instructions here for the user

        while(isRunning){
            
            System.out.print("> ");
            String input = scanner.nextLine();

            /*This function will process user input and respond accordingly*/
            input = processInput(input);

            if(input == "quit"){
                isRunning = false;
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
}
