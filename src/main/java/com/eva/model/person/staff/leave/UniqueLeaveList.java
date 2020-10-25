package com.eva.model.person.staff.leave;

import static com.eva.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.eva.model.person.staff.leave.exceptions.DuplicateLeaveException;
import com.eva.model.person.staff.leave.exceptions.LeaveNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * A list of Leaves that enforces uniqueness between its elements and does not allow nulls.
 * A leave is considered unique by comparing using {@code Leave#equals(Object)}. As such, adding and updating of
 * Leaves uses Leave#equals(Object) for equality so as to ensure that the leave being added or updated is
 * unique in terms of identity in the UniqueLeaveList. However, the removal of a leave uses Leave#equals(Object) so
 * as to ensure that the leave with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Leave#equals(Object)
 */
public class UniqueLeaveList implements Iterable<Leave> {
    private final ObservableList<Leave> internalList = FXCollections.observableArrayList();
    private final ObservableList<Leave> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent leave as the given argument.
     */
    public boolean contains(Leave toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a leave to the list.
     * The leave must not already exist in the list.
     */
    public void add(Leave toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLeaveException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the leave {@code target} in the list with {@code editedLeave}.
     * {@code target} must exist in the list.
     * The leave identity of {@code editedLeave} must not be the same as another existing leave in the list.
     */
    public void setLeave(Leave target, Leave editedLeave) {
        requireAllNonNull(target, editedLeave);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LeaveNotFoundException();
        }

        if (!target.equals(editedLeave) && contains(editedLeave)) {
            throw new DuplicateLeaveException();
        }

        internalList.set(index, editedLeave);
    }

    /**
     * Removes the equivalent leave from the list.
     * The leave must exist in the list.
     */
    public void remove(Leave toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LeaveNotFoundException();
        }
    }

    public void setLeaves(UniqueLeaveList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code leaves}.
     * {@code leaves} must not contain duplicate leaves.
     */
    public void setLeaves(List<Leave> leaves) {
        requireAllNonNull(leaves);
        if (!leavesAreUnique(leaves)) {
            throw new DuplicateLeaveException();
        }
        internalList.setAll(leaves);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Leave> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Leave> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLeaveList // instanceof handles nulls
                && internalList.equals(((UniqueLeaveList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code leaves} contains only unique leaves.
     */
    private boolean leavesAreUnique(List<Leave> leaves) {
        for (int i = 0; i < leaves.size() - 1; i++) {
            for (int j = i + 1; j < leaves.size(); j++) {
                if (leaves.get(i).equals(leaves.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Fills the contents of this list with {@code leaveSet}.
     */
    public void fill(Set<Leave> leaveSet) {
        List<Leave> leaves = new ArrayList<>(leaveSet);
        for (Leave leave : leaves) {
            add(leave);
        }
    }
}
