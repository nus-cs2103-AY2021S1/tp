package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.stock.Location;
import seedu.address.model.stock.Quantity;
import seedu.address.model.stock.SerialNumber;
import seedu.address.model.stock.Source;
import seedu.address.model.stock.Name;
import seedu.address.model.stock.Stock;

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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        return new Stock(modelName, modelSerialNumber, modelSource, modelQuantity, modelLocation);
    }

}
