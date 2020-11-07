package seedu.address.model.room.exceptions;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_ROOM_NUMBER;

/**
 * Signals that the operation will result in duplicate Rooms (Rooms are considered duplicates if they
 * have the same identity).
 */
public class DuplicateRoomException extends RuntimeException {
    public DuplicateRoomException() {
        super(MESSAGE_DUPLICATE_ROOM_NUMBER);
    }
}
