package seedu.stock.model.stock;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.stock.model.stock.exceptions.DuplicateSerialNumberSetException;
import seedu.stock.model.stock.exceptions.SerialNumberSetNotFoundException;

/**
 * A list of serialNumberSets that enforces uniqueness between its elements and does not allow nulls.
 * Supports a minimal set of list operations.
 *
 * @see SerialNumberSet#isSameSerialNumberSet(SerialNumberSet)
 */
public class UniqueSerialNumberSetList implements Iterable<SerialNumberSet> {

    private final ObservableList<SerialNumberSet> internalList = FXCollections.observableArrayList();
    private final ObservableList<SerialNumberSet> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent serial number set's source as the given argument.
     */
    public boolean contains(SerialNumberSet toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(serialNumberSet ->
                serialNumberSet.getSource().isSameSource(toCheck.getSource()));
    }

    /**
     * Adds a serial number set to the list.
     * The serial number set's source must not already exist in the list.
     *
     * @param toAdd The serialNumberSet to add.
     */
    public void add(SerialNumberSet toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateSerialNumberSetException();
        }
        internalList.add(toAdd);
    }

    public Optional<SerialNumberSet> getSerialNumberSet(Source source) {
        requireNonNull(source);
        for (int i = 0; i < internalList.size(); i++) {
            SerialNumberSet curr = internalList.get(i);
            if (curr.getSource().isSameSource(source)) {
                return Optional.of(curr);
            }
        }
        return Optional.empty();
    }
    /**
     * Replaces the serial number set {@code target} in the list with {@code editedSerialNumberSet}.
     * {@code target} must exist in the list.
     * The source identity of {@code editedSerialNumberSet} must not be the same as
     * another existing serial number set in the list.
     */
    public void setSerialNumberSet(SerialNumberSet target, SerialNumberSet editedSerialNumberSet) {
        requireAllNonNull(target, editedSerialNumberSet);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new SerialNumberSetNotFoundException();
        }

        if (!target.isSameSerialNumberSetSource(editedSerialNumberSet) && contains(editedSerialNumberSet)) {
            throw new DuplicateSerialNumberSetException();
        }

        internalList.set(index, editedSerialNumberSet);
    }

    /**
     * Removes the equivalent SerialNumberSet from the list.
     * The SerialNumberSet must exist in the list.
     */
    public void remove(SerialNumberSet toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new SerialNumberSetNotFoundException();
        }
    }

    public void setSerialNumberSets(UniqueSerialNumberSetList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code serialNumberSets}.
     * {@code serialNumberSets} must not contain duplicate serial number sets.
     */
    public void setSerialNumberSets(List<SerialNumberSet> serialNumberSets) {
        requireAllNonNull();
        if (!serialNumberSetsAreUnique(serialNumberSets)) {
            throw new DuplicateSerialNumberSetException();
        }

        internalList.setAll(serialNumberSets);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<SerialNumberSet> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<SerialNumberSet> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueSerialNumberSetList // instanceof handles nulls
                && internalList.equals(((UniqueSerialNumberSetList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code serial number sets} contains only unique serial number sets.
     */
    private boolean serialNumberSetsAreUnique(List<SerialNumberSet> serialNumberSets) {
        for (int i = 0; i < serialNumberSets.size() - 1; i++) {
            for (int j = i + 1; j < serialNumberSets.size(); j++) {
                if (serialNumberSets.get(i).isSameSerialNumberSetSource(serialNumberSets.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
