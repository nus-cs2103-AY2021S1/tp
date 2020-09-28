package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Reeve;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.student.Student;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableReeve {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedStudent> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableReeve(@JsonProperty("persons") List<JsonAdaptedStudent> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableReeve(ReadOnlyReeve source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Reeve toModelType() throws IllegalValueException {
        Reeve reeve = new Reeve();
        for (JsonAdaptedStudent jsonAdaptedStudent : persons) {
            Student student = jsonAdaptedStudent.toModelType();
            if (reeve.hasPerson(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            reeve.addPerson(student);
        }
        return reeve;
    }

}
