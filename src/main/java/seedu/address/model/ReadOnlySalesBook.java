package seedu.address.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a sales book
 */
public interface ReadOnlySalesBook {

    /**
     * Returns an unmodifiable view of the list of sales records.
     * This map will not contain any duplicate drink items.
     */
    ObservableList<SalesRecordEntry> getSalesRecord();

}
