package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;

public class JsonAdaptedName {

    private final String personName;

    /**
     * Constructs a {@code JsonAdaptedName} with the given {@code personName}.
     */
    @JsonCreator
    public JsonAdaptedName(String personName) {
        this.personName = personName;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedName(Name source) {
        personName = source.fullName;
    }

    @JsonValue
    public String getPersonName() {
        return personName;
    }

    /**
     * Converts this Jackson-friendly adapted name object into the model's {@code Name} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted name.
     */
    public Name toModelType() throws IllegalValueException {
        if (!Name.isValidName(personName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(personName);
    }
}
