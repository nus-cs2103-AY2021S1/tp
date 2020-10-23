package seedu.address.model.module.grade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents an association class that tracks the assignments and grade for a module.
 */
public class GradeTracker {
    public static final String MESSAGE_INVALID_GRADE =
            "Grades should be provided in the range from 0.00 to 1.00.";
    public static final String MESSAGE_INVALID_GRADEPOINT =
            "GradePoint should be given as a decimal from 0.00 to 5.00.";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT =
            "Assignments cannot be repeated.";

    private final List<Assignment> assignments;
    private Grade grade;
    private Optional<GradePoint> gradePoint;

    /**
     * Creates a GradeTracker that stores the assignment, grades and grade point for a module.
     */
    public GradeTracker() {
        this.assignments = new ArrayList<>();
        this.grade = new Grade(0);
        gradePoint = Optional.empty();
    }

    /**
     * Creates a GradeTracker that stores the assignment, grades and grade point for a module.
     * @param gradePoint GradePoint for a completed module
     */
    public GradeTracker(double gradePoint) {
        this.assignments = new ArrayList<>();
        this.grade = new Grade(0);
        this.gradePoint = Optional.of(new GradePoint(gradePoint));
    }

    public void setGrade(Grade newGrade) {
        this.grade = newGrade;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGradePoint(Optional<GradePoint> newGradePoint) {
        this.gradePoint = newGradePoint;
    }

    public Optional<Double> getGradePoint() {
        if (gradePoint.isPresent()) {
            return Optional.of(gradePoint.get().gradePoint);
        } else {
            return Optional.empty();
        }
    }

    public void addAssignment(Assignment newAssignment) {
        assignments.add(newAssignment);
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }



    /**
     * Checks if the Grade Tracker is valid.
     *
     * @param gradeTracker grade tracker to check.
     * @return true if the grade tracker is valid.
     */
    public static boolean isValidGradeTracker(GradeTracker gradeTracker) {
        boolean areAssignmentsValid = true;
        for (Assignment eachAssignment: gradeTracker.assignments) {
            if (!Assignment.isValidAssignmentResult(eachAssignment.assignmentResult)
                    && Assignment.isValidAssignmentName(eachAssignment.assignmentName)
                    && Assignment.isValidAssignmentPercentage(eachAssignment.assignmentPercentage)) {
                areAssignmentsValid = false;
                break;
            }
        }
        if (Grade.isValidGrade(gradeTracker.grade.gradeResult) && areAssignmentsValid) {
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
