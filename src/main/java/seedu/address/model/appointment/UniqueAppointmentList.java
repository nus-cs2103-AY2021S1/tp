package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.UniqueEntityList;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;

/**
 * Supports a minimal set of list operations.
 *
 * @see Appointment#isSameAppointment(Appointment)
 */
public class UniqueAppointmentList extends UniqueEntityList<Appointment> {
    //Solution below adapted from https://github.com/cs2103-ay1819s2-w13-1/main/tree/master/src/main/java/seedu/address/model/appointment

    /**
     * Replaces the appointment {@code target} in the list with {@code changedAppointment}.
     * {@code target} must exist in the list.
     * The appointment details of {@code changedAppointment} must not be
     * the same as another existing appointment in the list.
     */
    public void setAppointment(Appointment target, Appointment changedAppointment) {
        setEntity(target, changedAppointment);
    }

    /**
     * Replace the content of the list with another list.
     *
     * @param replacement the new list to replace the current list
     */
    public void setAppointments(UniqueAppointmentList replacement) {
        setEntities(replacement);
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        setEntities(appointments);
    }

    /**
     * Update all appointments where a client is involved.
     *
     * @param toEdit       The Id of the client to be replaced.
     * @param editedClient The object representing the new client.
     */
    public void updateClient(ClientId toEdit, Client editedClient) {
        requireAllNonNull(toEdit, editedClient);
        for (int i = 0; i < internalList.size(); i++) {
            Appointment currentAppointment = internalList.get(i);
            if (currentAppointment.getClientId().equals(toEdit)) {
                Appointment newAppointment = currentAppointment.replaceClient(editedClient);
                internalList.set(i, newAppointment);
            }
        }
    }

    /**
     * Update all appointments where a hairdresser is involved.
     *
     * @param toEdit            The Id of the hairdresser to be replaced.
     * @param editedHairdresser The object representing the new hairdresser.
     */
    public void updateHairdresser(HairdresserId toEdit, Hairdresser editedHairdresser) {
        requireAllNonNull(toEdit, editedHairdresser);
        for (int i = 0; i < internalList.size(); i++) {
            Appointment currentAppointment = internalList.get(i);
            if (currentAppointment.getHairdresserId().equals(toEdit)) {
                Appointment newAppointment = currentAppointment.replaceHairdresser(editedHairdresser);
                internalList.set(i, newAppointment);
            }
        }
    }

    /**
     * Update all appointments to reflect a deleted client.
     *
     * @param deleted id of the deleted client
     */
    public void deleteClient(ClientId deleted) {
        requireAllNonNull(deleted);
        for (int i = 0; i < internalList.size(); i++) {
            Appointment currentAppointment = internalList.get(i);
            if (currentAppointment.getClientId().equals(deleted)) {
                Appointment newAppointment = currentAppointment.deleteClient();
                internalList.set(i, newAppointment);
            }
        }
        return;
    }

    /**
     * Update all appointments to reflect a deleted hairdresser.
     *
     * @param deleted id of the deleted client
     */
    public void deleteHairdresser(HairdresserId deleted) {
        requireAllNonNull(deleted);
        for (int i = 0; i < internalList.size(); i++) {
            Appointment currentAppointment = internalList.get(i);
            if (currentAppointment.getHairdresserId().equals(deleted)) {
                Appointment newAppointment = currentAppointment.deleteHairdresser();
                internalList.set(i, newAppointment);
            }
        }
    }

    /**
     * Returns Appointment with given AppointmentId.
     */
    public Appointment findAppointmentById(AppointmentId idToCheck) {
        requireNonNull(idToCheck);
        Predicate<Appointment> predicate = new RecordContainsAppointmentIdPredicate(idToCheck);
        FilteredList<Appointment> appointmentWithId = internalList.filtered(predicate);
        if (appointmentWithId.isEmpty()) {
            return null;
        }
        return appointmentWithId.get(0);
    }

    @Override
    public DuplicateAppointmentException duplicateException() {
        return new DuplicateAppointmentException();
    }

    @Override
    public AppointmentNotFoundException notFoundException() {
        return new AppointmentNotFoundException();
    }
}
