package chopchop.model.attributes;

import static java.util.Objects.requireNonNull;
import static chopchop.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag for both Recipe and Ingredient.
 * E.G. "Halal", "Vegetarian" for Recipe. "Carbs", "Vitamin E" for Ingredient.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}.
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tag name should be alphanumeric";
    public static final String VALIDATION_REGEX = ".+";

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
        return test.matches(VALIDATION_REGEX);
    }

    public String getTagName() {
        return this.tagName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof Tag
            && tagName.equals(((Tag) other).tagName));
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
