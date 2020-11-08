package seedu.address.testutil.todolist;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.todolistcommands.EditTaskDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setName(task.getName().orElse(null));
        descriptor.setTags(task.getTags().orElse(null));
        descriptor.setPriority(task.getPriority().orElse(null));
        descriptor.setDate(task.getDate().orElse(null));
    }

    /**
     * Sets the {@code TaskName} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withName(String taskName) {
        if (taskName != null) {
            descriptor.setName(new TaskName(taskName));
        } else {
            descriptor.setName(null);
        }
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withPriority(String priority) {
        if (priority != null) {
            descriptor.setPriority(Priority.valueOf(priority));
        } else {
            descriptor.setPriority(null);
        }
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDate(String date) {
        if (date != null) {
            descriptor.setDate(new Date(date));
        } else {
            descriptor.setDate(null);
        }
        return this;
    }

    /**
     * Sets the {@code isPriorityDeleted} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withIsPriorityDeleted(boolean bool) {
        descriptor.setIsPriorityDeleted(true);
        return this;
    }

    /**
     * Sets the {@code isDateDeleted} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withIsDateDeleted(boolean bool) {
        descriptor.setIsDateDeleted(true);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
