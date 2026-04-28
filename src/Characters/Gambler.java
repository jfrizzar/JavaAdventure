package Characters;
import Items.Item;
import java.util.Scanner;

/*
    This character will play a dice game with the player. 
    When a player wins they get gold
    When a player loses the gambler takes an item from the players inventory
*/

public class Gambler extends NPCharacter{

    //Constructors
    public Gambler(String characterName, int characterHP,
        int characterMP, int characterGP, String[] greetings, 
        String[] goodbyes, String[] dialogueOptions, int inventorySize){
        super(characterName, characterHP, characterMP, characterGP
            ,greetings, goodbyes, dialogueOptions, inventorySize);
    }

    @Override
    public boolean handleChoice(int choice, MainCharacter player, Scanner input){
        switch(choice){
            
            case 1: //Response to "Where am I?"
            System.out.println(talk("Ah, you don't know where you are, do you? Here's the thing. If I told you, that would ruin the" +  
            " only thing that's fun about a mystery, now wouldn't it? Why don't you have a seat and we'll play some dice, ya? Looks " +
            "like you've got about " + player.getCharacterGP() + " gold pieces on you. I have plenty of gold, but don't worry, maybe you're" +
             " carrying something else I might like."));
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
            
            int inventorySize = player.getCharacterInventoryLength();

            //Finds random item and takes it
            int index = -1;

            for(int i = 0; i < inventorySize; i++){//looping through inventory
                int randomIndex = (int)(Math.random() * inventorySize);
                
                Item item = player.getCharacterInventoryItem(randomIndex);

                if(item != null && !item.getItemName().equals("Empty")){
                    index = randomIndex;
                    break;
                }
            }
            if(index == -1){
                //If the player has no items to take:
                System.out.println(talk("Y'ave nothing worth taking looks like. Game was on the house then, ya?"));
            }else{
                Item stolenItem = player.getCharacterInventoryItem(index);
                player.setInventoryItem(index, null); //removing item

                System.out.println("\nThe hooded man snatches your " + stolenItem.getItemName() + "! You may never see it again.");
            }
        }
        else{
            System.out.println(talk("Looks like no one won. Let's go again, ya?"));
        }


    }
}
