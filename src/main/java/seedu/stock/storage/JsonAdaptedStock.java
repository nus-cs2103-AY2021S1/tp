package seedu.stock.storage;

//import java.nio.file.ClosedWatchServiceException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.Stock;

/**
 * Jackson-friendly version of {@link Stock}.
 */
class JsonAdaptedStock {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Stock's %s field is missing!";

    private final String name;
    private final String serialNumber;
    private final String source;
    private final String quantity;
    private final String lowQuantity;
    private final String location;
    private final List<String> notes;
    private final boolean isBookmarked;

    /**
     * Constructs a {@code JsonAdaptedStock} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStock(@JsonProperty("name") String name, @JsonProperty("serialNumber") String serialNumber,
                            @JsonProperty("source") String source, @JsonProperty("quantity") String quantity,
                            @JsonProperty("lowQuantity") String lowQuantity, @JsonProperty("location") String location,
                            @JsonProperty("notes") List<String> notes,
                            @JsonProperty("isBookmarked") boolean isBookmarked) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.source = source;
        this.quantity = quantity;
        this.lowQuantity = lowQuantity;
        this.location = location;
        this.notes = notes;
        this.isBookmarked = isBookmarked;
    }

    /**
     * Converts a given {@code Stock} into this class for Jackson use.
     */
    public JsonAdaptedStock(Stock source) {
        name = source.getName().fullName;
        serialNumber = source.getSerialNumber().getSerialNumberAsString();
        this.source = source.getSource().value;
        quantity = source.getQuantity().quantity;
        lowQuantity = source.getQuantity().lowQuantity;
        location = source.getLocation().value;
        notes = source.getNotesValues();
        isBookmarked = source.getIsBookmarked();
    }

    /**
     * Converts this Jackson-friendly adapted stock object into the model's {@code Stock} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted stock.
     */
    public Stock toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (serialNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SerialNumber.class.getSimpleName()));
        }
        if (!SerialNumber.isValidSerialNumber(serialNumber)) {
            throw new IllegalValueException(SerialNumber.MESSAGE_CONSTRAINTS);
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
        if (lowQuantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "lowQuantity"));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }

        if (!Quantity.isValidQuantity(lowQuantity)) {
            throw new IllegalValueException(Quantity.LOW_QUANTITY_MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity, lowQuantity);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (notes != null && notes.size() > 0) {
            List<Note> modelNotesList = new ArrayList<>();
            for (String note : notes) {
                if (note == null) {
                    throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            Note.class.getSimpleName()));
                }
                if (!Note.isValidNote(note)) {
                    throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
                }
                final Note modelNote = new Note(note);
                modelNotesList.add(modelNote);
            }

            Stock stockToAdd = new Stock(modelName, modelSerialNumber, modelSource, modelQuantity, modelLocation,
                    modelNotesList);

            if (isBookmarked) {
                stockToAdd.setBookmarked();
            }

            return stockToAdd;
        }

        Stock stockToAdd = new Stock(modelName, modelSerialNumber, modelSource, modelQuantity, modelLocation);

        if (isBookmarked) {
            stockToAdd.setBookmarked();
        }

        return stockToAdd;

    }

}
