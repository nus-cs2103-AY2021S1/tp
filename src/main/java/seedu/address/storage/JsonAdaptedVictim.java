package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Victim;

/**
 * Jackson-friendly version of {@link Victim}.
 */
public class JsonAdaptedVictim {

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedVictim} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedVictim(String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Victim} into this class for Jackson use.
     */
    public JsonAdaptedVictim(Victim source) {
        name = source.name;
    }

    @JsonValue
    public String getVictimName() {
        return name;
    }

    /**
     * Converts this Jackson-friendly adapted victim object into the model's {@code Victim} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted victim.
     */
    public Victim toModelType() throws IllegalValueException {
        if (!Victim.isValidName(name)) {
            throw new IllegalValueException(Victim.MESSAGE_CONSTRAINTS);
        }
        return new Victim(name);
    }
}
