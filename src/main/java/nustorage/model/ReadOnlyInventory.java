package nustorage.model;

import javafx.collections.ObservableList;
import nustorage.model.record.InventoryRecord;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyInventory {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<InventoryRecord> getInventoryRecordList();
}
