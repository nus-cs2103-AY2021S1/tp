package seedu.homerce.model.appointment;

import static seedu.homerce.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.homerce.model.client.Client;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.uniquelist.UniqueListItem;

/**
 * Represents an Appointment in the homerce.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment implements UniqueListItem {
    private static final DateTimeFormatter FORMAT_INPUT = DateTimeFormatter.ofPattern("HHmm");

    private final Date appointmentDate;
    private final TimeOfDay timeOfDay;
    private final Client client;
    private final Service service;
    private final Status status;

    /**
     * Constructor for an Appointment.
     */
    public Appointment(Date appointmentDate, TimeOfDay timeOfDay, Client client, Service service) {
        requireAllNonNull(client, service, appointmentDate, timeOfDay);
        this.appointmentDate = appointmentDate;
        this.timeOfDay = timeOfDay;
        this.client = client;
        this.service = service;
        this.status = new Status("n");
    }

    /**
     * Overloaded constructor for creating an appointment with edited client information.
     */
    public Appointment(Date appointmentDate, TimeOfDay timeOfDay, Service service, Client client, Status status) {
        requireAllNonNull(client, status);
        this.appointmentDate = appointmentDate;
        this.timeOfDay = timeOfDay;
        this.service = service;
        this.client = client;
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public Service getService() {
        return service;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public TimeOfDay getAppointmentStartTime() {
        return timeOfDay;
    }

    public Status getStatus() {
        return status;
    }

    public TimeOfDay getAppointmentEndTime() {
        // Duration time has intervals of 0.5h
        double durationMinutes = service.getDuration().value * 60;
        LocalTime endTime = timeOfDay.getLocalTime().plusMinutes((long) durationMinutes);
        String formattedEndTime = endTime.format(FORMAT_INPUT);

        return new TimeOfDay(formattedEndTime);
    }

    public void markDone() {
        status.markDone();
    }

    public void markUnDone() {
        status.markUnDone();
    }

    /**
     * Returns true if both appointments have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getClient().equals(getClient())
                && otherAppointment.getService().equals(getService())
                && otherAppointment.getAppointmentDate().equals(getAppointmentDate())
                && otherAppointment.getAppointmentStartTime().equals(getAppointmentStartTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(client, service, appointmentDate, timeOfDay, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getAppointmentDate())
                .append(" ")
                .append(getAppointmentStartTime())
                .append(" Client: ")
                .append(getClient())
                .append(" Service: ")
                .append(getService())
                .append(" Done? ")
                .append(getStatus());
        return builder.toString();
    }

    /**
     * Method to prevent duplicate appointments from being added into a UniqueAppointmentList.
     */
    @Override
    public boolean isSame(UniqueListItem other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }
        Appointment otherAppointment = (Appointment) other;
        if (!appointmentDate.equals(otherAppointment.appointmentDate)) {
            return false;
        } else {
            return isClashing(otherAppointment);
        }
    }

    private boolean isClashing(Appointment other) {
        LocalTime startTimeOfThis = getAppointmentStartTime().getLocalTime();
        LocalTime startTimeOfOther = other.getAppointmentStartTime().getLocalTime();
        if (startTimeOfThis.equals(startTimeOfOther)) {
            return true;
        }
        Duration durationThis = getService().getDuration();
        Duration durationOther = other.getService().getDuration();
        LocalTime endTimeOfThis = startTimeOfThis
            .plusHours((long) Math.floor(durationThis.value))
            .plusMinutes((long) ((durationThis.value % 1) * 60));
        LocalTime endTimeOfOther = startTimeOfOther
            .plusHours((long) Math.floor(durationOther.value))
            .plusMinutes((long) ((durationOther.value % 1) * 60));
        boolean result = (endTimeOfOther.isAfter(startTimeOfThis) && endTimeOfOther.isBefore(endTimeOfThis))
                || (endTimeOfOther.isAfter(endTimeOfThis) && startTimeOfOther.isBefore(startTimeOfThis))
                || (endTimeOfThis.isAfter(endTimeOfOther) && startTimeOfThis.isBefore(startTimeOfOther))
                || (endTimeOfThis.isAfter(startTimeOfOther) && endTimeOfThis.isBefore(endTimeOfOther));
        return result;
    }
}
