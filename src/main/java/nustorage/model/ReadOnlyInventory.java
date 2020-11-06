package nustorage.model;

import javafx.collections.ObservableList;
import nustorage.model.record.InventoryRecord;

/**
 * Unmodifiable view of the inventory
 */
public interface ReadOnlyInventory {

    /**
     * Returns an unmodifiable view of the inventory record list.
     * This list will not contain any duplicate inventory records.
     */
    ObservableList<InventoryRecord> getInventoryRecordList();
}
