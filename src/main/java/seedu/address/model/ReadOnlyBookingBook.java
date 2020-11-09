package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.booking.Booking;

public interface ReadOnlyBookingBook {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Booking> getBookingList();
}
