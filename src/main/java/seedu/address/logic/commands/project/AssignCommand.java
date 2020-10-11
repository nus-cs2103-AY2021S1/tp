package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

import java.util.List;

/**
 * Assigns a task to a person with participation in the current project.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns the task identified by the index number used in the displayed task list to the team member"
            + "that participates in the current project with his/her name.\n"
            + "Parameters: INDEX (must be a positive integer), NAME (must be present in the project)\n"
            + "Example: " + COMMAND_WORD + " 1 Lucas";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Assigns task: %1$s to %s";

    private final Index targetIndex;
    private final Participation assignee;

    public AssignCommand(Index targetIndex, Participation assignee) {
        this.targetIndex = targetIndex;
        this.assignee = assignee;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getFilteredProjectList().get(0);
        List<Task> lastShownTaskList = project.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Task taskToAssociate = lastShownTaskList.get(targetIndex.getZeroBased());
        assignee.addTask(taskToAssociate);

        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS, taskToAssociate, assignee));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssignCommand // instanceof handles nulls
                && targetIndex.equals(((AssignCommand) other).targetIndex)); // state check
    }
}
