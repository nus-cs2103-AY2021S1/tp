package seedu.address.model.room.exceptions;

public class DuplicateRoomException extends RuntimeException {
    public DuplicateRoomException() {
        super("Operation would result in duplicate rooms");
    }
}
