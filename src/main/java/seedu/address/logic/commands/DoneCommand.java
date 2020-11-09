package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.event.Event;

/**
 * Deletes a task identified using it's displayed index from the PlaNus.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": mark the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX:TIME_TAKEN (one or more positive integers that is separated by a white space)\n"
            + "Example: " + COMMAND_WORD + " 1:20 2:10 3:120";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Task: %1$s is marked as complete.";

    private final Index[] targetIndexes;
    private final int[] durations;

    /**
     * Creates an DoneCommand to mark tasks with {@code targetIndexes} as done.
     */
    public DoneCommand(Index[] targetIndexes, int[] durations) {
        requireNonNull(targetIndexes);
        this.targetIndexes = targetIndexes;
        this.durations = durations;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();


        Task[] selectedTasks =
                checkIndexValidity(targetIndexes, lastShownList, Messages.MESSAGE_INVALID_TASKS_DISPLAYED_INDEX)
                        .toArray(new Task[0]);

        checkIfAllAreDeadlines(lastShownList);
        Deadline[] deadlinesToMarkAsDone = Arrays.copyOf(selectedTasks, selectedTasks.length, Deadline[].class);
        checkAllHaveIncompleteStatus(deadlinesToMarkAsDone);
        model.markAsDone(deadlinesToMarkAsDone, durations);
        return new CommandResult(buildMessage(deadlinesToMarkAsDone));
    }

    /**
     * @param tasks that is been marked as done
     * returns message built by the list of tasks done.
     */
    public static String buildMessage(Task[] tasks) {
        StringBuilder message = new StringBuilder();
        for (Task task : tasks) {
            message.append(String.format(MESSAGE_DONE_TASK_SUCCESS, task.getTitle())).append("\n");
        }
        return message.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && Arrays.equals(targetIndexes, ((DoneCommand) other).targetIndexes)); // state check
    }

    private void checkAllHaveIncompleteStatus(Deadline[] deadlinesToMarkAsDone) throws CommandException {
        for (Deadline deadline : deadlinesToMarkAsDone) {
            if (deadline.isDone()) {
                throw new CommandException(Messages.MESSAGE_INCORRECT_TASK_STATUS);
            }
        }
    }

    private void checkIfAllAreDeadlines(List<Task> lastShownList) throws CommandException {
        for (Index targetIndex : targetIndexes) {
            Task task = lastShownList.get(targetIndex.getZeroBased());
            if (task instanceof Event) {
                throw new CommandException(Messages.MESSAGE_INVALID_DONE_TASK_TYPE);
            }
        }
    }
}
