package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's Nric in Hospify.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)}
 */
public class Nric {

    public static final String MESSAGE_CONSTRAINTS =
            "NRIC must start with an alphabet, followed by 7 digits and ends with another alphabet";
    public static final String VALIDATION_REGEX = "[a-zA-Z]\\d{7}[a-zA-Z]$";
    public final String value;

    /**
     * Constructs an NRIC with a given value.
     *
     * @param value A valid NRIC.
     */
    public Nric(String value) {
        requireNonNull(value);
        checkArgument(isValidNric(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid NRIC number.
     */
    public static boolean isValidNric(String test) {
        return test.trim().matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Nric // instanceof handles nulls
                && value.equals(((Nric) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
