package seedu.address.model.deliverable;

import javafx.collections.ObservableList;
import seedu.address.model.deliverable.deliverable.Deliverable;

/**
 * Unmodifiable view of an deliverable book
 */
public interface ReadOnlyDeliverableBook {
    /**
     * Returns an unmodifiable view of the deliverables list.
     * This list will not contain any duplicate deliverables.
     */
    ObservableList<Deliverable> getDeliverableList();
}
