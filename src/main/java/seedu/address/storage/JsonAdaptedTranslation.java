package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.entry.Translation;


public class JsonAdaptedTranslation {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Translation's %s field is missing!";
    private final String translation;

    /**
     * Constructs a {@code JsonAdaptedTranslation} with the given {@code Translation}.
     */
    @JsonCreator
    public JsonAdaptedTranslation(String translation) {
        this.translation = translation;
    }

    /**
     * Converts a given {@code Translation} into this class for Jackson use.
     */
    public JsonAdaptedTranslation(Translation source) {
        translation = source.getTranslation();
    }

    //Dummy Constructor
    public JsonAdaptedTranslation() {
        translation = "";
    }

    @JsonValue
    public String getTranslation() {
        return translation;
    }

    /**
     * Converts this Jackson-friendly adapted Translation object into the model's {@code
     * Translation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *                               Translation.
     */
    public Translation toModelType() throws IllegalValueException {
        if (translation == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Translation.class.getSimpleName()));
        }
        if (!Translation.isValidTranslation(translation)) {
            throw new IllegalValueException(Translation.MESSAGE_CONSTRAINTS);
        }
        return new Translation(translation);
    }

}
