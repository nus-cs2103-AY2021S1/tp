package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.roomservice.RoomService;

/**
 * Unmodified view of room service book
 */
public interface ReadOnlyRoomServiceBook {

    /**
     * Returns an unmodifiable view of the room service list.
     */
    ObservableList<RoomService> getRoomServiceList();
}
