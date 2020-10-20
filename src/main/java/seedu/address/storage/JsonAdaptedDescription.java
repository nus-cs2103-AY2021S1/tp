package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.description.Description;

public class JsonAdaptedDescription {
    private final String descName;

    /**
     * Constructs a {@code JsonAdaptedDescription} with the given {@code descName}.
     */
    @JsonCreator
    public JsonAdaptedDescription(String descName) {
        this.descName = descName;
    }

    /**
     * Converts a given {@code Description} into this class for Jackson use.
     */
    public JsonAdaptedDescription(Description source) {
        descName = source.getDescription();
    }

    @JsonValue
    public String getDescName() {
        return descName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Description} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted description.
     */
    public Description toModelType() throws IllegalValueException {
        if (!Description.isValidDescription(descName)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(descName);
    }
}
