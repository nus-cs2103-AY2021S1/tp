package nustorage.model.item;

import java.util.Iterator;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nustorage.model.record.InventoryRecord;

/**
 * Class to store different InventoryRecords.
 */
public class Inventory implements Iterable<InventoryRecord>, ReadOnlyInventory {

    private final ObservableList<InventoryRecord> internalList = FXCollections.observableArrayList();
    private final ObservableList<InventoryRecord> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Constructs inventory object to hold InventoryRecords.
     */
    public Inventory() {

    }

    /**
     * Adds InventoryRecord into inventory.
     * @param inventoryRecord to be added.
     */
    public void addInventoryRecord(InventoryRecord inventoryRecord) {
        internalList.add(inventoryRecord);
    }

    /**
     * Removes InventoryRecord from inventory.
     * @param inventoryRecord to be removed.
     */
    public void deleteInventoryRecord(InventoryRecord inventoryRecord) {
        internalList.remove(inventoryRecord);
    }

    /**
     * Checks if Inventory contains given InventoryRecord
     */
    public boolean hasInventoryRecord(InventoryRecord inventoryRecord) {
        return internalList.contains(inventoryRecord);
    }

    /**
     * Edit target with edited Inventory record
     */
    public void setInventoryRecord(InventoryRecord target, InventoryRecord editedInventoryRecord) {
        int index = internalList.indexOf(target);
        internalList.remove(index);
        internalList.add(index, editedInventoryRecord);
    }

    public ObservableList<InventoryRecord> getInventoryList() {
        return internalList;
    }

    public ObservableList<InventoryRecord> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<InventoryRecord> iterator() {
        return internalList.iterator();
    }

    @Override
    public String toString() {
        return internalList.stream()
                .map(InventoryRecord::toString)
                .collect(Collectors.joining("\n"));
    }
}

