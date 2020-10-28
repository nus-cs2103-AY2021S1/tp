package seedu.address.model.module.grade;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an assignment in the Grade Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidAssignmentName(String)} (String)}
 */
public class Assignment {

    public static final String MESSAGE_ASSIGNMENT_NAME_CONSTRAINTS =
            "Assignment names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String MESSAGE_ASSIGNMENT_PERCENTAGE_CONSTRAINTS =
            "Assignment percentage should be in the range 0 - 100";
    public static final String MESSAGE_ASSIGNMENT_RESULT_CONSTRAINTS =
            "Assignment result should be in the range 0.00 to 1.00";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String assignmentName;
    public final double assignmentPercentage;
    public final double assignmentResult;

    /**
     * Constructs a {@code Assignment}.
     *
     * @param assignmentName name of the assignment.
     * @param assignmentPercentage the percentage of the total grade that this assignment takes up.
     * @param assignmentResult the result achieved for this assignment. Range from 0 to 1.
     */
    public Assignment(String assignmentName, double assignmentPercentage, double assignmentResult) {
        requireNonNull(assignmentName);
        /*checkArgument(isValidAssignmentName(assignmentName), MESSAGE_ASSIGNMENT_NAME_CONSTRAINTS);
        checkArgument(isValidAssignmentPercentage(assignmentPercentage), MESSAGE_ASSIGNMENT_PERCENTAGE_CONSTRAINTS);
        checkArgument(isValidAssignmentResult(assignmentResult), MESSAGE_ASSIGNMENT_RESULT_CONSTRAINTS);*/
        this.assignmentName = assignmentName;
        this.assignmentPercentage = assignmentPercentage;
        this.assignmentResult = assignmentResult;
    }

    /**
     * Default constructor for Assignment.
     */
    public Assignment() {
        assignmentName = "";
        assignmentPercentage = 0;
        assignmentResult = 0;
    }

    /**
     * Returns true if a given string is a valid assignment name.
     */
    public static boolean isValidAssignmentName(String test) {
        //return test.matches(VALIDATION_REGEX);
        return true;
    }

    /**
     * Returns true if a given double is a valid assignment percentage.
     */
    public static boolean isValidAssignmentPercentage(double test) {
        if (test <= 100 && test >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid assignment result.
     */
    public static boolean isValidAssignmentResult(double test) {
        if (test <= 1 && test >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public double getPercentageOfGrade() {
        return assignmentPercentage;
    }

    public double getResult() {
        return assignmentResult;
    }

    /**
     * Returns true if both assignments have the same name.
     * This defines a weaker notion of equality between two assignments.
     *
     * @param otherAssignment other assignment to be compared
     * @return true if both assignment has the same name.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (this == otherAssignment) {
            return true;
        }

        return getAssignmentName().equals((otherAssignment.getAssignmentName()));
    }

    @Override
    public String toString() {
        return String.format("Assignment %s is %.2f of the total grade and the result is %.2f",
                assignmentName, assignmentPercentage, assignmentResult);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && assignmentName.equals(((Assignment) other).assignmentName))
                && assignmentResult == ((Assignment) other).assignmentResult
                && assignmentPercentage == ((Assignment) other).assignmentPercentage; // state check
    }

    @Override
    public int hashCode() {
        return assignmentName.hashCode();
    }

}
