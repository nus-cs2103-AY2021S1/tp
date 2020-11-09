package seedu.medibook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.medicaldetail.Condition;
import seedu.medibook.model.medicaldetail.Tag;

/**
 * Jackson-friendly version of {@link Condition}.
 */
public class JsonAdaptedCondition extends JsonAdaptedTag {

    /**
     * Constructs a {@code JsonAdaptedCondition} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedCondition(String tagName) {
        super(tagName);
    }

    /**
     * Converts a given {@code Condition} into this class for Jackson use.
     */
    public JsonAdaptedCondition(Condition source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted condition object into the model's {@code Condition} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted condition.
     */
    @Override
    public Condition toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Condition(tagName);
    }
}
