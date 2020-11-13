package seedu.address.model.meeting.meeting.exceptions;

/**
 * Signals that the operation will result in duplicate Meetings (Meetings are considered duplicates if
 * they have the same information).
 */
public class DuplicateMeetingException extends RuntimeException {
    public DuplicateMeetingException() {
        super("Operation would result in duplicate meetings.");
    }
}


