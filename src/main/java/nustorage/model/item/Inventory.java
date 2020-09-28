package nustorage.model.item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nustorage.model.record.InventoryRecord;

/**
 * Class to store different InventoryRecords.
 */
public class Inventory {

    private final List<InventoryRecord> inventory;

    /**
     * Constructs inventory object to hold InventoryRecords.
     */
    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Adds InventoryRecord into inventory.
     * @param inventoryRecord to be added.
     */
    public void addInventoryRecord(InventoryRecord inventoryRecord) {
        inventory.add(inventoryRecord);
    }

    /**
     * Removes InventoryRecord from inventory.
     * @param inventoryRecord to be removed.
     */
    public void deleteInventoryRecord(InventoryRecord inventoryRecord) {
        inventory.remove(inventoryRecord);
    }

    @Override
    public String toString() {
        return inventory.stream()
                .map(InventoryRecord::toString)
                .collect(Collectors.joining("\n"));
    }
}

