package seedu.address.logic.commands.sort;

import java.util.Comparator;
import java.util.function.Predicate;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDateComparator;
/**
 * Sorts task list according to date and time in ascending order.
 */
public class SortTaskCommand extends SortCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts tasks by date ";
    public static final String MESSAGE_SUCCESS = "Sorted tasks by date";
    public static final String MESSAGE_EMPTY_FILTERED_TASK_LIST = "The list is empty. Displaying an unfiltered"
            + " sorted task"
            + " list instead.";
    public static final String MESSAGE_EMPTY_TASK_LIST = "The task list is empty. Please add tasks to sort them.";
    public static final Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Comparator<Task> comparator = new TaskDateComparator();
        model.updateSortedTaskList(comparator);
        if (model.filteredTaskListIsEmpty()) {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            model.updateSortedTaskList(comparator);
            if (model.filteredTaskListIsEmpty()) {
                return new CommandResult(MESSAGE_EMPTY_TASK_LIST, "TASK");
            } else {
                return new CommandResult(MESSAGE_EMPTY_FILTERED_TASK_LIST, "TASK");
            }
        } else {
            return new CommandResult(MESSAGE_SUCCESS, "TASK");
        }
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortTaskCommand);
    }
}
