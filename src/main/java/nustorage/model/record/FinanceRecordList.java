package nustorage.model.record;

import static java.util.Objects.requireNonNull;
import static nustorage.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nustorage.model.record.exceptions.DuplicateFinanceRecordException;
import nustorage.model.record.exceptions.FinanceRecordNotFoundException;

public class FinanceRecordList implements Iterable<FinanceRecord> {

    private final ObservableList<FinanceRecord> internalList = FXCollections.observableArrayList();
    private final ObservableList<FinanceRecord> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent finance record as the given argument.
     */
    public boolean contains(FinanceRecord toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRecord);
    }

    /**
     * Adds a finance record to the list.
     * The finance record must not already exist in the list.
     */
    public void add(FinanceRecord toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateFinanceRecordException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the finance record {@code target} in the list with {@code editedRecord}.
     * {@code target} must exist in the list.
     * The unique ID of {@code editedRecord} must not be the same as another existing finance record in the list.
     */
    public void setFinanceRecord(FinanceRecord target, FinanceRecord editedRecord) {
        requireAllNonNull(target, editedRecord);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FinanceRecordNotFoundException();
        }

        internalList.set(index, editedRecord);
    }

    public FinanceRecord getFinanceRecord(int recordId) {
        Optional<FinanceRecord> record = internalList.stream().filter(r -> r.getID() == recordId).findFirst();
        if (record.isEmpty()) {
            throw new FinanceRecordNotFoundException();
        }
        return record.get();
    }

    /**
     * Removes the equivalent finance record from the list.
     * The finance record must exist in the list.
     */
    public void remove(FinanceRecord toRemove) {
        requireNonNull(toRemove);

        if (!internalList.contains(toRemove)) {
            throw new FinanceRecordNotFoundException();
        }
        internalList.remove(toRemove);
    }

    public void setFinanceRecords(FinanceRecordList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code financeRecords}.
     * {@code financeRecords} must not contain duplicate finance records.
     */
    public void setFinanceRecords(List<FinanceRecord> financeRecords) {
        requireAllNonNull(financeRecords);
        if (!financeRecordsAreUnique(financeRecords)) {
            throw new DuplicateFinanceRecordException();
        }
        internalList.setAll(financeRecords);
    }

    private boolean financeRecordsAreUnique(List<FinanceRecord> financeRecords) {
        for (int i = 0; i < financeRecords.size() - 1; i++) {
            for (int j = i + 1; j < financeRecords.size(); j++) {
                if (financeRecords.get(i).isSameRecord(financeRecords.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<FinanceRecord> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<FinanceRecord> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FinanceRecordList // instanceof handles nulls
                && internalList.equals(((FinanceRecordList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
