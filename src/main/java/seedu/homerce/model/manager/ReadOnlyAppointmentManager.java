package seedu.homerce.model.manager;

import java.util.Calendar;

import javafx.collections.ObservableList;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.appointment.predicate.AppointmentWeekPredicate;

/**
 * Unmodifiable view of am appointment book
 */
public interface ReadOnlyAppointmentManager {
    /**
     * Returns a view of the appointment list.
     * This list will not contain any duplicate appointments.
     */
    ObservableList<Appointment> getAppointmentList();

    /**
     * Returns a view of the appointment list which is a deep copy of the original.
     */
    ObservableList<Appointment> getAppointmentListCopy();

    /**
     * Returns an appointment predicate. Used to determine the current week for use in the schedule view.
     */
    AppointmentWeekPredicate getCurrentWeek();

    /**
     * Returns calendar to determine the current week. Used for the schedule view.
     */
    Calendar getCalendar();
}
