package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.ConflictingAppointmentException;

/**
 * A list of appointments that prevents time conflicts between its elements and does not allow nulls.
 * A time conflict between two appointments is checked using {@code Appointment#hasTimeConflict(Appointment)}.
 * As such, adding and updating of appointments uses Appointment#hasTimeConflict(Appointment) so as to ensure
 * that the appointment being added or updated is unique in terms of appointment time in the UniqueAppointmentList.
 * However, the removal of an appointment uses Appointment#equals(Object) so as to ensure
 * that the appointment with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Appointment#hasTimeConflict(Appointment)
 */
public class UniqueAppointmentList implements Iterable<Appointment> {

    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an appointment that clashes with the given appointment.
     */
    public boolean clashes(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::hasTimeConflict);
    }

    /**
     * Adds an appointment to the list.
     * The appointment must not clash with other appointments in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (clashes(toAdd)) {
            throw new ConflictingAppointmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The appointment time of {@code editedAppointment} must not clash with another existing appointment in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        internalList.set(index, editedAppointment);
    }

    /**
     * Removes the equivalent appointment from the list.
     * The appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    public void setAppointments(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Appointments}.
     * {@code Appointments} must not contain duplicate Appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new ConflictingAppointmentException();
        }

        internalList.setAll(appointments);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return new SortedList<>(internalUnmodifiableList, Appointment::compare);
    }

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAppointmentList // instanceof handles nulls
                && internalList.equals(((UniqueAppointmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Appointments} contains only unique Appointments.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).hasTimeConflict(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
