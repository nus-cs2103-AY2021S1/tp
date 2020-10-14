package nustorage.model;

import javafx.collections.ObservableList;
import nustorage.model.record.FinanceRecord;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyFinanceAccount {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<FinanceRecord> getFinanceList();
}
