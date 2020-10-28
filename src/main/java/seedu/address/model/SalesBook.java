package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;

/**
 * Wraps all data related to drink sales at the sales-book level
 * Fixed Drink types.
 */
public class SalesBook implements ReadOnlySalesBook {

    private UniqueSalesRecordList record;

    private final Logger logger = LogsCenter.getLogger(SalesBook.class);

    public SalesBook() {
        record = new UniqueSalesRecordList();
    }

    /**
     * Creates a SalesBook using the record in {@code toBeCopied}.
     *
     * @param toBeCopied the SalesBook to be copied from
     */
    public SalesBook(ReadOnlySalesBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code SalesBook} with {@code newData}.
     */
    public void resetData(ReadOnlySalesBook newData) {
        requireNonNull(newData);
        setRecord(newData.getSalesRecord());
    }

    /**
     * Replaces the contents of the sales record list with {@code sales}.
     * {@code sales} must not contain duplicate sales record entries.
     */
    public void setRecord(List<SalesRecordEntry> sales) {
        requireNonNull(sales);
        record.setSalesRecord(sales);
    }

    /**
     * Adds a salesRecordEntry to the salesBook.
     */
    public void addSalesRecordEntry(SalesRecordEntry p) {
        record.add(p);
    }

    /**
     * Returns true if a salesRecordEntry with the same identity as {@code salesRecordEntry} exists in the record.
     */
    public boolean hasSalesRecordEntry(SalesRecordEntry salesRecordEntry) {
        requireNonNull(salesRecordEntry);
        return record.contains(salesRecordEntry);
    }

    public UniqueSalesRecordList getRecord() {
        return record;
    }

    /**
     * Overwrites existing sales record based on the sales information which is provided as a Map.
     *
     * If there was no existing sales record, then it will initialise the number sold of the
     * drink items, that the user did not provide, to 0.
     *
     * @param sales sales information that has been parsed.
     */
    public void overwriteSales(Map<Drink, Integer> sales) {
        requireNonNull(sales);
        assert !sales.isEmpty();
        logger.fine("SalesBook is being overwritten with the new user input.");
        HashMap<Drink, Integer> newRecord = new HashMap<>();
        for (Drink key : Drink.values()) {
            Optional<Integer> userInput = Optional.ofNullable(sales.get(key));
            Integer toReplace = 0;
            if (!isEmptySalesRecord()) {
                toReplace = record.getSalesEntry(key).getNumberSold();
            }
            Integer changedValue = userInput.orElse(toReplace);
            newRecord.put(key, changedValue);
        }
        record.setSalesRecord(newRecord);
        assert !record.isEmpty();
    }

    /**
     * Checks whether the sales record is empty.
     *
     * @return true if the sales record is empty, false otherwise.
     */
    public boolean isEmptySalesRecord() {
        return record.isEmpty();
    }

    /**
     * Sorts the sales record in descending order.
     */
    public void sortRecord() {
        record.sort();
    }

    //// util methods

    /**
     * Returns an unmodifiable view of the list of sales records.
     * This map will not contain any duplicate drink items.
     */
    @Override
    public ObservableList<SalesRecordEntry> getSalesRecord() {
        return record.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();

        record.forEach(x -> display.append(x + "\n"));

        return display.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SalesBook // instanceof handles nulls
                && record.equals(((SalesBook) other).record));
    }

    @Override
    public int hashCode() {
        return record.hashCode();
    }

}
