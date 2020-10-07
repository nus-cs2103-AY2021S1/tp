package seedu.address.model.patient;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 *
 */
public class Nric {


    public static final String MESSAGE_CONSTRAINTS =
            "Starts with an alphabet, followed by 8 digits and ends with another alphabet";
    public static final String VALIDATION_REGEX = "[a-zA-Z]\\d{8}[a-zA-Z]$";
    public final String value;

    /**
     * Creates a NRIC with a given value.
     *
     * @param value
     */
    public Nric(String value) {
        requireNonNull(value);
        checkArgument(isValidNric(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid phone number.
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
