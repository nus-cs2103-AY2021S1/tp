package com.eva.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an Eva database
 */
public interface ReadOnlyEvaDatabase<P> {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<P> getPersonList();
}
