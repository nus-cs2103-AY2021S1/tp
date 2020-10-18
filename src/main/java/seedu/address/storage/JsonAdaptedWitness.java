package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.investigationcase.Name;
import seedu.address.model.investigationcase.Witness;


/**
 * Jackson-friendly version of {@link Witness}.
 */
public class JsonAdaptedWitness {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Witness' %s field is missing!";

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedWitness} with the given {@code witnessName}.
     */
    @JsonCreator
    public JsonAdaptedWitness(@JsonProperty("name") String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Witness} into this class for Jackson use.
     */
    public JsonAdaptedWitness(Witness source) {
        name = source.getName().alphaNum;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Witness} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted witness.
     */
    public Witness toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        return new Witness(modelName);
    }

}
