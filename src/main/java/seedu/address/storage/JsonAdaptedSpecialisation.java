package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.specialisation.Specialisation;

/**
 * Jackson-friendly version of {@link Specialisation}.
 */
public class JsonAdaptedSpecialisation {

    private final String specialisation;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code specialisation}.
     */
    @JsonCreator
    public JsonAdaptedSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    /**
     * Converts a given {@code Specialisation} into this class for Jackson use.
     */
    public JsonAdaptedSpecialisation(Specialisation source) {
        specialisation = source.getSpecInString();
    }

    @JsonValue
    public String getSpecialisationName() {
        return specialisation;
    }

    /**
     * Converts this Jackson-friendly adapted spec object into the model's {@code Specialisation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted spec.
     */
    public Specialisation toModelType() throws IllegalValueException {
        if (!Specialisation.isValidSpecialisation(specialisation)) {
            throw new IllegalValueException(Specialisation.MESSAGE_CONSTRAINTS);
        }
        return new Specialisation(specialisation);
    }

}
