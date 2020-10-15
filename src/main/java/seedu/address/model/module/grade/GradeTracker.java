package seedu.address.model.module.grade;

import java.util.ArrayList;
import java.util.List;

/**
 * Tracks the assignments and final grade for a module.
 */
public class GradeTracker {
    public static final String MESSAGE_INVALID_GRADE =
            "Grades should be provided in the range from 0.00 to 1.00.";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT =
            "Assignments cannot be repeated.";
    private final List<Assignment> assignments;
    private double grade;

    /**
     * Creates a GradeTracker that stores the assignments and grades for a module.
     */
    public GradeTracker() {
        this.assignments = new ArrayList<>();
        this.grade = 0;
    }

    public void setGrade(double newGrade) {
        this.grade = newGrade;
    }

    public double getGrade() {
        return grade;
    }

    public void addAssignment(Assignment newAssignment) {
        assignments.add(newAssignment);
    }

    public List<Assignment> getAssignments() {
        return assignments;
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

    /**
     * Checks if the Grade Tracker is valid.
     *
     * @param gradeTracker grade tracker to check.
     * @return true if the grade tracker is valid.
     */
    public static boolean isValidGradeTracker(GradeTracker gradeTracker) {
        if (GradeTracker.isValidGrade(gradeTracker.getGrade())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks for duplicated assignments in the module.
     *
     * @param otherAssignment the assignment being checked.
     * @return true if the assignment already exists
     */
    public boolean isDuplicateAssignment(Assignment otherAssignment) {
        for (Assignment eachAssignment : assignments) {
            if (eachAssignment.equals(otherAssignment)) {
                return true;
            }
        }
        return false;
    }
}
