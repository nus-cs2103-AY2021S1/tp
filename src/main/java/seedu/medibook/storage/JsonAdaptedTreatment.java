package seedu.medibook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.medicaldetail.Tag;
import seedu.medibook.model.medicaldetail.Treatment;

/**
 * Jackson-friendly version of {@link Treatment}.
 */
public class JsonAdaptedTreatment extends JsonAdaptedTag {

    /**
     * Constructs a {@code JsonAdaptedTreatment} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTreatment(String tagName) {
        super(tagName);
    }

    /**
     * Converts a given {@code Treatment} into this class for Jackson use.
     */
    public JsonAdaptedTreatment(Treatment source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted treatment object into the model's {@code Treatment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted treatment.
     */
    @Override
    public Treatment toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Treatment(tagName);
    }

}
