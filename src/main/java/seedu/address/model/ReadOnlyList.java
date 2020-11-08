package seedu.address.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a list.
 */
public interface ReadOnlyList<T> {

    /**
     * Returns an unmodifiable view of a list.
     */
    ObservableList<T> getReadOnlyList();

}
