package seedu.stock.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.Stock;

/**
 * Jackson-friendly version of {@link Stock}.
 */
class JsonAdaptedStock {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String serialNumber;
    private final String source;
    private final String quantity;
    private final String location;

    /**
     * Constructs a {@code JsonAdaptedStock} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStock(@JsonProperty("name") String name, @JsonProperty("serialNumber") String serialNumber,
                            @JsonProperty("source") String source, @JsonProperty("quantity") String quantity,
                            @JsonProperty("location") String location) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.source = source;
        this.quantity = quantity;
        this.location = location;
    }

    /**
     * Converts a given {@code Stock} into this class for Jackson use.
     */
    public JsonAdaptedStock(Stock source) {
        name = source.getName().fullName;
        serialNumber = source.getSerialNumber().serialNumber;
        this.source = source.getSource().value;
        quantity = source.getQuantity().quantity;
        location = source.getLocation().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Stock} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted stock.
     */
    public Stock toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            System.out.println("donkey");
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (serialNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Source.class.getSimpleName()));
        }
        if (!SerialNumber.isValidSerialNumber(serialNumber)) {
            throw new IllegalValueException(Source.MESSAGE_CONSTRAINTS);
        }
        final SerialNumber modelSerialNumber = new SerialNumber(serialNumber);

        if (source == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Source.class.getSimpleName()));
        }
        if (!Source.isValidSource(source)) {
            throw new IllegalValueException(Source.MESSAGE_CONSTRAINTS);
        }
        final Source modelSource = new Source(source);

        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        return new Stock(modelName, modelSerialNumber, modelSource, modelQuantity, modelLocation);
    }

}
