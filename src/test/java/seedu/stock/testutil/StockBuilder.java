package seedu.stock.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.Stock;

/**
 * A utility class to help with building Stock objects.
 */
public class StockBuilder {

    public static final String DEFAULT_NAME = "banana bun";
    public static final String DEFAULT_SERIALNUMBER = "fairprice1";
    public static final String DEFAULT_SOURCE = "fairprice";
    public static final String DEFAULT_QUANTITY = "1000";
    public static final String DEFAULT_LOW_QUANTITY = "0";
    public static final String DEFAULT_LOCATION = "fruits section, subsection b";
    public static final String DEFAULT_NOTE = "this is the default note.";

    private Name name;
    private SerialNumber serialNumber;
    private Source source;
    private Quantity quantity;
    private Location location;
    private List<Note> notes;

    /**
     * Creates a {@code StockBuilder} with the default details.
     */
    public StockBuilder() {
        name = new Name(DEFAULT_NAME);
        serialNumber = new SerialNumber(DEFAULT_SERIALNUMBER);
        source = new Source(DEFAULT_SOURCE);
        quantity = new Quantity(DEFAULT_QUANTITY, DEFAULT_LOW_QUANTITY);
        location = new Location(DEFAULT_LOCATION);
        notes = new ArrayList<>();
    }

    /**
     * Initializes the StockBuilder with the data of {@code stockToCopy}.
     */
    public StockBuilder(Stock stockToCopy) {
        name = stockToCopy.getName();
        serialNumber = stockToCopy.getSerialNumber();
        source = stockToCopy.getSource();
        quantity = stockToCopy.getQuantity();
        location = stockToCopy.getLocation();
        notes = stockToCopy.getNotes();
    }

    /**
     * Sets the {@code Name} of the {@code Stock} that we are building.
     */
    public StockBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code SerialNumber} of the {@code Stock} that we are building.
     */
    public StockBuilder withSerialNumber(String serialNumber) {
        this.serialNumber = new SerialNumber(serialNumber);
        return this;
    }

    /**
     * Sets the {@code Source} of the {@code Stock} that we are building.
     */
    public StockBuilder withSource(String source) {
        this.source = new Source(source);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Stock} that we are building.
     */
    public StockBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Quantity} and lowQuantity of the {@code Stock} that we are building.
     */
    public StockBuilder withQuantity(String quantity, String lowQuantity) {
        this.quantity = new Quantity(quantity, lowQuantity);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Stock} that we are building.
     */
    public StockBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code List<Note>} of the {@code Stock} that we are building.
     */
    public StockBuilder withNotes(String... notesList) {
        this.notes = new ArrayList<>();
        for (String noteText : notesList) {
            Note note = new Note(noteText);
            this.notes.add(note);
        }
        return this;
    }

    /**
     * Adds the {@code Note} of the {@code Stock} that we are building.
     */
    public StockBuilder addNote(String noteText) {
        List<Note> updatedNotesList = new ArrayList<>();
        Note noteToAdd = new Note(noteText);
        for (Note note : this.notes) {
            updatedNotesList.add(note);
        }
        updatedNotesList.add(noteToAdd);
        this.notes = updatedNotesList;
        return this;
    }

    /**
     * Removes notes from stock builder.
     * @return StockBuilder without notes.
     */
    public StockBuilder withoutNotes() {
        List<Note> noNotes = new ArrayList<>();
        this.notes = noNotes;
        return this;
    }

    public Stock build() {
        return new Stock(name, serialNumber, source, quantity, location, notes);
    }

}
