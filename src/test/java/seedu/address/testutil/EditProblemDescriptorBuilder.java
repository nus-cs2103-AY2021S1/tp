package seedu.address.testutil;

import seedu.address.logic.commands.EditProblemCommand;
import seedu.address.model.problem.Description;
import seedu.address.model.problem.Problem;
import seedu.address.model.problem.Severity;

/**
 * A utility class to help with building EditBookDescriptor objects.
 */
public class EditProblemDescriptorBuilder {

    private EditProblemCommand.EditProblemDescriptor descriptor;

    public EditProblemDescriptorBuilder() {
        descriptor = new EditProblemCommand.EditProblemDescriptor();
    }

    public EditProblemDescriptorBuilder(EditProblemCommand.EditProblemDescriptor descriptor) {
        this.descriptor = new EditProblemCommand.EditProblemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookDescriptor} with fields containing {@code book}'s details
     */
    public EditProblemDescriptorBuilder(Problem problem) {
        descriptor = new EditProblemCommand.EditProblemDescriptor();
        descriptor.setDescription(problem.getDescription());
        descriptor.setSeverity(problem.getSeverity());
    }

    /**
     * Sets the {@code Name} of the {@code EditBookDescriptor} that we are building.
     */
    public EditProblemDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Isbn} of the {@code EditBookDescriptor} that we are building.
     */
    public EditProblemDescriptorBuilder withSeverity(String severity) {
        descriptor.setSeverity(new Severity(severity));
        return this;
    }


    public EditProblemCommand.EditProblemDescriptor build() {
        return descriptor;
    }
}
