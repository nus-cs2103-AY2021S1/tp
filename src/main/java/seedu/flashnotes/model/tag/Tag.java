package seedu.flashnotes.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.commons.util.AppUtil.checkArgument;

import seedu.flashnotes.model.deck.Deck;

/**
 * Represents a Tag in the flashnotes.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String RESERVED_TAG_NAME = Deck.getReservedDeckName();

    public static final String MESSAGE_CONSTRAINTS = "Tags names should not be blank. "
            + "Tag name should not use the reserved name: " + RESERVED_TAG_NAME;

    public final String tagName;


    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return !test.isBlank() && test.length() <= 140 && !test.equals(RESERVED_TAG_NAME);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return tagName;
    }

}
