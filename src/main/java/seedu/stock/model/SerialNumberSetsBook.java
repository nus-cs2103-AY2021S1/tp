package seedu.stock.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.stock.model.stock.AccumulatedQuantity;
import seedu.stock.model.stock.SerialNumberSet;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.UniqueSerialNumberSetList;

/**
 * Wraps all data at the SerialNumberSetsBook level.
 * Duplicates are not allowed (by .isSameSerialNumberSet comparison).
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
     * Creates a SerialNumberSetsBook using the SerialNumberSets in the {@code toBeCopied}.
     */
    public SerialNumberSetsBook(ReadOnlySerialNumberSetsBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the SerialNumberSet list with {@code serialNumberSetList}.
     * {@code serialNumberSets} must not contain duplicate SerialNumberSet.
     */
    public void setSerialNumberSets(List<SerialNumberSet> serialNumberSetList) {
        this.serialNumberSets.setSerialNumberSets(serialNumberSetList);
    }

    /**
     * Resets the existing data of this {@code SerialNumberSetsBook} with {@code newData}.
     */
    public void resetData(ReadOnlySerialNumberSetsBook newData) {
        requireNonNull(newData);

        setSerialNumberSets(newData.getSerialNumberSetsList());
    }

    //// serial number set-level operations

    /**
     * Returns true if a SerialNumberSet with the same identity as {@code serialNumberSet} exists
     *     in the SerialNumberSetsBook.
     */
    public boolean hasSerialNumberSet(SerialNumberSet serialNumberSet) {
        requireNonNull(serialNumberSet);
        return serialNumberSets.contains(serialNumberSet);
    }

    /**
     * Adds a SerialNumberSet to the SerialNumberSetsBook.
     * The stock must not already exist in the stock book.
     */
    public void addSerialNumberSet(SerialNumberSet p) {
        serialNumberSets.add(p);
    }

    /**
     * Replaces the given SerialNumberSet {@code target} in the list with {@code editedSerialNumberSet}.
     * {@code target} must exist in the SerialNumberSetsBook.
     * The SerialNumberSet identity of {@code editedSerialNumberSet} must not be the same as
     * another existing SerialNumberSet in the SerialNumberSetsBook.
     */
    public void setSerialNumberSet(SerialNumberSet target, SerialNumberSet editedSerialNumberSet) {
        requireNonNull(editedSerialNumberSet);

        serialNumberSets.setSerialNumberSet(target, editedSerialNumberSet);
    }

    /**
     * Removes {@code key} from this {@code SerialNumberSetsBook}.
     * {@code key} must exist in the SerialNumberSetsBook
     */
    public void removeSerialNumberSet(SerialNumberSet key) {
        //removes the serial number set related to the given key.
        serialNumberSets.remove(key);
    }

    /**
     * Increases the quantity in the serial number set by 1 of the respective source company,
     *     used when new stock is added.
     *
     * @param source The source company name.
     */
    public void incrementSerialNumberSet(Source source) {
        Optional<SerialNumberSet> serialNumberSetOptional = serialNumberSets.getSerialNumberSet(source);
        if (serialNumberSetOptional.isPresent()) {
            SerialNumberSet current = serialNumberSetOptional.get();
            SerialNumberSet updated = current.getNewIncrementedSerialNumberSet();
            setSerialNumberSet(current, updated);
        } else {
            SerialNumberSet toAdd = new SerialNumberSet(source, new AccumulatedQuantity("1"));
            //adds the desired serial number set by calling the addSerialNumberSet method.
            addSerialNumberSet(toAdd);
        }
    }

    /**
     * Generates a serial number for the stock from a particular source company.
     *
     * @param source The source company name.
     */
    public String generateNextSerialNumber(Source source) {
        Optional<SerialNumberSet> serialNumberSetOptional = serialNumberSets.getSerialNumberSet(source);
        if (serialNumberSetOptional.isPresent()) {
            SerialNumberSet current = serialNumberSetOptional.get();
            String numberSection = current.getAccumulatedQuantity().getIncrementedAccumulatedQuantity()
                                        .getAccumulatedQuantity();
            return source.value + numberSection;
        } else {
            return source.value + "1";
        }
    }
    //// util methods

    @Override
    public String toString() {
        String toReturn = "Serial Number Sets are:";
        for (int i = 0; i < serialNumberSets.asUnmodifiableObservableList().size(); i++) {
            toReturn += "\n" + serialNumberSets.asUnmodifiableObservableList().get(i);
        }
        return toReturn.equals("Serial Number Sets are:") ? "No serial number sets in serialNumberSetsBook"
                                                          : toReturn;
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
