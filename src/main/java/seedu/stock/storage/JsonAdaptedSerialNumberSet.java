package seedu.stock.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.model.stock.AccQuantity;
import seedu.stock.model.stock.SerialNumberSet;
import seedu.stock.model.stock.Source;

/**
 * Jackson-friendly version of {@link SerialNumberSet}.
 */
class JsonAdaptedSerialNumberSet {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "SerialNumberSet's %s field is missing!";

    private final String source;
    private final String accQuantity;

    /**
     * Constructs a {@code JsonAdaptedSerialNumberSet} with the given SerialNumberSet details.
     */
    @JsonCreator
    public JsonAdaptedSerialNumberSet(@JsonProperty("source") String source,
                                      @JsonProperty("accQuantity") String accQuantity) {
        this.source = source;
        this.accQuantity = accQuantity;
    }

    /**
     * Converts a given {@code SerialNumberSet} into this class for Jackson use.
     */
    public JsonAdaptedSerialNumberSet(SerialNumberSet serialNumberSet) {
        this.source = serialNumberSet.getSource().value;
        this.accQuantity = serialNumberSet.getAccQuantity().getAccQuantity();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code SerialNumberSet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted SerialNumberSet.
     */
    public SerialNumberSet toModelType() throws IllegalValueException {
        if (source == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Source.class.getSimpleName()));
        }
        if (!Source.isValidSource(source)) {
            throw new IllegalValueException(Source.MESSAGE_CONSTRAINTS);
        }
        final Source modelSource = new Source(source);

        if (accQuantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AccQuantity.class.getSimpleName()));
        }
        if (!AccQuantity.isValidAccQuantity(accQuantity)) {
            throw new IllegalValueException(AccQuantity.MESSAGE_CONSTRAINTS);
        }
        final AccQuantity modelAccQuantity = new AccQuantity(accQuantity);

        return new SerialNumberSet(modelSource, modelAccQuantity);
    }

}
