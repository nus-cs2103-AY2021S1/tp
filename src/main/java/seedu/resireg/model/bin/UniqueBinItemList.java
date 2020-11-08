package seedu.resireg.model.bin;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.resireg.model.bin.exceptions.BinItemNotFoundException;
import seedu.resireg.model.bin.exceptions.DuplicateBinItemException;

/**
 * A list of rooms that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code BinItem#equals(BinItem)}.
 * As such, adding and updating of rooms uses BinItem#equals(BinItem) for equality so as to ensure
 * that the BinItem being added or updated is unique in terms of identity in the UniqueBinItemList.
 * However, the removal of a BinItem uses BinItem#equals(Object) so as to ensure that the BinItem with
 * exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueBinItemList implements Iterable<BinItem> {

    private final ObservableList<BinItem> internalList = FXCollections.observableArrayList();
    private final ObservableList<BinItem> internalImmutableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent BinItem as the given argument.
     */
    public boolean contains(BinItem toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a BinItem to the list.
     * The BinItem must not already exist in the list.
     */
    public void add(BinItem toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBinItemException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the BinItem {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in the list.
     * The BinItem identity of {@code editedRoom} must not be the same as another existing BinItem in the list.
     */
    public void setBinItem(BinItem target, BinItem editedBinItem) {
        requireAllNonNull(target, editedBinItem);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BinItemNotFoundException();
        }

        if (!target.equals(editedBinItem) && contains(editedBinItem)) {
            throw new DuplicateBinItemException();
        }

        internalList.set(index, editedBinItem);
    }

    /**
     * Removes the equivalent BinItem from the list.
     * The BinItem must exist in the list.
     */
    public void remove(BinItem toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BinItemNotFoundException();
        }
    }

    public void setBinItems(UniqueBinItemList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms.
     */
    public void setBinItems(List<BinItem> rooms) {
        requireAllNonNull(rooms);
        if (!binItemsAreUnique(rooms)) {
            throw new DuplicateBinItemException();
        }

        internalList.setAll(rooms);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<BinItem> asUnmodifiableObservableList() {
        return internalImmutableList;
    }

    @Override
    public Iterator<BinItem> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueBinItemList // instanceof handles nulls
            && internalList.equals(((UniqueBinItemList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code rooms} contains only unique rooms.
     */
    private boolean binItemsAreUnique(List<BinItem> binItems) {
        for (int i = 0; i < binItems.size() - 1; i++) {
            for (int j = i + 1; j < binItems.size(); j++) {
                if (binItems.get(i).equals(binItems.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void removeAll(List<BinItem> toRemove) {
        internalList.removeAll(toRemove);
    }
}
