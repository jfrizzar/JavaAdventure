package Items;

public class Item {
    private String itemName;

    //Constructor
    public Item(String itemName) {
        setItemName(itemName);
    }

    //Get methods
    public String getItemName() {
        return this.itemName;
    }

    //Set methods
    public void setItemName(String itemName) {
        if (itemName == null || itemName.isBlank() || itemName.isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be an empty string.");
        }
        this.itemName = itemName;
    }

    //Special purpose methods
    public String toString() {
        return getItemName();
    }
}