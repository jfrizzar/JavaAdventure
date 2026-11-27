package characters;
import java.util.Scanner;
import items.Item;
import location.Location;

//The Merchant class sells items to the player and interacts with the player
public class Merchant extends NPCharacter {

    private Item[] shopItems;
    private int[] shopItemsPrices;

    //Constructor
    public Merchant(String characterName, int characterCurrentHP, int characterMaxHP, 
                     int characterCurrentMP, int characterMaxMP, int characterGP, 
                     int characterCurrentXP, int characterMaxXP, int inventorySize,
                     Location characterLocation, String[] npcGreetings, String[] npcGoodbyes, 
                     String[] npcDialogueOptions, Item[] shopItems, int[] shopItemPrices){
        super(characterName, characterCurrentHP, characterMaxHP, 
              characterCurrentMP, characterMaxMP, characterGP, 
              characterCurrentXP, characterMaxXP, inventorySize,
              characterLocation, npcGreetings, npcGoodbyes,
              npcDialogueOptions);
    }

    //Get Methods
    public Item getShopItem(int index){
        return shopItems[index];
    }

    public int getItemPrice(int index){
        return shopItemsPrices[index];
    }

    public void setShopItem(int index, Item newItem){
        this.shopItems[index] = newItem;
    }

    public void setShopPrice(int index, int itemPrice){
        this.shopItemsPrices[index] = itemPrice;
    }


    @Override
    public boolean handleChoice(int choice, MainCharacter player, String optionSelected){
        switch(choice){
            case 1: //Response to "Placeholder?"
                System.out.println(talk("""
                    How are you? You seem a little groggy. Did you wake up on the wrong side of the bed? ...Not one
                    for talking, are you? Well, I'm Wizlo and this is my shop. It's not much of a looker, but if you 
                    have a look at my items, you might find one that suits your fancy.
                                        """));
                return true;

            case 2: //Shop
                openShop(player, optionSelected);
                return true;

            case 3: //Chooses exit interaction
                return false;

            default: //User types something other than the choices
                System.out.println(talk("\nI don't think I can do that, friend.")); 
                return true;
        }
    }

    //Show shop inventory
    public void openShop(MainCharacter player, String optionSelected){
        boolean shopping = true;

        System.out.println(talk("Feel free to point to anything you like."));

        while(shopping){
            showShop(player); //shows the shop
            System.out.println("Choose an item: ");
            String line = optionSelected.trim();

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
                buyItem(choice - 1, player);
            }
        }
    }

    //Helper method to print the shop interface
    public void showShop(MainCharacter player){
        System.out.println("\n~~~~~ Wizlo's Wonders ~~~~~");

        //Looping through
        for(int i = 0; i < shopItems.length; i++){
            System.out.println((i + 1) + ". " + shopItems[i].getItemName() + " - " + shopItemsPrices[i] + " GP");
        
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
        int price = shopItemsPrices[index];

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
}