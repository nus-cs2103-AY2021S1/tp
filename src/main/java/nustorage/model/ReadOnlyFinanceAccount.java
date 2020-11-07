package nustorage.model;


import javafx.collections.ObservableList;
import nustorage.model.record.FinanceRecord;


/**
 * Unmodifiable view of a finance account
 */
public interface ReadOnlyFinanceAccount {

    /**
     * Returns an unmodifiable view of the finance record list.
     * This list will not contain any duplicate finance records.
     */
    ObservableList<FinanceRecord> getFinanceList();

}
