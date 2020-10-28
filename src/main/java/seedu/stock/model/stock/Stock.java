package seedu.stock.model.stock;

import static seedu.stock.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Represents a Stock in the stock book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Stock {

    // Identity fields
    private final Name name;
    private SerialNumber serialNumber;
    private final Source source;
    private final Quantity quantity;
    private final Location location;
    private final List<Note> notes;
    private boolean isBookmarked;

    /**
     * Every field must be present and not null.
     */
    public Stock(Name name, SerialNumber serialNumber, Source source, Quantity quantity, Location location) {
        requireAllNonNull(name, serialNumber, source, quantity, location);
        this.name = name;
        this.serialNumber = serialNumber;
        this.source = source;
        this.quantity = quantity;
        this.location = location;
        this.notes = new ArrayList<>();
        this.isBookmarked = false;
    }

    /**
     * Every field must be present and not null.
     */
    public Stock(Name name, SerialNumber serialNumber, Source source, Quantity quantity,
                 Location location, List<Note> notes) {
        requireAllNonNull(name, serialNumber, source, quantity, location);
        this.name = name;
        this.serialNumber = serialNumber;
        this.source = source;
        this.quantity = quantity;
        this.location = location;
        this.notes = notes;
        this.isBookmarked = false;
    }

    public Name getName() {
        return name;
    }

    public SerialNumber getSerialNumber() {
        return serialNumber;
    }

    public Source getSource() {
        return source;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Location getLocation() {
        return location;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public boolean getIsBookmarked() {
        return isBookmarked;
    }

    /**
     * Bookmarks the stock
     *
     */
    public void setBookmarked() {
        this.isBookmarked = true;
    }

    /**
     * Unbookmarks the stock
     *
     */
    public void setUnbookmarked() {
        this.isBookmarked = false;
    }

    /**
     * Returns the values of the notes of this stock in a list.
     * @return list of string of the values of notes of stock.
     */
    public List<String> getNotesValues() {
        List<String> notesList = new ArrayList<>();

        for (Note note : notes) {
            notesList.add(note.value);
        }

        return notesList;
    }

    /**
     * Returns string of all the notes of stock.
     * @param notes list of notes of stock
     * @return string with all the notes appended
     */
    public String notesToString(List<Note> notes) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < notes.size(); i++) {
            builder.append(i + 1).append(". ").append(notes.get(i)).append("\n");
        }

        return builder.toString();
    }

    /**
     * Generates a new same stock with the note added to stock.
     * @param noteToAdd note to add to stock
     * @return stock with added note
     */
    public Stock addNote(Note noteToAdd) {
        Name name = this.name;
        SerialNumber serialNumber = this.serialNumber;
        Source source = this.source;
        Quantity quantity = this.quantity;
        Location location = this.location;
        List<Note> notesToUpdate = this.notes;
        notesToUpdate.add(noteToAdd);

        return new Stock(name, serialNumber, source, quantity, location, notesToUpdate);
    }

    /**
     * Generates a new same stock with the note, specified by the note index, deleted.
     * @param indexOfNoteToDelete the index of the note to delete
     * @return stock with deleted note
     */
    public Stock deleteNote(int indexOfNoteToDelete) {
        Name name = this.name;
        SerialNumber serialNumber = this.serialNumber;
        Source source = this.source;
        Quantity quantity = this.quantity;
        Location location = this.location;
        List<Note> notesToUpdate = this.notes;

        Stock updatedStock;
        if (indexOfNoteToDelete == 0) {
            updatedStock = new Stock(name, serialNumber, source, quantity, location);
        } else {
            notesToUpdate.remove(indexOfNoteToDelete - 1);
            updatedStock = new Stock(name, serialNumber, source, quantity, location, notesToUpdate);
        }

        return updatedStock;

    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = new SerialNumber(serialNumber);
    }

    /**
     * Returns true if both stocks of the same name, serialnumber and source.
     * This defines a weaker notion of equality between two stocks.
     */
    public boolean isSameStock(Stock otherStock) {
        if (otherStock == this) {
            return true;
        }

        return otherStock != null
                && (otherStock.getName().equals(getName())
                && otherStock.getSource().equals(getSource()))
                && otherStock.getSerialNumber().equals(getSerialNumber());
    }

    /**
     * Returns true if both stocks have the same identity and data fields.
     * This defines a stronger notion of equality between two stocks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Stock)) {
            return false;
        }

        Stock otherStock = (Stock) other;
        return (otherStock.getName().equals(getName())
                && otherStock.getSource().equals(getSource()))
                || otherStock.getSerialNumber().equals(getSerialNumber());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, serialNumber, quantity, source, location, notes);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" SerialNumber: ")
                .append(getSerialNumber())
                .append(" Source: ")
                .append(getSource())
                .append(" Quantity: ")
                .append(getQuantity())
                .append(" Location: ")
                .append(getLocation())
                .append(" Note: ")
                .append(notesToString(getNotes()));
        return builder.toString();
    }

}
