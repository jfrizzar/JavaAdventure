package Characters;
import Items.Item;

public class Gambler extends NPCharacter{
    //This character will play a dice game with the player. Win = gold, lose = gambler takes
    //- an item from the player

    //Constructor
    public Gambler(String name, int hp, int mp, int gp, String[] greetings, String[] goodbyes, String[] dialogueOptions, Item[] inventory){
        super(name, hp, mp, gp, greetings, goodbyes, dialogueOptions, inventory);
    }

    @Override
    public boolean handleChoice(int choice, MainCharacter player){
        switch(choice){
            
            case 1: //Response to "Where am I?"
            System.out.println(talk("Ah, you don't know where you are, do you? Here's the thing. If I told you, that would ruin the" +  
            " only thing that's fun about a mystery, now wouldn't it? Why don't you have a seat and we'll play some dice, ya? Looks " +
            "like you've got about " + player.getCharacterGP() + " gold pieces on you. Don't worry, Maybe you're carrying something else" +
            " I might like."));
            return true;

            case 2: //Dice game chosen (Ends in gold reward or taken item)
            playDiceGame(player);
            return true;

            case 3: //Chooses exit interaction
            return false;

            default: //User types something other than the choices
            System.out.println(talk("\nYeah, that ain't happenin'.")); 
            return true;
        }
    }


    //Dice game method
    private void playDiceGame(MainCharacter player){

        System.out.println(talk("Nothing passes the time like a little risk, ya? Highest number wins.\n"));

        int playerRoll = (int)(Math.random() * 6) + 1;
        int gamblerRoll = (int)(Math.random() * 6) + 1;

        System.out.println("You roll: " + playerRoll);
        System.out.println(getCharacterName() + " rolls: " + gamblerRoll);

        if(playerRoll > gamblerRoll){//gambler gives player 20 gold
            System.out.println(talk("Luck runs out eventually, you know? Here's 20 gold pieces."));
            player.setCharacterGP(player.getCharacterGP() + 20); //adding 20 gp to player character
        }
        else if(playerRoll < gamblerRoll){//gambler takes item if there is one to take
            System.out.println(talk("Well, well, well. Not your lucky day. What's a " + 
            "little greed without spontineity? I'll be taking something from you then."));
            System.out.println("The gambling man takes something from your inventory so quickly you're unsure what exactly he took");//placeholder
            //unfinished (Need items finished)
        }
        else{
            System.out.println(talk("Looks like no one won. Let's go again, ya?"));
        }


    }

}//End of class
