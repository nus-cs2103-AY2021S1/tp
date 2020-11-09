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
 * Mark a task as done as identified by the index number.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX (0 + week number in [1,13] + two-digit task number e.g. 01205)\n"
            + "Example: " + COMMAND_WORD + "0101";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Done Task: \n%1$s";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Index targetIndex;

    public DoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public Index getTargetIndex() {
        return this.targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        TaskList lastShownList = model.getTaskList();
        Task taskToMarkAsDone = lastShownList.getTask(targetIndex);
        try {
            model.markTaskAsDone(targetIndex);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
        return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, taskToMarkAsDone));
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof DoneCommand)) {
            return false;
        } else {
            return this.targetIndex.equals(((DoneCommand) other).targetIndex);
        }
    }
}
