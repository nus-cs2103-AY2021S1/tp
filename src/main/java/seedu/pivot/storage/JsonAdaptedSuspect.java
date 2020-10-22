package seedu.pivot.storage;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.exceptions.IllegalValueException;
import seedu.pivot.model.investigationcase.Name;
import seedu.pivot.model.investigationcase.Suspect;


/**
 * Jackson-friendly version of {@link Suspect}.
 */
public class JsonAdaptedSuspect {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Suspect's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedSuspect.class);

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedSuspect} with the given suspect details.
     */
    @JsonCreator
    public JsonAdaptedSuspect(@JsonProperty("name") String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Suspect} into this class for Jackson use.
     */
    public JsonAdaptedSuspect(Suspect source) {
        this.name = source.getName().getAlphaNum();
    }

    /**
     * Converts this Jackson-friendly adapted suspect object into the model's {@code Suspect} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted suspect.
     */
    public Suspect toModelType() throws IllegalValueException {
        logger.info("Converting JSON to Suspect");
        if (name == null) {
            logger.warning("Suspect name is null. Check data");
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            logger.warning("Suspect name is invalid. Check data");
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        return new Suspect(modelName);
    }
}
