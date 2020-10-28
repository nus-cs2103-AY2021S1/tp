package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the PlaNus.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String SEARCH_CONSTRAINTS = "Search phrase for tag names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public static final String DEFAULT_TAG = "";

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
     * Constructs a default {@code Tag}.
     */
    private Tag() {
        tagName = DEFAULT_TAG;
    }

    /**
     * Create a placeholder {@code Tag} that has an empty tag value.
     * @return an empty tag.
     */
    public static Tag defaultTag() {
        return new Tag();
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
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
     * Format tag as text for viewing.
     */
    public String toString() {
        return tagName.equals("") ? "" : '[' + tagName + ']';
    }

}
