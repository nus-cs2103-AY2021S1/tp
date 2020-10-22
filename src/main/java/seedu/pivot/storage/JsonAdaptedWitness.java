package seedu.pivot.storage;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.exceptions.IllegalValueException;
import seedu.pivot.model.investigationcase.Name;
import seedu.pivot.model.investigationcase.Witness;



/**
 * Jackson-friendly version of {@link Witness}.
 */
public class JsonAdaptedWitness {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Witness' %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedWitness.class);

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
        this.name = source.getName().getAlphaNum();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Witness} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted witness.
     */
    public Witness toModelType() throws IllegalValueException {
        logger.info("Converting JSON to Witness");
        if (name == null) {
            logger.warning("Witness name is null. Check data");
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            logger.warning("Witness name is invalid. Check data");
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        return new Witness(modelName);
    }

}
