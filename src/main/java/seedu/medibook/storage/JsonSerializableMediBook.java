package seedu.medibook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.MediBook;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.patient.Patient;

/**
 * An Immutable MediBook that is serializable to JSON format.
 */
@JsonRootName(value = "medibook")
class JsonSerializableMediBook {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMediBook} with the given patients.
     */
    @JsonCreator
    public JsonSerializableMediBook(@JsonProperty("patients") List<JsonAdaptedPatient> patients) {
        this.patients.addAll(patients);
    }

    /**
     * Converts a given {@code ReadOnlyMediBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMediBook}.
     */
    public JsonSerializableMediBook(ReadOnlyMediBook source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this medi book into the model's {@code MediBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MediBook toModelType() throws IllegalValueException {
        MediBook mediBook = new MediBook();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (mediBook.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            mediBook.addPatient(patient);
        }
        return mediBook;
    }

}
