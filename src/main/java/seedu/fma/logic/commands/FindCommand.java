package seedu.fma.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.fma.commons.core.Messages;
import seedu.fma.model.Model;
import seedu.fma.model.util.NameContainsKeywordsPredicate;

/**
 * Finds and lists all logs in log book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String AC_SUGGESTION = COMMAND_WORD + " <keywords in logs>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all logs that contain all of "
            + "the specified keywords (case-insensitive) anywhere in the log and displays them.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " abs";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredLogList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_LOGS_LISTED_OVERVIEW, model.getFilteredLogList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }

}
