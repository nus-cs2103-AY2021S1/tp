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
import seedu.address.model.patient.Patient;

/**
 * An Immutable CliniCal that is serializable to JSON format.
 */
@JsonRootName(value = "clinical")
class JsonSerializableCliniCal {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCliniCal} with the given patients.
     */
    @JsonCreator
    public JsonSerializableCliniCal(@JsonProperty("patients") List<JsonAdaptedPatient> patients) {
        this.patients.addAll(patients);
    }

    /**
     * Converts a given {@code ReadOnlyCliniCal} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCliniCal}.
     */
    public JsonSerializableCliniCal(ReadOnlyCliniCal source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
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
        return cliniCal;
    }

}
