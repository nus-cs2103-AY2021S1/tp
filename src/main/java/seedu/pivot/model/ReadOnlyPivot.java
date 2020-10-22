package seedu.pivot.model;

import javafx.collections.ObservableList;
import seedu.pivot.model.investigationcase.Case;

/**
 * Unmodifiable view of a PIVOT
 */
public interface ReadOnlyPivot {

    /**
     * Returns an unmodifiable view of the cases list.
     * This list will not contain any duplicate cases.
     */
    ObservableList<Case> getCaseList();

}
