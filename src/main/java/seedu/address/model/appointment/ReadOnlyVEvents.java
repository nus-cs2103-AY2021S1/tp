package seedu.address.model.appointment;

import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyVEvents {

    /**
     * Returns an unmodifiable view of the VEvent list.
     */
    ObservableList<VEvent> getAllVEvents();

}
