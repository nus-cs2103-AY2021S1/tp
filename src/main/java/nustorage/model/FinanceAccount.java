package nustorage.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.FinanceRecordList;

public class FinanceAccount implements ReadOnlyFinanceAccount {

    private final FinanceRecordList financeRecords;
    {
        financeRecords = new FinanceRecordList();
    }

    public FinanceAccount() {
    }

    /**
     * Creates an FinanceAccount using the Finance Records in the {@code toBeCopied}
     */
    public FinanceAccount(ReadOnlyFinanceAccount toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the finance record list with {@code financeRecords}.
     * {@code financeRecords} must not contain duplicate records.
     */
    public void setFinanceRecords(List<FinanceRecord> financeRecords) {
        this.financeRecords.setFinanceRecords(financeRecords);
    }


    /**
     * Resets the existing data with {@code newData}.
     */
    public void resetData(ReadOnlyFinanceAccount newData) {
        requireNonNull(newData);
        setFinanceRecords(newData.getFinanceList());
    }

    /**
     * Returns true if a finance record with the same ID as {@code financeRecord} exists.
     */
    public boolean hasFinanceRecord(FinanceRecord financeRecord) {
        requireNonNull(financeRecord);
        return financeRecords.contains(financeRecord);
    }

    /**
     * Adds a finance record to NUStorage.
     * The finance record must not already exist in NUStorage.
     */
    public void addFinanceRecord(FinanceRecord financeRecord) {
        financeRecords.add(financeRecord);
    }

    /**
     * Replaces the given finance record {@code target} in the list with {@code editedRecord}.
     * {@code target} must exist in NUStorage.
     * The unique ID of {@code editedRecord} must not match any existing record in NUStorage.
     */
    public void setFinanceRecord(FinanceRecord target, FinanceRecord editedRecord) {
        requireNonNull(editedRecord);
        financeRecords.setFinanceRecord(target, editedRecord);
    }

    public FinanceRecord getFinanceRecord(int recordId) {
        return financeRecords.getFinanceRecord(recordId);
    }

    /**
     * Removes the finance record with the corresponding index
     *
     * @param key FinanceWindow record to be removed
     */
    public void removeFinanceRecord(FinanceRecord key) {
        financeRecords.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return financeRecords.asUnmodifiableObservableList().size() + " finance records";
    }

    @Override
    public ObservableList<FinanceRecord> getFinanceList() {
        return financeRecords.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FinanceAccount // instanceof handles nulls
                && financeRecords.equals(((FinanceAccount) other).financeRecords));
    }
}
