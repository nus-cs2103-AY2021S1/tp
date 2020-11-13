package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.global.EditCommand.EditProjectDescriptor;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
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
        descriptor.setRepoUrl(project.getRepoUrl());
        descriptor.setProjectDescription(project.getProjectDescription());
        descriptor.setTags(project.getProjectTags());
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
     * Sets the {@code RepoUrl} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withRepoUrl(String repoUrl) {
        descriptor.setRepoUrl(new RepoUrl(repoUrl));
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
     * Parses the {@code projectTags} into a {@code Set<ProjectTag>} and set it to the {@code EditProjectDescriptor}
     * that we are building.
     */
    public EditProjectDescriptorBuilder withTags(String... projectTags) {
        Set<ProjectTag> projectTagSet = Stream.of(projectTags).map(ProjectTag::new).collect(Collectors.toSet());
        descriptor.setTags(projectTagSet);
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code Set<Task>} and set it to the {@code EditProjectDescriptor}
     * that we are building.
     */
    public EditProjectDescriptorBuilder withTasks(String... tasks) {
        Set<Task> taskSet = Stream.of(tasks).map(x -> new Task(x, null, null, 0))
                .collect(Collectors.toSet());
        descriptor.setTasks(taskSet);
        return this;
    }

    public EditProjectDescriptor build() {
        return descriptor;
    }
}
