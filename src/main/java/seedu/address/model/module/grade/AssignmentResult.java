package seedu.address.model.module.grade;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the results achieved for this assignment.
 */
public class AssignmentResult {

    public static final String MESSAGE_CONSTRAINTS =
            "Assignment result should be a value between 0.00 to 100.00 and it should not be blank.";

    public final double assignmentResult;

    /**
     * Constructs a {@code assignmentResult}.
     *
     * @param assignmentResult A valid assignment result.
     */
    public AssignmentResult(double assignmentResult) {
        checkArgument(isValidAssignmentResult(assignmentResult), MESSAGE_CONSTRAINTS);
        this.assignmentResult = assignmentResult;
    }

    /**
     * Returns true if a given double is a valid assignment result.
     */
    public static boolean isValidAssignmentResult(double test) {
        return test <= 100 && test >= 0;
    }


    @Override
    public String toString() {
        return String.valueOf(assignmentResult);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignmentResult // instanceof handles nulls
                && assignmentResult == ((AssignmentResult) other).assignmentResult); // state check
    }
}
