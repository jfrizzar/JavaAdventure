package characters;
import location.Location;

/*
    This class acts as a blueprint for NPC characters. 
    Its subclasses will be NPC archetypes like Merchant and Gambler from which 
    NPC characters can be made from
*/

public class NPCharacter extends Character{ 
   private String[] npcGreetings; //Array for randomly picked greetings
   private String[] npcGoodbyes; //Array for randomly picked goodbyes
   private String[] npcDialogueOptions;  //Array that holds main dialogue options 

   //Constructor
   public NPCharacter(String characterName, int characterCurrentHP, int characterMaxHP, 
                     int characterCurrentMP, int characterMaxMP, int characterGP, 
                     int characterCurrentXP, int characterMaxXP, int inventorySize,
                     Location characterLocation, String[] npcGreetings, String[] npcGoodbyes, 
                     String[] npcDialogueOptions){
        super(characterName, characterCurrentHP, characterMaxHP, 
              characterCurrentMP, characterMaxMP, characterGP, 
              characterCurrentXP, characterMaxXP, inventorySize,
              characterLocation);
    setNPCGreetings(npcGreetings);
    setNPCGoodbyes(npcGoodbyes);
    setNPCDialogueOptions(npcDialogueOptions);
   }

   //Get Methods
   public String[] getNPCGreetings(){
        return npcGreetings;
   }

   public String[] getNPCGoodbyes(){
        return npcGoodbyes;
   }

   public String[] getNPCDialogueOptions(){
        return npcDialogueOptions;
   }

   //Set Methods
   public void setNPCGreetings(String[] npcGreetings){
        this.npcGreetings = npcGreetings;
   }

   public void setNPCGoodbyes(String[] npcGoodbyes){
        this.npcGoodbyes = npcGoodbyes;
   }

   public void setNPCDialogueOptions(String[] npcDialogueOptions){
        this.npcDialogueOptions = npcDialogueOptions;
   }

    //Special Purpose Methods
        //Shows one random greeting at the beginning of the interaction menu
    public void showRandomGreeting(){
        if(npcGreetings != null && npcGreetings.length > 0){
            int i = (int)(Math.random() * npcGreetings.length);
            System.out.println(talk(npcGreetings[i]));
        }
        else{
            System.out.println("They do not want to talk to you.");
        }
    }
    
    //Shows one random goodbye at the end of the interaction menu
    public void showRandomGoodbye(){
        if(npcGoodbyes != null && npcGoodbyes.length > 0){
            int i = (int)(Math.random() * npcGoodbyes.length);
            System.out.println(talk(npcGoodbyes[i]));
        }
   }

    //Shows dialogue options in the interaction menu
   public void showDialogueMenu(){
        if(npcDialogueOptions != null){
            for(int i = 0; i < npcDialogueOptions.length; i++){
                System.out.println((i + 1) + ". " + npcDialogueOptions[i]);
            }
        }
   }

   //This method is overridden in the NPC subclasses
   public boolean handleChoice(int choice, MainCharacter player, String optionSelected){
        System.out.println(getCharacterName() + " doesn't respond."); //expect this print if override doesn't work
        return false;
   }

   //Helper method
   public String talk(String dialogue){
        return "\n" + getCharacterName() + " says: " + dialogue;
   }

   @Override
   public String toString(){
        return super.toString() + "\nNPC with dialogue system.";
   }
}