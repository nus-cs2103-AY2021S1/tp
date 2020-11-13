package seedu.address.testutil;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.project.EditTaskCommand;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditTaskCommand.EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskCommand.EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskCommand.EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskCommand.EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code project}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskCommand.EditTaskDescriptor();
        descriptor.setIsDone(task.isDone());
        descriptor.setDeadline(task.getDeadline().orElse(null));
        descriptor.setProgress(task.getProgress());
        descriptor.setPublishDate(task.getPublishDate());
        descriptor.setTaskDescription(task.getDescription());
        descriptor.setTaskName(task.getTaskName());
        descriptor.setAssignees(task.getAssignees());
    }

    /**
     * Sets the {@code ProjectName} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskName (String taskName) {
        descriptor.setTaskName(taskName);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code RepoUrl} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder isDone(String isDone) {
        descriptor.setIsDone(Boolean.parseBoolean(isDone));
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskDescription(String taskDescription) {
        descriptor.setTaskDescription(taskDescription);
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withProgress(String progress) {
        descriptor.setProgress(Double.parseDouble(progress));
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withPublishDate(String publishDate) {
        descriptor.setPublishDate((LocalDate.parse(publishDate)));
        return this;
    }

    /**
     * Parses the {@code projectTags} into a {@code Set<ProjectTag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withAssignees(String... assignees) {
        Set<String> taskAssigneeSet = Stream.of(assignees).collect(Collectors.toSet());
        descriptor.setAssignees(taskAssigneeSet);
        return this;
    }

    public EditTaskCommand.EditTaskDescriptor build() {
        return descriptor;
    }
}
