package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's IC number in the CliniCal application.
 * Guarantees: immutable; is valid as declared in {@link #isValidIcNumber(String)}
 */
public class IcNumber {

    public static final String MESSAGE_CONSTRAINTS = "IC numbers should be of the format @xxxxxxx# "
            + "where each of the three parts adhere to the following constraints:\n"
            + "1. @ is a letter that can be \"S\", \"T\", \"F\" or \"G\" "
            + "depending on the status of the holder.\n"
            + "2. xxxxxxx is a 7-digit serial number assigned to the document holder.\n"
            + "3. # is the checksum letter calculated with respect to @ and xxxxxxx.";
    // a letter that can be "S", "T", "F" or "G" depending on the status of the patient
    private static final String IC_FIRST_CHARACTER_REGEX = "(?i)^[STFG]";
    // a 7-digit serial number assigned to the document holder
    private static final String IC_MIDDLE_REGEX = "\\d{7}";
    // the checksum letter calculated with respect to first character and 7 digits
    private static final String IC_LAST_CHARACTER_REGEX = "[a-zA-Z]$";
    public static final String VALIDATION_REGEX =
            IC_FIRST_CHARACTER_REGEX + IC_MIDDLE_REGEX + IC_LAST_CHARACTER_REGEX;

    public final String value;

    /**
     * Constructs an {@code IcNumber}.
     *
     * @param icNumber A valid ic number.
     */
    public IcNumber(String icNumber) {
        requireNonNull(icNumber);
        checkArgument(isValidIcNumber(icNumber), MESSAGE_CONSTRAINTS);
        value = icNumber;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidIcNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IcNumber // instanceof handles nulls
                && value.equals(((IcNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
