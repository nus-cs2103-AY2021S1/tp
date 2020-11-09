package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.Model;
import seedu.address.model.task.TaskMatchesFindKeywordPredicate;

/**
 * Finds and lists all events in lifebook whose description contains any of the desc keywords.
 * and the tag matches the given tag keyword.
 * Keyword matching is case insensitive.
 */
public class FindEventCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " event"
        + ": Finds all events whose descriptions contain any of "
        + "the specified desc keywords and the tag matches the given tag keyword (case-insensitive) "
        + "and displays them as a list with index numbers.\n"
        + "Parameters: [" + PREFIX_DESCRIPTION + "DESC_KEYWORD [MORE_DESC_KEYWORDS]...] "
        + "[" + PREFIX_TAG + "TAG_KEYWORD]\n"
        + "Remarks: at least one of " + PREFIX_DESCRIPTION + " or " + PREFIX_TAG + " must be included in the command. "
        + "Keyword cannot be empty\n"
        + "Example: " + COMMAND_WORD + " event " + PREFIX_DESCRIPTION + "attend meeting " + PREFIX_TAG + "urgent";

    private final TaskMatchesFindKeywordPredicate predicate;

    public FindEventCommand(TaskMatchesFindKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()), "TASK");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCommand // instanceof handles nulls
                && predicate.equals(((FindEventCommand) other).predicate)); // state check
    }
}
