package seedu.flashcard.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the flashcard deck.
 * Guarantees: immutable.
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tag names should be a single word, alphanumeric and "
                                                        + "not more than 50 characters";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

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
        return test.length() <= 50 && test.matches(VALIDATION_REGEX);
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
        return '[' + tagName + ']';
    }

}
