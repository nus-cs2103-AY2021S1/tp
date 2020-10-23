package seedu.resireg.model.semester;

import static seedu.resireg.commons.util.AppUtil.checkArgument;

public class AcademicYear {

    // A constant to denote when the first academic year started.
    public static final int YEAR_OF_ESTABLISHMENT = 1980;

    public static final String MESSAGE_CONSTRAINTS = "Academic Year has to be greater than or equal to "
            + YEAR_OF_ESTABLISHMENT;
    public final int value;

    /**
     * Constructs a {@code AcademicYear}.
     *
     * @param value A valid academic year
     */
    public AcademicYear(int value) {
        checkArgument(isValidAcademicYear(value), MESSAGE_CONSTRAINTS);
        assert value >= YEAR_OF_ESTABLISHMENT : MESSAGE_CONSTRAINTS;
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid academic year.
     */
    public static boolean isValidAcademicYear(int test) {
        return test >= 1980;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicYear // instanceof handles nulls
                && value == ((AcademicYear) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value;
    }

}
