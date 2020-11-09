package seedu.cc.model.account.entry;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.AppUtil.checkArgument;

/**
 * Represents an Entry's description.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS = "Description can take in any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private final String value;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description of an Entry.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.value = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
