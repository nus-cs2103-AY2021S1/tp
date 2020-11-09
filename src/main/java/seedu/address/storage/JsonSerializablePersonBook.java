package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PersonBook;
import seedu.address.model.ReadOnlyPersonBook;
import seedu.address.model.person.Person;

/**
 * An Immutable PersonBook that is serializable to JSON format.
 */
@JsonRootName(value = "personbook")
class JsonSerializablePersonBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePersonBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePersonBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyPersonBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePersonBook}.
     */
    public JsonSerializablePersonBook(ReadOnlyPersonBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this person book into the model's {@code PersonBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PersonBook toModelType() throws IllegalValueException {
        PersonBook personBook = new PersonBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (personBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            personBook.addPerson(person);
        }
        return personBook;
    }

}
