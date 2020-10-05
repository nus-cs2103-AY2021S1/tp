package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Email;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building EditProjectDescriptor objects.
 */
public class EditProjectDescriptorBuilder {

    private EditProjectDescriptor descriptor;

    public EditProjectDescriptorBuilder() {
        descriptor = new EditProjectDescriptor();
    }

    public EditProjectDescriptorBuilder(EditProjectDescriptor descriptor) {
        this.descriptor = new EditProjectDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProjectDescriptor} with fields containing {@code project}'s details
     */
    public EditProjectDescriptorBuilder(Project project) {
        descriptor = new EditProjectDescriptor();
        descriptor.setProjectName(project.getProjectName());
        descriptor.setDeadline(project.getDeadline());
        descriptor.setEmail(project.getEmail());
        descriptor.setProjectDescription(project.getProjectDescription());
        descriptor.setTags(project.getTags());
        descriptor.setTasks(project.getTasks());
    }

    /**
     * Sets the {@code ProjectName} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withProjectName(String projectName) {
        descriptor.setProjectName(new ProjectName(projectName));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code ProjectDescription} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withProjectDescription(String projectDescription) {
        descriptor.setProjectDescription(new ProjectDescription(projectDescription));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditProjectDescriptor}
     * that we are building.
     */
    public EditProjectDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code Set<Task>} and set it to the {@code EditProjectDescriptor}
     * that we are building.
     */
    public EditProjectDescriptorBuilder withTasks(String... tasks) {
        Set<Task> taskSet = Stream.of(tasks).map(x -> new Task(x, null, null, 0, false))
                .collect(Collectors.toSet());
        descriptor.setTasks(taskSet);
        return this;
    }

    public EditProjectDescriptor build() {
        return descriptor;
    }
}
