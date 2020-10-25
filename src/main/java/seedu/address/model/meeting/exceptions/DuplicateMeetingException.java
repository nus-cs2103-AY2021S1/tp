package seedu.address.model.meeting.exceptions;

/**
 * Signals that the operation would result in duplicate Meetings (Meetings are the
 * same when they have the same venue, time, property id, bidder id, meeting type).
 */
public class DuplicateMeetingException extends RuntimeException {
    public DuplicateMeetingException() {
        super("Operations would result in duplciate meetings");
    }
}
