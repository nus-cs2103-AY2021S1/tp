package seedu.address.model.deliverable.deliverable;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.deliverable.deliverable.exceptions.DeliverableNotFoundException;
import seedu.address.model.deliverable.deliverable.exceptions.DuplicateDeliverableException;
import seedu.address.model.util.TimeEventComparator;

public class UniqueDeliverableList implements Iterable<Deliverable> {

    private final ObservableList<Deliverable> internalList = FXCollections.observableArrayList();
    private final ObservableList<Deliverable> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private final TimeEventComparator timeEventComparator = new TimeEventComparator();

    /**
     * Returns true if the list contains an equivalent deliverable as the given argument.
     */
    public boolean contains(Deliverable toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDeliverable);
    }

    /**
     * Adds a deliverable to the list.
     * The deliverable must not already exist in the list.
     */
    public void add(Deliverable toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDeliverableException();
        }
        internalList.add(toAdd);
        sortList();
    }

    /**
     * Replaces the deliverable {@code target} in the list with {@code editedDeliverable}.
     * {@code target} must exist in the list.
     * The deliverable identity of {@code editedDeliverable} must not be the same as
     * another existing deliverable in the list.
     */
    public void setDeliverable(Deliverable target, Deliverable editedDeliverable) {
        requireAllNonNull(target, editedDeliverable);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DeliverableNotFoundException();
        }

        if (!target.isSameDeliverable(editedDeliverable) && contains(editedDeliverable)) {
            throw new DuplicateDeliverableException();
        }

        internalList.set(index, editedDeliverable);
        sortList();
    }

    /**
     * Removes the equivalent deliverable from the list.
     * The deliverable must exist in the list.
     */
    public void remove(Deliverable toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DeliverableNotFoundException();
        }
    }

    public void setDeliverables(UniqueDeliverableList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code deliverables}.
     * {@code deliverables} must not contain duplicate deliverables.
     */
    public void setDeliverables(List<Deliverable> deliverables) {
        requireAllNonNull(deliverables);
        if (!deliverablesAreUnique(deliverables)) {
            throw new DuplicateDeliverableException();
        }

        internalList.setAll(deliverables);
        sortList();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Deliverable> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Sort the list chronologically according to Deadline.
     */
    public void sortList() {
        Collections.sort(internalList, timeEventComparator);
    }

    @Override
    public Iterator<Deliverable> iterator() {
        sortList();
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDeliverableList // instanceof handles nulls
                && internalList.equals(((UniqueDeliverableList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code deliverables} contains only unique deliverables.
     */
    private boolean deliverablesAreUnique(List<Deliverable> deliverables) {
        for (int i = 0; i < deliverables.size() - 1; i++) {
            for (int j = i + 1; j < deliverables.size(); j++) {
                if (deliverables.get(i).isSameDeliverable(deliverables.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the internal list of deliverables */
    public ObservableList<Deliverable> getInternalDeliverableList() {
        return internalList;
    }
}
