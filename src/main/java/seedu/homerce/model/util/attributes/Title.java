package seedu.homerce.model.util.attributes;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.commons.util.AppUtil.checkArgument;

/**
 * Represents an Title for any model object.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class Title {

    public static final String MESSAGE_CONSTRAINTS = "Title must be made up of alphanumeric words that is not more than"
            + " 50 characters long.";

    /** Check for 50 alphanumeric characters */
    public static final String VALIDATION_REGEX = "^(?!\\s*$)[\\p{Alnum}\\s]{1,50}";

    public final String value;

    /**
     * Constructs a {@code Title}.
     *
     * @param title a valid title.
     */
    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        value = title;
    }

    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidTitle(String input) {
        return input.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && value.equals(((Title) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
