package seedu.address.logic.commands.sort;

import java.util.function.Predicate;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Clears all sorting from both displayed lists.
 */
public class SortClearCommand extends SortCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all sorting ";
    public static final String MESSAGE_EMPTY_LISTS = "Both lists are currently empty. Please add items to adjust "
            + "sorting.";
    public static final String MESSAGE_EMPTY_TASKLIST = "The task list is currently empty. Please add tasks to adjust "
            + "its sorting. Person list sorting cleared.";
    public static final String MESSAGE_EMPTY_ADDRESSBOOK = "The person list is currently empty. Please add contacts "
            + "to adjust its sorting. Task list sorting cleared.";
    public static final String MESSAGE_SUCCESS = "All sorting cleared";
    public static final Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    public static final Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateSortedPersonList(null);
        model.updateSortedTaskList(null);
        if (model.filteredTaskListIsEmpty()) {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        }
        if (model.filteredAddressBookIsEmpty()) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }
        if (model.taskListIsEmpty() && model.addressBookIsEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LISTS, "CONTACT");
        } else if (model.taskListIsEmpty() && !model.addressBookIsEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_TASKLIST, "CONTACT");
        } else if (!model.taskListIsEmpty() && model.addressBookIsEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_ADDRESSBOOK, "CONTACT");
        } else {
            return new CommandResult(MESSAGE_SUCCESS, "CONTACT");
        }
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortClearCommand);
    }
}
