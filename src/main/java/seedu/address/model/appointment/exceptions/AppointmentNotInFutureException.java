package seedu.address.model.appointment.exceptions;
//@@author {nicktohzyu}-reused
//Solution below adapted from https://github.com/cs2103-ay1819s2-w13-1/main/tree/master/src/main/java/seedu/address/model/appointment

/**
 * Signals that the Appointment is not set in the future compared to system time.
 */
public class AppointmentNotInFutureException extends RuntimeException {
    public AppointmentNotInFutureException() {
        super("Operation would result in creating an appointment in the past");
    }
}
//@@author
