package seedu.address.model.appointment;

import seedu.address.model.appointment.exceptions.AppointmentNotInFutureException;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * Represents an Appointment that must be created in the future compared to system time.
 */
public class FutureAppointment extends Appointment {
    // Solution below adapted from
    // https://github.com/cs2103-ay1819s2-w13-1/main/tree/master/src/main/java/seedu/address/model/appointment

    public static final String MESSAGE_CONSTRAINT_FUTURE = "Appointments can only be made in the future";

    /**
     * Constructs an {@code Appointment} with a stated status.
     *
     * @param client            A valid client.
     * @param hairdresser       A valid hairdresser.
     * @param date              A valid appointment date
     * @param time              A valid appointment time
     */
    public FutureAppointment(Client client, Hairdresser hairdresser, AppointmentDate date, AppointmentTime time) {
        super(client, hairdresser, date, time, AppointmentStatus.ACTIVE);
        //TODO: Should it be !isFuture() instead?
        if (isPast()) {
            throw new AppointmentNotInFutureException();
        }
    }
}
