package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;

/**
 * Allows users to add a todo task in Daily todo list
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = "Looks like you're trying to use the " + COMMAND_WORD + " command: "
            + "Adds the task identified by the index number used in the specified task list into Daily todo list\n"
            + "Compulsory Parameter: INDEX (must be a positive integer and valid index number)\n"
            + "To add task from Pending Tasks: (E.g. " + COMMAND_WORD + " 1)\n";

    public static final String MESSAGE_ADD_TODO_TASK_SUCCESS = "Added Daily Todo Task: %1$s";
    public static final String MESSAGE_DUPLICATE_TODO_TASK = "This todo task already exists in Daily Todo List.";

    protected final Index targetIndex;

    public TodoCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredPendingTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
        }

        Task taskToAdd = lastShownList.get(targetIndex.getZeroBased());
        Name name = taskToAdd.getName();
        Deadline deadline = taskToAdd.getDeadline();
        Todo todoToAdd = new Todo(name, deadline);
        if (model.hasTodo(todoToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO_TASK);
        }

        model.addTodo(todoToAdd);
        return new CommandResult(String.format(MESSAGE_ADD_TODO_TASK_SUCCESS, todoToAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TodoCommand)) { // instanceof handles nulls
            return false;
        }
        return other == this // short circuit if same object
                || (other instanceof TodoCommand
                && targetIndex.equals(((TodoCommand) other).targetIndex));
    }
}
