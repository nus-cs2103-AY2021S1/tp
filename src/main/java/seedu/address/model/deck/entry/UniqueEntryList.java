package seedu.address.model.deck.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.deck.exceptions.DuplicateEntryException;
import seedu.address.model.deck.exceptions.EntryNotFoundException;

/**
 * A list of entries that enforces uniqueness between its elements and does not allow nulls. An
 * entry is considered unique by comparing using {@code Entry#isSameEntry(Entry)}. As such, adding
 * and updating of entries uses Entry#isSameEntry(Entry) for equality so as to ensure that the entry
 * being added or updated is unique in terms of identity in the UniqueEntryList. However, the
 * removal of an entry uses Entry#equals(Object) so as to ensure that the entry with exactly the
 * same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Entry#isSameEntry(Entry)
 */
public class UniqueEntryList implements Iterable<Entry> {

    private final ObservableList<Entry> internalList = FXCollections.observableArrayList();
    private final ObservableList<Entry> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns the entry at the specified index in the list. Indexing starts from 0.
     *
     * @param index Index of specific entry.
     * @return Entry at the specified index.
     */
    public Entry get(int index) {
        assert (index >= 0);
        return internalList.get(index);
    }

    /**
     * Returns the length of the entry list.
     *
     * @return Length of entry list.
     */
    public int length() {
        return internalList.size();
    }

    /**
     * Return true if the list is empty.
     *
     * @return True if the list does not contain any entries.
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Returns true if the list contains an equivalent entry as the given argument.
     *
     * @param toCheck Entry to check whether or not it is in the list
     * @return True if the entry already exists in the list
     */
    public boolean contains(Entry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEntry);
    }

    /**
     * Adds an entry to the list. The entry cannot already exist in the list.
     *
     * @param toAdd Entry to be added to the list.
     */
    public void add(Entry toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEntryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Adds an entry to the list. The entry can already exist in the list. Only called when user is
     * playing a quiz.
     *
     * @param toAdd Entry to be added to the list.
     */
    public void addLeitner(Entry toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the entry {@code target} in the list with {@code editedEntry}. {@code target} must
     * exist in the list. The entry identity of {@code editedEntry} must not be the same as another
     * existing entry in the list.
     *
     * @param target      The entry to be replaced.
     * @param editedEntry The entry to replace the target with.
     */
    public void setEntry(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }

        if (!target.isSameEntry(editedEntry) && contains(editedEntry)) {
            throw new DuplicateEntryException();
        }

        internalList.set(index, editedEntry);
    }

    /**
     * Removes the specified entry from the list. The entry must already be in the list.
     *
     * @param toRemove Entry to be removed from the list.
     */
    public void remove(Entry toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    /**
     * Replaces the current entry list with another entry list.
     *
     * @param replacement Entry list to replace the current list.
     */
    public void setEntries(UniqueEntryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code entries}. {@code entries} must not contain
     * duplicate entries.
     *
     * @param entries Entries to replace entries in the current list.
     */
    public void setEntries(List<Entry> entries) {
        requireAllNonNull(entries);
        if (!entriesAreUnique(entries)) {
            throw new DuplicateEntryException();
        }

        internalList.setAll(entries);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return The current list of entries as an unmodifiable list
     */
    public ObservableList<Entry> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Entry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueEntryList // instanceof handles nulls
            && internalList.equals(((UniqueEntryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code entries} contains only unique entries.
     *
     * @param entries Entries in the list
     * @return True if the entries in the list are all different
     */
    public static boolean entriesAreUnique(List<Entry> entries) {
        for (int i = 0; i < entries.size() - 1; i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                if (entries.get(i).isSameEntry(entries.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
