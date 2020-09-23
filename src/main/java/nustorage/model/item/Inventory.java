package nustorage.model.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to store different Items.
 */
public class Inventory {

    private List<Item> inventory;

    /**
     * Constructs inventory object to hold Items.
     */
    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Adds item into inventory.
     * @param item to be added.
     */
    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Removes item from inventory.
     * @param item to be removed.
     */
    public void deleteItem(Item item) {
        inventory.remove(item);
    }
}
