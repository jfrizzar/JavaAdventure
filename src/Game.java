import java.util.Scanner;

public class Game{
    public static void main(String[] args){
        Game myGame = new Game();
        myGame.start();
        
    }

    /*This method starts the game*/
    public void start(){
        initialize();
        play();
    }

    /*This method initializes everything within game
    like locations, characters, items, etc.*/
    public void initialize(){

    }

    /*This is the game engine which contains the while loop
    which keeps the game running.*/
    public static void play(){
        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to a Java-based text adventure!");

        while(isRunning){
            System.out.print("> ");
            String input = scanner.nextLine();
            //TODO
            processInput(input);
            if (input == "quit"){
                isRunning = false;
            }
        }

    }
}
