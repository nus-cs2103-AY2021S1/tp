package nustorage.model;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nustorage.commons.core.index.Index;
import nustorage.model.record.FinanceRecord;

public class FinanceAccount implements Iterable<FinanceRecord> {

    private final ObservableList<FinanceRecord> internalList = FXCollections.observableArrayList();
    private final ObservableList<FinanceRecord> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public FinanceAccount() {

    }

    public void addRecord(FinanceRecord record) {
        internalList.add(record);
    }

    public void setFinanceRecord(FinanceRecord target, FinanceRecord newRecord) {
        int index = internalList.indexOf(target);
        internalList.remove(index);
        internalList.add(index, newRecord);
    }

    /**
     * Removes the finance record with the corresponding index
     *
     * @param targetIndex Index of finance record to be removed
     * @return Optional containing removed finance record if index is valid, else an empty optional
     */
    public Optional<FinanceRecord> removeRecord(Index targetIndex) {

        if (targetIndex.getZeroBased() >= internalList.size()) {
            return Optional.empty();
        }

        return Optional.of(internalList.remove(targetIndex.getZeroBased()));
    }

    public int count() {
        return internalList.size();
    }

    /**
     * Returns the net transaction amount of all finance records
     *
     * @return Net transaction amount of all finance records
     */
    public double netProfit() {
        return internalList.stream()
                .mapToDouble(FinanceRecord::getAmount)
                .sum();
    }

    public List<FinanceRecord> filterRecords (Predicate<FinanceRecord> filter) {
        return internalList.stream().filter(filter).collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return internalList.isEmpty();
    }


    public ObservableList<FinanceRecord> getFinanceList() {
        return internalList;
    }

    public ObservableList<FinanceRecord> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<FinanceRecord> iterator() {
        return internalList.iterator();
    }

    @Override
    public String toString() {
        return internalList.stream()
                .map(FinanceRecord::toString)
                .collect(Collectors.joining("\n"));
    }
}
