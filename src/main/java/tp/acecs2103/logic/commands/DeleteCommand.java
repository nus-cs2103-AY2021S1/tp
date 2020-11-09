package tp.acecs2103.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import tp.acecs2103.commons.core.LogsCenter;
import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.TaskList;
import tp.acecs2103.model.task.Index;
import tp.acecs2103.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (0 + week number in [1,13] + two-digit task number e.g. 01205)\n"
            + "Example: " + COMMAND_WORD + " 0101";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: \n%1$s";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TaskList lastShownList = model.getTaskList();
        Index index = targetIndex;
        Task taskToDelete = lastShownList.getTask(index);
        try {
            model.deleteTask(index);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
