package seedu.forgetfulnus.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.util.AppUtil.checkArgument;

/**
 * Represents a Predefined Difficulty Tag in the Glossary.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDifficultyTag(String)}
 */
public class DifficultyTag extends PredefinedTag implements Comparable<DifficultyTag> {

    public static final String MESSAGE_CONSTRAINTS = "Difficulty levels should be EASY, MEDIUM or HARD only";
    public static final String EASY_TAG = "EASY";
    public static final String MEDIUM_TAG = "MEDIUM";
    public static final String HARD_TAG = "HARD";

    /**
     * Constructs a {@code Difficulty Tag}.
     *
     * @param tagName A valid difficulty tag name.
     */
    public DifficultyTag(String tagName) {
        super(tagName);
        requireNonNull(tagName);
        checkArgument(isValidDifficultyTag(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid difficulty tag name.
     */
    public static boolean isValidDifficultyTag(String testInput) {
        String test = testInput.replaceAll("\\s+", "");
        return test.equalsIgnoreCase(EASY_TAG) || test.equalsIgnoreCase(MEDIUM_TAG) || test.equalsIgnoreCase(HARD_TAG);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DifficultyTag // instanceof handles nulls
                && tagName.equals(((DifficultyTag) other).tagName)); // state check
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

    /**
     * Compares this {@code DifficultyTag} to another {@code DifficultyTag}
     * @param other the DifficultyTag to compare to
     * @return an integer representing whether this DifficultyTag is "greater than" the other DifficultyTag
     */
    public int compareTo(DifficultyTag other) {
        int thisValue = this.tagName.equals(EASY_TAG)
                ? 1
                : this.tagName.equals(MEDIUM_TAG)
                        ? 2
                        : 3;
        int otherValue = other.tagName.equals(EASY_TAG)
                ? 1
                : other.tagName.equals(MEDIUM_TAG)
                        ? 2
                        : 3;
        return thisValue - otherValue;
    }
}
