package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified by the index number used in the displayed task list.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deletetask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final Index targetIndex;
    /**
     * Creates an DeleteTaskCommand that deletes the task that corresponds to the given index.
     *
     * @param targetIndex the index number of the task to be deleted.
     */
    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert model.getProjectToBeDisplayedOnDashboard().isPresent();

        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        List<Task> lastShownTaskList = project.getFilteredSortedTaskList();

        if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownTaskList.get(targetIndex.getZeroBased());
        project.deleteTask(taskToDelete);


        if (model.getTaskToBeDisplayedOnDashboard().isPresent()
                && model.getTaskToBeDisplayedOnDashboard().get().equals(taskToDelete)) {
            model.resetTaskToBeDisplayedOnDashboard();
            project.updateTaskOnView(null);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
    }
}
