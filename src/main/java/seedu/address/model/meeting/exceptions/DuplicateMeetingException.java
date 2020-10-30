package seedu.address.model.meeting.exceptions;

import seedu.address.commons.core.Messages;

/**
 * Signals that the operation would result in duplicate Meetings (Meetings are the
 * same when they have the same venue, date, property id, bidder id, meeting type).
 */
public class DuplicateMeetingException extends RuntimeException {
    public DuplicateMeetingException() {
        super(Messages.MESSAGE_DUPLICATE_MEETING);
    }
}
