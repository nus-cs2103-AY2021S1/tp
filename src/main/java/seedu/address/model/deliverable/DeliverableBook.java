package seedu.address.model.deliverable;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.model.deliverable.deliverable.UniqueDeliverableList;

/**
 * Wraps all data at the deliverable-book level
 * Duplicates are not allowed (by .isSameDeliverable comparison)
 */
public class DeliverableBook implements ReadOnlyDeliverableBook {
    private final UniqueDeliverableList deliverables;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        deliverables = new UniqueDeliverableList();
    }

    public DeliverableBook() {}

    /**
     * Creates an DeliverableBook using the Deliverables in the {@code toBeCopied}
     */
    public DeliverableBook(ReadOnlyDeliverableBook toBeCopied) {
        this();
        resetData(toBeCopied);
        sortDeliverables();
    }

    /**
     * Sorts the contents of the deliverable list by Deadline in chronological order.
     */
    public void sortDeliverables() {
        this.deliverables.sortList();
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the deliverable list with {@code deliverables}.
     * {@code deliverables} must not contain duplicate deliverables.
     */
    public void setDeliverables(List<Deliverable> deliverables) {
        this.deliverables.setDeliverables(deliverables);
    }

    /**
     * Resets the existing data of this {@code DeliverableBook} with {@code newData}.
     */
    public void resetData(ReadOnlyDeliverableBook newData) {
        requireNonNull(newData);
        setDeliverables(newData.getDeliverableList());
    }

    //// deliverable-level operations

    /**
     * Returns true if a deliverable with the same identity as {@code deliverable} exists in the deliverable book.
     */
    public boolean hasDeliverable(Deliverable deliverable) {
        requireNonNull(deliverable);
        return deliverables.contains(deliverable);
    }

    /**
     * Adds a deliverable to the deliverable book.
     * The deliverable must not already exist in the deliverable book.
     */
    public void addDeliverable(Deliverable p) {
        deliverables.add(p);
    }

    /**
     * Replaces the given deliverable {@code target} in the list with {@code editedDeliverable}.
     * {@code target} must exist in the deliverable book.
     * The deliverable identity of {@code editedDeliverable} must not be the same as
     * another existing deliverable in the deliverable book.
     */
    public void setDeliverable(Deliverable target, Deliverable editedDeliverable) {
        requireNonNull(editedDeliverable);
        deliverables.setDeliverable(target, editedDeliverable);
    }

    /**
     * Removes {@code key} from this {@code DeliverableBook}.
     * {@code key} must exist in the deliverable book.
     */
    public void removeDeliverable(Deliverable key) {
        deliverables.remove(key);
    }

    @Override
    public String toString() {
        return deliverables.asUnmodifiableObservableList().size() + " deliverables";
        // TODO: refine later
    }

    @Override
    public ObservableList<Deliverable> getDeliverableList() {
        return deliverables.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliverableBook // instanceof handles nulls
                && deliverables.equals(((DeliverableBook) other).deliverables));
    }

    @Override
    public int hashCode() {
        return deliverables.hashCode();
    }

    public ObservableList<Deliverable> getInternalDeliverableList() {
        return deliverables.getInternalDeliverableList();
    }
}

