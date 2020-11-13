package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;


/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String patientName;
    private final String patientIcNumber;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("patientName") String patientName,
                                  @JsonProperty("patientIcNumber") String patientIcNumber,
                                  @JsonProperty("startTime") String startTime,
                                  @JsonProperty("endTime") String endTime) {
        this.patientName = patientName;
        this.patientIcNumber = patientIcNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        patientName = source.getPatientName().fullName;
        patientIcNumber = source.getPatientIc().value;
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {

        if (patientName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(patientName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(patientName);

        if (patientIcNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    IcNumber.class.getSimpleName()));
        }
        if (!IcNumber.isValidIcNumber(patientIcNumber)) {
            throw new IllegalValueException(IcNumber.MESSAGE_CONSTRAINTS);
        }
        final IcNumber modelIcNumber = new IcNumber(patientIcNumber);

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentDateTime.class.getSimpleName()));
        }
        if (!AppointmentDateTime.isValidDateTime(startTime)) {
            throw new IllegalValueException(AppointmentDateTime.MESSAGE_CONSTRAINTS);
        }
        final AppointmentDateTime modelStartTime = new AppointmentDateTime(startTime);

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentDateTime.class.getSimpleName()));
        }
        if (!AppointmentDateTime.isValidDateTime(endTime)) {
            throw new IllegalValueException(AppointmentDateTime.MESSAGE_CONSTRAINTS);
        }
        final AppointmentDateTime modelEndTime = new AppointmentDateTime(endTime);

        return new Appointment(modelName, modelIcNumber, modelStartTime, modelEndTime);
    }

}
