package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's sex in the CliniCal application.
 * Guarantees: immutable; is valid as declared in {@link #isValidSex(String)}
 */
public class Sex {

    public static final String MESSAGE_CONSTRAINTS = "Sex should be one of the two: "
            + "M | F\n";
    // a letter that can be "M" or "F" depending on the sex of the patient
    private static final String SEX = "(?i)^[MF]$";
    public static final String VALIDATION_REGEX = SEX;

    public final String value;

    /**
     * Constructs an {@code Sex}.
     *
     * @param sex A valid sex.
     */
    public Sex(String sex) {
        requireNonNull(sex);
        checkArgument(isValidSex(sex), MESSAGE_CONSTRAINTS);
        value = sex;
    }

    /**
     * Constructs a default {@code Sex}.
     *
     */
    public Sex() {
        value = "N/A";
    }

    /**
     * Returns if a given string is a valid sex.
     */
    public static boolean isValidSex(String test) {
        if (test.equals("N/A")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sex // instanceof handles nulls
                && value.equals(((Sex) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
