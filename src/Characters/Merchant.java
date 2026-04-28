package Characters;
import Items.Item;
import java.util.Scanner;

//The Merchant class sells items to the player and interacts with the player
public class Merchant extends NPCharacter {

    //Merchant inventoryto sell
    private Item[] shopItems;

    //Prices for each item (NOTE: These two arrays must stay in sync)
    private int[] itemPrices;

    //Constructor
    public Merchant(String characterName,int characterHP, int characterMP, int characterGP, 
        String[] greetings, String[] goodbyes, String[] dialogueOptions, int inventorySize,
        Item[] shopItems, int[] itemPrices){

            super(characterName, characterHP, characterMP, characterGP, greetings, goodbyes, dialogueOptions, inventorySize);
            this.shopItems = shopItems;
            this.itemPrices = itemPrices;
    }

    //Getters 
    public Item getShopItem(int index){
        return shopItems[index];
    }

    public int getItemPrice(int index){
        return itemPrices[index];
    }


    @Override
    public boolean handleChoice(int choice, MainCharacter player, Scanner input){
        switch(choice){
            
            case 1: //Response to "Placeholder?"
            System.out.println(talk("How are you? You seem a little groggy. Did you wake up on the wrong side of the bed? ...Not one" + 
                " for talking, are you? Well, I'm Wizlo and this is my shop. It's not much of a looker, but if you have a look at my items," +
                " you might find one that suits your fancy."));
            return true;

            case 2: //Shop
            openShop(player, input);
            return true;

            case 3: //Chooses exit interaction
            return false;

            default: //User types something other than the choices
            System.out.println(talk("\nI don't think I can do that, friend.")); 
            return true;
        }
    }



    //Show shop inventory
    public void openShop(MainCharacter player, Scanner input){

        boolean shopping = true;

        System.out.println(talk("Feel free to point to anything you like."));

        while(shopping){

        showShop(player); //shows the shop

        System.out.println("Choose an item: ");

        String line = input.nextLine().trim();

        if(line.isEmpty()){
            System.out.println("Invalid input.");
            continue;
        }

        if(!line.matches("\\d+")){
            System.out.println("Invalid input.");
            continue;
        }
        int choice = Integer.parseInt(line);

        if(choice == 0){
            shopping = false;
            System.out.println(talk("Come again anytime, friend."));
        }else{
            buyItem(choice -1, player);
        }
      }
    }

    //Helper method to print the shop interface
    public void showShop(MainCharacter player){
        System.out.println("\n~~~~~ Wizlo's Wonders ~~~~~");

        //Looping through
        for(int i = 0; i < shopItems.length; i++){
            System.out.println((i + 1) + ". " + shopItems[i].getItemName() + " - " + itemPrices[i] + " GP");
        
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Your gold: " + player.getCharacterGP());
        System.out.println("[Enter 0 to leave shop]");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
    }

    //Method to buy item from merchant
    public boolean buyItem(int index, MainCharacter player){
        if(index < 0 || index >= shopItems.length){
            System.out.println("That item doesn't exist.");
            return false;
        }
        Item item = shopItems[index];
        int price = itemPrices[index];

        //Check if player has enough gold
        if(player.getCharacterGP() < price){
            System.out.println("You don't have enough gold.");
            return false;
        }

        //Take player gold for the item
        player.setCharacterGP(player.getCharacterGP() - price);

        //Adding the item to player inventory
        for(int i = 0; i < player.getCharacterInventoryLength(); i++){
            Item slot = player.getCharacterInventoryItem(i);
            
            if(slot == null || slot.getItemName().equals("Empty")){

                player.setInventoryItem(i, item);

                System.out.println("You buy " + item.getItemName() + " for " + price + " GP.");
                return true;
            }
        }

        System.out.println("Your inventory is full. Purchase cancelled.");

        player.setCharacterGP(player.getCharacterGP() + price);//returning money to player
        return false;
    }



    
}//End of class
