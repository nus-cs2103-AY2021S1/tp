package seedu.address.model.patient;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 *
 */
public class Nric {


    public static final String MESSAGE_CONSTRAINTS =
            "Starts with an alphabet, followed by 8 digits and ends with another alphabet";
    public static final String VALIDATION_REGEX = "\\D\\d{8}\\D$";
    public final String nric;

    /**
     *
     * @param nric
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        this.nric = nric;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return nric;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Nric // instanceof handles nulls
                && nric.equals(((Nric) other).nric)); // state check
    }

    @Override
    public int hashCode() {
        return nric.hashCode();
    }

}
