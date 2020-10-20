package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.FindTaskCriteria;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskSearchCriteriaPredicate;

import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * Finds and lists all tasks in the TodoList whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "findtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " lab06 midterm quiz";

    private final Logger logger = LogsCenter.getLogger(FindTaskCommand.class);

    private final Predicate<Task> taskMatchesCriteriaPredicate;

    public FindTaskCommand(FindTaskCriteria findTaskCriteria) {
        this.taskMatchesCriteriaPredicate = findTaskCriteria.getFindTaskPredicate();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTodoList(taskMatchesCriteriaPredicate);
        logger.info("The following tasks matching all search criteria by the user have been found");
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTodoList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && taskMatchesCriteriaPredicate.equals(
                        ((FindTaskCommand) other).taskMatchesCriteriaPredicate)); // state check
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
