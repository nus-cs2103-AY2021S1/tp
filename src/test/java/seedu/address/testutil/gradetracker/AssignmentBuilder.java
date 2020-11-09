package seedu.address.testutil.gradetracker;

import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;

public class AssignmentBuilder {
    public static final String DEFAULT_ASSIGNMENT_NAME = "Quiz 1";
    public static final double DEFAULT_ASSIGNMENT_PERCENTAGE = 10;
    public static final double DEFAULT_ASSIGNMENT_RESULT = 80;

    private AssignmentName assignmentName;
    private AssignmentPercentage assignmentPercentage;
    private AssignmentResult assignmentResult;

    /**
     * Creates a {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        assignmentName = new AssignmentName(DEFAULT_ASSIGNMENT_NAME);
        assignmentPercentage = new AssignmentPercentage(DEFAULT_ASSIGNMENT_PERCENTAGE);
        assignmentResult = new AssignmentResult(DEFAULT_ASSIGNMENT_RESULT);
    }

    /**
     * Initializes the AssignmentBuilder with the data of {@code assignmentToCopy}.
     */
    public AssignmentBuilder(Assignment assignmentToCopy) {
        assignmentName = assignmentToCopy.getAssignmentName().get();
        assignmentPercentage = assignmentToCopy.getAssignmentPercentage().get();
        assignmentResult = assignmentToCopy.getAssignmentResult().get();
    }

    /**
     * Sets the {@code AssignmentName} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withAssignmentName(String assignmentName) {
        this.assignmentName = new AssignmentName(assignmentName);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withAssignmentPercentage(double assignmentPercentage) {
        this.assignmentPercentage = new AssignmentPercentage(assignmentPercentage);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withAssignmentResult(double assignmentResult) {
        this.assignmentResult = new AssignmentResult(assignmentResult);
        return this;
    }


    public Assignment build() {
        return new Assignment(assignmentName, assignmentPercentage, assignmentResult);
    }

}
