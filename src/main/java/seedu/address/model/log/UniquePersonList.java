package seedu.address.model.log;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.log.exceptions.DuplicateLogException;
import seedu.address.model.log.exceptions.LogNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A log is considered unique by comparing using {@code Log#isSamePerson(Log)}. As such, adding and updating of
 * persons uses Log#isSamePerson(Log) for equality so as to ensure that the log being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a log uses Log#equals(Object) so
 * as to ensure that the log with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Log#isSamePerson(Log)
 */
public class UniquePersonList implements Iterable<Log> {

    private final ObservableList<Log> internalList = FXCollections.observableArrayList();
    private final ObservableList<Log> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent log as the given argument.
     */
    public boolean contains(Log toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a log to the list.
     * The log must not already exist in the list.
     */
    public void add(Log toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLogException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the log {@code target} in the list with {@code editedLog}.
     * {@code target} must exist in the list.
     * The log identity of {@code editedLog} must not be the same as another existing log in the list.
     */
    public void setPerson(Log target, Log editedLog) {
        requireAllNonNull(target, editedLog);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LogNotFoundException();
        }

        if (!target.isSamePerson(editedLog) && contains(editedLog)) {
            throw new DuplicateLogException();
        }

        internalList.set(index, editedLog);
    }

    /**
     * Removes the equivalent log from the list.
     * The log must exist in the list.
     */
    public void remove(Log toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LogNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code logs}.
     * {@code logs} must not contain duplicate logs.
     */
    public void setPersons(List<Log> logs) {
        requireAllNonNull(logs);
        if (!personsAreUnique(logs)) {
            throw new DuplicateLogException();
        }

        internalList.setAll(logs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Log> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Log> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code logs} contains only unique logs.
     */
    private boolean personsAreUnique(List<Log> logs) {
        for (int i = 0; i < logs.size() - 1; i++) {
            for (int j = i + 1; j < logs.size(); j++) {
                if (logs.get(i).isSamePerson(logs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
