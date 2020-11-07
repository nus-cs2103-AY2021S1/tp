package seedu.address.storage.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PatientRecords;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.patient.Patient;

//@@author
/**
 * An Immutable Patient Records that is serializable to JSON format.
 */
@JsonRootName(value = "covigentapp")
public class JsonSerializablePatientRecords {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePatientRecords} with the given patients.
     */
    @JsonCreator
    public JsonSerializablePatientRecords(@JsonProperty("patients") List<JsonAdaptedPatient> patients) {
        this.patients.addAll(patients);
    }

    /**
     * Converts a given {@code ReadOnlyPatientRecords} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePatientRecords}.
     */
    public JsonSerializablePatientRecords(ReadOnlyList<Patient> source) {
        patients.addAll(source.getReadOnlyList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PatientRecords} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PatientRecords toModelType() throws IllegalValueException {
        PatientRecords patientRecords = new PatientRecords();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (patientRecords.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            patientRecords.addPatient(patient);
        }
        return patientRecords;
    }

}
