package seedu.stock.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.stock.model.stock.*;

/**
 * Wraps all data at the stock-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class SerialNumberSetsBook implements ReadOnlySerialNumberSetsBook {

    private final UniqueSerialNumberSetList serialNumberSets;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        serialNumberSets = new UniqueSerialNumberSetList();
    }

    public SerialNumberSetsBook() {}

    /**
     * Creates an StockBook using the Persons in the {@code toBeCopied}
     */
    public SerialNumberSetsBook(ReadOnlySerialNumberSetsBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setSerialNumberSets(List<SerialNumberSet> serialNumberSetList) {
        this.serialNumberSets.setSerialNumberSets(serialNumberSetList);
    }

    /**
     * Resets the existing data of this {@code StockBook} with {@code newData}.
     */
    public void resetData(ReadOnlySerialNumberSetsBook newData) {
        requireNonNull(newData);

        setSerialNumberSets(newData.getSerialNumberSetsList());
    }

    //// person-level operations

    /**
     * Returns true if a stock with the same identity as {@code stock} exists in the stock book.
     */
    public boolean hasSerialNumberSet(SerialNumberSet serialNumberSet) {
        requireNonNull(serialNumberSet);
        return serialNumberSets.contains(serialNumberSet);
    }

    /**
     * Adds a stock to the stock book.
     * The stock must not already exist in the stock book.
     */
    public void addSerialNumberSet(SerialNumberSet p) {
        serialNumberSets.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedStock}.
     * {@code target} must exist in the stock book.
     * The stock identity of {@code editedStock} must not be the same as another existing stock in the stock book.
     */
    public void setSerialNumberSet(SerialNumberSet target, SerialNumberSet editedSerialNumberSet) {
        requireNonNull(editedSerialNumberSet);

        serialNumberSets.setSerialNumberSet(target, editedSerialNumberSet);
    }

    /**
     * Removes {@code key} from this {@code StockBook}.
     * {@code key} must exist in the stock book.
     */
    public void removeSerialNumberSet(SerialNumberSet key) {
        serialNumberSets.remove(key);
    }

    public void incrementSerialNumberSet(Source source) {
        Optional<SerialNumberSet> serialNumberSetOptional = serialNumberSets.getSerialNumberSet(source);
        if (serialNumberSetOptional.isPresent()) {
            SerialNumberSet current = serialNumberSetOptional.get();
            SerialNumberSet updated = current.getNewIncrementedSerialNumberSet();
            setSerialNumberSet(current, updated);
        } else {
            SerialNumberSet toAdd = new SerialNumberSet(source, new AccQuantity("1"));
            addSerialNumberSet(toAdd);
        }
    }

    public String generateNextSerialNumber(Source source) {
        Optional<SerialNumberSet> serialNumberSetOptional = serialNumberSets.getSerialNumberSet(source);
        if (serialNumberSetOptional.isPresent()) {
            SerialNumberSet current = serialNumberSetOptional.get();
            String numberSection = current.getAccQuantity().getIncrementedAccQuantity().accQuantity;
            return source.value + numberSection;
        } else {
            return source.value + "1";
        }
    }
    //// util methods

    @Override
    public String toString() {
        return serialNumberSets.asUnmodifiableObservableList().size() + " serial number sets";
        // TODO: refine later
    }

    @Override
    public ObservableList<SerialNumberSet> getSerialNumberSetsList() {
        return serialNumberSets.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SerialNumberSetsBook // instanceof handles nulls
                && serialNumberSets.equals(((SerialNumberSetsBook) other).serialNumberSets));
    }

    @Override
    public int hashCode() {
        return serialNumberSets.hashCode();
    }
}
