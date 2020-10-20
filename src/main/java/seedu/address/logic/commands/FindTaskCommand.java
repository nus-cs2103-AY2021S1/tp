package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.FindTaskCriteria;
import seedu.address.model.task.Task;

import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * Finds and lists all tasks in the TodoList whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "findtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks which match all"
            + "the specified search parameters provided (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "At least one of the following search parameters must be provided: \n"
            + "Parameters: [n/TASKNAME] [d/TASK_DATE] [p/TASK_PRIORITY]\n"
            + "Example: " + COMMAND_WORD + " n/lab p/Highest";

    private final Logger logger = LogsCenter.getLogger(FindTaskCommand.class);

    /**
     *
     */
    private final Predicate<Task> predicate;

    public FindTaskCommand(FindTaskCriteria findTaskCriteria) {
        this.predicate = findTaskCriteria.getFindTaskPredicate();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTodoList(predicate);
        logger.info("The following tasks matching all search criteria by the user have been found");
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTodoList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && predicate.equals(
                        ((FindTaskCommand) other).predicate)); // state check
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
