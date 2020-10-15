package seedu.address.model.module.grade;

import java.util.ArrayList;
import java.util.List;

/**
 * Tracks the assignments and final grade for a module.
 */
public class GradeTracker {
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
