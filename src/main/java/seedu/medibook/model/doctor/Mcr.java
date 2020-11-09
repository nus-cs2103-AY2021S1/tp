package seedu.medibook.model.doctor;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Doctor's Medical Registration ID (MCR) in MediBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidMcr(String)}
 */
public class Mcr {

    public static final String MESSAGE_CONSTRAINTS = "MCR should be of the format M@xxxx#"
            + "and adhere to the following constraints:\n"
            + "1. The @ should be either a letter or number\n"
            + "2. The xxxx should be digits\n"
            + "3. The # should be a letter";

    public static final String FIRST_CHARACTER_REGEX = "[Mm]";
    public static final String SECOND_CHARACTER_REGEX = "[a-zA-Z0-9]";
    public static final String DIGITS_REGEX = "\\d{4}";
    public static final String LAST_CHARACTER_REGEX = "[a-zA-Z]";
    public static final String VALIDATION_REGEX = FIRST_CHARACTER_REGEX + SECOND_CHARACTER_REGEX + DIGITS_REGEX
            + LAST_CHARACTER_REGEX;
    public final String value;

    /**
     * Constructs a {@code Mcr}.
     *
     * @param mcr A valid Mcr string.
     */
    public Mcr(String mcr) {
        requireNonNull(mcr);
        checkArgument(isValidMcr(mcr), MESSAGE_CONSTRAINTS);
        String mcrInCaps = mcr.toUpperCase();
        value = mcrInCaps;
    }

    /**
     * Returns true if the given string is a valid IC number.
     */
    public static boolean isValidMcr(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mcr // instanceof handles nulls
                && value.equals(((Mcr) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
