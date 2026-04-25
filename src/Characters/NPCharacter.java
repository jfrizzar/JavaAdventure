package Characters;
import java.util.Scanner;

/*
    This class acts as a blueprint for NPC characters. 
    Its subclasses will be NPC archetypes like Merchant and Gambler from which 
    NPC characters can be made from
*/

public class NPCharacter extends Character{ 
   private String[] greetings; //Array for randomly picked greetings
   private String[] goodbyes; //Array for randomly picked goodbyes
   private String[] dialogueOptions;  //Array that holds main dialogue options 

   //Constructors
   public NPCharacter(){
    super();
   }
   
   public NPCharacter(String characterName, int characterHP, 
    int characterMP, int characterGP, String[] greetings,
    String[] goodbyes, String[] dialogueOptions, int inventorySize){
    super(characterName, characterHP, characterMP, characterGP, inventorySize);
    setGreetings(greetings);
    setGoodbyes(goodbyes);
    setDialogueOptions(dialogueOptions);
   }

   //Get methods
   public String[] getGreetings(){
    return greetings;
   }

   public String[] getGoodbyes(){
    return goodbyes;
   }

   public String[] getDialogueOptions(){
    return dialogueOptions;
   }

   //Set methods
   public void setGreetings(String[] greetings){
    this.greetings = greetings;
   }

   public void setGoodbyes(String[] goodbyes){
    this.goodbyes = goodbyes;
   }

   public void setDialogueOptions(String[] dialogueOptions){
    this.dialogueOptions = dialogueOptions;
   }

   
    //Shows one random greeting at the beginning of the interaction menu
    public void showRandomGreeting(){
    if(greetings != null && greetings.length > 0){
        int i = (int)(Math.random() * greetings.length);
        System.out.println(talk(greetings[i]));
        }
    }

    //Shows one random goodbye at the end of the interaction menu
   public void showRandomGoodbye(){
    if(goodbyes != null && goodbyes.length > 0){
        int i = (int)(Math.random() * goodbyes.length);
        System.out.println(talk(goodbyes[i]));
    }
   }

   //Shows dialogue options in the interaction menu
   public void showDialogueMenu(){
    if(dialogueOptions != null){
        for(int i = 0; i < dialogueOptions.length; i++){
            System.out.println((i+1) + ". " + dialogueOptions[i]);
        }
    }
   }

   //Main interaction loop (calls on the show methods)
   public void interact(MainCharacter player){ //Note: this parameter allows the NPC character to play with the user character's methods
    Scanner input = new Scanner(System.in);
    boolean running = true;

    //1. Shows a random greeting
    showRandomGreeting();

    //2. Dialogue Loop
    while(running){
        System.out.println("\nTalking to " + getCharacterName());
        showDialogueMenu();

        System.out.print("Choose an option: ");

        if(!input.hasNextInt()){
            System.out.println("You cannot choose that.");
            input.next(); //clears scanner
            continue; //restarts loop
        }

        int choice = input.nextInt();
        running = handleChoice(choice, player);
    }

    //3. Shows a random goodbye
    showRandomGoodbye();
    input.close();
   }

   // ====To be overidden==== //
   public boolean handleChoice(int choice, MainCharacter player){
    System.out.println(getCharacterName() + " doesn't respond."); //expect this print if override doesn't work
    return false;
   }

   //Helper method
   public String talk(String dialogue){
    return "\n" + getCharacterName() + " says: " + dialogue;
   }

   @Override
   public String toString(){
    return super.toString() + 
    "\nNPC with dialogue system.";
   }
}
