package seedu.tr4cker.testutil;

import seedu.tr4cker.logic.commands.EditCommand;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;

/**
 * A utility class to help with building EditTodoDescriptor objects.
 */
public class EditTodoDescriptorBuilder {
    private final EditCommand.EditTodoDescriptor descriptor;

    public EditTodoDescriptorBuilder() {
        descriptor = new EditCommand.EditTodoDescriptor();
    }

    public EditTodoDescriptorBuilder(EditCommand.EditTodoDescriptor descriptor) {
        this.descriptor = new EditCommand.EditTodoDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTodoDescriptor} with fields containing {@code todo}'s details
     */
    public EditTodoDescriptorBuilder(Todo todo) {
        descriptor = new EditCommand.EditTodoDescriptor();
        descriptor.setName(todo.getName());
        descriptor.setDeadline(todo.getDeadline());
    }

    /**
     * Sets the {@code Name} of the {@code EditTodoDescriptor} that we are building.
     */
    public EditTodoDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditTodoDescriptor} that we are building.
     */
    public EditTodoDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline, false));
        return this;
    }

    public EditCommand.EditTodoDescriptor build() {
        return descriptor;
    }
}
