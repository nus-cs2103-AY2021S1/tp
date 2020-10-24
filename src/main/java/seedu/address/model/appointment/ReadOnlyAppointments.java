package seedu.address.model.appointment;

import java.util.List;

/**
 * Unmodifiable view of appointments
 */
public interface ReadOnlyAppointments {
    List<Appointment> getAllAppointments();
}
