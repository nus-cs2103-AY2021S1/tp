package seedu.simplykitchen.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the Food inventory.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS =
            "Tags should only contain alphanumeric, spaces or these special characters: #$%&-()";
    public static final String MESSAGE_EXCEED_LIMIT = "Tags should not exceed 72 characters.";
    public static final String VALIDATION_REGEX = "[#$%&()\\-\\s\\p{Alnum}]+";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), generateErrorMessage(tagName));
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        if (test.length() > 72) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Generates an error message based on why the Tag is invalid.
     *
     * @param invalidTag An invalid Tag.
     * @return A string describing the error message.
     */
    public static String generateErrorMessage(String invalidTag) {
        if (invalidTag.length() > 72) {
            return MESSAGE_EXCEED_LIMIT;
        }
        return MESSAGE_CONSTRAINTS;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.toLowerCase().equals(((Tag) other).tagName.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return tagName.toLowerCase().hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
