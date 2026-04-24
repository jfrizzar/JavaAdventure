package Items;

public class Item {
    private String itemName;

    public Item() {
    }

    public Item(String itemName) {
        setItemName(itemName);
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        if (itemName == null || itemName.isBlank() || itemName.isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be an empty string.");
        }
        this.itemName = itemName;
    }

    public String toString() {
        return getItemName();
    }
}