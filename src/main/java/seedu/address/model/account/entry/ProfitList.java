package seedu.address.model.account.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.account.entry.exceptions.EntryNotFoundException;

/**
 * A list that stores the profit entries.
 * Supports a minimal set of list operations.
 */
public class ProfitList implements Iterable<Profit> {
    private final ObservableList<Profit> internalList = FXCollections.observableArrayList();
    private final ObservableList<Profit> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent profit as the given argument.
     */
    public boolean contains(Entry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a profit to the list.
     */
    public void add(Profit toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the profit {@code target} in the list with {@code editedProfit}.
     * {@code target} must exist in the list.
     */
    public void setProfit(Profit target, Profit editedProfit) {
        requireAllNonNull(target, editedProfit);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }

        internalList.set(index, editedProfit);
    }

    /**
     * Removes the equivalent profit from the list.
     * The profit entry must exist in the list.
     */
    public void remove(Profit toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    public void setProfits(ProfitList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code profits}.
     */
    public void setProfits(List<Profit> profits) {
        requireAllNonNull(profits);
        internalList.setAll(profits);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Profit> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Profit> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfitList // instanceof handles nulls
                && internalList.equals(((ProfitList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
