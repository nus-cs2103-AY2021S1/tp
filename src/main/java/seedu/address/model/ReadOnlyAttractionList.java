package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.attraction.Attraction;

/**
 * Unmodifiable view of an attraction list
 */
public interface ReadOnlyAttractionList {

    /**
     * Returns an unmodifiable view of the attraction list.
     * This list will not contain any duplicate attractions.
     */
    ObservableList<Attraction> getAttractionList();

}
