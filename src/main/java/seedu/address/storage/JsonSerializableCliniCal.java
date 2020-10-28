package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.CliniCal;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;

/**
 * An Immutable CliniCal that is serializable to JSON format.
 */
@JsonRootName(value = "clinical")
class JsonSerializableCliniCal {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointment list contains duplicate appointment(s).";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCliniCal} with the given patients and appointments.
     */
    @JsonCreator
    public JsonSerializableCliniCal(@JsonProperty("patients") List<JsonAdaptedPatient> patients,
                                    @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.patients.addAll(patients);
        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlyCliniCal} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCliniCal}.
     */
    public JsonSerializableCliniCal(ReadOnlyCliniCal source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
        appointments.addAll(source.getAppointmentList().stream().map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this CliniCal application into the model's {@code CliniCal} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CliniCal toModelType() throws IllegalValueException {
        CliniCal cliniCal = new CliniCal();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (cliniCal.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            cliniCal.addPatient(patient);
        }
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (cliniCal.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            cliniCal.addAppointment(appointment);
        }
        return cliniCal;
    }

}
