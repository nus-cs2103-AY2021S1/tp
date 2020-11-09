package seedu.address.testutil.gradetracker;

import seedu.address.logic.commands.gradetrackercommands.EditAssignmentDescriptor;
import seedu.address.model.module.grade.Assignment;
import seedu.address.model.module.grade.AssignmentName;
import seedu.address.model.module.grade.AssignmentPercentage;
import seedu.address.model.module.grade.AssignmentResult;

/**
 * A utility class to help with building EditAssignmentDescriptor objects.
 */
public class EditAssignmentDescriptorBuilder {

    private EditAssignmentDescriptor descriptor;

    public EditAssignmentDescriptorBuilder() {
        descriptor = new EditAssignmentDescriptor();
    }

    public EditAssignmentDescriptorBuilder(EditAssignmentDescriptor descriptor) {
        this.descriptor = new EditAssignmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAssignmentDescriptor} with fields containing {@code Assignment}'s details
     */
    public EditAssignmentDescriptorBuilder(Assignment assignment) {
        descriptor = new EditAssignmentDescriptor();
        if (assignment.getAssignmentName().isPresent()) {
            descriptor.setAssignmentName(assignment.getAssignmentName().get());
        }
        if (assignment.getAssignmentPercentage().isPresent()) {
            descriptor.setAssignmentPercentage(assignment.getAssignmentPercentage().get());
        }
        if (assignment.getAssignmentResult().isPresent()) {
            descriptor.setAssignmentResult(assignment.getAssignmentResult().get());
        }
    }

    /**
     * Sets the {@code AssignmentName} of the {@code EditAssignmentDescriptor} that we are building.
     */
    public EditAssignmentDescriptorBuilder withAssignmentName(String assignmentName) {
        descriptor.setAssignmentName(new AssignmentName(assignmentName));
        return this;
    }

    /**
     * Sets the {@code AssignmentPercentage} of the {@code EditAssignmentDescriptor} that we are building.
     */
    public EditAssignmentDescriptorBuilder withAssignmentPercentage(double assignmentPercentage) {
        descriptor.setAssignmentPercentage(new AssignmentPercentage(assignmentPercentage));
        return this;
    }

    /**
     * Sets the {@code AssignmentResult} of the {@code EditAssignmentDescriptor} that we are building.
     */
    public EditAssignmentDescriptorBuilder withAssignmentResult(double assignmentResult) {
        descriptor.setAssignmentResult(new AssignmentResult(assignmentResult));
        return this;
    }

    public EditAssignmentDescriptor build() {
        return descriptor;
    }
}
