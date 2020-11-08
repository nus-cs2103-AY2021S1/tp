package seedu.address.model.module.grade;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.model.module.Module;
import seedu.address.model.module.grade.comparator.AssignmentNameComparator;

/**
 * Represents an association class that tracks the assignments and grade for a module.
 */
public class GradeTracker implements ReadOnlyGradeTracker {
    public static final String MESSAGE_INVALID_GRADE =
            "Grades should be provided in the range from 0.00 to 100.00.";
    public static final String MESSAGE_INVALID_GRADEPOINT =
            "GradePoint should be given as a decimal from 0.00 to 5.00.";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT =
            "Assignments cannot be repeated.";
    public static final double ASSIGNMENT_PERCENTAGE_TOTAL = 100;
    private final UniqueAssignmentList assignments;
    private final AssignmentNameComparator comparator = new AssignmentNameComparator();
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        assignments = new UniqueAssignmentList();
    }
    private Grade grade;
    private GradePoint gradePoint;

    /**
     * Creates a GradeTracker that stores the assignment, grades and grade point for a module.
     */
    public GradeTracker() {
        this.grade = new Grade(0);
        gradePoint = null;
    }

    /**
     * Creates a GradeTracker that stores the assignment, grades and grade point for a module.
     * @param gradePoint GradePoint for a completed module
     */
    public GradeTracker(double gradePoint) {
        this.grade = new Grade(0);
        this.gradePoint = new GradePoint(gradePoint);
    }

    public void setGrade(Grade newGrade) {
        this.grade = newGrade;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGradePoint(GradePoint newGradePoint) {
        this.gradePoint = newGradePoint;
    }

    public Optional<GradePoint> getGradePoint() {
        return Optional.ofNullable(gradePoint);
    }

    public void addAssignment(Assignment newAssignment) {
        assignments.add(newAssignment);
    }

    /**
     * Removes {@code key} from this {@code assignments}.
     * {@code key} must exist in the assignments.
     */
    public void removeAssignment(Assignment key) {
        assignments.remove(key);
    }

    public List<Assignment> getSortedAssignments() {
        return new SortedList<>(assignments
                .asUnmodifiableObservableList(), comparator);
    }

    @Override
    public ObservableList<Assignment> getAssignments() {
        return assignments.asUnmodifiableObservableList();
    }

    /**
     * Replaces the given assignment {@code targetAssignment} in the list with {@code editedAssignment}.
     * {@code targetAssignment} must exist in the grade tracker.
     * The name of the assignment {@code editedAssignment} must not be the same as another
     * existing assignment in the grade tracker.
     */
    public void setAssignment(Assignment targetAssignment, Assignment editedAssignment) {
        requireNonNull(editedAssignment);
        assignments.setAssignment(targetAssignment, editedAssignment);
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
            if (eachAssignment.getAssignmentName().isPresent()
                    && !AssignmentName.isValidAssignmentName(eachAssignment.getAssignmentName().get().assignmentName)
                    && eachAssignment.getAssignmentPercentage().isPresent()
                    && AssignmentPercentage.isValidAssignmentPercentage(
                            eachAssignment.getAssignmentPercentage().get().assignmentPercentage)
                    && eachAssignment.getAssignmentResult().isPresent()
                    && AssignmentResult.isValidAssignmentResult(
                            eachAssignment.getAssignmentResult().get().assignmentResult)) {
                areAssignmentsValid = false;
                break;
            }
        }
        return Grade.isValidGrade(gradeTracker.grade.gradeResult) && areAssignmentsValid;
    }

    /**
     * Checks for duplicated assignments in the module.
     *
     * @param otherAssignment the assignment being checked.
     * @return true if the assignment already exists
     */
    public boolean containsDuplicateAssignment(Assignment otherAssignment) {
        requireNonNull(otherAssignment);
        for (Assignment eachAssignment : assignments) {
            if (eachAssignment.equals(otherAssignment)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the current total assignment percentages exceed 100.00 if the next assignment is added.
     *
     * @return true if the threshold is exceeded.
     */
    public boolean exceedsAssignmentPercentageThreshold(Assignment assignment) {
        double totalAssignmentPercentage = 0;

        for (Assignment eachAssignment : assignments) {
            if (eachAssignment.getAssignmentPercentage().isPresent()) {
                totalAssignmentPercentage += eachAssignment.getAssignmentPercentage().get().assignmentPercentage;
            }
        }

        return assignment.getAssignmentPercentage().isPresent() && totalAssignmentPercentage
                + assignment.getAssignmentPercentage().get().assignmentPercentage > ASSIGNMENT_PERCENTAGE_TOTAL;
    }

    /**
     * Functions like exceedsAssignmentPercentageThreshold but specific to edit assignment command.
     *
     * @return true if the threshold is exceeded.
     */
    public boolean exceedsAssignmentPercentageThreshold(Assignment assignmentToEdit, Assignment editedAssignment) {
        double totalAssignmentPercentage = 0;

        for (Assignment eachAssignment : assignments) {
            if (eachAssignment.getAssignmentPercentage().isPresent()) {
                totalAssignmentPercentage += eachAssignment.getAssignmentPercentage().get().assignmentPercentage;
            }
        }
        if (editedAssignment.getAssignmentPercentage().isPresent()
                && assignmentToEdit.getAssignmentPercentage().isPresent()) {
            totalAssignmentPercentage = totalAssignmentPercentage + (editedAssignment.getAssignmentPercentage().get()
                    .assignmentPercentage - assignmentToEdit.getAssignmentPercentage().get().assignmentPercentage);
        }
        return totalAssignmentPercentage > ASSIGNMENT_PERCENTAGE_TOTAL;
    }

    /**
     * Calculates the current updated grade to set for the gradetracker.
     *
     * @return the updated grade for the gradetracker.
     */
    public Grade calculateNewGrade() {
        double accumulatedAssignmentPercentage = 0;
        double accumulatedAssignmentResult = 0;
        for (Assignment eachAssignment : assignments) {
            if (eachAssignment.getAssignmentPercentage().isPresent() && eachAssignment.getAssignmentResult()
                    .isPresent()) {
                double assignmentPercentage = eachAssignment.getAssignmentPercentage().get().assignmentPercentage;
                double assignmentResult = eachAssignment.getAssignmentResult().get().assignmentResult;
                accumulatedAssignmentPercentage += assignmentPercentage;
                accumulatedAssignmentResult = (assignmentPercentage / ASSIGNMENT_PERCENTAGE_TOTAL) * assignmentResult;
            }
        }
        if (accumulatedAssignmentPercentage == 0) {
            return new Grade(0);
        }
        Grade newGrade = new Grade(accumulatedAssignmentResult
                / (accumulatedAssignmentPercentage / ASSIGNMENT_PERCENTAGE_TOTAL));
        this.grade = newGrade;
        return newGrade;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GradeTracker)) {
            return false;
        }

        GradeTracker otherGradeTracker = (GradeTracker) other;
        return otherGradeTracker.getGrade().equals(getGrade())
                && otherGradeTracker.getAssignments().equals(getAssignments());
    }
}
