package nustorage.model.item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    List<Item> inventory;

    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    public void addItem(Item item) {
        inventory.add(item);
    }
}
