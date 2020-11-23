package seedu.address.model.appointment.exceptions;

import seedu.address.model.exception.DuplicateEntityException;

//@@author {nicktohzyu}-reused
// Solution below adapted from
// https://github.com/cs2103-ay1819s2-w13-1/main/tree/master/src/main/java/seedu/address/model/appointment

/**
 * Signals that the operation will result in duplicate Appointment
 * (Appointments are considered duplicates if they have the same
 * client, hairdresser, date, and time).
 */
public class DuplicateAppointmentException extends DuplicateEntityException {
    public DuplicateAppointmentException() {
        super("appointments");
    }
}
//@@author
