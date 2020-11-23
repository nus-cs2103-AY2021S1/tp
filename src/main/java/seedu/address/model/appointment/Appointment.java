package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Entity;
import seedu.address.model.IdCounter;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;

/**
 * Represents an Appointment between a client and a hairdresser.
 */
public class Appointment implements Entity {
    //Solution below adapted from https://github.com/cs2103-ay1819s2-w13-1/main/tree/master/src/main/java/seedu/address/model/appointment
    private static final Logger logger = LogsCenter.getLogger(Appointment.class);
    private final AppointmentId id;
    private final ClientId clientId;
    private final HairdresserId hairdresserId;
    private final AppointmentDate date;
    private final AppointmentTime time;
    private final AppointmentStatus appointmentStatus;
    private final Client client;
    private final Hairdresser hairdresser;
    private final AppointmentDuration duration;

    /**
     * Constructs an {@code Appointment} with a stated status.
     *
     * @param client            A valid client.
     * @param hairdresser       A valid hairdresser.
     * @param date              A valid appointment date
     * @param time              A valid appointment time
     * @param appointmentStatus A valid appointmentStatus
     */
    public Appointment(Client client, Hairdresser hairdresser, AppointmentDate date,
                       AppointmentTime time, AppointmentStatus appointmentStatus) {
        requireAllNonNull(client, hairdresser, date, time, appointmentStatus);
        this.id = IdCounter.getInstance().generateNewAppointmentId();
        this.client = client;
        this.hairdresser = hairdresser;
        this.clientId = client.getId();
        this.hairdresserId = hairdresser.getId();
        this.date = date;
        this.time = time;
        this.appointmentStatus = appointmentStatus;
        this.duration = new AppointmentDuration();
        logger.finer("Appointment object constructed");
    }

    /**
     * For id counter.
     * This is an existing appointment and does not need to generate a new ID.
     */
    public Appointment(AppointmentId id, Client client, Hairdresser hairdresser, AppointmentDate date,
                       AppointmentTime time, AppointmentStatus appointmentStatus) {
        requireAllNonNull(client, hairdresser, date, time, appointmentStatus);
        this.id = id;
        this.client = client;
        this.hairdresser = hairdresser;
        this.clientId = client.getId();
        this.hairdresserId = hairdresser.getId();
        this.date = date;
        this.time = time;
        this.appointmentStatus = appointmentStatus;
        this.duration = new AppointmentDuration();
        logger.finer("Appointment object constructed");
    }

    @Override
    public String toString() {
        String str = "";
        str += "Appointment - ";
        str += "Client ID: " + clientId;
        str += " Hairdresser ID: " + hairdresserId;
        str += " Date: " + date;
        str += " Time: " + time;
        return str;
    }

    public AppointmentId getId() {
        return this.id;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public HairdresserId getHairdresserId() {
        return hairdresserId;
    }

    public Client getClient() {
        return client;
    }

    /**
     * Replaces the representation of the client in this appointment.
     *
     * @param newClient the client to replace the existing.
     * @return a new Appointment object with client replaced by the new client.
     */
    public Appointment replaceClient(Client newClient) {
        logger.info("Appointment " + id + " client replaced");
        return new Appointment(
            this.id,
            newClient,
            this.hairdresser,
            this.date,
            this.time,
            this.appointmentStatus
        );
    }

    /**
     * Deletes the representation of the client in this appointment.
     *
     * @return a new Appointment object with client replaced by null.
     */
    public Appointment deleteClient() {
        logger.info("Appointment " + id + " client deleted");
        return replaceClient(this.client.setTombstone());
    }

    public Hairdresser getHairdresser() {
        return hairdresser;
    }

    /**
     * Replaces the representation of the Hairdresser in this appointment.
     *
     * @param newHairdresser the Hairdresser to replace the existing.
     * @return a new Appointment object with Hairdresser replaced by the new Hairdresser.
     */
    public Appointment replaceHairdresser(Hairdresser newHairdresser) {

        logger.info("Appointment " + id + " hairdresser replaced");
        return new Appointment(
            this.id,
            this.client,
            newHairdresser,
            this.date,
            this.time,
            this.appointmentStatus
        );
    }

    /**
     * Deletes the representation of the hairdresser in this appointment.
     *
     * @return a new Appointment object with hairdresser replaced by null.
     */
    public Appointment deleteHairdresser() {
        logger.info("Appointment " + id + " hairdresser deleted");
        return replaceHairdresser(this.hairdresser.setTombstone());
    }

    public AppointmentDate getDate() {
        return date;
    }

    public AppointmentTime getTime() {
        return time;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public LocalDateTime startDateTime() {
        return LocalDateTime.of(date.date, time.time);
    }

    public LocalDateTime endDateTime() {
        return startDateTime().plusMinutes(duration.getNumMinutes());
    }

    public boolean isSameAppointment(Appointment that) {
        return isSame(that);
    }

    @Override
    public boolean isSame(Entity that) {
        return this.equals(that);
    }

    /**
     * Checks if this appointment is in the past compared to system time.
     *
     * @return true if the appointment is in the past.
     */
    public boolean isPast() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime appointmentDateTime = startDateTime();

        return appointmentDateTime.isBefore(currentDateTime);
    }

    /**
     * Checks if another appointment clashes with this appointment.
     * Two appointments are defined to clash if they contain either the same
     * hairdresser or client, and they overlap in time.
     *
     * @return true if the other appointment clashes with this.
     */
    public boolean isClash(Appointment that) {
        logger.fine("Checking clash between appointments " + id + " and " + that.id);
        // There is a clash IFF both are active, and the start time of either is between the start and end
        // time of the other, or their start times are the same
        if (!this.appointmentStatus.equals(AppointmentStatus.ACTIVE)
            || !that.appointmentStatus.equals(AppointmentStatus.ACTIVE)) {
            // At least one of the appointments are not active
            return false;
        }
        if (!that.getHairdresserId().equals(this.hairdresserId)
            && !that.getClientId().equals(this.clientId)) {
            // Neither the hairdresser nor the client are the same
            return false;
        }
        if (this.startDateTime().isAfter(that.startDateTime())
            && this.startDateTime().isBefore(that.endDateTime())) {
            return true;
        } else if (that.startDateTime().isAfter(this.startDateTime())
            && that.startDateTime().isBefore(this.endDateTime())) {
            return true;
        } else if (that.startDateTime().isEqual(this.startDateTime())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Defines equality between appointments based on hairdresserID,
     * clientID, date, and time. Does not consider status.
     *
     * @param o The object to compare to.
     * @return true if the other appointment has the same hairdresserID,
     * clientID, date, and time.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Appointment)) {
            return false;
        }

        Appointment that = (Appointment) o;

        if (!this.hairdresserId.equals(that.hairdresserId)) {
            return false;
        }

        if (!this.clientId.equals(that.clientId)) {
            return false;
        }

        if (!this.date.equals(that.date)) {
            return false;
        }

        if (!this.time.equals(that.time)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, hairdresserId, date, time);
    }
}
