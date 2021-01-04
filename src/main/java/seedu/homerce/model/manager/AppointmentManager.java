package seedu.homerce.model.manager;

import static java.util.Objects.requireNonNull;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.appointment.predicate.AppointmentWeekPredicate;
import seedu.homerce.model.appointment.uniquelist.UniqueAppointmentList;
import seedu.homerce.model.client.Client;

/**
 * Wraps all data at the AppointmentManager level
 * Duplicates are not allowed (by .equals comparison)
 */
public class AppointmentManager implements ReadOnlyAppointmentManager {

    private final UniqueAppointmentList appointments;
    private final Logger logger;
    private Calendar calendar;

    /**
     * Constructor for Appointment Manager
     */
    public AppointmentManager() {
        this.appointments = new UniqueAppointmentList();
        this.logger = Logger.getLogger("Appointment Manager");
        this.calendar = Calendar.getInstance();
        logger.info("Initialized new Appointment Manager with system date.");
    }

    /**
     * Creates an AppointmentManager using the Appointments in the {@code toBeCopied}
     */
    public AppointmentManager(ReadOnlyAppointmentManager toBeCopied) {
        this.appointments = new UniqueAppointmentList();
        this.logger = Logger.getLogger("Appointment Manager");
        resetData(toBeCopied);
        logger.info("Transferred contents from old appointment manager into this new one.");
        this.calendar = Calendar.getInstance();
        logger.info("Initialized new Appointment Manager with system date.");
    }

    //// list overwrite operations

    /**
     * Resets the existing data of this {@code AppointmentManager} with {@code newData}.
     */
    public void resetData(ReadOnlyAppointmentManager newData) {
        requireNonNull(newData);
        setAppointments(newData.getAppointmentList());
    }

    //// appointment-level operations

    /**
     * Returns true if a appointment with the same identity as {@code appointment} exists in the Homerce.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds a appointment to the Homerce.
     * The appointment must not already exist in Homerce.
     */
    public void addAppointment(Appointment p) {
        appointments.add(p);
    }

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setItems(appointments);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the Homerce.
     * The appointment identity of {@code editedAppointment} must not be the same as another existing appointment
     * in the Homerce.
     */
    public void setAppointments(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);
        appointments.setItem(target, editedAppointment);
    }

    /**
     * Updates appointment information after a client's details in the appointment get edited.
     */
    public void replaceClientsInAppointments(Client target, Client editedClient) {
        List<Appointment> editedList = appointments.stream().map(curr -> curr.getClient().isSame(target)
                ? new Appointment(curr.getAppointmentDate(), curr.getAppointmentStartTime(), curr.getService(),
                    editedClient, curr.getStatus())
                : curr
        ).collect(Collectors.toList());
        setAppointments(editedList);
    }

    /**
     * Removes {@code appointmentToRemove} from this {@code AppointmentManager}.
     * {@code appointmentToRemove} must exist in Homerce.
     */
    public void removeAppointment(Appointment appointmentToRemove) {
        appointments.remove(appointmentToRemove);
    }

    //// util methods

    @Override
    public String toString() {
        return "Appointment Manager:\n"
                + appointments.stream().map(Appointment::toString).collect(Collectors.joining("\n"))
                + "\n Total number of appointments: " + appointments.size();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asModifiableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentListCopy() {
        UniqueAppointmentList appointmentListCopy = new UniqueAppointmentList();
        List<Appointment> appointmentsItemsCopy = appointments.deepCopy();
        appointmentListCopy.setItems(appointmentsItemsCopy);
        return appointmentListCopy.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentManager // instanceof handles nulls
                && appointments.equals(((AppointmentManager) other).appointments));
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointments);
    }

    /**
     * Creates a deep copy of all the appointments in the unique list of appointments.
     *
     * @return an appointment manager with a list of deep copied appointments.
     */
    public AppointmentManager deepCopy() {
        List<Appointment> internalListCopy = appointments.deepCopy();
        AppointmentManager appointmentManagerCopy = new AppointmentManager();
        appointmentManagerCopy.setAppointments(internalListCopy);
        appointmentManagerCopy.setCalendar(calendar);
        return appointmentManagerCopy;
    }

    //// Used for pagination
    public Predicate<Appointment> getCurrentWeekPredicate() {
        return new AppointmentWeekPredicate(calendar);
    }

    //// Used for schedule to have knowledge of the current week when showing the appointments
    @Override
    public AppointmentWeekPredicate getCurrentWeek() {
        return new AppointmentWeekPredicate(calendar);
    }

    @Override
    public Calendar getCalendar() {
        return this.calendar;
    }

    public void setCalendarNextWeek() {
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
    }

    public void setCalendarPreviousWeek() {
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
    }

    /**
     * Set calendar to custom week for custom schedule command.
     */
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
