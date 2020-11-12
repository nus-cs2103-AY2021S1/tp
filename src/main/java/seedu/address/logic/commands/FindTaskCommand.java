package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in PlaNus whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "find-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks with the specified attribute"
            + "containing the specified valid search phrase (case-insensitive)"
            + "and displays them as a list with index numbers.\n"
            + "Parameters: ATTRIBUTE_1:SEARCH_PHRASE ATTRIBUTE_2:SEARCH_PHRASE ...\n"
            + "For list of all available attributes, please refer to the user guide by typing 'help' command\n"
            + "Example: " + COMMAND_WORD + " title:meet zijian desc:play games";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    private final TaskContainsKeywordsPredicate predicate;

    public FindTaskCommand(TaskContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        model.updateFilteredCalendar(predicate);
        return new CommandResult(
                String.format(MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && predicate.equals(((FindTaskCommand) other).predicate)); // state check
    }
}
