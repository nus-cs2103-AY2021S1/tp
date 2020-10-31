package seedu.cc.model.account.entry;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cc.model.account.entry.exceptions.EntryNotFoundException;

/**
 * A list that stores the revenue entries.
 * Supports a minimal set of list operations.
 */
public class RevenueList implements Iterable<Revenue> {
    private final ObservableList<Revenue> internalList = FXCollections.observableArrayList();
    private final ObservableList<Revenue> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent revenue as the given argument.
     */
    public boolean contains(Entry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a revenue to the list.
     */
    public void add(Revenue toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the revenue {@code target} in the list with {@code editedRevenue}.
     * {@code target} must exist in the list.
     */
    public void setRevenue(Revenue target, Revenue editedRevenue) {
        requireAllNonNull(target, editedRevenue);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }

        internalList.set(index, editedRevenue);
    }

    /**
     * Removes the equivalent revenue from the list.
     * The revenue entry must exist in the list.
     */
    public void remove(Revenue toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    public void setRevenues(RevenueList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code revenues}.
     */
    public void setRevenues(List<Revenue> revenues) {
        requireAllNonNull(revenues);
        internalList.setAll(revenues);
    }

    /**
     * Clears all contents of this list.
     */
    public void clearRevenues() {
        internalList.clear();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Revenue> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Revenue> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RevenueList // instanceof handles nulls
                && internalList.equals(((RevenueList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
