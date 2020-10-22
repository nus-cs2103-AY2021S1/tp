package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's blood type in the CliniCal application.
 * Guarantees: immutable; is valid as declared in {@link #isValidBloodType(String)}
 */
public class BloodType {

    public static final String MESSAGE_CONSTRAINTS = "Blood type should be 1 of the following: "
            + "A+ | A- | B+ | B-| O+ | O- | AB+ | AB-";
    // a blood group that can be "A", "B", "O" or "AB" depending on the presence of antigens A, B
    private static final String BLOOD_TYPE_ABO = "(?i)^(A|B|AB|O)";
    // a rh system that can be "+" or "-" depending on the presence of RhD antigen
    private static final String BLOOD_TYPE_RHD = "[+-]$";
    public static final String VALIDATION_REGEX = BLOOD_TYPE_ABO + BLOOD_TYPE_RHD;

    public final String type;

    /**
     * Constructs an {@code BloodType}.
     *
     * @param bloodType A valid blood type.
     */
    public BloodType(String bloodType) {
        requireNonNull(bloodType);
        checkArgument(isValidBloodType(bloodType), MESSAGE_CONSTRAINTS);
        type = bloodType;
    }

    /**
     * Constructs a default {@code BloodType}.
     *
     */
    public BloodType() {
        type = "N/A";
    }

    /**
     * Returns if a given string is a valid blood type.
     */
    public static boolean isValidBloodType(String test) {
        if (test.equals("N/A")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BloodType // instanceof handles nulls
                && type.equals(((BloodType) other).type)); // state check
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

}
