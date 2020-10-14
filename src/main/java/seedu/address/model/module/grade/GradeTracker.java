package seedu.address.model.module.grade;

import java.util.ArrayList;
import java.util.List;

public class GradeTracker {
    private final List<Assignment> assignments;
    private double grade;

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

    public boolean isDuplicateAssignment(Assignment duplicateAssignment) {
        for(Assignment eachAssignment : assignments) {
            if (eachAssignment.equals(duplicateAssignment)) {

                return true;
            }
        }

        return false;
    }
}
