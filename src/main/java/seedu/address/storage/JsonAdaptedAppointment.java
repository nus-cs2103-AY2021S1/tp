package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Appointment;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    private final String appointmentName;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given {@code appointmentName}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(String appointmentName) {
        this.appointmentName = appointmentName;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        appointmentName = source.toString();
    }

    @JsonValue
    public String getAppointmentName() {
        return appointmentName;
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (!Appointment.isValidAppointment(appointmentName)) {
            throw new IllegalValueException(Appointment.MESSAGE_CONSTRAINTS);
        }
        return new Appointment().setTime(appointmentName);
    }

}
