package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HospifyBook;
import seedu.address.model.ReadOnlyHospifyBook;
import seedu.address.model.patient.Patient;

/**
 * An Immutable HospifyBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableHospify {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPatient> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableHospify(@JsonProperty("persons") List<JsonAdaptedPatient> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyHospifyBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableHospify(ReadOnlyHospifyBook source) {
        persons.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this hospify into the model's {@code HospifyBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HospifyBook toModelType() throws IllegalValueException {
        HospifyBook addressBook = new HospifyBook();
        for (JsonAdaptedPatient jsonAdaptedPatient : persons) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (addressBook.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPatient(patient);
        }
        return addressBook;
    }

}
