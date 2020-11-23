package seedu.address.model.appointment.exceptions;

import seedu.address.model.exception.EntityNotFoundException;

//@@author {nicktohzyu}-reused
//Solution below adapted from https://github.com/cs2103-ay1819s2-w13-1/main/tree/master/src/main/java/seedu/address/model/appointment

/**
 * Signals that the operation is unable to find the specified appointment.
 */
public class AppointmentNotFoundException extends EntityNotFoundException {
    public AppointmentNotFoundException() {
        super("appointment");
    }
}
//@@author
