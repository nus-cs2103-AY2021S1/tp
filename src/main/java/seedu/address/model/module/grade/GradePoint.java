package seedu.address.model.module.grade;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an association class that tracks the CAP for a module
 */
public class GradePoint {
    public static final String MESSAGE_CONSTRAINTS =
            "GradePoint should be given as a decimal from 0.00 to 5.00.";
    public final double gradePoint;

    /**
     * Constructs a {@code GradePoint}.
     *
     * @param gradePoint A valid grade result.
     */
    public GradePoint(double gradePoint) {
        checkArgument(isValidGradePoint(gradePoint), MESSAGE_CONSTRAINTS);
        this.gradePoint = gradePoint;
    }

    /**
     * Checks if the grade can be placed into the grade tracker.
     *
     * @param test grade to be checked.
     * @return true if the grade is valid.
     */
    public static boolean isValidGradePoint(double test) {
        if (test >= 0 && test <= 5) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return Double.toString(this.gradePoint);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && gradePoint == ((GradePoint) other).gradePoint); // state check
    }
}
