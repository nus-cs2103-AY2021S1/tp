package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.room.Room;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyRoomBook {

    /**
     * Returns an unmodifiable view of the Room list.
     * This list will not contain any duplicate rooms.
     */
    ObservableList<Room> getRoomList();

}
