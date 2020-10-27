package nustorage.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import nustorage.model.record.InventoryRecord;
import nustorage.model.record.InventoryRecordList;

/**
 * Class to store different InventoryRecords.
 */
public class Inventory implements ReadOnlyInventory {

    private final InventoryRecordList inventoryRecords;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        inventoryRecords = new InventoryRecordList();
    }

    public Inventory() {

    }

    /**
     * Creates an Inventory using the InventoryRecord in the {@code toBeCopied}
     */
    public Inventory(ReadOnlyInventory toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the InventoryRecordList with {@code inventoryRecords}.
     * {@code inventoryRecords} must not contain duplicate records.
     */
    public void setInventoryRecords(List<InventoryRecord> inventoryRecords) {
        this.inventoryRecords.setInventoryRecords(inventoryRecords);
    }

    /**
     * Resets the existing data of this {@code Inventory} with {@code newData}.
     */
    public void resetData(ReadOnlyInventory newData) {
        requireNonNull(newData);
        setInventoryRecords(newData.getInventoryRecordList());
    }

    //// InventoryRecord-level operations

    /**
     * Returns true if an inventory record with the same identity as {@code inventoryRecord} exists in the inventory.
     */
    public boolean hasInventoryRecord(InventoryRecord inventoryRecord) {
        requireNonNull(inventoryRecord);
        return inventoryRecords.contains(inventoryRecord);
    }

    /**
     * Adds a inventory record to the inventory.
     * The inventory record must not already exist in the inventory.
     */
    public void addInventoryRecord(InventoryRecord inventoryRecord) {
        inventoryRecords.add(inventoryRecord);
    }

    /**
     * Replaces the given inventory record {@code target} in the list with {@code editedInventoryRecord}.
     * {@code target} must exist in the inventory.
     * The inventory record identity of {@code editedInventoryRecord}
     * must not be the same as another existing record in the inventory.
     */
    public void setInventoryRecord(InventoryRecord target, InventoryRecord editedInventoryRecord) {
        requireNonNull(editedInventoryRecord);

        inventoryRecords.setInventoryRecord(target, editedInventoryRecord);
    }

    /**
     * Removes {@code key} from this {@code Inventory}.
     * {@code key} must exist in the inventory.
     */
    public void removeInventoryRecord(InventoryRecord key) {
        inventoryRecords.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return inventoryRecords.asUnmodifiableObservableList().size() + " inventory records";
    }

    @Override
    public ObservableList<InventoryRecord> getInventoryRecordList() {
        return inventoryRecords.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Inventory // instanceof handles nulls
                && inventoryRecords.equals(((Inventory) other).inventoryRecords));
    }

    @Override
    public int hashCode() {
        return inventoryRecords.hashCode();
    }
}


