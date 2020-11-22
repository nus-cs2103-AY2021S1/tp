package seedu.forgetfulnus.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.util.AppUtil.checkArgument;

/**
 * Represents a Predefined Gender Tag in the Glossary.
 * Guarantees: immutable; name is valid as declared in {@link #isValidGenderTag(String)}
 */
public class GenderTag extends PredefinedTag {

    public static final String MESSAGE_CONSTRAINTS = "Gender Tags should be M, F, Neutral or omitted only";
    public static final String MASCULINE_GENDER_TAG = "M";
    public static final String FEMININE_GENDER_TAG = "F";
    public static final String NEUTRAL_GENDER_TAG = "NEUTRAL";
    public static final String NONE_GENDER_TAG = "NONE";


    /**
     * Constructs a {@code Gender Tag}.
     *
     * @param tagName A valid gender tag name.
     */
    public GenderTag(String tagName) {
        super(tagName);
        requireNonNull(tagName);
        checkArgument(isValidGenderTag(tagName), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid gender tag name.
     */
    public static boolean isValidGenderTag(String testInput) {
        String test = testInput.replaceAll("\\s+", "");
        return test.equalsIgnoreCase(MASCULINE_GENDER_TAG) || test.equalsIgnoreCase(FEMININE_GENDER_TAG)
                || test.equalsIgnoreCase(NEUTRAL_GENDER_TAG) || test.equalsIgnoreCase(NONE_GENDER_TAG);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenderTag // instanceof handles nulls
                && tagName.equals(((GenderTag) other).tagName)); // state check
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





