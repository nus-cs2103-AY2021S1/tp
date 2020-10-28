package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPOURL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.Set;

import seedu.address.logic.commands.global.AddCommand;
import seedu.address.logic.commands.global.EditCommand.EditProjectDescriptor;
import seedu.address.model.project.Project;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;

/**
 * A utility class for Project.
 */
public class ProjectUtil {

    /**
     * Returns an add command string for adding the {@code project}.
     */
    public static String getAddCommand(Project project) {
        return AddCommand.COMMAND_WORD + " " + getProjectDetails(project);
    }

    /**
     * Returns the part of command string for the given {@code project}'s details.
     */
    public static String getProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PROJECT_NAME + project.getProjectName().fullProjectName + " ");
        sb.append(PREFIX_DEADLINE + project.getDeadline().toString() + " ");
        sb.append(PREFIX_REPOURL + project.getRepoUrl().value + " ");
        sb.append(PREFIX_DESCRIPTION + project.getProjectDescription().value + " ");
        project.getProjectTags().stream().forEach(
            s -> sb.append(PREFIX_PROJECT_TAG + s.projectTagName + " ")
        );
        project.getTasks().stream().forEach(
            s -> sb.append(PREFIX_TASK + s.taskName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProjectDescriptor}'s details.
     */
    public static String getEditProjectDescriptorDetails(EditProjectDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getProjectName().ifPresent(name -> sb.append(PREFIX_PROJECT_NAME).append(name.fullProjectName)
            .append(" "));
        descriptor.getDeadline().ifPresent(
            deadline -> sb.append(PREFIX_DEADLINE).append(deadline.toString()).append(" "));
        descriptor.getRepoUrl().ifPresent(email -> sb.append(PREFIX_REPOURL).append(email.value).append(" "));
        descriptor.getProjectDescription().ifPresent(address -> sb.append(PREFIX_DESCRIPTION).append(
            address.value).append(" "));
        if (descriptor.getProjectTags().isPresent()) {
            Set<ProjectTag> projectTags = descriptor.getProjectTags().get();
            if (projectTags.isEmpty()) {
                sb.append(PREFIX_PROJECT_TAG);
            } else {
                projectTags.forEach(s -> sb.append(PREFIX_PROJECT_TAG).append(s.projectTagName).append(" "));
            }
        }
        sb.append(" ");
        if (descriptor.getTasks().isPresent()) {
            Set<Task> tasks = descriptor.getTasks().get();
            if (tasks.isEmpty()) {
                sb.append(PREFIX_TASK);
            } else {
                tasks.forEach(s -> sb.append(PREFIX_TASK).append(s.taskName).append(" "));
            }
        }
        return sb.toString();
    }
}
