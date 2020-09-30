package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.McGymmy;
import seedu.address.model.ReadOnlyMcGymmy;
import seedu.address.model.person.Person;

/**
 * An Immutable McGymmy that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableMcGymmy {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMcGymmy} with the given persons.
     */
    @JsonCreator
    public JsonSerializableMcGymmy(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyMcGymmy} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMcGymmy}.
     */
    public JsonSerializableMcGymmy(ReadOnlyMcGymmy source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code McGymmy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public McGymmy toModelType() throws IllegalValueException {
        McGymmy mcGymmy = new McGymmy();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (mcGymmy.hasFood(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            mcGymmy.addFood(person);
        }
        return mcGymmy;
    }

}
