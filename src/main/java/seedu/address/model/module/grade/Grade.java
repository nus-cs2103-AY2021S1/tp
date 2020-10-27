package seedu.address.model.module.grade;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Grade in the Grade Tracker.
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should be given as a decimal from 0.00 to 1.00.";
    public final double gradeResult;

    /**
     * Constructs a {@code Grade}.
     *
     * @param gradeResult A valid grade result.
     */
    public Grade(double gradeResult) {
        checkArgument(isValidGrade(gradeResult), MESSAGE_CONSTRAINTS);
        this.gradeResult = gradeResult;
    }

    /**
     * Checks if the grade can be placed into the grade tracker.
     *
     * @param test grade to be checked.
     * @return true if the grade is valid.
     */
    public static boolean isValidGrade(double test) {
        if (test >= 0 && test <= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return Double.toString(this.gradeResult);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && gradeResult == ((Grade) other).gradeResult); // state check
    }
}
