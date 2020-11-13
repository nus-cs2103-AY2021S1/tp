package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.label.Label;

/**
 * Jackson-friendly version of {@link Label}.
 */
public class JsonAdaptedLabel {
    private final String label;

    /**
     * Constructs a {@code JsonAdaptedLabel} with the given {@code label}.
     */
    @JsonCreator
    public JsonAdaptedLabel(String label) {
        this.label = label;
    }

    /**
     * Converts a given {@code Label} into this class for Jackson use.
     */
    public JsonAdaptedLabel(Label source) {
        label = source.getLabel();
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    /**
     * Converts this Jackson-friendly adapted label object into the model's {@code Label} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted label.
     */
    public Label toModelType() throws IllegalValueException {
        if (!Label.isValidLabel(label)) {
            throw new IllegalValueException(Label.MESSAGE_CONSTRAINTS);
        }
        return new Label(label);
    }
}
