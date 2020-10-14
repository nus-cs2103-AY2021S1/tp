package nustorage.model;


import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import nustorage.commons.core.index.Index;
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


    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<FinanceRecord> asUnmodifiableObservableList();


    boolean hasRecord(FinanceRecord record);


    void addRecord(FinanceRecord record);


    void setFinanceRecord(FinanceRecord target, FinanceRecord newRecord);


    Optional<FinanceRecord> removeRecord(Index targetIndex);


    int count();


    double netProfit();


    List<FinanceRecord> filterRecords(Predicate<FinanceRecord> filter);


    boolean isEmpty();

}
