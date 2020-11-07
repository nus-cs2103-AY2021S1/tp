package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;

/**
 * A utility class to help with building EditAssignmentDescriptor objects.
 */
public class EditAssignmentDescriptorBuilder {

    private EditCommand.EditAssignmentDescriptor descriptor;

    public EditAssignmentDescriptorBuilder() {
        descriptor = new EditCommand.EditAssignmentDescriptor();
    }

    public EditAssignmentDescriptorBuilder(EditCommand.EditAssignmentDescriptor descriptor) {
        this.descriptor = new EditCommand.EditAssignmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAssignmentDescriptor} with fields containing {@code assignment}'s details
     */
    public EditAssignmentDescriptorBuilder(Assignment assignment) {
        descriptor = new EditCommand.EditAssignmentDescriptor();
        descriptor.setName(assignment.getName());
        descriptor.setDeadline(assignment.getDeadline());
        descriptor.setModuleCode(assignment.getModuleCode());
    }

    /**
     * Sets the {@code Name} of the {@code EditAssignmentDescriptor} that we are building.
     */
    public EditAssignmentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the deadline of the {@code EditAssignmentDescriptor} that we are building.
     */
    public EditAssignmentDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Time(deadline));
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code EditAssignmentDescriptor} that we are building.
     */
    public EditAssignmentDescriptorBuilder withModuleCode(String moduleCode) {
        descriptor.setModuleCode(new ModuleCode(moduleCode));
        return this;
    }

    public EditCommand.EditAssignmentDescriptor build() {
        return descriptor;
    }
}
