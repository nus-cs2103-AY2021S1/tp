package seedu.address.model.module.grade;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an assignment in the Grade Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidAssignmentName(String)} (String)}
 */
public class Assignment {

    public static final String MESSAGE_CONSTRAINTS =
            "Assignment names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String assignmentName;
    public final double percentageOfGrade;
    public final double result;

    /**
     * Constructs a {@code Assignment}.
     *
     * @param assignmentName name of the assignment.
     * @param percentageOfGrade the percentage of the total grade that this assignment takes up.
     * @param result the result achieved for this assignment. Range from 0 to 1.
     */
    public Assignment(String assignmentName, double percentageOfGrade, double result) {
        requireNonNull(assignmentName);
        checkArgument(isValidAssignmentName(assignmentName), MESSAGE_CONSTRAINTS);
        this.assignmentName = assignmentName;
        this.percentageOfGrade = percentageOfGrade;
        this.result = result;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidAssignmentName(String test) {
        //return test.matches(VALIDATION_REGEX);
        return true;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    @Override
    public String toString() {
        return String.format("Assignment %s is %.2f of the total grade and the result is %.2f",
                assignmentName, percentageOfGrade, result);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && assignmentName.equals(((Assignment) other).assignmentName)); // state check
    }

    @Override
    public int hashCode() {
        return assignmentName.hashCode();
    }

}
