package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PersonAddressBook;
import seedu.address.model.ReadOnlyPersonAddressBook;
import seedu.address.model.information.Person;

/**
 * An Immutable PersonAddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializablePersonAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePersonAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePersonAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyPersonAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePersonAddressBook}.
     */
    public JsonSerializablePersonAddressBook(ReadOnlyPersonAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PersonAddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PersonAddressBook toModelType() throws IllegalValueException {
        PersonAddressBook addressBook = new PersonAddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

}
