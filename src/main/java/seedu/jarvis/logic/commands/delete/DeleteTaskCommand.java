package seedu.jarvis.logic.commands.delete;
import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.jarvis.commons.core.Messages;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.task.Task;

public class DeleteTaskCommand extends DeleteCommand {
    public static final String MESSAGE_DELETE_TASK_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the task ID used in the task list.\n"
            + "Example: " + COMMAND_WORD + " -t T1\n"
            + "Type \"view -t\" to verify your task ID before deleting!";
    public static final String MESSAGE_SUCCESS = "Deleted Task: %1$s";

    private final String taskId;

    /**
     * Creates an DeleteCommand to delete the specified {@code Task}
     */
    public DeleteTaskCommand(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        assert taskId != null : "Task id of task to be deleted should not be null";

        List<Task> allTasks = model.getFilteredTaskList();
        Task taskToDelete = null;
        boolean hasDeletedTask = false;
        int size = allTasks.size();
        for (int i = 0; i < size; i++) {
            Task currTask = allTasks.get(i);
            if (currTask.getTaskId().equals(taskId)) {
                taskToDelete = currTask;
                hasDeletedTask = true;
                break;
            }
        }

        if (hasDeletedTask) {
            assert taskToDelete != null : "Upon successful task deletion, the task deleted cannot be null";
            model.deleteTask(taskToDelete);
            return new CommandResult(String.format(MESSAGE_SUCCESS, taskToDelete),
                    CommandTargetFeature.Tasks);

        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_ID);
        }
    }
}
