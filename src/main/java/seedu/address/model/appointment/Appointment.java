package seedu.address.model.appointment;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_START_END;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.patient.Name;


/**
 * Represents a patient's Appointment in the CliniCal application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    private Name patientName;
    private AppointmentDateTime startTime;
    private AppointmentDateTime endTime;

    /**
     * Patient name, appointment time must be present and not null.
     */
    public Appointment(Name patientName, AppointmentDateTime startTime, AppointmentDateTime end) {
        requireAllNonNull(patientName, startTime, end);
        checkArgument(isValidStartEnd(startTime, end), MESSAGE_INVALID_APPOINTMENT_START_END);
        // should also check whether a patient is inside the patient database
        this.patientName = patientName;
        this.startTime = startTime;
        this.endTime = end;
    }

    /**
     * Validates that starting time of appointment provided is earlier than ending time provided
     *
     * @param startTime starting time of the appointment
     * @param endTime   ending time of the appointment
     * @return true if starting time is earlier than ending time
     */
    public static boolean isValidStartEnd(AppointmentDateTime startTime, AppointmentDateTime endTime) {
        return startTime.compareTo(endTime) < 0;
    }

    public Name getPatientName() {
        return patientName;
    }

    public AppointmentDateTime getStartTime() {
        return startTime;
    }

    public AppointmentDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns true if both appointments have the same patient name and appointment time.
     * This defines a stronger notion of equality between two appointments.
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
        return otherAppointment.getPatientName().equals(getPatientName())
                && otherAppointment.getStartTime().equals(getStartTime())
                && otherAppointment.getEndTime().equals(getEndTime());
    }

    /**
     * Returns true if both appointments have the same name.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getPatientName().equals(getPatientName());
    }

    /**
     * Returns true if both appointments have overlapping appointment time that causes time conflict.
     */
    public boolean hasTimeConflict(Appointment other) {
        int startDiff = this.getStartTime().compareTo(other.getStartTime());
        int endDiff = this.getEndTime().compareTo(other.getEndTime());
        if (startDiff == 0 || endDiff == 0) {
            return true;
        }
        if (startDiff * endDiff < 0) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patientName, startTime, endTime);
    }

}
