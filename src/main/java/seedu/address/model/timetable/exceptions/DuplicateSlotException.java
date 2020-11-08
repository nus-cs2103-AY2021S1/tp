package seedu.address.model.timetable.exceptions;

public class DuplicateSlotException extends RuntimeException {

    private static final String MESSAGE = "Operation would result in duplicate slots in your timetable.";

    public DuplicateSlotException() {
        super(MESSAGE);
    }
}
