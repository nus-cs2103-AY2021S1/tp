package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COMPLETION_STATUS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_ALL_TODOS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_COMPLETED_TASKS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_PENDING_TASKS;

import java.util.List;
import java.util.Set;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDescription;

/**
 * Marks the completion status of an existing task in TR4CKER.
 */
public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = "Looks like you're trying to use the " + COMMAND_WORD + " command: "
            + "Marks the Completion Status of the task identified by the index number used in the Pending Tasks list\n"
            + "Compulsory Parameter: INDEX (must be a positive integer and valid index number)\n"
            + "Optional Parameter: " + PREFIX_COMPLETION_STATUS + "COMPLETION_STATUS (0-100)\n"
            + "Please note the following:\n"
            + "1. Completion status can be increased or decreased but must be different from the current value\n"
            + "2. Completion status is a percentage of task done and therefore only accepts value from 0 - 100\n"
            + "3. Completion status is set to 100 by default if not entered\n"
            + "(E.g. " + COMMAND_WORD + " 2 " + PREFIX_COMPLETION_STATUS + "50)";

    public static final String MESSAGE_DONE_TASK_SUCCESS_DECREASE = "Decreased Completion Percentage of Task: %1$s";
    public static final String MESSAGE_DONE_TASK_SUCCESS_INCREASE = "Increased Completion Percentage of Task: %1$s";
    public static final String MESSAGE_COMPLETION_SAME = "Percentage must be different from current value.";

    private final Index index;
    private final CompletionStatus completionStatus;

    /**
     * @param index of the task in the filtered task list to mark as done
     * @param completionStatus new completion status of the task
     */
    public DoneCommand(Index index, CompletionStatus completionStatus) {
        requireNonNull(index);
        requireNonNull(completionStatus);

        this.index = index;
        this.completionStatus = completionStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredPendingTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToComplete = lastShownList.get(index.getZeroBased());
        Todo todoToComplete = new Todo(taskToComplete.getName(), taskToComplete.getDeadline());
        Task completedTask = createCompletedTask(taskToComplete, completionStatus);

        if (model.hasTodo(todoToComplete) && completedTask.isCompleted()) {
            model.deleteTodo(todoToComplete);
        }

        model.setTask(taskToComplete, completedTask);
        model.updateFilteredPendingTaskList(PREDICATE_SHOW_PENDING_TASKS);
        model.updateFilteredCompletedTaskList(PREDICATE_SHOW_COMPLETED_TASKS);
        model.updateFilteredTodoList(PREDICATE_SHOW_ALL_TODOS);


        if (completedTask.getCompletionStatus().compareTo(taskToComplete.getCompletionStatus()) > 0) {
            CommandResult commandResult =
                    new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS_INCREASE, completedTask));
            commandResult.setHomeTab();
            return commandResult;
        } else {
            CommandResult commandResult =
                    new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS_DECREASE, completedTask));
            commandResult.setHomeTab();
            return commandResult;
        }
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToComplete}
     * and new percentage with {@code completionStatus}.
     */
    private static Task createCompletedTask(Task taskToComplete, CompletionStatus completionStatus)
            throws CommandException {
        assert taskToComplete != null;

        Name initialName = taskToComplete.getName();
        Deadline initialDeadline = taskToComplete.getDeadline();
        CompletionStatus initialCompletionStatus = taskToComplete.getCompletionStatus();
        TaskDescription initialTaskDescription = taskToComplete.getTaskDescription();
        Set<ModuleCode> initialModuleCode = taskToComplete.getModuleCode();
        Set<Tag> initialTags = taskToComplete.getTags();
        if (completionStatus.equals(initialCompletionStatus)) {
            throw new CommandException(MESSAGE_COMPLETION_SAME);
        }
        return new Task(initialName, initialDeadline, completionStatus,
                initialTaskDescription, initialModuleCode, initialTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DoneCommand)) {
            return false;
        }

        // state check
        DoneCommand e = (DoneCommand) other;
        return index.equals(e.index)
                && completionStatus.equals(e.completionStatus);
    }
}
