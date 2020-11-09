package seedu.address.logic.commands.todolistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.FindTaskCriteria;

/**
 * Encapsulates methods and information to find and list all tasks in the todolist
 * which match the given predicate.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "findtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks which match all "
            + "the specified search parameters (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "At least one of the following search parameters must be provided: \n"
            + "Parameters: [n/NAME_KEYWORDS] [d/TASK_DATE] [p/TASK_PRIORITY] [s/TASK_STATUS] [t/TAG_KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "lab quiz "
            + PREFIX_PRIORITY + "high "
            + PREFIX_TAG + "easy cs2103T";

    private final Logger logger = LogsCenter.getLogger(FindTaskCommand.class);

    /**
     * Predicate to test if a task matches all the search criteria.
     */
    private final FindTaskCriteria findTaskCriteria;

    /**
     * Creates and initialises a new FindTaskCommand object to find tasks that match all the
     * specified search parameters.
     *
     * @param findTaskCriteria FindTaskCriteria object that encapsulates a list of predicates to test a task with.
     */
    public FindTaskCommand(FindTaskCriteria findTaskCriteria) {
        requireNonNull(findTaskCriteria);
        requireNonNull(findTaskCriteria.getFindTaskPredicate());
        logger.info("Executing FindTaskCommand");
        this.findTaskCriteria = findTaskCriteria;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTodoList(findTaskCriteria.getFindTaskPredicate());
        logger.info("The result of executing the find task command has been displayed");
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTodoList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && findTaskCriteria.equals(((FindTaskCommand) other).findTaskCriteria)); // state check
    }

}
