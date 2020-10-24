package nustorage.model.record;

import static java.util.Objects.requireNonNull;
import static nustorage.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nustorage.model.record.exceptions.DuplicateInventoryRecordException;
import nustorage.model.record.exceptions.InventoryRecordNotFoundException;

public class InventoryRecordList implements Iterable<InventoryRecord> {

    private final ObservableList<InventoryRecord> internalList = FXCollections.observableArrayList();
    private final ObservableList<InventoryRecord> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent inventory record as the given argument.
     */
    public boolean contains(InventoryRecord toCheck) {
        requireAllNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds InventoryRecord into the list.
     * InventoryRecord must not already be in the list.
     * @param inventoryRecord to be added.
     */
    public void add(InventoryRecord inventoryRecord) {
        requireNonNull(inventoryRecord);
        internalList.add(inventoryRecord);
    }

    /**
     * Edit target with edited Inventory record
     */
    public void setInventoryRecord(InventoryRecord target, InventoryRecord editedInventoryRecord) {
        requireAllNonNull(target, editedInventoryRecord);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new InventoryRecordNotFoundException();
        }

        if (!target.equals(editedInventoryRecord) && contains(editedInventoryRecord)) {
            throw new DuplicateInventoryRecordException();
        }

        internalList.set(index, editedInventoryRecord);
    }

    /**
     * Replaces the contents of this list with {@code inventoryRecords}.
     * {@code inventoryRecords} must not contain duplicate inventory records.
     */
    public void setInventoryRecords(List<InventoryRecord> inventoryRecords) {
        requireNonNull(inventoryRecords);
        if (!inventoryRecordsAreUnique(inventoryRecords)) {
            throw new DuplicateInventoryRecordException();
        }
        internalList.setAll(inventoryRecords);
    }

    /**
     * Removes the equivalent Inventory record from the list.
     * The inventory record must exist in the list.
     */
    public void remove(InventoryRecord toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new InventoryRecordNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<InventoryRecord> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<InventoryRecord> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryRecordList // instanceof handles nulls
                && internalList.equals(((InventoryRecordList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code inventoryRecords} contains only unique records.
     */
    private boolean inventoryRecordsAreUnique(List<InventoryRecord> inventoryRecords) {
        for (int i = 0; i < inventoryRecords.size() - 1; i++) {
            for (int j = i + 1; j < inventoryRecords.size(); j++) {
                if (inventoryRecords.get(i).equals(inventoryRecords.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
