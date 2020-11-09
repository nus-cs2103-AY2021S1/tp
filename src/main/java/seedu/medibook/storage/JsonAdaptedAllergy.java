package seedu.medibook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.medicaldetail.Allergy;
import seedu.medibook.model.medicaldetail.Tag;

/**
 * Jackson-friendly version of {@link Allergy}.
 */
public class JsonAdaptedAllergy extends JsonAdaptedTag {

    /**
     * Constructs a {@code JsonAdaptedAllergy} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAllergy(String tagName) {
        super(tagName);
    }

    /**
     * Converts a given {@code Allergy} into this class for Jackson use.
     */
    public JsonAdaptedAllergy(Allergy source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted allergy object into the model's {@code Allergy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted allergy.
     */
    @Override
    public Allergy toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Allergy(tagName);
    }

}
