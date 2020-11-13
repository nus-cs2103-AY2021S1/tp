package seedu.address.testutil;

import seedu.address.logic.commands.deliverable.EditCommand;
import seedu.address.model.deliverable.deliverable.Deadline;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.model.deliverable.deliverable.Milestone;
import seedu.address.model.util.Contacts;
import seedu.address.model.util.Description;
import seedu.address.model.util.Title;

public class EditDeliverableDescriptorBuilder {
    private EditCommand.EditDeliverableDescriptor descriptor;

    public EditDeliverableDescriptorBuilder() {
        descriptor = new EditCommand.EditDeliverableDescriptor();
    }

    public EditDeliverableDescriptorBuilder(EditCommand.EditDeliverableDescriptor descriptor) {
        this.descriptor = new EditCommand.EditDeliverableDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDeliverableDescriptor} with fields containing {@code Deliverable}'s details
     */
    public EditDeliverableDescriptorBuilder(Deliverable deliverable) {
        descriptor = new EditCommand.EditDeliverableDescriptor();
        descriptor.setTitle(deliverable.getTitle());
        descriptor.setMilestone(deliverable.getMilestone());
        descriptor.setDescription(deliverable.getDescription());
        descriptor.setDeadline(deliverable.getDeadline());
        descriptor.setContacts(deliverable.getContacts());
        descriptor.setIsComplete(deliverable.getIsComplete());
    }

    /**
     * Sets the {@code Title} of the {@code EditDeliverableDescriptor} that we are building.
     */
    public EditDeliverableDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Milestone} of the {@code EditDeliverableDescriptor} that we are building.
     */
    public EditDeliverableDescriptorBuilder withMilestone(String milestone) {
        descriptor.setMilestone(new Milestone(milestone));
        return this;
    }

    /**
     * Sets the {@code OptionalDescription} of the {@code EditDeliverableDescriptor} that we are building.
     */
    public EditDeliverableDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditDeliverableDescriptor} that we are building.
     */
    public EditDeliverableDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code To} of the {@code EditDeliverableDescriptor} that we are building.
     */
    public EditDeliverableDescriptorBuilder withContacts(String contacts) {
        descriptor.setContacts(new Contacts(contacts));
        return this;
    }

    /**
     * Sets the {@code isCompleted} of the {@code EditDeliverableDescriptor} that we are building.
     */
    public EditDeliverableDescriptorBuilder withIsCompleted(boolean isCompleted) {
        descriptor.setIsComplete(isCompleted);
        return this;
    }

    public EditCommand.EditDeliverableDescriptor build() {
        return descriptor;
    }
}
