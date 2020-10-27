package nustorage.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.FinanceRecordList;

public class FinanceAccount implements ReadOnlyFinanceAccount {

    private final FinanceRecordList financeRecords;
    {
        financeRecords = new FinanceRecordList();
    }

    private HashMap<Integer, FinanceRecord> financeRecordHashMap;
    {
        financeRecordHashMap = new HashMap<>();
    }

    public FinanceAccount() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public FinanceAccount(ReadOnlyFinanceAccount toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setFinanceRecords(List<FinanceRecord> financeRecords) {
        this.financeRecords.setFinanceRecords(financeRecords);

        for (FinanceRecord record : financeRecords) {
            financeRecordHashMap.put(record.getID(), record);
        }
    }


    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFinanceAccount newData) {
        requireNonNull(newData);
        financeRecordHashMap = new HashMap<>();
        setFinanceRecords(newData.getFinanceList());
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasFinanceRecord(FinanceRecord financeRecord) {
        requireNonNull(financeRecord);
        return financeRecordHashMap.containsKey(financeRecord.getID());
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addFinanceRecord(FinanceRecord financeRecord) {
        financeRecords.add(financeRecord);
        financeRecordHashMap.put(financeRecord.getID(), financeRecord);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setFinanceRecord(FinanceRecord target, FinanceRecord editedRecord) {
        requireNonNull(editedRecord);

        financeRecords.setFinanceRecord(target, editedRecord);
        financeRecordHashMap.replace(target.getID(), editedRecord);
    }

    public FinanceRecord getFinanceRecord(Integer targetID) {
        return financeRecordHashMap.get(targetID);
    }

    /**
     * Removes the finance record with the corresponding index
     *
     * @param key Finance record to be removed
     */
    public void removeFinanceRecord(FinanceRecord key) {
        financeRecords.remove(key);
        financeRecordHashMap.remove(key.getID());
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

    @Override
    public int hashCode() {
        return financeRecords.hashCode();
    }
}
