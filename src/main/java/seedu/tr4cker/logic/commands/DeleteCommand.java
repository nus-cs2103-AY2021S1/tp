package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from TR4CKER.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "Looks like you're trying to use the " + COMMAND_WORD + " command: "
            + "Deletes the task identified by the index number used in the specified task list\n"
            + "Compulsory Parameter: INDEX (must be a positive integer and valid index number)\n"
            + "To delete task from Pending Tasks: (E.g. " + COMMAND_WORD + " 1)\n"
            + "To delete task from Expired Tasks: (E.g. " + COMMAND_WORD + " expired 1)";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    protected final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredPendingTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        Todo todoToDelete = new Todo(taskToDelete.getName(), taskToDelete.getDeadline());
        model.deleteTask(taskToDelete);

        if (model.hasTodo(todoToDelete)) {
            model.deleteTodo(todoToDelete);
        }

        CommandResult commandResult = new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
        commandResult.setHomeTab();
        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }

}
