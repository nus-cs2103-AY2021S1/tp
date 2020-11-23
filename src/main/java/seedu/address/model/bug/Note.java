package seedu.address.model.bug;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Bug's optional note in the bug tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidNote(String)}
 */
public class Note {

    public static final String MESSAGE_CONSTRAINTS = "Note can take any values, and it should not be blank";

    /**
     * The first character of the note must not be whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code Note}.
     *
     * @param note A string representation of a valid note.
     */
    public Note(String note) {
        requireNonNull(note);
        value = note;
    }

    /**
     * Returns true if a given string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && value.equals(((Note) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
