package seedu.resireg.model.semester;

import static seedu.resireg.commons.util.AppUtil.checkArgument;

/**
 * Represents a Semester's number in ResiReg.
 * Guarantees: immutable; is valid as declared in {@link #isValidSemesterNumber(int)}
 */
public class SemesterNumber {

    public static final String MESSAGE_CONSTRAINTS = "Semester number can only be 1 or 2";
    public final int value;

    /**
     * Constructs a {@code SemesterNumber}.
     *
     * @param value A valid semester number.
     */
    public SemesterNumber(int value) {
        checkArgument(isValidSemesterNumber(value), MESSAGE_CONSTRAINTS);
        assert value == 1 || value == 2 : MESSAGE_CONSTRAINTS;
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidSemesterNumber(int test) {
        return test == 1 || test == 2;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SemesterNumber // instanceof handles nulls
                && value == ((SemesterNumber) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value;
    }

}
