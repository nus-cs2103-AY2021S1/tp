package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from PlaNus.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "delete-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final Index[] targetIndexes;

    /**
     * Creates an DeleteTaskCommand to to delete the tasks with {@code targetIndexes} from system.
     */
    public DeleteTaskCommand(Index[] targetIndexes) {
        requireNonNull(targetIndexes);
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        Task[] tasksToDelete = new Task[targetIndexes.length];
        if (Index.hasDuplicateIndex(targetIndexes)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_TASK_INDEX);
        }
        for (int i = 0; i < targetIndexes.length; i++) {
            if (targetIndexes[i].getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX);
            }
            tasksToDelete[i] = lastShownList.get(targetIndexes[i].getZeroBased());
        }

        model.deleteTask(tasksToDelete);
        for (Task task : tasksToDelete) {
            if (model.hasCalendarTask(task)) {
                model.deleteTaskInCalendar(new Task[]{task});
            }
        }
        return new CommandResult(buildMessage(tasksToDelete));
    }

    /**
     * @param tasks that is been deleted.
     * returns message built by the list of tasks deleted.
     */
    public static String buildMessage(Task[] tasks) {
        String message = "";
        for (int i = 0; i < tasks.length; i++) {
            message += String.format(MESSAGE_DELETE_TASK_SUCCESS, tasks[i].getTitle()) + "\n";
        }
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && Arrays.equals(targetIndexes, ((DeleteTaskCommand) other).targetIndexes)); // state check
    }
}
